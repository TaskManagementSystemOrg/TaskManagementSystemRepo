package commands.creation;

import Utils.ValidationHelpers;
import commands.contracts.Command;
import core.contracts.TaskManagementSystemRepository;
import models.builders.BoardBuilder;
import models.contracts.Board;
import models.contracts.Team;

import java.util.List;

public class CreateNewBoardInATeam  extends CreateNewEntityCommand<Board> {

    public CreateNewBoardInATeam(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    protected BoardBuilder createEntityBuilder() {
        return new BoardBuilder(repository);
    }
}
