import os
os.environ['TF_CPP_MIN_LOG_LEVEL'] = '2'

import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data
import math

mnist = input_data.read_data_sets('./dataset',one_hot=True,reshape=False)



# 查看mnist数据集的情况（训练集55000个样本，测试集10000个，验证集5000个）
print(mnist.train.images.shape,mnist.train.labels.shape)
print(mnist.test.images.shape,mnist.test.labels.shape)
print(mnist.validation.images.shape,mnist.validation.labels.shape)


# 设置超参数
batch_size = 128
ecophs = 5
lr = 0.1


# 搭建网络
tf.reset_default_graph()

#输入节点，输出节点
data_input = tf.placeholder(tf.float32,[None,28,28,1])
labels = tf.placeholder(tf.float32,[None,10])

## 一层卷积，一层向下采样，一次全连接，激活，一层全连接不激活输出
#weight = {
#        'w1':tf.Variable(tf.random_normal([3,3,1,6],stddev=0.1)),
#        'f1':tf.Variable(tf.random_normal([14*14*6,128],stddev=0.1)),
#        'f2':tf.Variable(tf.random_normal([128,10],stddev=0.1))
#        }
#
#bias = {
#        'wb1':tf.Variable(tf.zeros([6])),
#        'fb1':tf.Variable(tf.zeros([128])),
#        'fb2':tf.Variable(tf.zeros([10]))
#        }
#
##构图
#
#conv1 = tf.nn.conv2d(data_input,weight['w1'],[1,1,1,1],padding='SAME')
#conv1 = tf.nn.relu(conv1+bias['wb1'])
#pool1 = tf.nn.max_pool(conv1,[1,2,2,1],[1,2,2,1],padding='SAME')
#
#flatten = tf.reshape(pool1,[-1,14*14*6])
#
##f1 = tf.matmul(flatten,weight['f1'])
#f1 = tf.matmul(flatten,weight['f1'])
#f1 = tf.nn.relu(f1+bias['fb1'])
#
##logits = tf.matmul(f1,weight['f2'])
#logits = tf.matmul(f1,weight['f2'])+bias['fb2']


#2.0版本
conv1 = tf.layers.conv2d(
        data_input,
        16,
        (3,3),
        padding='SAME',
        activation=tf.nn.relu,
        kernel_initializer=tf.random_normal_initializer
        )
pool1 = tf.layers.max_pooling2d(
        conv1,
        (2,2),
        (2,2)
        )

flatten = tf.layers.flatten(pool1)

f1 = tf.layers.dense(flatten,128,activation=tf.nn.relu)

logits = tf.layers.dense(f1,10)

#计算误差，设置优化函数
cost = tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits(logits = logits,labels = labels))
optimizer = tf.train.GradientDescentOptimizer(lr).minimize(cost)

#计算准确度
corrected_prediction = tf.equal(tf.arg_max(logits,1),tf.arg_max(labels,1))
accuracy = tf.reduce_mean(tf.cast(corrected_prediction,tf.float32))

init = tf.global_variables_initializer()
with tf.Session() as sess:
    sess.run(init)
    bath_num = math.ceil(mnist.train.num_examples / batch_size)
    for i in range(ecophs):
        for j in range(bath_num):
            bath_data = mnist.train.next_batch(batch_size)
            sess.run(optimizer,feed_dict={
                    data_input:bath_data[0],
                    labels:bath_data[1]
                    })
        val_acc = sess.run(accuracy,feed_dict={
                    data_input:mnist.validation.images,
                    labels:mnist.validation.labels
                    })
        print('echops:{:<3},val_acc:{:.3f}'.format(i+1,val_acc))
        
    test_acc = sess.run(accuracy,feed_dict={
                    data_input:mnist.test.images,
                    labels:mnist.test.labels
                    })
    print('test_acc:{:.3f}'.format(test_acc))
















