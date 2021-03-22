package data;

import java.lang.annotation.*;

@Target(ElementType.TYPE)//表示该注解可以放在哪里,不写表示放在哪里都可以
//               1.CONSTRUCTOR:用于描述构造器
//        　　　　2.FIELD:用于描述域
//        　　　　3.LOCAL_VARIABLE:用于描述局部变量
//        　　　　4.METHOD:用于描述方法
//        　　　　5.PACKAGE:用于描述包
//        　　　　6.PARAMETER:用于描述参数
//        　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明

@Retention(RetentionPolicy.RUNTIME)//该注解的生命周期
//源文件->编译->class字节码文件->类加载到内存->运行时（runtime）

@Inherited//表示该注解能否被对象的子类所继承

@Documented//表示该注解能否被文档记录

public @interface ZhuJie1 {

}
