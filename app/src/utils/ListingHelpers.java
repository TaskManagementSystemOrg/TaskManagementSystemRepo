package utils;

import models.contracts.Printable;

import java.util.ArrayList;
import java.util.List;

public class ListingHelpers {

    public static <T extends Printable> String elementsToString(List<T> elements) {
        List<String> result = new ArrayList<>();
        for (T element : elements) {
            result.add(element.toString());
        }
        return "====================\n" + String.join(System.lineSeparator(), result) + "\n====================";
    }
}
