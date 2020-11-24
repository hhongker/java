# -*- coding: utf-8 -*-
"""
Created on Mon Jul 27 20:25:13 2020

@author: Dr. Tang
"""

import tensorflow as tf


inputs=tf.ones([5,6,6,2])#batchsize,input_width,input_height,channel
features=tf.ones([3,3,2,7])#kernal_width,kernel_height,channel_in,channel_out


batchsize=int(inputs.shape[0])
width=int(features.shape[0])
height=int(features.shape[1])
in_channel=int(features.shape[2])
out_channel=int(features.shape[3])
out_width=int(inputs.shape[1]-width+1)
out_height=int(inputs.shape[2]-height+1)


#feature_map=tf.Variable(tf.zeros([out_width,out_height]))
batches=[]
for b in range(batchsize):
    feature_maps=[]
    for l in range(out_channel): 
        feature_map=[]
        for k in range(in_channel):
            output=[]
            for i in range(out_width):
                for j in range(out_height):
                    output.append(tf.reduce_sum(inputs[b,i:i+width,j:j+height,k]*features[:,:,k,l]))
            t_output=tf.stack(output)
            t_output=tf.reshape(t_output,[out_width,out_height])
            feature_map.append(t_output)
        t_feature_map=tf.stack(feature_map,axis=-1)
        
        t_feature_map=tf.reduce_sum(t_feature_map,axis=-1)
        feature_maps.append(t_feature_map)
    t_feature_maps=tf.stack(feature_maps,axis=-1)
    batches.append(t_feature_maps)
t_batches=tf.stack(batches)
sess=tf.Session()
out=sess.run(t_feature_map)
print(out)