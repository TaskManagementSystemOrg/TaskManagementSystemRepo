package models;

import Utils.ValidationHelpers;
import models.contracts.Comment;
import models.contracts.Task;

import java.time.LocalDateTime;
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
    private ArrayList<String> history;
    private List<Comment> comments;

    public TaskImpl(int id, String title, String description) {
        this.id = id;
        setTitle(title);
        setDescription(description);
        this.history = new ArrayList<>();
        this.comments = new ArrayList<>();
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

    private void addComments(Comment commentToAdd) {
        comments.add(commentToAdd);
    }

    private void removeComment(Comment commentToRemove) {
        comments.remove(commentToRemove);
    }
}
