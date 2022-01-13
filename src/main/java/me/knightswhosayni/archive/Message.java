package me.knightswhosayni.archive;

import java.time.LocalDateTime;

public class Message {

    private String authorId;
    private String author;
    private LocalDateTime date;
    private String content;
    private String attachments;

    public Message(String authorId, String author, LocalDateTime date, String content, String attachments) {
        this.authorId = authorId;
        this.author = author;
        this.date = date;
        this.content = content;
        this.attachments = attachments;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttachments() {
        return attachments;
    }

    public void setAttachments(String attachments) {
        this.attachments = attachments;
    }
}
