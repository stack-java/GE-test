package consumers;

import util.Message;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<Message> blockingQueue;

    private String name;

    public Consumer(BlockingQueue<Message> blockingQueue, String name) {
        this.blockingQueue = blockingQueue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            Message myMessage = null;
            try {
                myMessage = blockingQueue.take();
                System.out.println("Consumer=" + name + " Priority=" + myMessage.getPriority() + " Text=" + myMessage.getContent());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public String getName() {
        return name;
    }

}