package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents a Person's block in Tracey.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {

    public static final String MESSAGE_CONSTRAINTS =
            "Only the following blocks exists: " + getBlockEnumAsString();

    public final String studentBlock;

    public enum HallBlock {
        A,
        B,
        C,
        D,
        E
    }

    /**
     * Constructs a {@code Block}.
     *
     * @param block A valid block.
     */
    public Block(String block) {
        requireNonNull(block);
        checkArgument(isValidBlock(block), MESSAGE_CONSTRAINTS);
        studentBlock = block.toUpperCase();
    }

    /**
     * Returns true if a given string is a valid block.
     *
     * @param test string to be tested to determine if valid block.
     * @return boolean where true if a given string is a valid block, false otherwise.
     */
    public static boolean isValidBlock(String test) {
        return Stream.of(HallBlock.values())
                .anyMatch(block -> block.name()
                        .equalsIgnoreCase(test));
    }

    /**
     * Returns the list of enum values from the HallBlock enum class as a string.
     *
     * @return String of HallBlock enum values.
     */
    public static String getBlockEnumAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        Stream.of(HallBlock.values()).forEach(block -> stringBuilder.append(block + " "));
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return studentBlock;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && studentBlock.equals(((Block) other).studentBlock)); // state check
    }

    @Override
    public int hashCode() {
        return studentBlock.hashCode();
    }

    /**
     * Returns a list of strings with enum values from the HallBlock enum class.
     *
     * @return List of HallBlock enum values.
     */
    public static List<String> getBlockEnumAsList() {
        return Stream.of(HallBlock.values()).map(Enum::name).collect(Collectors.toList());
    }
}
