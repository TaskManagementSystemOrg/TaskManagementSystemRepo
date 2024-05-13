package models.contracts;

import java.lang.reflect.Member;
import java.util.List;

public interface Team extends Printable{
    List<Person> getMembers();
    String getName();
    List<Board> getBoards();

    void addMember(Person memberToAdd);
    void  removeMember(Person memberToRemove);
    void addBoard(Board boardToAdd);
    void  removeBoard(Board boardToRemove);
}
