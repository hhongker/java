package data1;

import java.util.ArrayList;
import java.util.LinkedList;

public class Main {

    /**
     * 1)sleep()是Thread类静态方法，wait,notify,ntifyAll是Object的方法
     * 2)wait,notify,notifyAll必须用在同步控制方法或同步控制块中，sleep可以用在任何地方
     * 3)sleep方法导致此线程暂停执行时间，但保留监控状态，但恢复也是需要时间的，不会立刻恢复,不会释放对象锁
     * 4)wait方法会使访问该对象的线程等待，只要调用了notify或notifyAll方法才可以唤醒，该方法会释放对象锁，所以必须用在synchronized的块中
     */

    static LinkedList<String> changKu = new LinkedList<>();
    static Thread thread1 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true){
                synchronized (changKu) {
                    if (changKu.size() < 20) {
                        changKu.add("a");
                        System.out.println("线程1增加了一个，此时还有" + changKu.size() + "个");
                    } else {
                        try {
                            changKu.notifyAll();
                            changKu.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                        try {
                        Thread.sleep(32);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });
    static Thread thread2 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                synchronized (changKu) {
                    if (changKu.size() > 3) {
                        changKu.pollLast();
                        System.out.println("线程2删除了一个，此时还有" + changKu.size() + "个");
                    } else {
                        try {
                            changKu.notifyAll();
                            changKu.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(64);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });
    static Thread thread3 = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                synchronized (changKu) {
                    if (changKu.size() > 3) {
                        changKu.pollLast();
                        System.out.println("线程3删除了一个，此时还有" + changKu.size() + "个");
                    } else {
                        try {
                            changKu.notifyAll();
                            changKu.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(64);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    });

    public static void main(String[] args) {
        thread1.start();
        thread2.start();
        thread3.start();
    }


}
