package commands.creation;

import core.contracts.TaskManagementSystemRepository;
import models.builders.PersonBuilder;
import models.contracts.Person;

import java.util.List;

public class CreateNewPersonCommand extends CreateNewEntityCommand<Person> {

    public CreateNewPersonCommand(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    protected PersonBuilder createEntityBuilder() {
        return new PersonBuilder(repository);
    }
}
