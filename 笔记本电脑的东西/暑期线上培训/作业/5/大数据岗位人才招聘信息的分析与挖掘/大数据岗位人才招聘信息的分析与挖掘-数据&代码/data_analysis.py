import pandas as pd
import matplotlib.pyplot as plt
from wordcloud import WordCloud

job_info_new = pd.read_csv('job_info_new.csv', encoding='GBK')

# 热门岗位
a = job_info_new['岗位名'].value_counts()[:10]
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.15)
plt.bar(a.index, a)
plt.xticks(rotation=45)
plt.title('热门招聘岗位')
plt.show()

# 热门行业
a = job_info_new['行业'].value_counts()[:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.25)
plt.bar(a.index, a)
plt.xticks(rotation=45)
plt.title('热门行业分布')
plt.show()

# 热门招聘公司
a = job_info_new['公司名'].value_counts()[:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.25)
plt.bar(a.index, a)
plt.xticks(rotation=45)
plt.title('热门公司分布')
plt.show()

# 热门岗位的薪资待遇
a = job_info_new.groupby('岗位名').agg({'工资水平': 'mean', '公司名': 'count'}).sort_values('公司名', ascending=False)
b = a['工资水平'][:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.15)
plt.bar(b.index, b)
plt.xticks(rotation=45)
plt.title('热门岗位的工资水平')
plt.show()

# 热门行业的薪资待遇
a = job_info_new.groupby('行业').agg({'工资水平': 'mean', '公司名': 'count'}).sort_values('公司名', ascending=False)
b = a['工资水平'][:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.25)
plt.bar(b.index, b)
plt.xticks(rotation=45)
plt.title('热门行业的工资水平')
plt.show()

# 热门城市的工资水平
a = job_info_new.groupby('工作地点').agg({'工资水平': 'mean', '公司名': 'count'}).sort_values('公司名', ascending=False)
b = a['工资水平'][:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.25)
plt.bar(b.index, b)
plt.xticks(rotation=45)
plt.title('热门城市的工资水平')
plt.show()


# 热门城市的招聘分布
a = job_info_new.groupby('工作地点').agg({'工资水平': 'mean', '公司名': 'count'}).sort_values('公司名', ascending=False)
b = a['公司名'][:10]
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.25)
plt.bar(b.index, b)
plt.xticks(rotation=45)
plt.title('热门城市的招聘分布')
plt.show()

# 不同体量企业的薪资待遇
a = job_info_new.groupby('公司规模').agg({'工资水平': 'mean'})
b = a
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.15)
plt.bar(range(len(b)), b['工资水平'])
plt.xticks(range(len(b)), b.index, rotation=45)
plt.title('不同体量企业的工资水平')
plt.show()


# 不同体量公司的用人需求
a = job_info_new['公司规模'].value_counts()
b = a
plt.figure(figsize=(16, 9))
plt.rcParams['font.sans-serif'] = 'SimHei'
plt.subplots_adjust(bottom=0.15)
plt.bar(range(len(b)), b)
plt.xticks(range(len(b)), b.index, rotation=45)
plt.title('不同体量公司的用人需求')
plt.show()


# 岗位技能分析

def get_word_cloud(data=None, job_name=None):
    words = []
    describe = data['工作描述'][data['岗位名'] == job_name].str[1:-1]
    describe.dropna(inplace=True)
    [words.extend(i.split(',')) for i in describe]
    words = pd.Series(words)
    word_fre = words.value_counts()
    return word_fre


word_fre = get_word_cloud(data=job_info_new, job_name='开发工程师')
mask = plt.imread('duihuakuan.jpg')
wc = WordCloud(mask=mask, background_color='white', font_path=r'C:\Windows\Fonts\simhei.ttf')
wc.fit_words(word_fre)
plt.imshow(wc)

