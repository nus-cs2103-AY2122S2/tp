package seedu.address.model.person;

import java.util.Comparator;

import seedu.address.model.tag.Skill;

/**
 * Comparator to compare {@code Person}'s by the proficiency level of a given {@code Skill}.
 */
public class PersonBySkillProficiencyComparator implements Comparator<Person> {
    private final Skill skill;

    /**
     * Constructs a {@code PersonBySkillProficiencyComparator}.
     *
     * @param skill Skill to compare person by.
     */
    public PersonBySkillProficiencyComparator(Skill skill) {
        this.skill = skill;
    }

    @Override
    public int compare(Person person1, Person person2) {
        return Integer.compare(person1.getSkillProficiency(skill), person2.getSkillProficiency(skill));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonBySkillProficiencyComparator // instanceof handles nulls
                && skill.isSameSkill(((PersonBySkillProficiencyComparator) other).skill)); // check if same skill name
    }

}
