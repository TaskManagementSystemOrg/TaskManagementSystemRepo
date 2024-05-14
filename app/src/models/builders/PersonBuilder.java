package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.contracts.Person;
import models.PersonImpl;

public class PersonBuilder extends EntityBuilder<Person> {
    public PersonBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Person build() {
        collectCommonAttributes();
        return repository.createPerson(name);
    }
}
