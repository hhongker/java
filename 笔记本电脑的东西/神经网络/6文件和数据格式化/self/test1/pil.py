from PIL import Image

# In[创建一张图]
img1 = Image.new("RGB",(32,32),'#f40')
                 
img2 = Image.new("P",(32,32))

img3 = Image.new("L",(32,32),'#008c8c')
                 
# In[打开一张图片,一般操作]
img4 = Image.open('Images/000001.jpg')
#img4.show() #用默认软件打开一张图片
#img4.size #看这张图片大小
#img4.mode #看图片的数据类型
#img4.format #看图片的保存格式类型
#img4 = img4.resize((200,200)) # 图片变形
#img4 = img4.rotate(30) #图片旋转
#img4 = img4.point(lambda x: x+10) #对每个像素点进行操作
#img4 = img4.point(lambda x: x < 100) #小于100的像素点变成1，大于则变成0
#r,g,b = img4.split() #拆分成三个通道
#img4 = Image.merge('RGB',(r,g,b)) #将三个通道合并到一张图
# In[过滤增强]
from PIL import ImageFilter

import os
path = 'filter'
if not os.path.exists(path):
    os.mkdir(path)

img5 = Image.open('Images/000001.jpg')

om = img5.filter(ImageFilter.CONTOUR)
om.save(os.path.join(path,'CONTOUR轮廓效果.png'))
om = img5.filter(ImageFilter.BLUR)
om.save(os.path.join(path,'BLUR模糊效果.png'))
om = img5.filter(ImageFilter.DETAIL)
om.save(os.path.join(path,'DETAIL细节效果.png'))
om = img5.filter(ImageFilter.EDGE_ENHANCE)
om.save(os.path.join(path,'EDGE_ENHANCE边界加强效果.png'))
om = img5.filter(ImageFilter.EMBOSS)
om.save(os.path.join(path,'EMBOSS浮雕效果.png'))
om = img5.filter(ImageFilter.FIND_EDGES)
om.save(os.path.join(path,'FIND_EDGES边界效果.png'))
om = img5.filter(ImageFilter.SMOOTH)
om.save(os.path.join(path,'SMOOTH平滑效果.png'))
om = img5.filter(ImageFilter.SMOOTH_MORE)
om.save(os.path.join(path,'SMOOTH_MORE阈值平滑效果.png'))
om = img5.filter(ImageFilter.SHARPEN)
om.save(os.path.join(path,'SHARPEN锐化效果.png'))


from PIL import ImageEnhance

om = ImageEnhance.Contrast(img5) # 调整对比度
om.enhance(20).save(os.path.join(path,'调整对比度EnContrast.png')) #20倍
om = ImageEnhance.Color(img5) 
om.enhance(20).save(os.path.join(path,'调整颜色平衡EnContrast.png')) #20倍
om = ImageEnhance.Brightness(img5)
om.enhance(20).save(os.path.join(path,'调整亮度EnContrast.png')) #20倍
om = ImageEnhance.Sharpness(img5) 
om.enhance(20).save(os.path.join(path,'调整锐度EnContrast.png')) #20倍
# In[fun] 拆拼GIF


#将mp4转换成gif
def toGif(path):
    import moviepy.editor as mpy
    content=mpy.VideoFileClip(path)
#    剪辑到0分0秒到0分4秒
    c1 = content.subclip((0,0),(0,4)).resize((480,320))
    c1.write_gif('tanshishe.gif')
    
#拆分gif成png
def toPng(savePath,target):
    import os
    if not os.path.exists(savePath):
        os.mkdir(savePath)
    
    from PIL import Image
    im = Image.open(target)
    try:
        while True:
            im.save(savePath+'/picframe{:02d}.png'.format(im.tell()))
            im.seek(im.tell()+1)
    except:
        print('处理结束')
    
#合并成为gif
def appendGIf(path,target):
    import os
    from PIL import Image
    images = os.listdir(path)
    gifimages = []
    for image in images:
        im = Image.open(os.path.join(path,image))
        im = im.convert('RGB')
        r,g,b = im.split()
        newim = Image.merge('RGB',(g,r,b))
        newim.save(os.path.join(path,image))
        gifimages.append(newim)
    gifimages[0].save(target,save_all=True,append_images=gifimages,duration=3) #3秒
# In[test]
toGif('tanshishe.mp4') 

toPng('GIFimages','tanshishe.gif')

appendGIf('GIFimages','test1.gif')
