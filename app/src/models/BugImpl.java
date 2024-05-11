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
        this.stepsToReproduce = stepsToReproduce;
        this.priority = priority;
        this.severity = severity;
        this.status = status;
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
