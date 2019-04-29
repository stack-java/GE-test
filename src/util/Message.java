package util;

public class Message {
    // Our content has priority and content
    private int priority;
    private String content;

    public Message() {
    }

    public Message(int priority, String content) {
        this.priority = priority;
        this.content = content;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}