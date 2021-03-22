package data;

import data.Task;
import data.XianCheng1;

public class Main {




    public static void main(String[] args) {

//        创建三个任务
        Task task1 = new Task("A线程");
        Task task2 = new Task("B线程");
        Task task3 = new Task("C线程");

        XianCheng1 xianCheng1 = new XianCheng1(task1);

        Thread xianCheng2 = new Thread(new Runnable() {
            @Override
            public void run() {
                task2.run();
            }
        });

        XianCheng1 xianCheng3 = new XianCheng1(task3);



        xianCheng1.run();
        xianCheng2.run();
        xianCheng3.run();


        xianCheng1.start();
        xianCheng2.start();
        xianCheng3.start();

    }

}
