package models;

import models.contracts.Person;

public class CommentImpl {
    private Person author;
    private String content;

    public CommentImpl(Person author, String content) {
        setAuthor(author);
        setContent(content);
    }

    private void setAuthor(Person author) {
        this.author = author;
    }
    private void setContent(String content) {
        this.content = content;
    }

    public Person getAuthor() {
        return author;
    }
    public String getContent() {
        return content;
    }
}
