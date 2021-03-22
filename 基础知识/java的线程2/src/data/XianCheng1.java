package data;

public class XianCheng1 extends Thread{


    Task task;
    public XianCheng1(Task task){
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        task.run();
    }
}
