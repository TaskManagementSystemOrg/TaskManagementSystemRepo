package models;

import models.contracts.Feedback;
import models.enums.FeedbackStatus;

public class FeedbackImpl extends TaskImpl implements Feedback {
    private int rating;
    private FeedbackStatus status;

    public FeedbackImpl(int id, String title, String description, int rating, FeedbackStatus status) {
        super(id, title, description);
        setRating(rating);
        setStatus(status);
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }
    @Override
    public void setStatus(FeedbackStatus status) {
        this.status = status;
    }

    @Override
    public int getRating() {
        return rating;
    }
    @Override
    public FeedbackStatus getStatus() {
        return status;
    }
}
