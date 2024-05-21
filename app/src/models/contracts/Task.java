package models.contracts;

import java.util.List;

public interface Task extends Printable{
    int getId();
    String getTitle();
    String getDescription();
    List<Comment> getComments();
    List<String> getHistory();
    void addComment(Comment commentToAdd);
    void removeComment(Comment commentToRemove);
}
