# -*- coding:utf-8 -*-
import pandas as pd
data = pd.read_csv('5_Pandas_data.csv', encoding='utf-8')

# 判断第一列（Id）是否有缺失值：如果有，则补全。
if data.Id.hasnans:
    data.Id.fillna(-999, inplace=True)
    print('NaN filled with -999.')

# 判断是否有重复记录：如果有，则删除至唯一。
print(data.shape)
# result: (50, 4)
data.drop_duplicates(subset=['Chinese', 'Math', 'English'], keep='first', inplace=True)
print(data.shape)
# result: (43, 4)

# 计算成绩的平均值，作为新的一列加入原数据框。
data['Avg'] = (data['Chinese']+data['Math']+data['English'])/3.0
print(data.head(3))
# result:
#     Id  Chinese  Math  English        Avg
# 0  1.0     66.0    70       57  64.333333
# 1  2.0     67.0    71       59  65.666667
# 2  3.0     68.0    72       60  66.666667

# 寻找平均分最高的记录。
print(data[data['Avg'] == data['Avg'].max()])
#     Id  Chinese  Math  English        Avg
# 20  21.0     79.0    79       78  78.666667
# 22  23.0     79.0    78       79  78.666667

# 统计每个科目≥90分的人数。
for col in data.columns[1:4]:
    print('{0}:{1}'.format(col, (data[col] >= 90).sum()))
