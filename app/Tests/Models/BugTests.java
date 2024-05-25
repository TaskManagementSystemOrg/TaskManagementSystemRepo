package Models;

import models.BugImpl;
import models.CommentImpl;
import models.PersonImpl;
import models.contracts.Bug;
import models.contracts.Comment;
import models.enums.BugStatus;
import models.enums.Priority;
import models.enums.Severity;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BugTests {

        @Test
        public void testCreateBugWithValidAttributes() {
            Bug bug = new BugImpl(1, "Bug title valid", "Bug description", new ArrayList<>(),  Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE,"DevMember");
            assertEquals("Bug title valid", bug.getTitle());
            assertEquals("Bug description", bug.getDescription());
            assertEquals(Priority.HIGH, bug.getPriority());
            assertEquals(Severity.CRITICAL, bug.getSeverity());
            assertEquals(BugStatus.ACTIVE, bug.getStatus());
            assertEquals("DevMember", bug.getAssignee());
        }

        @Test
        public void testCreateBugWithInvalidTitle() {
            assertThrows(IllegalArgumentException.class, () -> {
                new BugImpl(1, "Short", "Bug description", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, null);
            });
        }

        @Test
        public void testChangeBugStatus() {
            Bug bug = new BugImpl(1, "Bug title valid", "Bug description", new ArrayList<>(),Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, null);
            bug.setStatus(BugStatus.DONE);
            assertEquals(BugStatus.DONE, bug.getStatus());
        }

        @Test
        public void testAddCommentToBug() {
            Bug bug = new BugImpl(1, "Bug title valid", "Bug description", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, null);
            Comment comment = new CommentImpl(new PersonImpl("Author"), "This is a comment");
            bug.addComment(comment);
            assertTrue(bug.getComments().contains(comment));
        }

        @Test
        public void testBugHistory() {
            Bug bug = new BugImpl(1, "Bug title valid", "Bug description", new ArrayList<>(), Priority.HIGH, Severity.CRITICAL, BugStatus.ACTIVE, null);
            bug.setStatus(BugStatus.DONE);
            List<String> history = bug.getHistory();
            assertTrue(history.contains("Status changed from Active to Done"));
        }
}
