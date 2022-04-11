package seedu.address.model.tag;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A public enumeration class for priority tags.
 */
public enum PriorityTagType {
    HIGH(3), MEDIUM(2), LOW(1);

    private final int priorityRanking;

    PriorityTagType(int priorityRanking) {
        this.priorityRanking = priorityRanking;
    }

    //Solution below adapted from https://stackoverflow.com/questions/1104975/a-for-loop-to-iterate-over-an-enum-in-java
    /**
     * Gets all enum values as strings.
     * @return All enum values as a concatenated string.
     */
    public static String getAllTypesInString() {
        return Stream.of(PriorityTagType.values())
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
        for (PriorityTagType pt : PriorityTagType.values()) {
            if (pt.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public int getPriorityRanking() {
        return this.priorityRanking;
    }
}
