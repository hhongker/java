# -*- coding: utf-8 -*-
"""
Created on Mon Jul 20 09:11:35 2020

@author: hhr
"""

# In[[5-操作题]利用鸢尾花数据做如下操作]
# （1）数据加载
from sklearn.datasets import load_iris
from sklearn.cluster import KMeans

iris = load_iris()['data']
#（2）标准化处理
from sklearn.preprocessing import StandardScaler

scaler = StandardScaler().fit(iris)
iris_std = scaler.transform(iris)#标准化后的数据
#（3）构建聚类模型并训练
model = KMeans(n_clusters=3).fit(iris_std)
#（4）聚类效果可视化展示
import matplotlib.pyplot as plt

for i in range(3):
    plt.scatter(iris_std[model.labels_ == i, 0],iris_std[model.labels_ == i, 1])
plt.show()

#（5）对模型进行评价
from sklearn.metrics import silhouette_score
silhouette_score(iris_std, model.labels_)