package Practice.Multithreading;

public class MultithreadingTaskOne {
    public static void main(String[] args){

        Task runner = new Task();
        runner.start();

        Task2 runner2 = new Task2();
        runner2.start();

        System.out.println("Hello World.");



    }

}
class Task extends Thread {
    public void run(){
        for (int i = 1; i< 1000; i+=2){
            System.out.println("number: "+i);
        }
    }

}
class Task2 extends Thread {
    public void run(){
        for (int i = 0; i<1000; i+=2){
            System.out.println("number: "+i);
        }
    }
}