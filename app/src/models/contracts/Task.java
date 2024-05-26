package models.contracts;

import models.enums.TaskType;

import java.util.List;

public interface Task extends Printable{
    int getId();
    String getTitle();
    String getDescription();
    List<Comment> getComments();
    List<String> getHistory();
    TaskType getType();
    void addComment(Comment commentToAdd);
    void removeComment(Comment commentToRemove);
    public String getStatusToString();
}
