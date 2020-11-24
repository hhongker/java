import requests
import pandas as pd
from lxml import etree
import time

url_pa = 'https://search.51job.com/list/000000,000000,0000,00,9,99,%25E6%2595%25B0%25E6%258D%25AE,2,'
url_end = '.html?lang=c&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&ord_field=0&dibiaoid=0&line=&welfare='



for i in range(1, 1000):
    print('正在爬取第', i, '页数据')
    url = url_pa + str(i) + url_end
    web = requests.get(url)
    web.encoding = 'GBK'
    dom = etree.HTML(web.text)
    job_name = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//p/span/a[@target="_blank"]/@title')
    company_name = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//span[@class="t2"]/a[@target="_blank"]/@title')
    address = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//span[@class="t3"]/text()')
    salary_mid = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//span[@class="t4"]')   # 部分招聘信息未公布薪资，故需要做进一步处理
    salary = [i.text for i in salary_mid]
    release_time = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//span[@class="t5"]/text()')

    href = dom.xpath('//div[@class="dw_table"]/div[@class="el"]//p/span/a[@target="_blank"]/@href')

    JobDes = []
    CompanyType = []
    NumberStaff = []
    Industry = []
    for i in range(len(href)):
        web_sub = requests.get(href[i])
        web_sub.encoding = 'GBK'
        dom_sub = etree.HTML(web_sub.text)
        job_des = dom_sub.xpath('//div[@class="tCompany_main"]//div[@class="bmsg job_msg inbox"]/p/text()')
        company_type = dom_sub.xpath('//div[@class="tCompany_sidebar"]//div[@class="com_tag"]/p[1]/@title')
        number_staff = dom_sub.xpath('//div[@class="tCompany_sidebar"]//div[@class="com_tag"]/p[2]/@title')
        industry_ = dom_sub.xpath('//div[@class="tCompany_sidebar"]//div[@class="com_tag"]/p[3]/@title')
        JobDes.append(job_des)
        CompanyType.append(company_type)
        NumberStaff.append(number_staff)
        Industry.append(industry_)
        time.sleep(1)

    da = pd.DataFrame()
    da['岗位名'] = job_name
    da['公司名'] = company_name
    da['工作地点'] = address
    da['工资'] = salary
    da['发布时间'] = release_time
    da['公司类型'] = CompanyType
    da['公司人数'] = NumberStaff
    da['行业'] = Industry
    da['工作描述'] = JobDes
    try:
        da.to_csv('job_info.csv', mode='a+', encoding='GBK', header=None)
    except:
        print('当页数据写入失败， 跳转到下页继续爬取。')
    time.sleep(1)



