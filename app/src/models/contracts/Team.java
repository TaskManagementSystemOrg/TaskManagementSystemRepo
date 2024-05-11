package models.contracts;

import java.lang.reflect.Member;
import java.util.List;

public interface Team {
    List<Person> getMembers();
    String getName();
    List<Board> getBoards();

    void addMember(Member memberToAdd);
    void  removeMember(Member memberToRemove);
    void addBoard(Board boardToAdd);
    void  removeBoard(Board boardToRemove);
}
