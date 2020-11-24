#!/usr/bin/env python
# coding: utf-8

import matplotlib as mpl
mpl.use('Agg')
import matplotlib.pyplot as plt
import numpy as np

# 必须配置中文字体，否则会显示成方块
# 注意所有希望图表显示的中文必须为unicode格式
plt.rcParams['font.sans-serif'] = 'SimHei'## 设置中文显示
plt.rcParams['axes.unicode_minus'] = False

font_size = 10 # 字体大小
fig_size = (14, 6) # 图表大小

sex=['男','女']
place=['城镇','乡村']
datas=np.load('6_Matplotlib_populations.npz')
keys=datas.keys()
#for key in keys:
    #print(key)
    
a=datas['data']
b=datas['feature_names']

# 更新字体大小
mpl.rcParams['font.size'] = font_size
# 更新图表大小
mpl.rcParams['figure.figsize'] = fig_size
# 设置柱形图宽度
bar_width = 0.4
# 添加数据标签
def add_labels(rects):
    for rect in rects:
        height = rect.get_height()
        plt.text(rect.get_x() + rect.get_width() / 2, height, height, ha='center', va='bottom',rotation=5)
        # 柱形图边缘用白色填充，纯粹为了美观
        rect.set_edgecolor('white')
        
no = np.arange(20)
no1 = np.arange(19,-1,-1)
rects1 = plt.bar(no, a[:-2,2], bar_width, color='#0072BC', label=sex[0])
rects2 = plt.bar(no + bar_width, a[:-2,3], bar_width, color='#ED1C24', label=sex[1])  
# X轴标题
plt.xticks(no + bar_width, a[no1,0])
# Y轴范围
plt.ylim(ymax=80000, ymin=0)
# 图表标题
plt.title(u'各年份的男女人口数目的柱状图')
plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, ncol=5)   
# 添加数据标签 
add_labels(rects1)
add_labels(rects2)
plt.savefig(u'各年份的男女人口数目的柱状图.png')
           
plt.figure()
rects3 = plt.bar(no, a[:-2,4], bar_width, color='#0072BC', label=place[0])
rects4 = plt.bar(no + bar_width, a[:-2,5], bar_width, color='#ED1C24', label=place[1]) 
# X轴标题
plt.xticks(no + bar_width, a[no1,0])
# Y轴范围
plt.ylim(ymax=86000, ymin=0)
add_labels(rects3)
add_labels(rects4)
plt.title(u'各年份的城乡人口数目的柱状图')
# 图例显示在图表下方
plt.legend(loc='upper center', bbox_to_anchor=(0.5, -0.05), fancybox=True, ncol=5)
# 图表输出到本地
plt.savefig(u'各年份的城乡人口数目的柱状图.png')
