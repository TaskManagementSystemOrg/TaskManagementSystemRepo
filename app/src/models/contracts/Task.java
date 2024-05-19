package models.contracts;

public interface Task extends Printable{
    int getId();
    String getTitle();
    String getDescription();
    void addComment(Comment commentToAdd);
    void removeComment(Comment commentToRemove);
}
