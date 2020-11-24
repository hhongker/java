import tensorflow as tf
from tensorflow.examples.tutorials.mnist import input_data


mnist = input_data.read_data_sets('./data',one_hot=True,reshape=False)


lr = 0.1
batch_size = 128 
echops = 10


tf.reset_default_graph()
input_data = tf.placeholder(tf.float32,[None,28,28,1])
labels = tf.placeholder(tf.float32,[None,10])


weight_conv1 = tf.Variable(tf.random_normal([5,5,1,6],stddev=0.1))
weight_conv2 = tf.Variable(tf.random_normal([5,5,6,16],stddev=0.1))
weights_fc1=tf.Variable(tf.random_normal([5*5*16,120],stddev=0.1))
weights_fc2=tf.Variable(tf.random_normal([120,84],stddev=0.1))
weights_out=tf.Variable(tf.random_normal([84,10],stddev=0.1))


covn1 = tf.nn.conv2d(input_data,weight_conv1,[1,1,1,1],padding='SAME')
pool1 = tf.nn.max_pool(covn1,[1,2,2,1],[1,2,2,1],padding='SAME')
covn2 = tf.nn.conv2d(pool1,weight_conv2,[1,1,1,1],padding='VALID')
pool2 = tf.nn.max_pool(covn2,[1,2,2,1],[1,2,2,1],padding='SAME')
flatten1 = tf.reshape(pool2,[ -1, pool2.shape[1]*pool2.shape[2]*pool2.shape[3] ])
fc1 = tf.matmul(flatten1,weights_fc1)
fc2 = tf.matmul(fc1,weights_fc2)
logits = tf.matmul(fc2,weights_out)





cost=tf.reduce_mean(tf.nn.softmax_cross_entropy_with_logits
                    (logits=logits,labels=labels))
optimizer = tf.train.GradientDescentOptimizer(lr).minimize(cost)

corrected_prediction = tf.equal(tf.arg_max(logits,1),tf.arg_max(labels,1))
accuracy = tf.reduce_mean(tf.cast(corrected_prediction,tf.float32))
init = tf.global_variables_initializer()

with tf.Session() as sess:
    sess.run(init)
    num_batches = mnist.train.num_examples // batch_size + 1
    for epoch in range(echops):
        for batch in range(num_batches):
            batches = mnist.train.next_batch(batch_size)
            sess.run(optimizer,feed_dict={
                    input_data:batches[0],
                    labels:batches[1]
                    })
        acc = sess.run(accuracy,feed_dict={
                input_data:mnist.validation.images,
                labels:mnist.validation.labels
                })
        print('Epoch:{:<2},acc:{:.3f}'.format(epoch+1,acc))
        
    test_acc = sess.run(accuracy,feed_dict={
            input_data:mnist.test.images,
            labels:mnist.test.labels
            })
    print('test_acc:',test_acc)












