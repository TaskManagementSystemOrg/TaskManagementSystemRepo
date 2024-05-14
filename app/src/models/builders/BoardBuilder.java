package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.BoardImpl;
import models.contracts.Board;

public class BoardBuilder extends EntityBuilder<Board> {

    public BoardBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Board build() {
        collectCommonAttributes();
        return new BoardImpl(name);
    }
}
