# In[数据预处理]
import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）//附件.csv',encoding='GB2312')

#去掉一些重复作用的列
data = data.drop(['大类名称','中类名称','小类名称','单位','销售月份'],axis=1)

#格式化日期
def formatTime(x):
    x=str(x)
    return str(x[:4])+'-'+str(x[4:6])+'-'+str(x[6:])
data['销售日期']=data['销售日期'].apply(formatTime)

#获取有异常的日期
excriqi = set()
for i in data['销售日期']:
    try:
        pd.to_datetime(i)
        
    except:
        print(i)
        excriqi.add(i)
        
#去掉有异常的日期
for i in excriqi:
    data.drop(data[data['销售日期']==i].index,inplace=True)

#将日期的格式转换好
data['销售日期']= pd.to_datetime(data['销售日期'])



'''钟如国的2_4
'''

data=pd.read_csv('../01.中间数据（如有）//附件.csv',encoding='GBK')

error_index=list(data[data['销售日期']==20150229].index)
data.drop(error_index,inplace=True) #去除异常数字
data['销售日期'] = pd.to_datetime(data['销售日期'], format='%Y%m%d').dt.date
data['销售月份'] = pd.to_datetime(data['销售月份'], format='%Y%m').dt.month
data['销售日期'] = data['销售日期'].astype('datetime64')

data['周数']=data['销售日期'].dt.weekofyear
week_gb=data[['商品类型','周数','销售金额']].groupby(by=['商品类型','周数']).sum().reset_index()
data2=week_gb[week_gb['商品类型'].isin(['生鲜','一般商品'])]


'''钟如国的2_4
'''







#将数据保存起来
data.to_csv('../01.中间数据（如有）/task1_1.csv',index=None)



# In[任务2.2 统计每个大类商品的销售金额，将结果保存为“task1_2.csv]


import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_1.csv')

data['大类编码销售金额']=data.groupby('大类编码').agg({'销售金额':'sum'})

data['大类编码销售金额'].sum()
#将数据保存起来
data.to_csv('../01.中间数据（如有）/task1_2.csv',index=None)


# In[任务2.3 统计每个中类商品的促销销售金额和非促销销售金额，将结果保存为“task1_3.csv”]

import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

d = data.groupby(['是否促销','中类编码']).agg({'销售金额':'sum'})
data.drop(data[data['是否促销']=='9.9'].index,inplace=True)
d.to_csv('../01.中间数据（如有）/task1_3.csv')

# In[任务2.4 统计生鲜类产品和一般产品的每周销售金额，将结果保存为“task1_4.csv”]

import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

data['第几周']= pd.DatetimeIndex(data['销售日期']).weekofyear

d = data.groupby(['商品类型','第几周']).agg({'销售金额':'sum'})

d.to_csv('../01.中间数据（如有）/task1_4.csv')


# In[任务2.5 统计每位顾客每月的消费额及消费天数，将结果保存为“task1_5.csv”，并在报告中列出用户编号为 0-10 的结果]


import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')
data['月分']= pd.DatetimeIndex(data['销售日期']).month
d = data.groupby(['顾客编号','月分']).agg({'销售金额':'sum','销售日期':'count'})
d.to_csv('../01.中间数据（如有）/task1_5.csv')

# In[任务3.1 绘制生鲜类商品和一般商品每天销售金额的折线图，并分析比较两类产品的销售状况]

data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

d = data.groupby(['商品类型','销售日期'],as_index=False).agg({'销售金额':'sum'})

ax1 = d.loc[d['商品类型']=='一般商品',:]
ax2 = d.loc[d['商品类型']=='生鲜',:]

import matplotlib.pyplot as plt

# 一般商品每天销售金额的折线图
plt.figure(figsize=(26,10))
plt.title(list(ax1['商品类型'])[0])
plt.plot(ax1['销售日期'],ax1['销售金额'])
plt.xticks(rotation=90)
plt.legend(list(ax1['商品类型'])[0])

# 生鲜类商品每天销售金额的折线图
plt.figure(figsize=(26,10))
plt.title(list(ax2['商品类型'])[0])
plt.plot(ax2['销售日期'],ax2['销售金额'])
plt.xticks(rotation=90)
plt.legend(list(ax2['商品类型'])[0])


# In[任务3.2 按月绘制各大类商品销售金额的占比饼图，并分析其销售状况]
import matplotlib.pyplot as plt
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

data['monthMany'] = pd.DatetimeIndex(data['销售日期']).month

d = data.groupby(['monthMany','大类编码'],as_index=False).agg({'销售金额':'sum'})

fig = plt.figure(figsize=(16,16)) #创建画布

ax1 = fig.add_subplot(2,2,1)
plt.title('第一季度')
ax1.pie(d.loc[d['monthMany']==1,'销售金额'],autopct='%.2f %%',labels=d.loc[d['monthMany']==1,'大类编码'])

ax2 = fig.add_subplot(2,2,2)
plt.title('第二季度')
ax2.pie(d.loc[d['monthMany']==2,'销售金额'],autopct='%.2f %%',labels=d.loc[d['monthMany']==2,'大类编码'])

ax3 = fig.add_subplot(2,2,3)
plt.title('第三季度')
ax3.pie(d.loc[d['monthMany']==3,'销售金额'],autopct='%.2f %%',labels=d.loc[d['monthMany']==3,'大类编码'])

ax4 = fig.add_subplot(2,2,4)
plt.title('第四季度')
ax4.pie(d.loc[d['monthMany']==4,'销售金额'],autopct='%.2f %%',labels=d.loc[d['monthMany']==4,'大类编码'])

# In[任务3.3 绘制促销商品和非促销商品销售金额的周环比增长率柱状图]
import matplotlib.pyplot as plt
import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

data['第几周']= pd.DatetimeIndex(data['销售日期']).weekofyear

d = data.groupby(['是否促销','第几周'],as_index=False).agg({'销售金额':'sum'})

#周环比增长率=（本周数-上周数）/上周数×100%
#由于第一周的数据没有，因此不需要算第一周

def zhouhuanbi(x):
    a1 = x['销售金额'][1:].reset_index().drop('index',axis=1)
    a2 = x['销售金额'][:-1].reset_index().drop('index',axis=1)
    o = (a1 - a2) / a2
#    print(o['销售金额'])
    o = list(o['销售金额'])
    o.insert(0,None)
    return o

yes = d.loc[d['是否促销']=='是',:]
no = d.loc[d['是否促销']=='否',:]

y = zhouhuanbi(yes)
n = zhouhuanbi(no)
d['周环比'] = [None,None] + y + n

dd = d[['是否促销','第几周','周环比']].dropna()


#绘图
fig = plt.figure(figsize=(10,8)) #创建画布
plt.bar(list(dd.loc[dd['是否促销']=='否','第几周']-0.2),list(dd.loc[dd['是否促销']=='否','周环比']),alpha=0.6,width=0.4)
plt.bar(list(dd.loc[dd['是否促销']=='是','第几周']+0.2),dd.loc[dd['是否促销']=='是','周环比'],width=0.4)
plt.xticks(list(dd.loc[dd['是否促销']=='是','第几周']))
plt.xlabel('周')
plt.ylabel('周环比')
plt.legend(['非促销','促销'])


# In[课时 11 : 任务4.1 根据消费情况，分别为累计消费前 10 的顾客画像]

import matplotlib.pyplot as plt
import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

#统计顾客消费金额
d = data.groupby(['顾客编号'],as_index=False).agg({'销售金额':'sum'})
#排序
d.sort_values('销售金额', inplace=True)
#找到前十的顾客
qianshi = d.iloc[-10:,:]

#画图象
fig = plt.figure(figsize=(10,8)) #创建画布
plt.plot(range(len(qianshi)),qianshi['销售金额'],marker='o')
plt.xticks(range(len(qianshi)),qianshi['顾客编号'])
plt.xlabel('顾客编号')
plt.ylabel('销售金额')

# In[课时 12 : 任务4.2 分析各大类商品的销售情况，总结其销售规律]
import matplotlib.pyplot as plt
import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

d = data.groupby(['大类编码'],as_index=False).agg({'销售金额':'sum','销售数量':'sum'})


#绘图
fig = plt.figure(figsize=(10,8)) #创建画布
plt.plot(range(len(d)),d['销售金额'],marker='o')

plt.xticks(range(len(d)),d['大类编码'])
plt.xlabel('大类编码')
plt.ylabel('销售金额')
plt.show()

fig = plt.figure(figsize=(10,8)) #创建画布
plt.plot(range(len(d)),d['销售数量'],marker='*')
plt.ylabel('销售数量')
plt.xlabel('大类编码')
plt.xticks(range(len(d)),d['大类编码'])
plt.show()

# 总结：有上面两幅图可以看出，12号，15号，20号，22号的大类别比较受欢迎，其余的类别均比较冷门，销售量和销售金额都比较小


# In[课时 13 : 任务4.3 分析促销对商品销售的影响，为超市制定销售策略提供建议]

import matplotlib.pyplot as plt
import pandas as pd

#读取数据
data = pd.read_csv('../01.中间数据（如有）/task1_2.csv')

d = data.groupby(['是否促销'],as_index=False).agg({'销售金额':'sum','销售数量':'sum'}).iloc[1:,:]

#绘图
fig = plt.figure(figsize=(10,8)) #创建画布

#是促销
plt.bar([0.5,1.5],list(d.iloc[0][1:]),width=0.3)
#不是促销
plt.bar([1,2],list(d.iloc[1][1:]),width=0.3)

plt.xticks([0.5,1,1.5,2],['销售金额','销售数量','销售金额','销售数量'])

plt.legend(['促销','非促销'])

#非促销的销售量和销售金额都比非销售的要高，因此建议超市不要高太多促销

