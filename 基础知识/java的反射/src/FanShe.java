import data.Data1;
import data.ZhuJie;


import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

public class FanShe {


    public FanShe(){
    }
    public FanShe(int i) throws Exception{
        //        获取类对象的方法
        Class clazz = Data1.class;
        clazz = new Data1().getClass();
        clazz = Class.forName("data.Data1");
        clazz = ClassLoader.getSystemClassLoader().loadClass("data.Data1");

//        获取类继承类
        Class classp = clazz.getSuperclass();
//        获取所有的接口
        Class[] classes = clazz.getInterfaces();
//        创建无参对象
        Data1 data1 = (Data1) clazz.newInstance();
//        利用构造方法创建对象
        Constructor constructor = clazz.getConstructor();
        Data1 data11 = (Data1) constructor.newInstance();
//        创建有参的对象
        Constructor constructor1 = clazz.getConstructor(int.class,String.class);
        Data1 data12 = (Data1) constructor1.newInstance(1,"小明");
//        获取构造参数名字
        System.out.println(constructor.getName());
//        获取参数类型
        Class[] clapp = constructor1.getParameterTypes();
        for (Class cl : clapp) System.out.println(cl);
//        获取参数个数
        System.out.println(constructor1.getParameterCount());
//        获取方法上面的注解
        Annotation claaa = constructor1.getAnnotation(Data1.class);
//        获取方法上的所有注解
        Annotation[] claaas = constructor1.getDeclaredAnnotations();
//        获取所有的参数
        Parameter[] parameter =  constructor1.getParameters();
//        获取参数上的类型
        System.out.println(parameter[1].getType());
//        获取参数上的所有注解
        Annotation[] annotations = parameter[1].getAnnotations();
//        获取参数上的某个注解
        ZhuJie annotation = parameter[1].getAnnotation(ZhuJie.class);



//        获取一个共有属性（包括继承的）
        Field filed1 = clazz.getField("idd");
//        获取一个私有属性
        Field field2 = clazz.getDeclaredField("id");
//        给私有属性赋值
        field2.setAccessible(true);
        field2.set(clazz.newInstance(),111);
//        获取所有共有属性（包括继承）
        Field[] fields1 = clazz.getFields();
//        获取所有属性（包括私有的）
        Field[] fields2 = clazz.getDeclaredFields();
//        获取属性上的注解
        ZhuJie zhuJie = filed1.getAnnotation(ZhuJie.class);
//        获取所有注解
        Annotation[] clajj = filed1.getAnnotations();


//        获取某方法
        Method clamm = clazz.getMethod("setId",int.class);
//        获取私有方法
        Method claamm1 = clazz.getDeclaredMethod("setName",String.class);
//        获取一堆共有方法
        Method[] clamms = clazz.getMethods();
//        获取一堆私有方法
        Method[] claammss = clazz.getDeclaredMethods();
//        获取方法的参数
        Parameter[] parameter1 = clamm.getParameters();
//        获取方法的返回类型
        Class clatt = clamm.getReturnType();
//        获取方法上面的某个注解
        Annotation annotation1 = clamm.getAnnotation(ZhuJie.class);
//        获取注解上所有的注解
        Annotation[] annotations1 = clamm.getAnnotations();
//        获取参数前面的注解
        ZhuJie annotation2 = claammss[0].getAnnotation(ZhuJie.class);
//        获取注解对象上面的值
        annotation2.BBB();
//        获取参数前面的所有注解
        Annotation[] annotations2 = claammss[0].getAnnotations();

//        获取注解的类
        Class claaat = annotation2.getClass();
//        获取注解上的属性
        Field field = claaat.getField("name");
//        获取某个注解的值
        field.get(claaat.newInstance());

    }


    public static void main(String[] args) throws Exception {
    }
}
