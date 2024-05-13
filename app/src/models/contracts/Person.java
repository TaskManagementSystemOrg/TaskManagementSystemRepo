package models.contracts;

import java.util.List;

public interface Person extends Printable{
    String getName();
    List<Task> getTasks();
    List<String> getActivity();
}
