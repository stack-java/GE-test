
import consumers.Router;
import producers.Producer;
import util.Message;
import util.MyComparator;

import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {

        // Use PriorityBlockingQueue to write
        // Use util.MesComparator to sort our message
        PriorityBlockingQueue<Message> priorityBlockingQueue =
                new PriorityBlockingQueue<Message>(1000, new MyComparator());

        ExecutorService executor = Executors.newCachedThreadPool();
        ScheduledExecutorService executorSchedule = Executors.newScheduledThreadPool(1);

        Router router = new Router(priorityBlockingQueue);

        // Start producer. Every 2 hours producers will start
        executorSchedule.scheduleWithFixedDelay(new Producer(priorityBlockingQueue), 0, 2,TimeUnit.HOURS);
        // Start route
        executor.submit(router);
        // Start consume
        executor.submit(router.addConsumer("Consumer1"));
        executor.submit(router.addConsumer("Consumer2"));

    }
}
