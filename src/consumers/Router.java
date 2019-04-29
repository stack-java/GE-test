package consumers;

import util.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class Router implements Runnable {

    private final BlockingQueue<Message> producerQueue;

    private final List<BlockingQueue<Message>> blockingQueueList = new ArrayList<>();

    public Router(BlockingQueue<Message> producerQueue) {
        this.producerQueue = producerQueue;
    }

    // add new consumer and queue
    public Consumer addConsumer(String consumerName){
        BlockingQueue queue = new PriorityBlockingQueue<Message>(producerQueue);
        blockingQueueList.add(queue);
        return new Consumer(queue, consumerName);
    }

    // route message from producerQueue to another
    @Override
    public void run() {
        while(true){
            try {
                Message message = producerQueue.take();
                blockingQueueList.forEach(blockingQueue -> {
                    try {
                        blockingQueue.put(message);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
