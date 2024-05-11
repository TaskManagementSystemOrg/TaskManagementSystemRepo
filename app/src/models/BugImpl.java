package models;

import models.contracts.Bug;
import models.contracts.Comment;
import models.contracts.Person;
import models.enums.BugStatus;
import models.enums.Priority;
import models.enums.Severity;

import java.util.ArrayList;
import java.util.List;

public class BugImpl extends TaskImpl implements Bug {
    private List<String> stepsToReproduce;
    private Priority priority;
    private Severity severity;
    private BugStatus status;
    private Person assignee;

    public BugImpl(int id, String title, String description, List<String> stepsToReproduce, Priority priority,
                   Severity severity, BugStatus status, Person assignee) {
        super(id, title, description);
        setSteps(stepsToReproduce);
        setPriority(priority);
        setSeverity(severity);
        setStatus(status);
        setAssignee(assignee);
    }

    private void setSteps(List<String> stepsToReproduce) {
        this.stepsToReproduce = stepsToReproduce;
    }
    private void setPriority(Priority priority) {
        this.priority = priority;
    }
    private void setSeverity(Severity severity) {
        this.severity = severity;
    }
    private void setStatus(BugStatus status) {
        this.status = status;
    }
    private void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    @Override
    public List<String> getStepsToReproduce() {
        return this.stepsToReproduce;
    }
    @Override
    public Priority getPriority() {
        return this.priority;
    }
    @Override
    public Severity getSeverity() {
        return this.severity;
    }
    @Override
    public BugStatus getStatus() {
        return this.status;
    }
    @Override
    public Person getAssignee() {
        return this.assignee;
    }
}
