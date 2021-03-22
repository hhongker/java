package data;

public class Task {

    private String name;
    public Task(String name){
        this.name = name;
    }
    public void run(){
        long s = System.currentTimeMillis();
        long j = 0;
        System.out.println();
        for (int i= 0; i < 1000; i ++) {
            j+=i;
            j--;
            j+=2*3-1;
//            System.out.print(this.name);
        }
        System.out.println();
        long e = System.currentTimeMillis();
        System.out.println(this.name+"计算完毕："+(e-s));
//        System.out.println(s);
//        System.out.println(e);
    }
}
