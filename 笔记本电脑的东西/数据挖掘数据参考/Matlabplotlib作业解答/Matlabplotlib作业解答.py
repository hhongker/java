import numpy as np
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = 'SimHei'
plt.rcParams['axes.unicode_minus'] = False

datas=np.load('6_Matplotlib_populations.npz')
keys=datas.keys()
for key in keys:
    print(key)  
a=datas['data']
b=datas['feature_names']
print(a)
print(b)
no = np.arange(20)
no1 = np.arange(19,-1,-1)
p = plt.figure(figsize=(8,15))#设置画布

#子图1 绘制散点图
ax1 = p.add_subplot(2,1,1)

mar=['o','D','*','p','v']
c=['r','b','m','y','g']
for i in range(5):
    plt.scatter(no,a[no1,i+1], marker=mar[i],c=c[i])
plt.xlabel('年份')#添加横轴标签
plt.ylabel('人口')#添加纵轴标签
plt.xticks(no,a[no1,0],rotation=45)
plt.title('人口数据散点图') 
plt.legend([b[1],b[2],b[3],b[4],b[5]],loc='best')#添加图例

#子图2 绘制点线图
ax2 = p.add_subplot(2,1,2)
for i in range(5):
    plt.plot(no,a[no1,i+1],color=c[i],linestyle='--',marker=mar[i])#绘制折线图
plt.xlabel('年份')#添加横轴标签
plt.ylabel('人口')#添加纵轴标签
plt.xticks(no,a[no1,0],rotation=45)
plt.title('人口数据点线图') 
plt.legend([b[1],b[2],b[3],b[4],b[5]],loc='best')#添加图例
plt.show()
