package seedu.address.model.util;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Contains utility methods for converting a list of Object to a list of String.
 */
public class ListUtil {
    /**
     * Convert a list of Object to a list of indexed String
     * @param lst the list of Object
     * @param <T> the Object type, must have a meaningful @code{toString} method
     * @return the list of String with each element indexed
     */
    public static <T> List<String> toIndexedStringList(List<T> lst) {
        return lst.stream()
                .map(obj -> (lst.indexOf(obj) + 1) + ". " + obj.toString() + " ")
                .collect(Collectors.toList());
    }
}
