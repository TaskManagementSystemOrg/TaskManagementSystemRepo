package models;

import models.contracts.Person;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private StoryStatus status;
    private Person assignee;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, StoryStatus status, Person assignee) {
        super(id, title, description);
        setPriority(priority);
        setSize(size);
        setStatus(status);
        setAssignee(assignee);
    }
    @Override
    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    @Override
    public void setSize(Size size) {
        this.size = size;
    }
    @Override
    public void setStatus(StoryStatus status) {
        this.status = status;
    }
    private void setAssignee(Person assignee) {
        this.assignee = assignee;
    }

    @Override
    public Priority getPriority() {
        return this.priority;
    }
    @Override
    public Size getSize() {
        return this.size;
    }
    @Override
    public StoryStatus getStatus() {
        return this.status;
    }
    @Override
    public Person getAssignee() {
        return this.assignee;
    }
}
