import pandas as pd


# 1. 将数据集存入一个名为chipo的数据框内
chipo = pd.read_csv('chipotle.tsv', sep='\t')
# 2. 查看前10行内容
chipo.head(10)
# 3. 数据集中有多少个列(columns)？
chipo.shape[1]
# 4. 打印出全部的列名称
chipo.columns
# 5. 数据集的索引是怎样的？
chipo.index
# 6. 被下单数最多商品(item)是什么?
chipo.groupby(by='item_name').agg({'quantity': 'sum'}).idxmax()
# 7. 在item_name这一列中，一共有多少种商品被下单？
chipo['item_name'].nunique()
# 8. 一共有多少个商品被下单？
chipo['quantity'].sum()
# 9. 将item_price转换为浮点数
chipo['item_price'] = chipo['item_price'].str[1:].astype(float)
# string = '$2.39'
# float(string[1:])

# 10. 在该数据集对应的时期内，收入(revenue)是多少？
chipo['revenue'] = chipo['quantity'] * chipo['item_price']
chipo['revenue'].sum()
# 11. 在该数据集对应的时期内，一共有多少订单？
chipo['order_id'].nunique()
# 12. 平均客单价是多少？
chipo['revenue'].sum()/chipo['order_id'].nunique()          # 方法一：利用总收入除以总订单数
chipo.groupby('order_id').agg({'revenue': 'sum'}).mean()    # 方法二：利用分组聚合总操直接求解
