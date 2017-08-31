package id.meetsme.meetsme.services.models;

/**
 * Created by Ibam on 8/31/2017.
 */

public class MessageListModel {
    private java.lang.String name;
    private java.lang.String last_message;
    private java.lang.String unread_count;
    private java.lang.String time_stamp;

    public MessageListModel() {
    }

    public MessageListModel(java.lang.String name, java.lang.String last_message, java.lang.String unread_count, java.lang.String time_stamp) {
        this.name = name;
        this.last_message = last_message;
        this.unread_count = unread_count;
        this.time_stamp = time_stamp;
    }

    public java.lang.String getName() {
        return name;
    }

    public void setName(java.lang.String name) {
        this.name = name;
    }

    public java.lang.String getLast_message() {
        return last_message;
    }

    public void setLast_message(java.lang.String last_message) {
        this.last_message = last_message;
    }

    public java.lang.String getUnread_count() {
        return unread_count;
    }

    public void setUnread_count(java.lang.String unread_count) {
        this.unread_count = unread_count;
    }

    public java.lang.String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(java.lang.String time_stamp) {
        this.time_stamp = time_stamp;
    }
}
