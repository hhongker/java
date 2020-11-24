# -*- coding: utf-8 -*-
"""
Created on Sun Jul 12 15:30:25 2020

@author: Dr. Tang
"""

import tensorflow as tf
 
#a=tf.constant(3)
#b=tf.constant(4)
a = tf.placeholder(tf.int16)
b = tf.placeholder(tf.int16)
c=tf.multiply(a,b)

sess=tf.InteractiveSession()

print(c.eval(feed_dict={a:12,b:15}))

with tf.Session() as sess:
    print(sess.run(c,feed_dict={a:12,b:15}))



g=tf.Graph()
with g.as_default():
    aa=tf.constant(3)
    bb=tf.constant(4)
    cc=tf.multiply(aa,bb)
    
with tf.Session(graph=g) as sess:
    print(sess.run(cc))
