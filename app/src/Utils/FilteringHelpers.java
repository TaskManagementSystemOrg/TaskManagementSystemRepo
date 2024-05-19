package Utils;

import models.contracts.Printable;
import models.contracts.Task;

import java.util.List;
import java.util.stream.Collectors;

public class FilteringHelpers {
    public static List<String> filter(List<String> list, String criteria) {
        return list.stream()
                .filter(item -> item.contains(criteria))
                .collect(Collectors.toList());
    }
}
