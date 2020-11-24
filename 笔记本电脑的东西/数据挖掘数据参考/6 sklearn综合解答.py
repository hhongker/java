# 1.将数据进行转置，转置后行为用户编号、列为日期、值为用户每日用电量。

import numpy as np
import pandas as pd
import os

#将数据进行转置，转置后行为用户编号、列为日期、值为用户每日用电量。

data=pd.read_csv('6_data.csv',parse_dates=['DATA_DATE'],encoding='gbk')

data.head()

#透视表

data_new=pd.pivot_table(data=data,values='KWH',index='CONS_NO',columns='DATA_DATE')

# 2.对数据中的异常数据进行识别并处理。

def clear_(x=None):

    QL=x.quantile(0.25)#下四分位数

    QU=x.quantile(0.75)#上四分位数

    IQR=QU-QL

    x[((x>QU+1.5*IQR)|(x<QL-1.5*IQR))]=None

    return  x

data_new.apply(clear_,axis=0)#对每一行操作

 

# 3.统计每个用户用电数据的基本统计量，包括：最大值、最小值、均值、中位数、和、方差、偏度、峰度。

feature1=data_new.T.agg(['max','min','mean','median','sum','var','skew','kurt'],axis=0).T

 

# 4.每个用户用电数据按日差分，并求取差分结果的基本统计量，统计量同3。

feature2=data_new.T.diff(axis=1).agg(['max','min','mean','median','sum','var','skew','kurt'],axis=0).T

 

#  5.求取每个用户的5%分位数。

feature3=data_new.quantile(0.05,axis=1)

 

# 6.每个用户按周求和并差分（一周7天，年度分开），并求取差分结果的基本统计量，统计量同3。

data_new.columns.week

feature4=(data_new.T.resample('W',how='sum')).T.diff(axis=1).T.agg(['max','min','mean','median','sum','var','skew','kurt'],axis=0).T

 

# 7.统计每个用户的日用电量在其最大值0.9倍以上的次数。

feature5=data_new.apply(lambda x:sum(x>x.max()*0.9),axis=1)

 

# 8.求取每个用户日为最大值/最小值的索引月份，若最大值/最小值存在于多个月份中，则输出含有最大值/最小值最多的那个月份。如1号用户的最小值为0，12个月每个月都有0，则看哪个月的0最多。

feature6=data_new.apply(lambda x: x==x.min(),axis=1).groupby(by=data_new.columns.month,axis=1).sum().idxmax(axis=1) #最小值

feature7=data_new.apply(lambda x: x==x.max(),axis=1).groupby(by=data_new.columns.month,axis=1).sum().idxmax(axis=1) #最大值

# 10.合并上述特征。

pd.concat([feature1,feature2,feature3,feature4,feature5,feature6,feature7],axis=0)