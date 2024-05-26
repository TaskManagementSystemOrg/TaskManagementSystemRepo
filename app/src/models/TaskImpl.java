package models;

import utils.ValidationHelpers;
import models.contracts.Comment;
import models.contracts.Task;
import models.enums.TaskType;

import java.util.ArrayList;
import java.util.List;

public abstract class TaskImpl implements Task {
    public static final int TITLE_MIN_LENGTH = 10;
    public static final int TITLE_MAX_LENGTH = 100;
    public static final String TITLE_OUT_OF_BOUNDS_ERROR_MSG = "Title should be between 10 and 100 characters!";
    public static final int DESCRIPTION_MIN_LENGTH = 10;
    public static final int DESCRIPTION_MAX_LENGTH = 500;
    public static final String DESCRIPTION_OUT_OF_BOUND_ERROR_MESSAGE = "Description should be between 10 and 500 characters!";
    private int id;
    private String title;
    private String description;
    private List<String> history;
    private List<Comment> comments;
    private TaskType type;


    public TaskImpl(int id, String title, String description, TaskType taskType) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.history = new ArrayList<>();
        this.comments = new ArrayList<>();
        this.type = taskType;
    }

    private void setTitle(String title) {
        ValidationHelpers.validateStringLength(title, TITLE_MIN_LENGTH, TITLE_MAX_LENGTH, TITLE_OUT_OF_BOUNDS_ERROR_MSG);
        this.title = title;
    }
    private void setDescription(String description) {
        ValidationHelpers.validateStringLength(description, DESCRIPTION_MIN_LENGTH, DESCRIPTION_MAX_LENGTH, DESCRIPTION_OUT_OF_BOUND_ERROR_MESSAGE);
        this.description = description;
    }

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public String getDescription() {
        return this.description;
    }
    public ArrayList<String> getHistory() {
        return new ArrayList<>(history);
    }
    public List<Comment> getComments() {
        return new ArrayList<>(comments);
    }
    public TaskType getType(){return this.type;}


    @Override
    public String toString() {
        return String.format("Title: %s, Description: %s", getTitle(), getDescription());
    }

    @Override
    public void addComment(Comment commentToAdd) {
        comments.add(commentToAdd);
    }
    @Override
    public void removeComment(Comment commentToRemove) {
        comments.remove(commentToRemove);
    }
    @Override
    public String getStatusToString() {
        return "";
    }
}
