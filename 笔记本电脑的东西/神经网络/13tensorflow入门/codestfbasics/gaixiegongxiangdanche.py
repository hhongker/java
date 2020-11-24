# -*- coding: utf-8 -*-

# -*- coding: utf-8 -*-
"""
Created on Sun Jun 23 11:04:35 2019

@author: tang

数据集介绍 自行车共享租赁过程与环境和季节性环境高度相关。 
例如，天气状况， 降水，星期几，是否工作日或周末，季节，
一天中的小时等都会影响出租行为。 数据集是为期两年的历史数据：2011年--2012年
一、数据导入
"""
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
data_path = r'hour.csv'

rides = pd.read_csv(data_path)

#a=pd.DataFrame({'a':[1,2,3],'b':[4,5,6],'c':[7,8,9]})
#rides.head(10)
#rides.shape

#rides.columns
#可视化方法
#rides[:24*10].plot(x='instant', y='cnt')

'''
二、数据预处理

1、离散变量：生成哑变量（或成为one-hot变换）量纲问题

2、归一化转换，消除量纲。（初始值较大的，会严重影响模型判定权重）
'''

dummy_fields = ['season', 'weathersit', 'mnth', 'hr', 'weekday']
for each in dummy_fields:
    dummies = pd.get_dummies(rides[each], prefix=each)
    rides = pd.concat([rides, dummies], axis=1)
    print(rides.shape)
    
    
fields_to_drop = ['instant', 'dteday', 'season', 'weathersit', 
                  'weekday', 'atemp', 'mnth', 'workingday', 'hr']
data = rides.drop(fields_to_drop, axis=1)


quant_features = ['casual', 'registered', 'cnt', 'temp', 'hum', 'windspeed']

#保存换算的均值和标准差，以便后续使用。
#这个过程也被称为归一化，这里归一化成一个正太分布
scaled_features = {}
for each in quant_features:
    mean, std = data[each].mean(), data[each].std()
    scaled_features[each] = [mean, std]
    data.loc[:, each] = (data[each] - mean)/std



'''
三、训练数据集（50%-80%）、验证数据集(训练中）（20%-5%）、测试数据集（训练完）（20%-10%）

验证集：验证训练出来的模型效果,以便对模型进行调优；（参与建模的）

测试集：最终评判模型效果。（不参与建模）本项目中：拿出了最后21天的数据作为测试数据集。
'''
test_data = data[-21*24:]


#训练数据集中需要移除掉 测试数据集数据
data = data[:-21*24]

#将训练数据集数据拆分为 特征值 和目标值
target_fields = ['cnt', 'casual', 'registered']
features, targets = data.drop(target_fields, axis=1), data[target_fields]
test_features, test_targets = test_data.drop(target_fields, axis=1), test_data[target_fields]
#验证英文 validation
train_features, train_targets = features[:-60*24], targets[:-60*24]
val_features, val_targets = features[-60*24:], targets[-60*24:]

#超参数的设置
iterations = 6000
learning_rate = 0.5
hidden_nodes = 8
output_nodes = 1
input_nodes = train_features.shape[1]

import tensorflow as tf

train_x = tf.plceholder(dtype=tf.floa32,shape=(None,input_nodes))







import sys

### 设置超参数 ###


#N_i = train_features.shape[1]
#network = NeuralNetwork(N_i, hidden_nodes, output_nodes, learning_rate)
losses = {'train':[], 'validation':[]}
for ii in range(6000):
    
    #每次随机从训练数据集中抽取128条记录作为训练
    batch = np.random.choice(train_features.index, size=128)
    X, y = train_features.iloc[batch].values, train_targets.iloc[batch]['cnt'].values
    train(X, y,weights_input_to_hidden,weights_hidden_to_output)
    
    #打印出训练过程
    train_loss = MSE(run(train_features).T, train_targets['cnt'].values)
    
    val_loss = MSE(run(val_features).T, val_targets['cnt'].values)
    sys.stdout.write("\rProgress: {:2.1f}".format(100 * ii/float(iterations)) \
                     + "% ... Training loss: " + str(train_loss)[:5] \
                     + " ... Validation loss: " + str(val_loss)[:5])
    sys.stdout.flush()
    
    losses['train'].append(train_loss)
    losses['validation'].append(val_loss)# -*- coding: utf-8 -*-





plt.figure()
plt.plot(losses['train'], label='Training loss')
plt.plot(losses['validation'], label='Validation loss')
plt.legend()



_, ax = plt.subplots(figsize=(8,4))
#这里很重要
mean, std = scaled_features['cnt']
#原来归一化的时候是减去均值再除以方差，现在就要乘以方差再加上均值
predictions = run(test_features).T*std + mean

#plt.rcParams['font.sans-serif'] = ['KaiTi'] # 指定默认字体
#plt.rcParams['axes.unicode_minus'] = False # 解决保存图像是负号'-'显示为方块的问题

ax.plot(predictions.T, label='Predict')
ax.plot((test_targets['cnt']*std + mean).values, label='Actual')
ax.legend()

dates = pd.to_datetime(rides.iloc[test_data.index]['dteday'])
dates = dates.apply(lambda d: d.strftime('%b %d'))
ax.set_xticks(np.arange(len(dates))[12::24])
_ = ax.set_xticklabels(dates[12::24], rotation=45)