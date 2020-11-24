# -*- coding: utf-8 -*-
"""
Created on Wed Jul 15 19:24:34 2020

@author: hhr
"""
# In[探索 Iris 鸢尾花数据]
#1. 读取数据，将数据存成变量iris
import pandas as pd
iris = pd.read_csv(r'D:\root\Desktop\暑期线上培训\作业\2\Python数据分析实训\iris.csv',header=None)
#2. 创建数据框的列名称['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'class']
iris.columns = ['sepal_length','sepal_width','petal_length','petal_width','class']
#3. 数据框中有缺失值吗？
iris.isna().sum().sum()
#4. 将列petal_length的第十到十九行设置为缺失值。
iris['petal_length'][9:19] = None
#5. 将petal_lengt缺失值全部替换为1.0。
iris = iris.fillna(1)
#6. 删除列class。
iris = iris.drop('class',axis=1)
#7. 将数据框前三行设置为缺失值。
iris.iloc[0:3,:] = None
#8. 删除有缺失值的行。
iris = iris.dropna()
#9. 重新设置索引。
iris = iris.reset_index(drop=True)



# In[探索Chipotle快餐数据]
import pandas as pd
#1. 将数据集存入一个名为chipo的数据框内
chipo = pd.read_csv(r'D:\root\Desktop\暑期线上培训\作业\2\Python数据分析实训\chipotle.tsv',sep = '\t')

#2. 查看前10行内容
chipo.head(10)
#3. 数据集中有多少个列(columns)？
len(chipo.columns)
#4. 打印出全部的列名称
chipo.columns
#5. 数据集的索引是怎样的？
chipo.index
#6. 被下单数最多商品(item)是什么?
chipo.groupby(by='item_name').agg({'quantity':'sum'}).idxmax()
#7. 在item_name这一列中，一共有多少种商品被下单？
chipo['item_name'].nunique()
#8. 一共有多少个商品被下单？
chipo['quantity'].sum()
#9. 将item_price转换为浮点数
chipo['item_price'] = chipo['item_price'].str[1:].astype(float)
#10. 在该数据集对应的时期内，收入(revenue)是多少？
chipo['revenue'] = chipo['item_price']*chipo['quantity']
chipo['revenue'].sum()
#11. 在该数据集对应的时期内，一共有多少订单？
chipo['order_id'].nunique()
#12. 平均每一单(order)对应的总价是多少？
#法一
chipo['revenue'].sum() / chipo['order_id'].nunique()
#法二
chipo.groupby('order_id').agg({'revenue':'sum'}).mean()
# In[探索 Apple 公司股价数据]

#1. 读取“appl_1980_2014.csv”数据并存为一个名叫apple的数据框。
import pandas as pd
apple = pd.read_csv(r'D:\root\Desktop\暑期线上培训\作业\2\Python数据分析实训\appl_1980_2014.csv')

#2. 查看每一列的数据类型。
apple.dtypes
#3. 将Date这个列转换为datetime类型。
apple['Date'] = pd.to_datetime(apple['Date'])
#4. 将Date设置为索引。
apple.set_index('Date',inplace = True)
#5. 有重复的日期吗？
apple.shape[0] == apple.index.nunique()
#6. 将index设置为升序。
apple = apple.sort_index(ascending=True)
#7. 找到每个月的最后一个交易日(businessday)。
apple.groupby([apple.index.year,apple.index.month]).agg({'Open':lambda x: x.index.day.max()})
#8. 数据集中最早的日期和最晚的日期相差多少天？
(apple.index.max() - apple.index.min()).days
#9. 在数据中一共有多少个月？
apple.groupby([apple.index.year,apple.index.month]).agg({'Open':lambda x: x.index.day.max()}).shape[0]
#10. 按照时间顺序可视化Adj Close值。

import matplotlib.pyplot as plt
plt.plot(apple.index,apple['Adj Close'])
plt.show()






















