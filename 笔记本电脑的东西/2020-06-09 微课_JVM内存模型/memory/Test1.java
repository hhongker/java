package com.duing.qingping.memory;

//StackOverflowError
public class Test1 {

    public static void main(String[] args) {
        new Test1().test();
    }

    public void test() {
        test();
    }
}
