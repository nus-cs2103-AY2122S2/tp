package seedu.linkedout.model.skill;

import static java.util.Objects.requireNonNull;
import static seedu.linkedout.commons.util.AppUtil.checkArgument;

/**
 * Represents a Skill attribute for a particular applicant
 * Guarantees: immutable; name is valid as declared in {@link #isValidSkillName(String)}
 */
public class Skill {

    public static final String MESSAGE_CONSTRAINTS =
            "Skill should not be completely made up of symbols and be between 1 to 5 words.";
    public static final String VALIDATION_REGEX = "^\\W*(?:\\w+\\b\\W*){1,5}$";

    public final String skillName;

    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName A valid skill name.
     */
    public Skill(String skillName) {
        requireNonNull(skillName);
        checkArgument(isValidSkillName(skillName), MESSAGE_CONSTRAINTS);
        this.skillName = skillName;
    }

    /**
     * Returns true if a given string is a valid skill name.
     */
    public static boolean isValidSkillName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Skill // instanceof handles nulls
                && skillName.equals((((Skill) other).skillName))); // state check
    }

    @Override
    public int hashCode() {
        return skillName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + skillName + ']';
    }

}

