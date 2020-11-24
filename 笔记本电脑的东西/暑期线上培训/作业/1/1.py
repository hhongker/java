import pandas as pd

#1.
data = pd.read_csv(r'D:/root/Desktop/暑期线上培训/作业/1/job_info.csv',encoding='gb18030',header=None)


#2.
data.columns = ['公司', '岗位', '工作地点', '工资', '发布日期']

#3.
data.groupby('岗位').agg({'工作地点':'count'}).idxmax()
a.工资.value_counts()
a['工资'].value_counts()

#4.
data.loc[data['发布日期']=='09-03',:]

#5.
data.loc[data['工作地点']=='深圳',:].loc[data['岗位']=='数据分析师']

#6.
import re
a = data[['工资']].loc[data['工资'].isna() == False,:]
def getMaxAndMin(x):
    mm = x.split('-')
    si = ""
    
    if len(mm) == 2:
#        mm[1] = mm[1][:-3]
        mm[1] = re.match('\d*', mm[1]).group()
#        if x[-3] == '万':
        if re.search('万',x):
            mm[0] = '最低工资：'+ mm[0]  + '0000元,'
            mm[1] = '最高工资：'+ mm[1] + '0000元'
#        elif x[-3] == '千':
        elif re.search('千',x):
            mm[0] = '最低工资：'+ mm[0]  + '000元,'
            mm[1] = '最高工资：'+ mm[1] + '000元'
        si = mm[0]+mm[1]
#    1000元/天
    else:
        return '最低最高工资为：'+re.match('\d*', mm[0]).group()+'元'
    return si

a['工资'].apply(getMaxAndMin)




