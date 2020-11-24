# -*- coding: utf-8 -*-
"""
参考： https://blog.csdn.net/zimiao552147572/article/details/106722610/
"""

import numpy as np
from sklearn.metrics.pairwise import pairwise_distances
import pandas as pd

users = ["User1", "User2", "User3", "User4", "User5"]
items = ["Item A", "Item B", "Item C", "Item D", "Item E"]
# 用户购买记录数据集
datasets = [
    [1,0,1,1,0],
    [1,0,0,1,1],
    [1,0,1,0,0],
    [0,1,0,1,1],
    [1,1,1,0,1],
]
df = pd.DataFrame(datasets, columns=items,index=users)
print(df)

# 计算所有的数据两两的杰卡德相似系数
# 计算用户间相似度  1-杰卡德距离=杰卡德相似度
user_similar = 1 - pairwise_distances(np.array(df).astype(np.bool), metric="jaccard")
user_similar = pd.DataFrame(user_similar, columns=users, index=users)
print("用户之间的两两相似度：")
print(user_similar)


topN_users = {}
# 遍历每一行数据
for i in user_similar.index:
    # 取出每一列数据，并删除自身，然后排序数据
    _df = user_similar.loc[i].drop([i])
    #sort_values 排序 按照相似度降序排列
    _df_sorted = _df.sort_values(ascending=False)
    # 从排序之后的结果中切片 取出前两条（相似度最高的两个）
    top2 = list(_df_sorted.index[:2])
    topN_users[i] = top2
 
print("Top2相似用户：")
print(topN_users)
 
# 准备空白dict用来保存推荐结果
rs_results = {}
#遍历所有的最相似用户
for user, sim_users in topN_users.items():
    rs_result = set()    # 存储推荐结果
    for sim_user in sim_users:
        # 构建初始的推荐结果
        rs_result = rs_result.union(set(df.loc[sim_user].replace(0,np.nan).dropna().index))
    # 过滤掉已经购买过的物品
    rs_result -= set(df.loc[user].replace(0,np.nan).dropna().index)
    rs_results[user] = rs_result
print("最终推荐结果：")
print(rs_results)
