package util;

import java.util.Comparator;

public class MyComparator implements Comparator<Message> {
    @Override
    public int compare(Message o1, Message o2) {
        return o1.getPriority() - o2.getPriority();
    }
}