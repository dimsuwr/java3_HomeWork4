public class Main {

    static Object block = new Object();
    static volatile int currentNum = 1;
    static final  int num = 5;

    public static void main(String[] args) {
        new Thread(()->{
            try{
                for (int i = 0; i < num ; i++) {
                    synchronized (block){
                        while (currentNum != 1){
                            block.wait();
                        }
                        System.out.print((i + 1) + ") " + "А ");
                        currentNum = 2;
                        block.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();


        new Thread(()->{
            try{
                for (int i = 0; i < num ; i++) {
                    synchronized (block){
                        while (currentNum != 2){
                            block.wait();
                        }
                        System.out.print("B ");
                        currentNum = 3;
                        block.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();





        new Thread(()->{
            try{
                for (int i = 0; i < num ; i++) {
                    synchronized (block){
                        while (currentNum != 3){
                            block.wait();
                        }
                        System.out.println("C ");
                        currentNum = 1;
                        block.notifyAll();
                    }
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

    }
}
