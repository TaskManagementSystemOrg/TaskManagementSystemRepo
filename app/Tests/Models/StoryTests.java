package Models;
import models.StoryImpl;
import models.CommentImpl;
import models.PersonImpl;
import models.contracts.Comment;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoryTests {

    @Test
    public void testCreateStoryWithValidAttributes() {
        Story story = new StoryImpl(1, "Story title valid", "Story description", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, "DevMember");
        assertEquals("Story title valid", story.getTitle());
        assertEquals("Story description", story.getDescription());
        assertEquals(Priority.HIGH, story.getPriority());
        assertEquals(Size.LARGE, story.getSize());
        assertEquals(StoryStatus.NOT_DONE, story.getStatus());
        assertEquals("DevMember", story.getAssignee());
    }

    @Test
    public void testCreateStoryWithInvalidTitle() {
        assertThrows(IllegalArgumentException.class, () -> {
            new StoryImpl(1, "Short", "Story description", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, null);
        });
    }

    @Test
    public void testChangeStoryStatus() {
        Story story = new StoryImpl(1, "Story title valid", "Story description", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, null);
        story.setStatus(StoryStatus.IN_PROGRESS);
        assertEquals(StoryStatus.IN_PROGRESS, story.getStatus());
    }

    @Test
    public void testAddCommentToStory() {
        Story story = new StoryImpl(1, "Story title valid", "Story description", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, null);
        Comment comment = new CommentImpl(new PersonImpl("Author"), "This is a comment");
        story.addComment(comment);
        assertTrue(story.getComments().contains(comment));
    }

    @Test
    public void testStoryHistory() {
        Story story = new StoryImpl(1, "Story title valid", "Story description", Priority.HIGH, Size.LARGE, StoryStatus.NOT_DONE, null);
        story.setStatus(StoryStatus.IN_PROGRESS);
        List<String> history = story.getHistory();
        assertTrue(history.contains("Status changed from Not Done to In Progress"));
    }
}