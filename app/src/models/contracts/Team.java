package models.contracts;

import java.lang.reflect.Member;
import java.util.List;

public interface Team extends Printable{
    List<String> getMembers();
    String getName();
    List<String> getBoards();
    List<String> getActivity();

    void addMember(String memberToAdd);
    void  removeMember(String memberToRemove);
    void addBoard(String boardToAdd);
    void  removeBoard(String boardToRemove);

}
