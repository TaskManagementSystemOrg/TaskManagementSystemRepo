package models.contracts;

import models.enums.FeedbackStatus;
import models.enums.Priority;

import java.util.List;

public interface Feedback extends Task{
    int getRating();
    FeedbackStatus getStatus();
    List<Comment> getComments();
    List<String> getHistory();
    void setRating(int rating);
    void setStatus(FeedbackStatus status);
}
