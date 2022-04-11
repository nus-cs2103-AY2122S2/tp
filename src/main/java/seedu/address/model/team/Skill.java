package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Represents a Skill in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSkillName(String)}
 */
public class Skill {

    public static final int MAX_LENGTH_OF_SKILL_NAME = 10;
    public static final String NAME_CONSTRAINTS =
        "Skill names should be alphanumeric word(s) that can contain special characters #, +, and -. \nMaximum length "
                + "of a skill name is 10 characters.";
    //The regex below checks for skill name constraints described in NAME_CONSTRAINTS above.
    public static final String NAME_VALIDATION_REGEX = "[\\w|\\d|\\s#+-]+";
    public static final String PROFICIENCY_CONSTRAINTS_RANGE = "Skill proficiency should be within range of 1-100";
    public static final String PROFICIENCY_CONSTRAINTS_INTEGER = "Skill proficiency must be an integer";
    public static final String PROFICIENCY_VALIDATION_BETWEEN_0_TO_100 = "^[1-9]$|^[1-9][0-9]$|^(100)$";
    public static final String PROFICIENCY_VALIDATION_ONLY_INTEGERS = "^[0-9]+$";
    public static final String SKILL_INPUT_CONSTRAINTS = "Skill input should be: Skill Name_Skill proficiency. eg: "
        + "Java_50";
    public static final String MESSAGE_USAGE = "Skill stores a Skill Name and Skill proficiency."
            + "Skill proficiency is indicated by different hues of green with brighter "
            + "green indicating a higher proficiency.\n"
            + "Usage: <Command> s/<Skill Name>_<Skill Proficiency> \nExample: edit 1 s/Java_50\n";
    private static Logger logger = Logger.getLogger("Skill");

    public final String skillName;
    public final int skillProficiency;


    /**
     * Constructs a {@code Skill}.
     *
     * @param skillName        A valid skill name.
     * @param skillProficiency A valid skill proficiency.
     */
    public Skill(String skillName, int skillProficiency) {
        requireNonNull(skillName);
        checkArgument(isValidSkillName(skillName), NAME_CONSTRAINTS);
        checkArgument(isValidSkillProficiencyRange(skillProficiency), PROFICIENCY_CONSTRAINTS_RANGE);
        this.skillName = skillName;
        this.skillProficiency = skillProficiency;
        assert skillProficiency <= 100 && skillProficiency > 0 : "Skill proficiency should be between 1 to 100";
        assert skillName != "" : "Skill name should not be empty";
        logger.log(Level.INFO, String.format("Created %s with proficiency of %d", skillName, skillProficiency));
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
        this.skillProficiency = 1;
        assert skillName != "" : "Skill name should not be empty";
        logger.log(Level.INFO, String.format("Created %s with proficiency of 0", skillName));
    }

    /**
     * Returns true if a given string is a valid skill name.
     */
    public static boolean isValidSkillName(String test) {
        return test.matches(NAME_VALIDATION_REGEX) && test.length() <= MAX_LENGTH_OF_SKILL_NAME;
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
