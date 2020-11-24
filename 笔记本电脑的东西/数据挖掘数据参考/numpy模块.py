# -*- coding: utf-8 -*-

import numpy as np

#1
a=np.random.randint(0,8,(10))
print(a)

#2
print(a[0])
print(a[::2])
print(a[-3:])

#3
print(np.min(a))
print(np.sort(a))
print(np.unique(a))
print(np.sum(a))
print(np.std(a))

#4
print(len(np.where(a>4)))
print(np.argsort(a)[2])

#5# 
#a=np.reshape(a,(1,10))
#b=np.concatenate((a,a))

b=np.zeros((2,10),int)
b[0,:]=a
b[1,:]=a
print(b)

#6
b=np.reshape(b,(4,5))
print(b)
print(b[:,2:5])

#7 
np.save("aa.npy",b)
bb=np.load("aa.npy")
print(b)