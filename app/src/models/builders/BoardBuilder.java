package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Board;
import models.BoardImpl;

public class BoardBuilder extends EntityBuilder<Board> {
    public BoardBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Board build() {
        collectCommonAttributes();
        return repository.createBoardInTeam(name,name);
    }
}
