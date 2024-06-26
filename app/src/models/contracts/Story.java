package models.contracts;

import models.PersonImpl;
import models.StoryImpl;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;

import java.util.List;

public interface Story extends Task{
    Priority getPriority();
    Size getSize();
    StoryStatus getStatus();
    String getAssignee();
    void setPriority(Priority priority);
    void setSize(Size size);
    void setStatus(StoryStatus status);
    void setAssignee(String assignee);
}
