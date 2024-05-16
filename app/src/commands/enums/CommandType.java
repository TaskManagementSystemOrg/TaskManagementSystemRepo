package commands.enums;

public enum CommandType {
    CREATE_PERSON("Create a new person"),
    SHOW_ALL_PEOPLE("Show all people"),
    SHOW_PERSON_ACTIVITY("Show person's activity"),
    CREATE_TEAM("Create a new team"),
    SHOW_ALL_TEAMS("Show all teams"),
    SHOW_TEAM_ACTIVITY("Show team's activity"),
    ADD_PERSON_TO_TEAM("Add person to team"),
    SHOW_ALL_TEAM_MEMBERS("Show all team members"),
    CREATE_BOARD("Create a new board in a team"),
    SHOW_ALL_TEAM_BOARDS("Show all team boards"),
    SHOW_BOARD_ACTIVITY("Show board's activity"),
    CREATE_BUG("Create a new Bug in a board"),
    CREATE_STORY("Create a new Story in a board"),
    CREATE_FEEDBACK("Create a new Feedback in a board"),
    CHANGE_BUG_PRIORITY("Change the Priority of a bug"),
    CHANGE_BUG_SEVERITY("Change the Severity of a bug"),
    CHANGE_BUG_STATUS("Change the Status of a bug"),
    CHANGE_STORY_PRIORITY("Change the Priority of a story"),
    CHANGE_STORY_SIZE("Change the Size of a story"),
    CHANGE_STORY_STATUS("Change the Status of a story"),
    CHANGE_FEEDBACK_RATING("Change the Rating of a feedback"),
    CHANGE_FEEDBACK_STATUS("Change the Status of a feedback"),
    ASSIGN_TASK("Assign a task to a person"),
    UNASSIGN_TASK("Unassign a task from a person"),
    ENTER_BOARD("Enter a board to go into.");

    private final String description;

    CommandType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}