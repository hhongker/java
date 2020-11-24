import pandas as pd
import numpy as np
import tensorflow as tf
import sys

data = pd.read_csv(r'hour.csv')

#将以下的值去除量纲
dummy_fields = ['season', 'weathersit', 'mnth', 'hr', 'weekday']
for i in dummy_fields:
    dummies = pd.get_dummies(data[i],prefix = i)
    data = pd.concat([data,dummies],axis=1)
    print(data.shape)

#删除一些列    
fields_to_drop = ['instant', 'dteday', 'season', 'weathersit', 
                  'weekday', 'atemp', 'mnth', 'workingday', 'hr']
data = data.drop(fields_to_drop, axis=1)    



#对某些列保存换算的均值和标准差，以便后续使用。
#这个过程也被称为归一化，这里归一化成一个正太分布
quant_features = ['casual', 'registered', 'cnt', 'temp', 'hum', 'windspeed']
scaled_features = {}
for each in quant_features:
    mean, std = data[each].mean(), data[each].std()
    scaled_features[each] = [mean, std]
    data.loc[:, each] = (data[each] - mean)/std

    
#拆分出测试集
test_data = data[:21*24]
test_target = test_data['cnt']
test_data = test_data.drop(['cnt', 'casual', 'registered'],axis=1)

data = data[21*24:]
data.index = range(len(data))

#拆分出验证集
val_data = data[:10*24]
val_target = val_data['cnt']
val_data = val_data.drop(['cnt', 'casual', 'registered'],axis=1)

data = data[10*24:]
data.index = range(len(data))


#得到训练集
train_target = data['cnt']    
train_data = data.drop(['cnt', 'casual', 'registered'],axis=1)


#设置超参数
ecoph = 6000
lr = 0.5
input_nodes = train_data.shape[1]
hidden_nodes = 16
output_nodes = 1


#搭建神经网络


#画出节点
input_nodes_placeholders = tf.placeholder(dtype = tf.float32,shape=[None,input_nodes])
output_nodes_placeholders = tf.placeholder(dtype = tf.float32,shape=[None,output_nodes])


input_hidden_weight1 = tf.Variable(tf.random_normal([input_nodes,hidden_nodes],stddev=0.1))
hidden_output_weight1 = tf.Variable(tf.random_normal([hidden_nodes,output_nodes],stddev=0.1))

#画出节点之间的关系
hidden_placeholders = tf.sigmoid(tf.matmul(input_nodes_placeholders,input_hidden_weight1))
final_placeholders = tf.matmul(hidden_placeholders,hidden_output_weight1)


#计算误差
loss = tf.reduce_mean(tf.square(final_placeholders - output_nodes_placeholders))
train_op = tf.train.GradientDescentOptimizer(lr).minimize(loss)


init = tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init)
    for i in range(ecoph):
        batch_index  = np.random.choice(train_data.index, size=128)
        
        sess.run(train_op,feed_dict={
                    input_nodes_placeholders:train_data.iloc[batch_index].values,
                    output_nodes_placeholders:train_target[batch_index].values[:,None]
                })
        train_loss = sess.run(loss,feed_dict={
                    input_nodes_placeholders:train_data.iloc[batch_index].values,
                    output_nodes_placeholders:train_target[batch_index].values[:,None]
                })
    
        val_loss = sess.run(loss,feed_dict={
                    input_nodes_placeholders:val_data.values,
                    output_nodes_placeholders:val_target.values[:,None]
                })
    
        sys.stdout.write("\rProgress: {:2.1f}".format(100 * i/float(ecoph)) \
                         + "% ... Training loss: " + str(train_loss)[:5] \
                         + " ... Validation loss: " + str(val_loss)[:5])
        sys.stdout.flush()
        
        
    test_loss = sess.run(loss,feed_dict={
                    input_nodes_placeholders:test_data.values,
                    output_nodes_placeholders:test_target.values[:,None]
                })
    print('\n测试得到的误差为:{}'.format(test_loss))    





with tf.Session() as sess:
    sess.run(init)        
    test_loss = sess.run(loss,feed_dict={
                    input_nodes_placeholders:test_data.values,
                    output_nodes_placeholders:test_target.values[:,None]
                })    
    predictions=sess.run(final_placeholders,feed_dict={input_nodes_placeholders:test_data.values}) 
    print(test_loss)










    
    
    
    
    
    
    

