package models;

import models.contracts.Person;
import models.contracts.Story;
import models.enums.Priority;
import models.enums.Size;
import models.enums.StoryStatus;
import models.enums.TaskType;

public class StoryImpl extends TaskImpl implements Story {
    private Priority priority;
    private Size size;
    private StoryStatus status;
    private String assignee;

    public StoryImpl(int id, String title, String description, Priority priority, Size size, StoryStatus status, String assignee) {
        super(id, title, description, TaskType.STORY);
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
    public void setAssignee(String assignee) {
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
    public String getAssignee() {
        return this.assignee;
    }

    @Override
    public String getStatusToString() {
        return status.toString();
    }
}
