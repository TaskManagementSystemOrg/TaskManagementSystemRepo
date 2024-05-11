package models.contracts;

import models.enums.BugStatus;
import models.enums.Priority;
import models.enums.Severity;

import java.util.List;

public interface Bug extends Task{
    List<String> getStepsToReproduce();
    Priority getPriority();
    Severity getSeverity();
    BugStatus getStatus();
    Person getAssignee();
    List<Comment> getComments();
    List<String> getHistory();

}
