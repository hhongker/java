# -*- coding: utf-8 -*-
"""
Created on Fri Jun 26 10:03:43 2020

@author: hhr
"""
# In[数据准备]
import pandas as pd
data = pd.read_excel('../01.中间数据/供应链商品销售数据.xlsx',header=None)
data = data.iloc[1:,1:] #将读取多余的第一行与第一列干掉
data.columns = data.loc[1] #将列设置为第1行的值
data = data.drop([1]) #将多余的第一行干掉
data.reset_index(drop=True, inplace=True) #重排索引
data = data.fillna(0) #将非数替换成0
# In[任务3 分析新开拓销售点的选址] 我认为是谁销售额平均值更大
scv = data[['销售点类型','销售额（万元）']].groupby('销售点类型').agg({ 
    '销售额（万元）':['sum','count','var','mean'] #算出总共的值和数量和方差
})
#m = scv['销售额（万元）']['sum'] / scv['销售额（万元）']['count']#两个店的平均销售额为


#画图
import matplotlib.pyplot as plt
plt.style.use('ggplot') #设置风格
fig = plt.figure(figsize=(12,6))


ax1 = fig.add_subplot(1,2,1) #子图一
plt.title('销售额平均值')
m = scv['销售额（万元）']['mean']
ax1.pie(m,autopct='%.2f %%',radius=1,explode=[0.1]+[0]*(len(m)-1),labels=['CBD店','社区店'],labeldistance=1,wedgeprops={
    'width':0.7
})


ax2 = fig.add_subplot(1,2,2) #子图
plt.title('销售额方差')
v = scv['销售额（万元）']['var']
ax2.pie(v,autopct='%.2f %%',radius=1,explode=[0.1]+[0]*(len(v)-1),labels=['CBD店','社区店'],labeldistance=1,wedgeprops={
    'width':0.7
})
plt.show()
#显然cbd店更用利

# In[任务4 分析销售季节性特点] 
mon = data.groupby('销售月份').agg({ #分组计算值
    '销售额（万元）':['sum','count','mean']
})
#分完组后按照月份排序
monMortList = ['January','February','March','April','May','June','July','August','September','October','November','December']
mon = mon.reindex(monMortList)


#绘图
import matplotlib.pyplot as plt
plt.style.use('ggplot') #设置风格
fig = plt.figure(figsize=(14,16))

ax1 = fig.add_subplot(2,1,1) #子图一
plt.title('销售总金额，销售个数，销售平均值折线图')
mdf = mon['销售额（万元）']
for i in mdf:
    ax1.plot(mdf.index,mdf[i])
    for i, j in zip(mdf.index,mdf[i]): #设置文本的位置
        plt.text(i,j,'%.2f'%j,ha='center',va='top')
plt.legend(mdf.columns)


ax2 = fig.add_subplot(2,1,2) #子图二
plt.title('销售总金额，销售个数，销售平均值条形图')
mdf = mon['销售额（万元）']
import numpy as np
x = np.arange(12)
ax2.bar(x-0.3,mdf['sum'],width=0.3)
for i, j in zip(x-0.3,mdf['sum']): #设置文本的位置
    plt.text(i,j,'%.2f'%j,ha='center',va='bottom')
        
ax2.bar(x,mdf['count'],width=0.3)
for i, j in zip(x,mdf['count']): #设置文本的位置
        plt.text(i,j,'%.2f'%j,ha='center',va='bottom')
        
ax2.bar(x+0.3,mdf['mean'],width=0.3,color='black')
for i, j in zip(x+0.3,mdf['mean']): #设置文本的位置
        plt.text(i,j,'%.2f'%j,ha='center',va='bottom')

plt.xticks(x,monMortList,fontproperties = 'Times New Roman', size = 10)
plt.legend(mdf.columns)
plt.show()

# In[任务5 分析社区店有哪些季节性很强的产品]
jijieshop = pd.pivot_table(data[['商品代号','销售月份','销售额（万元）']],
               index='销售月份',
               columns='商品代号',
               fill_value=0,
               values='销售额（万元）',
               aggfunc=['sum','count'])
monMortList = ['January','February','March','April','May','June','July','August','September','October','November','December']
jijieshop = jijieshop.reindex(monMortList)


j1 = jijieshop.apply(lambda x: x[0:3].sum(), axis=0)
j2 = jijieshop.apply(lambda x: x[3:6].sum(), axis=0)
j3 = jijieshop.apply(lambda x: x[6:9].sum(), axis=0)
j4 = jijieshop.apply(lambda x: x[9:12].sum(), axis=0)

j = pd.DataFrame(list(zip(j1,j2,j3,j4)))
j.index = j1.index
j.columns = ['春','夏','秋','冬']

jijiesum = j.loc[map(lambda x :'sum' in x,j.index),:] #每个商品对应每个季节销售额总和
jijiecount = j.loc[map(lambda x :'count' in x,j.index),:] #每个商品对应每个季节销售个数


#查看每个季节对应季节性销售额比较大的几个商品
print('春季销售总额比较大的几样商品',
jijiesum['春'].sort_values(ascending=False).head()
)
print('夏季销售总额比较大的几样商品',
jijiesum['夏'].sort_values(ascending=False).head()
)
print('秋季销售总额比较大的几样商品',
jijiesum['秋'].sort_values(ascending=False).head()
)
print('冬季销售总额比较大的几样商品',
jijiesum['冬'].sort_values(ascending=False).head()
)

#查看每个季节对应季节性销售数量比较大的几个商品
print('春季销售数量比较大的几样商品',
jijiecount['春'].sort_values(ascending=False).head()
)
print('夏季销售数量比较大的几样商品',
jijiecount['夏'].sort_values(ascending=False).head()
)
print('秋季销售数量比较大的几样商品',
jijiecount['秋'].sort_values(ascending=False).head()
)
print('冬季销售数量比较大的几样商品',
jijiecount['冬'].sort_values(ascending=False).head()
)

#画图
#1,画出销售总额的图
import matplotlib.pyplot as plt
plt.style.use('ggplot') #设置风格
plt.figure(figsize=(16,10))

plt.title('每个商品在对应的季节里的销售总量变化')
for i in jijiesum.columns:
    plt.plot([i[1] for i in jijiesum.index],jijiesum[i])
fig = plt.legend(jijiesum.columns)
xtisum = plt.xticks(rotation=45)
plt.show()

#2,画出销售数量的图
import matplotlib.pyplot as plt
plt.style.use('ggplot') #设置风格
plt.figure(figsize=(16,10))

plt.title('每个商品在对应的季节里的销售总量变化')
for i in jijiecount.columns:
    plt.plot([i[1] for i in jijiecount.index],jijiecount[i])
fig = plt.legend(jijiecount.columns)
xtisum = plt.xticks(rotation=45)
plt.show()


# In[文档以下]
# In[任务4 可挑选哪些产品做为新销售店的主要铺货产品，以帮助新店顺利经营。]
shop = data[['商品代号','销售额（万元）']].groupby('商品代号').agg({ 
    '销售额（万元）':['sum','count'] #算出总共的值和数量和方差
})

print('商品销售额较多的',
shop['销售额（万元）']['sum'].sort_values(ascending=False).head()
)

print('商品销售数量较多的',
shop['销售额（万元）']['count'].sort_values(ascending=False).head()
)

#画图
import matplotlib.pyplot as plt
plt.style.use('ggplot') #设置风格
plt.figure(figsize=(16,10))

plt.title('各商品销售量情况')
for i in shop['销售额（万元）'].columns:
    plt.plot(shop.index,shop['销售额（万元）'][i])
plt.legend(shop['销售额（万元）'].columns)
xtisum = plt.xticks(rotation=45)
plt.show()

#显然safety 8产品无论销量还是销售额都very good

# In[任务5 分析销售季节性特点，据以安排店员年休假或培训时间，以及订货量控制。]同上任务4
#可以看出March ,August, October，April销售量不高,其余都挺高

# In[任务6 假设进货商品均余半年保质期，分析社区店有哪些季节性很强的产品，必须在哪个时点前促销出清，否则会滞销过期。]
jijieshop = pd.pivot_table(data[['商品代号','销售月份','销售额（万元）']],
               index='销售月份',
               columns='商品代号',
               fill_value=0,
               values='销售额（万元）',
               aggfunc=['count'])
monMortList = ['January','February','March','April','May','June','July','August','September','October','November','December']
jijieshop = jijieshop.reindex(monMortList)


#画图
import matplotlib.pyplot as plt
#plt.style.use('ggplot') #设置风格
plt.figure(figsize=(18,20))


plt.title('各商品在每个月份中的数量')
for i in jijieshop['count'].columns:
    plt.plot(jijieshop.index,jijieshop['count'][i])
plt.legend(jijieshop['count'].columns)

xtisum = plt.xticks(rotation=45)
plt.show()

#可以看到某些商品在随着季节变化减少的特别快，说明该商品季节性强

# In[任务7 完成项目报告，根据项目背景和数据分析结果，给客户提供店铺运营的个性化定制方案（作业）]
# 综上所述，靠各位多出力了





