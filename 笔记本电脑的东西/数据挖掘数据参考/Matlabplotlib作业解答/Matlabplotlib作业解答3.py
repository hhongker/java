# -*- coding: utf-8 -*-

# In[3]:
import numpy as np
import matplotlib.pyplot as plt
plt.rcParams['font.sans-serif'] = 'SimHei'## 设置中文显示
plt.rcParams['axes.unicode_minus'] = False

datas=np.load('6_Matplotlib_populations.npz')
a=datas['data']
b=datas['feature_names']
p=plt.figure(figsize=(6,6))## 将画布设定为正方形，则绘制的饼图是正圆
j=1#位置
#用1996、2002、2008、2014年的数据绘制四个子图，每个子图为男女人口比例的饼图。
for i in range(1,20,6):    
    p.add_subplot(2,2,j)
    label= ['男','女']## 定义饼状图的标签，标签是列表
    explode = [0.01,0.01]## 设定各项离心n个半径
    plt.pie(a[i,2:4],explode=explode,labels=label,autopct='%1.1f%%')## 绘制饼图
    year=a[i,0]
    plt.title(str(year)+'男女人口比例的饼图')
    
    j+=1;
plt.savefig('1996、2002、2008、2014年男女人口比例的饼图')
plt.show()


# In[4]:
#import numpy as np
#import matplotlib.pyplot as plt
#plt.rcParams['font.sans-serif'] = 'SimHei'## 设置中文显示
#plt.rcParams['axes.unicode_minus'] = False

datas=np.load('6_Matplotlib_populations.npz')
a=datas['data']
b=datas['feature_names']
label= ['年末总人口','男性人口','女性人口','城镇人口','乡村人口']## 定义标签
gdp = (list(a[:-2,1]),list(a[:-2,2]),list(a[:-2,3]),list(a[:-2,4]),list(a[:-2,5]))
plt.figure(figsize=(6,4))
plt.boxplot(gdp,notch=True,labels = label, meanline=True)
plt.title('1996-2015年人口数据每个特征的箱线图')
plt.savefig('1996-2015年人口数据每个特征的箱线图.png')
plt.show()
