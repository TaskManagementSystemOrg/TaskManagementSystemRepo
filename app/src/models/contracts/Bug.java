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
    String getAssignee();
    void setPriority(Priority priority);
    void setSeverity(Severity severity);
    void setStatus(BugStatus status);
    void setAssignee(String assignee);

}
