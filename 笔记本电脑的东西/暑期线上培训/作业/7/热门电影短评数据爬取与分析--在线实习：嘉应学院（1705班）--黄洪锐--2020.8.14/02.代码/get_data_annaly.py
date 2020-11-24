# In[TASK1 获取豆瓣评论信息并存放好]

import requests
from bs4 import BeautifulSoup as bs
import time
import pandas as pd


head = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36',
    'Sec-Fetch-Site':'same-origin'
}
url = 'https://movie.douban.com/subject/1866479/comments?start={}&limit=20&sort=new_score&status=P'


#loginurl =  'https://accounts.douban.com/passport/login?source=movie'
#login = {
#        'username':'18813215324',
#        'password':'aa100468'
#        }
#
#s = requests.Session()
#rpp = s.post(loginurl,data=login,headers=head)
#a = rpp.text
#rpp.url


for i in range(0,500,20):
    print(i)

    newurl = url.format(str(i))
    rpp = requests.get(newurl,headers=head)
    soup = bs(rpp.content,'lxml')
    
#    comments = [i.text for i in soup.select('#comments div.comment > p > span')] 
#    usernames = [i.text for i in soup.select('#comments div.comment > h3 > span.comment-info > a')]
#    starts = [i['class'] for i in soup.select('#comments div.comment > h3 > span.comment-info > span.rating')]                                        
#    times = [i['title'] for i in soup.select('#comments  div.comment > h3 > span.comment-info > span.comment-time')]                 
#    hasuse =  [i.text for i in soup.select('#comments div.comment > h3 > span.comment-vote > span')]                     
#    userhrefs = [i['href'] for i in soup.select('#comments div.comment > h3 > span.comment-info > a')]
    
    comments = []
    usernames = []
    starts = []
    times = []
    hasuses = []
    userhrefs = []
    for com in soup.select('#comments div.comment'):
        try:                   
            comment = com.select('span.short')[0].text
            username = com.select('h3 > span.comment-info > a')[0].text
            start = com.select('h3 > span.comment-info > span.rating')[0]['class']
            time1 = com.select('h3 > span.comment-info > span.comment-time')[0]['title']
            hasuse = com.select('h3 > span.comment-vote > span')[0].text
            userhref = com.select('h3 > span.comment-info > a')[0]['href']
        except:
            continue
        else:
            comments.append(comment)
            usernames.append(username)
            starts.append(start)
            times.append(time1)
            hasuses.append(hasuse)
            userhrefs.append(userhref)

    citys = []
    intodoubantimes = []            
 
    for iurl in userhrefs:
        
        rpp1 = requests.get(iurl,headers=head)
        
        soup = bs(rpp1.content,'lxml')
        try:
            citys.append(soup.select('#profile > div > div.bd > div.basic-info > div > a')[0].text)
            intodoubantimes.append(soup.select('#profile > div > div.bd > div.basic-info > div > div')[0].text)    
        except:
            citys.append(None)
            intodoubantimes.append(None)            
        time.sleep(1)
   
    try:
        data = pd.DataFrame()
        print(len(comments),len(usernames),len(starts),len(times),len(hasuses),len(citys),len(intodoubantimes))

        data['comments'] = comments
        data['username'] = usernames
        data['starts'] = starts
        data['times'] = times
        data['hasuses'] = hasuses
        data['citys'] = citys
        data['intodoubantimes'] = intodoubantimes

        data.to_csv('../01.中间数据（如有）/comments_info.csv', mode='a+',header=None,encoding='utf_8_sig')
    except:
        print('当页数据写入失败， 跳转到下页继续爬取。')
    time.sleep(1)
   
# In[TASK2 分析短评的关键信]
import pandas as pd
import numpy as np
import re
import jieba
import matplotlib.pyplot as plt
from wordcloud import WordCloud


data = pd.read_csv(r'../01.中间数据（如有）/comments_info.csv',index_col=0,header=None)

#数据预处理

#加上列名
data.columns = ['comments','username','starts','times','hasuses','citys','intodoubantimes']
data.index = range(len(data))
#处理start列变为数字

data['starts'] = data['starts'].apply(lambda x: int(re.search('\d+',str(x)).group())).fillna(0)

#将时间转换成datatime
data['times'] = pd.to_datetime(data['times'])

#处理citys
data['citys'] = data['citys'].fillna('')
data['citys'] = data['citys'].str.lower().apply(lambda x: str(x).split(',')[0])


#处理加入豆瓣的时间
def formatTimeToDouban(x):
    res = re.search('\d+-\d+-\d+',str(x))
    if res:
        return res.group()
    return np.nan
data['intodoubantimes'] = data['intodoubantimes'].apply(formatTimeToDouban)


#评论分词并且去掉停用词操作
with open('../01.中间数据（如有）/stopword.txt', 'r') as f:
    stopword1 = f.read()
with open('../01.中间数据（如有）/stoplist.txt', 'r',encoding='utf-8') as f:
    stopword2 = f.read()
a = data['comments'].apply(lambda x: x.lower()).apply(lambda x: ''.join(x)).apply(jieba.lcut).apply(lambda x: [i for i in x if i not in stopword1]).apply(lambda x: [i for i in x if i not in stopword2])
a[a.apply(lambda x: len(x) < 6)] = np.nan
data['comments'] = a

#词频统计
def get_word_cloud(data=None,colname=None):
    words = []
    describe = data[colname].str[1:-1]
    describe.dropna(inplace=True)
    [words.extend(i) for i in describe]
    words = pd.Series(words)
    word_fre = words.value_counts()
    return word_fre

word_fre = get_word_cloud(data=data, colname='comments')

#绘制词云图
mask = plt.imread('../01.中间数据（如有）/aixin.jpg')
wc = WordCloud(mask=mask, background_color='white', font_path=r'../01.中间数据（如有）/simhei.ttf')
wc.fit_words(word_fre)
plt.imshow(wc)

#分别统计好评差评，好评，一般的评语
#创建一个列，好评一般。。。

def getLeval(x):
    if x >= 40:
        return '好评'
    elif x == 30:
        return '一般'
    else:
        return '差评'
data['leval'] = data['starts'].apply(getLeval)
def get_word_cloud(data=None,colname=None,tianjian=None):
    words = []
    describe = data[colname][data['leval'] == tianjian].str[1:-1]
    describe.dropna(inplace=True)
    [words.extend(i) for i in describe]
    words = pd.Series(words)
    word_fre = words.value_counts()
    return word_fre

#好评
word_fre = get_word_cloud(data=data, colname='comments',tianjian='好评')
mask = plt.imread('../01.中间数据（如有）/aixin.jpg')
wc = WordCloud(mask=mask, background_color='white', font_path=r'../01.中间数据（如有）/simhei.ttf')
wc.fit_words(word_fre)
plt.imshow(wc)
# 一般
word_fre = get_word_cloud(data=data, colname='comments',tianjian='一般')
mask = plt.imread('../01.中间数据（如有）/aixin.jpg')
wc = WordCloud(mask=mask, background_color='white', font_path=r'../01.中间数据（如有）/simhei.ttf')
wc.fit_words(word_fre)
plt.imshow(wc)
# 差评
word_fre = get_word_cloud(data=data, colname='comments',tianjian='差评')
mask = plt.imread('../01.中间数据（如有）/aixin.jpg')
wc = WordCloud(mask=mask, background_color='white', font_path=r'../01.中间数据（如有）/simhei.ttf')
wc.fit_words(word_fre)
plt.imshow(wc)

#处理完成，保存
data.to_csv('../01.中间数据（如有）/comments_info1.csv',encoding='utf_8_sig')


# In[ TASK3 时间分析]

# 分析用户发表短评数量随日期的变化情况
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

data = pd.read_csv(r'../01.中间数据（如有）/comments_info1.csv',index_col=0)

data['days'] = pd.PeriodIndex(pd.to_datetime(data['times']),freq='D')
a = data.groupby('days').agg({'comments': 'count'})

plt.figure(figsize=(16,12))
plt.plot(range(len(a)),a)
plt.xlabel('日期')
plt.ylabel('观影次数')
plt.xticks(range(len(a)),a.index,rotation=45)
plt.show()


# 分析用户发表短评数量时刻的变化情况
# 同上



# 分析随日期变化，评分变化情况
a = data.groupby(['days','leval'],as_index=False).agg({'username': 'count'})

fig = plt.figure(figsize=(12,28))

#好评随时间变化情况
ax1 = fig.add_subplot(3,1,1)
ax1.plot(range(len(a.loc[a['leval']=='好评','username'])),a.loc[a['leval']=='好评','username'])
plt.xlabel('日期')
plt.ylabel('评分情况')
plt.xticks(range(len(a.loc[a['leval']=='好评','username'])),a.loc[a['leval']=='好评','days'],rotation=45)
plt.legend(['好评'])

#一般随时间变化情况
ax2 = fig.add_subplot(3,1,2) 
ax2.plot(range(len(a.loc[a['leval']=='一般','username'])),a.loc[a['leval']=='一般','username'])
plt.xlabel('日期')
plt.ylabel('评分情况')
plt.xticks(range(len(a.loc[a['leval']=='一般','username'])),a.loc[a['leval']=='一般','days'],rotation=45)
plt.legend(['一般'])

#差评随时间变化情况
ax3 = fig.add_subplot(3,1,3) 
ax3.plot(range(len(a.loc[a['leval']=='差评','username'])),a.loc[a['leval']=='差评','username'])
plt.xlabel('日期')
plt.ylabel('评分情况')
plt.xticks(range(len(a.loc[a['leval']=='差评','username'])),a.loc[a['leval']=='差评','days'],rotation=45)
plt.legend(['差评'])

plt.show()


# In[TASK4 城市分析]

import matplotlib.pyplot as plt
import pandas as pd
# 分析用户长居城市分布情况
a = data.loc[data['citys'] != '','citys'].value_counts()

#画出折线图
plt.figure(figsize=(16,8))
plt.plot(a.index,a.values)
plt.xlabel('地区')
plt.ylabel('人数')
plt.xticks(rotation=45)
plt.legend(['用户长居城市分布情况'])
plt.show()


#画出饼图
plt.figure(figsize=(12,12))
plt.pie(a.values,autopct='%.2f %%',radius=1,labels=list(a.index),labeldistance=1,wedgeprops={
    'width':0.7
})
plt.show()

# 分析不同城市的评分情况
a = data.loc[data['citys'] != '',:].groupby(['citys','leval'],as_index=False).agg({'username': 'count'})

plt.figure(figsize=(16,8))
plt.plot(a.loc[a['leval']=='好评','citys'],a.loc[a['leval']=='好评','username'])
plt.xlabel('城市')
plt.ylabel('好评数量')
plt.xticks(rotation=45)
plt.legend(['好评数量'])
plt.show()


#plt.figure(figsize=(16,8))
#plt.plot(a.loc[a['leval']=='一般','citys'],a.loc[a['leval']=='一般','username'])
#plt.xlabel('城市')
#plt.ylabel('好评数量')
#plt.xticks(rotation=45)
#plt.legend(['好评数量'])
#plt.show()
#
#
#plt.figure(figsize=(16,8))
#plt.plot(a.loc[a['leval']=='差评','citys'],a.loc[a['leval']=='差评','username'])
#plt.xlabel('城市')
#plt.ylabel('好评数量')
#plt.xticks(rotation=45)
#plt.legend(['好评数量'])
#plt.show()


# In[TASK5 用户会龄分析]
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
#分析《复仇者联盟4》发布短评用户的会龄分布
plt.figure(figsize=(12,12))
a = pd.DatetimeIndex(pd.to_datetime(data['intodoubantimes'].dropna())).year.value_counts()
plt.pie(a.values,autopct='%.2f %%',radius=1,labels=list(a.index),labeldistance=1,wedgeprops={
    'width':0.7
})
plt.show()    

 
# 分析会龄对评分的影响
a = data[['intodoubantimes','leval','username']].dropna()
a['intodoubantimes'] = pd.DatetimeIndex(pd.to_datetime(a['intodoubantimes'])).year
a = a.groupby(['intodoubantimes','leval'],as_index=False).agg({'username': 'count'})


#好评
plt.figure(figsize=(12,8))
plt.plot(a.loc[a['leval']=='好评','intodoubantimes'],a.loc[a['leval']=='好评','username'])
plt.xlabel('会龄')
plt.ylabel('好评数量')
plt.legend(['好评数量'])
plt.show()

#一般
plt.figure(figsize=(12,8))
plt.plot(a.loc[a['leval']=='一般','intodoubantimes'],a.loc[a['leval']=='一般','username'])
plt.xlabel('会龄')
plt.ylabel('好评数量')
plt.legend(['好评数量'])
plt.show()

#差评
plt.figure(figsize=(12,8))
plt.plot(a.loc[a['leval']=='差评','intodoubantimes'],a.loc[a['leval']=='差评','username'])
plt.xlabel('会龄')
plt.ylabel('好评数量')
plt.legend(['好评数量'])
plt.show()






                                       