import pandas as pd

# 读取数据，将数据存成变量iris
iris = pd.read_csv('iris.csv', header=None)
# 创建数据框的列名称['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'class']
iris.columns = ['sepal_length', 'sepal_width', 'petal_length', 'petal_width', 'class']
# 数据框中有缺失值吗？
iris.isnull().sum().sum()
# 将列petal_length的第十到十九行设置为缺失值。
iris.iloc[9:19, 2] = None
# 将petal_lengt缺失值全部替换为1.0。
# iris_new = iris.fillna(1)
iris.fillna(1, inplace=True)   # 直接对原始数据进行修改
# 删除列class。
iris.drop('class', axis=1, inplace=True)
# 将数据框前三行设置为缺失值。
iris.loc[0:2, :] = None
# 删除有缺失值的行。
iris.dropna(inplace=True)
# 重新设置索引。
iris.reset_index(inplace=True, drop=True)

print(iris.head())
