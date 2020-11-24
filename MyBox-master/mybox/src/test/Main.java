package test;

import box.arraybox.ArrayBox;
import box.linkbox.LinkBox;


public class Main {


    public static void main(String[] args) {
//        ArrayBox<Integer> arrayBox = new ArrayBox(5);
//        for(int i = 0; i < 1000; i ++){
//            arrayBox.add(i);
//        }
//
//        arrayBox.remvoe(0);
//        arrayBox.remvoe(777);
//        arrayBox.remvoe(997);
//
//        for(int i = 0; i < arrayBox.size(); i ++){
//            System.out.println(arrayBox.get(i));
//        }
//        System.out.println("=========\n"+arrayBox.size());


        LinkBox<Integer> linkBox = new LinkBox<>();
        for(int i = 0; i < 1000; i ++){
            linkBox.add(i);
        }

        linkBox.remvoe(0);
        linkBox.remvoe(777);
        linkBox.remvoe(997);

        for(int i = 0; i < linkBox.size(); i ++){
            System.out.println(linkBox.get(i));
        }
        System.out.println("=========\n"+linkBox.size());
    }



}
