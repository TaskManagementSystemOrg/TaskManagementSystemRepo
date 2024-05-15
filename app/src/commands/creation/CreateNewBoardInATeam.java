package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.BoardBuilder;
import models.contracts.Board;

public class CreateNewBoardInATeam  extends CreateNewEntityCommand<Board> {

    public CreateNewBoardInATeam(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    protected BoardBuilder createEntityBuilder() {
        return new BoardBuilder(repository);
    }
}
