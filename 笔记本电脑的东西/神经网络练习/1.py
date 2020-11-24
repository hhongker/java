import tensorflow as tf

from tensorflow.examples.tutorials.mnist import input_data
    
mnist=input_data.read_data_sets('./data',one_hot=True)    

#设置超参数
leanring_rate = 0.01
batch_size=128
epochs=100

#数据
features = tf.placeholder(tf.float32,[None,784])
labels = tf.placeholder(tf.float32,[None,10])

weights = tf.Variable(tf.random_normal([784,10],stddev=0.1))
bias = tf.Variable(tf.zeros([10]))

logits=tf.matmul(features,weights)+bias

probs = tf.nn.softmax(logits)
cross_entropy=-tf.reduce_sum(labels*tf.log(probs),axis=1)
cost=tf.reduce_mean(cross_entropy)

optimizer=tf.train.GradientDescentOptimizer(leanring_rate).minimize(cost)
correct_predction=tf.equal(tf.arg_max(logits,1),tf.arg_max(labels,1))
accuracy = tf.reduce_mean(tf.cast(correct_predction,tf.float32))

init=tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init)
    tatal_batch=int(mnist.train.num_examples/batch_size)+1
    for epoch in range(epochs):
        for i in range(tatal_batch):
            batch_features,batch_labels=mnist.train.next_batch(batch_size)
            sess.run(optimizer,feed_dict={features:batch_features,labels:batch_labels})
            
            if epoch%10==0:
                val_acc=sess.run(accuracy,feed_dict={
                        features:mnist.validation.images,
                        labels:mnist.validation.labels
                        })
                print('Epoch {:<3} val_acc:{:.3f}'.format(epoch,val_acc))
            
    test_acc=sess.run(accuracy,feed_dict={
             features:mnist.test.images,
             labels:mnist.test.labels
             })
    print('test_acc:{:<3}'.format(test_acc))
    
    
    
    
    
    
    


