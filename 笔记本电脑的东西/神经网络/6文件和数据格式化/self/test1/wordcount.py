# In[fun]中文与英文的词频统计
def enWordCount(path):
    import string
    
    txt = None
    with open(path) as f:
        txt = f.read()

    for i in string.punctuation:
        if i in txt:
            txt = txt.replace(i,'')
    txt = txt.lower().split()
    wc = dict()
    for i in txt:
        wc[i] = wc.get(i,0) + 1
    wc = sorted(wc.items(),key = lambda x:x[1],reverse=True)
    return wc



def chWordCount(path):
    import jieba
    
    txt = None
    with open(path,'r+',encoding='UTF-8') as f:
        txt = f.read()
    
    biaodian = '，。”“’‘；：-——+=】【{}（）*&……%￥#@！、|~·/？》。《，'
    for i in biaodian:
        if i in txt:
            txt = txt.replace(i,'')

    words = jieba.lcut(txt)
    wc = {}
    for i in words:
        if (len(i)==1):
            continue
        else:
            wc[i] = wc.get(i,0) + 1
    wc = sorted(wc.items(),key = lambda x:x[1],reverse=True)
    return wc
# In[test]
a = enWordCount('reviews.txt')

b = chWordCount('sanguo.txt')
