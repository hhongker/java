package com.duing.qingping.memory;

import java.util.*;

// OutOfMemoryError
public class Test2 {

    public static void main(String[] args) {

        ArrayList list = new ArrayList();
        while (true) {
            list.add(new Test2());
        }

    }
}