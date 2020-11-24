package com.duing.qingping.memory;

public class TestLoad {

    //《深入理解java虚拟机》 周志明
    public static void main(String[] args) {
//        1)
//        System.out.println(SubClass.value);

        // 对于静态变量而言，调用时会触发定义变量类的初始化，不会触发子类的初始化

        //  A  123
        //  B  SubClass init  +  123
        //  C  SuperClass init +  SubClass init  +  123
        //  D  SuperClass init  +  123

//        2)
//        SuperClass[] arr = new SuperClass[10];

        // A  会打印 SuperClass init
        // B  不会打印 SuperClass init


        // SuperClass不是合法的类名，因为在jdk底层有一个同名的类，用来实现数组中的相关功能
//        System.out.println(arr[0] instanceof SuperClass);

//        3）
//        System.out.println(SubClass.HELLOWORLD);

        // 常量被存储到方法区的常量池中，对常量的引用其实是对常量池的引用，并不会触发类的初始化

        //  A  hello world
        //  B  SubClass init  +  hello world
        //  C  SuperClass init +  SubClass init  +  hello world
        //  D  SuperClass init  +  hello world
    }

}

class SuperClass {

    static {
        System.out.println("SuperClass init");
    }

    public static int value = 123;

    public static final String HELLOWORLD = "hello world";
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init");
    }

}
