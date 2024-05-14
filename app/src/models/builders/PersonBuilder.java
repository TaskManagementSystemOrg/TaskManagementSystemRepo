package models.builders;

import core.contracts.TaskManagementSystemRepository;
import models.PersonImpl;
import models.contracts.Person;

public class PersonBuilder extends EntityBuilder<Person> {

    public PersonBuilder(TaskManagementSystemRepository repository) {
        super(repository);
    }

    @Override
    public Person build() {
        collectCommonAttributes();
        return new PersonImpl(name);
    }
}
