package models.contracts;

import models.PersonImpl;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;

import java.util.List;

public interface Story extends Task{
    Priority getPriority();
    Size getSize();
    StoryStatus getStatus();
    Person getAssignee();
    List<Comment> getComments();
    List<String> getHistory();
}
