# -*- coding: utf-8 -*-
"""
Created on Wed Jul  1 10:24:45 2020

@author: hhr
"""
# In[爬取豆瓣肖申克救赎的评论]

from bs4 import BeautifulSoup as bs
import requests as rq
import os


head = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36',
    'Sec-Fetch-Site':'same-origin'
    }




def crawling(url='https://movie.douban.com/subject/1292052/reviews?start=',pageNumber=1):
    
    import os
    
    rpp = rq.get(url+"0",headers=head,timeout=5)
    soup = bs(rpp.content,'lxml')
    pageNumber = soup.select('#content > div > div.article > div.paginator > span.thispage')[0]['data-total-page']

    with open(os.path.join(os.getcwd(),'豆瓣肖申克救赎的评论.txt'),'a+',encoding='utf-8') as f:
        for i in range(int(pageNumber)):
            rpp = rq.get(url + str(i*20),headers=head,timeout=5)
            soup = bs(rpp.content,'lxml')
            comments = soup.select('.short-content')
            for i in comments:
                f.write(i.text.replace(" ","")[:-5])
        

crawling()

# In[爬取百度图片]
from bs4 import BeautifulSoup as bs
import requests as rq
import os
import re
import requests 
from PIL import Image

head = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/79.0.3945.117 Safari/537.36',
    'Sec-Fetch-Site':'same-origin'
    }



def crawlingForImage(page=1,url='http://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word={}B1&pn=0&gsm=0&ct=&ic=0&lm=0&width=0&height=0'
                     ,keyword='花'):
   
    
    rpp = rq.get(url.format(keyword),headers=head,timeout=10)
    soup = bs(rpp.content,'lxml')
    try:
        newUrl = 'http://image.baidu.com'+soup.select("#page a.n")[0]['href']
    except Exception:
        return                                           

    if not os.path.exists(os.path.join(os.getcwd(),'百度爬取的图片',keyword)):
        os.makedirs(os.path.join(os.getcwd(),'百度爬取的图片',keyword))
    
    for i,j in enumerate(re.findall(r'"objURL":"(.*?)"',str(soup))):
        name = str(page)+'_'+str(i + 1) + '.jpg'
        try:
            pic = requests.get(j, timeout=15)
            pic.raise_for_status()
            
            with open(os.path.join(os.getcwd(),'百度爬取的图片',keyword,name), 'wb') as f:
                f.write(pic.content)
            
            img=Image.open(os.path.join(os.getcwd(),'百度爬取的图片',keyword,name))
            print('成功下载第%s张图片: %s' % (str(page)+'_'+str(i + 1), str(j)))
            del img
        except Exception as e:
            print('下载第%s张图片时失败: %s' % (str(page)+'_'+str(i + 1), str(j)))
            if os.path.exists(os.path.join(os.getcwd(),'百度爬取的图片',keyword,name)):
                os.remove(os.path.join(os.getcwd(),'百度爬取的图片',keyword,name))
            print(e)
            continue
    
    crawlingForImage(page=page+1,url=newUrl,keyword=keyword)


crawlingForImage(url='http://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word={}B1&pn=0&gsm=0&ct=&ic=0&lm=0&width=0&height=0',keyword='狗')


# In[]
import re
url='http://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=花B1&pn=0&gsm=0&ct=&ic=0&lm=0&width=0&height=0'
rpp = rq.get(url,headers=head,timeout=10)
soup = bs(rpp.content,'lxml')
content = str(soup)
re.findall(r'objURL":"(.*?)"',str(soup))

def a(k = 1,b=2):
    if k >99:
        return
    print(k,b)
    a(k = k+1,b= b + 10)
    
    
a(k= 8,b= 0)
