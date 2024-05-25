package Models;

import models.FeedbackImpl;
import models.CommentImpl;
import models.PersonImpl;
import models.contracts.Comment;
import models.contracts.Feedback;
import models.enums.FeedbackStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeedbackTests {

    @Test
    public void testCreateFeedbackWithValidAttributes() {
        Feedback feedback = new FeedbackImpl(1, "Feedback title valid", "Feedback description", 5, FeedbackStatus.NEW);
        assertEquals("Feedback title valid", feedback.getTitle());
        assertEquals("Feedback description", feedback.getDescription());
        assertEquals(5, feedback.getRating());
        assertEquals(FeedbackStatus.NEW, feedback.getStatus());
    }

    @Test
    public void testChangeFeedbackStatus() {
        Feedback feedback = new FeedbackImpl(1, "Feedback title valid", "Feedback description", 5, FeedbackStatus.NEW);
        feedback.setStatus(FeedbackStatus.DONE);
        assertEquals(FeedbackStatus.DONE, feedback.getStatus());
    }

    @Test
    public void testAddCommentToFeedback() {
        Feedback feedback = new FeedbackImpl(1, "Feedback title valid", "Feedback description", 5, FeedbackStatus.NEW);
        Comment comment = new CommentImpl(new PersonImpl("Author"), "This is a comment");
        feedback.addComment(comment);
        assertTrue(feedback.getComments().contains(comment));
    }

    @Test
    public void testFeedbackHistory() {
        Feedback feedback = new FeedbackImpl(1, "Feedback title valid", "Feedback description", 5, FeedbackStatus.NEW);
        feedback.setStatus(FeedbackStatus.DONE);
        List<String> history = feedback.getHistory();
        assertTrue(history.contains("Status changed from New to Done"));
    }
}
