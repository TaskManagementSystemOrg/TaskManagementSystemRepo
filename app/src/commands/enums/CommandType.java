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
    ENTER_BOARD("Enter a board to go into."),
    SAVE("Saves to json"),
    SHOW_ENTERED_BOARD("Shows entered board"),
    EXIT_BOARD("exits current entered board/team/person"),
    ENTER_TEAM("Enters a team"),
    EXIT_TEAM("exits a team"),
    NOT_LOGGED_IN_COMMAND("To display when not logged in"),
    LOG_IN("logs you in"),
    LOG_OUT("logs you out"),
    SHOW_ALL_BUGS("shows all bugs"),
    SHOW_ALL_FEEDBACK("shows all feedback"),
    SHOW_ALL_STORIES("shows all stories"),
    SHOW_ALL_TASKS("shows all tasks"),
    SHOW_ALL_TASKS_WITH_ASSIGNEE("shows all task for an assignee"),
    SHOW_COMMENTS("show commnets for a given task"),
    ADD_COMMENT("adds comment"),
    REMOVE_COMMENT("removes comment"),
    ADMIN("calls to log in as admin"),
    USER("calls to log in as a user");

    private final String description;

    CommandType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}