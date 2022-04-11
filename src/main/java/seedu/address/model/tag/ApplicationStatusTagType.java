package seedu.address.model.tag;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A public enumeration class for application status tags.
 */
public enum ApplicationStatusTagType {
    NOT_APPLIED(1), APPLIED(2), INTERVIEWED(3),
    REJECTED(4), ACCEPTED(5);

    private final int statusRanking;

    ApplicationStatusTagType(int statusRanking) {
        this.statusRanking = statusRanking;
    }

    //Solution below adapted from https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
    /**
     * Gets all enum values as strings.
     * @return All enum values as a concatenated string.
     */
    public static String getAllTypesInString() {
        return Stream.of(ApplicationStatusTagType.values())
                .map((tag) -> tag.name())
                .collect(Collectors.joining(", "));
    }

    //Solution below adapted from https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
    /**
     * Checks whether the given string is within the enum values.
     * @param test String to test.
     * @return True if the string is same as one of the enum values.
     */
    public static boolean contains(String test) {
        for (ApplicationStatusTagType ast : ApplicationStatusTagType.values()) {
            if (ast.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public int getStatusRanking() {
        return this.statusRanking;
    }
}
