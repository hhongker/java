import pandas as pd
import numpy as np
import re
import jieba

data = pd.read_csv('job_info.csv', encoding='GBK', header=None, index_col=0)
data.index = range(len(data))
data.columns = ['公司名', '岗位名', '工作地点', '工资', '发布日期', '工作描述', '公司类型', '公司规模', '行业']
data.drop_duplicates(subset=['公司名', '岗位名'], inplace=True)
'''
====================================================================================================
一、岗位名数据处理
=====================================================================================================
'''
# 1、岗位名探索
data['岗位名'] = data['岗位名'].str.strip().astype(str).apply(lambda x: x.lower())
data['岗位名'].value_counts()
# 2、岗位太多太杂，我们需要筛选出待分析岗位数据
target_job = ['算法', '分析', '工程师', '开发', '数据', '运营', '运维']    # 目标岗位
index = [data['岗位名'].str.count(i) for i in target_job]
index = np.array(index).sum(axis=0) > 0
job_info = data[index]


# 3、将岗位名称标准化：目前岗位名称太多太杂，需要统一
job_list = ['数据分析', '数据挖掘', '算法', '大数据',
            '开发工程师', '运营', '软件工程', '前端开发',
            '深度学习', 'AI', '数据库', '数据库', '数据产品',
            '客服', 'java', '.net', 'andrio', '人工智能', 'c++',
            '数据管理']

job_list = np.array(job_list)


def rename(x=None, name_list=job_list):
    index = [i in x for i in name_list]
    if sum(index) > 0:
        return name_list[index][0]
    else:
        return x


job_info['岗位名'] = job_info['岗位名'].apply(rename)
job_info['岗位名'].value_counts()

'''
====================================================================================================
二、工资数据处理
目前工资是一个范围（如1.5-2.5万/月），现需取出每个岗位的最低工资与最高工资，单位为“元/月”
若招聘信息中无工资数据则无需处理。（如2-2.5万/月，则最低工资为20000，最高工资为25000。）
=====================================================================================================
'''
job_info['工资'].str[-1].value_counts()
index1 = job_info['工资'].str[-1].apply(lambda x: x in ['年', '月'])
index2 = job_info['工资'].str[-3].apply(lambda x: x in ['万', '千'])
job_info = job_info[index1 & index2]


def get_max_min(x=None):
    try:
        if x[-3] == '万':
            a = [float(i)*10000 for i in re.findall('\d+\.?\d*', x)]
        elif x[-3] == '千':
            a = [float(i)*1000 for i in re.findall('\d+\.?\d*', x)]
        if x[-1] == '年':
            a = [i/12 for i in a]
        return a
    except:
        return x


salary = job_info['工资'].apply(get_max_min)
job_info['最低工资'] = salary.str[0]
job_info['最高工资'] = salary.str[1]
job_info['工资水平'] = job_info[['最低工资', '最高工资']].mean(axis=1)

'''
====================================================================================================
三、工作地点处理
=====================================================================================================
'''

# 1、工作地点统一命名
address_list = ['北京', '上海', '广州', '深圳', '杭州', '苏州', '长沙',
                '武汉', '天津', '成都', '西安', '东莞', '合肥', '佛山',
                '宁波', '南京', '重庆', '长春', '郑州', '常州', '福州',
                '沈阳', '济南', '宁波', '厦门', '贵州', '珠海', '青岛',
                '无锡', '大连']
address_list = np.array(address_list)


def rename(x=None, name_list=address_list):
    index = [i in x for i in name_list]
    if sum(index) > 0:
        return name_list[index][0]
    else:
        return x


job_info['工作地点'] = job_info['工作地点'].apply(rename)


'''
====================================================================================================
四、公司类型数据处理
=====================================================================================================
'''
job_info['公司类型'].value_counts()
job_info.loc[job_info['公司类型'].apply(lambda x: len(x) < 6), '公司类型'] = np.nan
job_info['公司类型'] = job_info['公司类型'].str[2:-2]


'''
====================================================================================================
五、行业数据处理
=====================================================================================================
'''
job_info['行业'].value_counts()
job_info.loc[job_info['行业'].apply(lambda x: len(x) < 6), '行业'] = np.nan
job_info['行业'] = job_info['行业'].str[2:-2].str.split(',').str[0]

'''
====================================================================================================
六、工作描述数据处理
=====================================================================================================
'''
with open('stopword.txt', 'r') as f:
    stopword = f.read()

a = job_info['工作描述'].str[2:-2].apply(lambda x: x.lower()).apply(lambda x: ''.join(x)).apply(jieba.lcut).apply(lambda x: [i for i in x if i not in stopword])
a[a.apply(lambda x: len(x) < 6)] = np.nan
job_info['工作描述'] = a
'''
====================================================================================================
七、公司人数进行处理
=====================================================================================================
'''
job_info['公司规模'].str[0]


def get_number_staff(x=None):
    try:
        a = [int(i) for i in re.findall('\d+', x)]
        if len(a) == 1:
            n = a[0]
        elif len(a) == 2:
            n = np.mean(a)
        return n
    except:
        return np.nan


job_info['公司规模'] = job_info['公司规模'].apply(get_number_staff)

'''
===============
八、构造新数据
===============
'''
features = ['岗位名', '公司名', '工作地点', '公司类型', '公司规模', '行业', '工资水平', '发布日期', '工作描述']
data_new = job_info[features]   # 清洗干净后的数据
data_new.to_csv('job_info_new.csv', encoding='GBK', index=None)

data_new.groupby('岗位名').agg({'岗位名': 'count'}).sort_values('岗位名')
data_new['岗位名'].value_counts()