package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Skill in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSkillName(String)}
 */
public class Skill {

    public static final String NAME_CONSTRAINTS = "Skill names should be alphanumeric";
    public static final String NAME_VALIDATION_REGEX = "\\p{Alnum}+";
    public static final String PROFICIENCY_CONSTRAINTS_RANGE = "Skill proficiency should be within range of 1-100";
    public static final String PROFICIENCY_CONSTRAINTS_INTEGER = "Skill proficiency must be an integer";
    public static final String PROFICIENCY_VALIDATION_BETWEEN_0_TO_100 = "^[0-9]$|^[1-9][0-9]$|^(100)$";
    public static final String PROFICIENCY_VALIDATION_ONLY_INTEGERS = "^[0-9]+$";
    public static final String SKILL_INPUT_CONSTRAINTS = "Skill input should be: Skill Name_Skill proficiency. eg: "
            + "Java_50";

    public final String skillName;
    public final int skillProficiency;

    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName A valid skill name.
     * @param skillProficiency A valid skill proficiency.
     */
    public Skill(String skillName, int skillProficiency) {
        requireNonNull(skillName);
        checkArgument(isValidSkillName(skillName), NAME_CONSTRAINTS);
        checkArgument(isValidSkillProficiencyRange(skillProficiency), PROFICIENCY_CONSTRAINTS_RANGE);
        this.skillName = skillName;
        this.skillProficiency = skillProficiency;
    }

    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName A valid skill name.
     */
    public Skill(String skillName) {
        requireNonNull(skillName);
        checkArgument(isValidSkillName(skillName), NAME_CONSTRAINTS);
        this.skillName = skillName;
        this.skillProficiency = 0;
    }

    /**
     * Returns true if a given string is a valid skill name.
     */
    public static boolean isValidSkillName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given int is a valid skill proficiency level.
     */
    public static boolean isValidSkillProficiencyRange(int test) {
        String testInt = String.valueOf(test);
        return testInt.matches(PROFICIENCY_VALIDATION_BETWEEN_0_TO_100);
    }

    /**
     * Returns true if given String contains only integers
     */
    public static boolean isValidSkillProficiencyInteger(String test) {
        return test.matches(PROFICIENCY_VALIDATION_ONLY_INTEGERS);
    }

    public boolean isSameSkill(Skill skill) {
        return this.skillName.equalsIgnoreCase(skill.skillName);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Skill // instanceof handles nulls
                && skillName.equals(((Skill) other).skillName)
                && skillProficiency == ((Skill) other).skillProficiency); // state check
    }

    @Override
    public int hashCode() {
        String combined = String.format("%s_%d", skillName, skillProficiency);
        return combined.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return String.format("[%s, %d]", skillName, skillProficiency);
    }

}
