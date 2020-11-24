import pandas as pd
import matplotlib.pyplot as plt

# 1. 读取“appl_1980_2014.csv”数据并存为一个名叫apple的数据框。
apple = pd.read_csv('appl_1980_2014.csv')
# 2. 查看每一列的数据类型。
apple.dtypes
# 3. 将Date这个列转换为datetime类型。
apple['Date'] = pd.to_datetime(apple['Date'])
# 4. 将Date设置为索引。
apple.set_index('Date', inplace=True)
# 5. 有重复的日期吗？
apple.shape[0] == apple.index.nunique()   # 没有重复的日期
# 6. 将index设置为升序。
apple.sort_index(ascending=True, inplace=True)
# 7. 找到每个月的最后一个交易日(businessday)。
apple.groupby(by=[apple.index.year, apple.index.month]).agg({'Open': lambda x: x.index.day.max()}).astype(int)
# 8. 数据集中最早的日期和最晚的日期相差多少天？
(apple.index.max() - apple.index.min()).days
# 9. 在数据中一共有多少个月？
apple.groupby(by=[apple.index.year, apple.index.month]).agg({'Open': lambda x: x.index.day.max()}).astype(int).shape[0]
# 10. 按照时间顺序可视化Adj Close值。
plt.plot(apple.index, apple['Adj Close'])
plt.show()
