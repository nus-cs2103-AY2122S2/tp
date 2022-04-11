package seedu.address.model.tutorialgroup;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a TutorialGroup in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidTutorialGroupName(String)}
 */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS = "Must STRICTLY consist of a module code, followed by a "
        + "space, then the tutorial name. It should not be blank. The tutorial name must have a non-zero digit and "
        + "cannot start or end with a hyphen. The name consist of letters or hyphens or digits or underscores."
        + "Tutorial Group may only have a maximum of 100 characters including whitespace."
        + "ALL letters in tutorial group inputs will be converted into uppercase letters.";
    public static final String VALIDATION_REGEX = "[A-Za-z]{2,3}[1-8]\\d{3}[A-Za-z]{0,2} "
        + "[\\w-]+"; // ensures correct module code and tutorial name contain letters or digits or underscores or
    // hyphens

    public final String tutorialGroupName;

    /**
     * Constructs a {@code TutorialGroup}.
     *
     * @param tutorialGroupName A valid tutorial group name.
     */
    public TutorialGroup(String tutorialGroupName) {
        requireNonNull(tutorialGroupName);
        assert tutorialGroupName == tutorialGroupName.toUpperCase() : "tutorial group should be in uppercase";
        checkArgument(isValidTutorialGroupName(tutorialGroupName), MESSAGE_CONSTRAINTS);
        this.tutorialGroupName = tutorialGroupName;
    }

    /**
     * Returns true if a given string is a valid tutorial group name.
     */
    public static boolean isValidTutorialGroupName(String test) {
        if (test.length() > 100 || !test.contains(" ")) {
            return false;
        }
        String[] tutorialGroupSplitByOneWhitespace = test.split(" ", 2);
        String tutorialName = tutorialGroupSplitByOneWhitespace[1];
        for (int i = 1; i <= 10; i++) {
            if (tutorialName.contains(String.format("%d", i))) {
                break;
            }
            if (i == 10) {
                return false;
            }
        }
        String lastCharacterOfTutorialName = tutorialName.substring(tutorialName.length() - 1);
        String firstCharacterOfTutorialName = tutorialName.substring(0, 1);
        if (!lastCharacterOfTutorialName.matches("[\\w]")
            || !firstCharacterOfTutorialName.matches("[\\w]")) {
            //ensures tutorial name does not start and end with hyphens
            return false;
        }
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * {@code TutorialGroup}s are equal if they have the same tutorial group name <strong>ignoring cases.</strong>
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TutorialGroup // instanceof handles nulls
                && tutorialGroupName.equalsIgnoreCase(((TutorialGroup) other).tutorialGroupName)); // state check
    }

    @Override
    public int hashCode() {
        return tutorialGroupName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + tutorialGroupName + ']';
    }

}
