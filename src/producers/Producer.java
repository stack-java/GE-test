package producers;

import util.Message;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class Producer implements Runnable {

    private final int MAX_PRIORITY = 10;
    private final int MIN_PRIORITY = 1;
    private final int DEFUALT_COUNT_MES = 10;
    private final int LENGHT_MES = 7;

    private PriorityBlockingQueue<Message> priorityBlockingQueue;
    private final Random random = new Random();

    public Producer(PriorityBlockingQueue<Message> priorityBlockingQueue) {
        this.priorityBlockingQueue = priorityBlockingQueue;
    }

    @Override
    public void run() {
        for (int i = 0; i<DEFUALT_COUNT_MES; i++){
            Message myMessage = new Message(genInt(), genString());
            priorityBlockingQueue.add(myMessage);
        }
    }

    private String genString() {
        byte[] array = new byte[LENGHT_MES];
        random.nextBytes(array);
        return new String(array, Charset.defaultCharset());
    }

    private int genInt() {
        return random.ints(MIN_PRIORITY, MAX_PRIORITY).findFirst().getAsInt();
    }

}
