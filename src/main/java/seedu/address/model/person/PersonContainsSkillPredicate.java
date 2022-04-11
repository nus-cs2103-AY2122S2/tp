package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.team.SkillSet;

/**
 * Tests that a {@code Person}'s {@code Skill} matches any of the keywords given.
 */
public class PersonContainsSkillPredicate implements Predicate<Person> {
    private final SkillSet skillSet;

    /**
     *
     * @param skillSet
     */
    public PersonContainsSkillPredicate(SkillSet skillSet) {
        this.skillSet = skillSet;
    }

    @Override
    public boolean test(Person person) {
        return person.getSkillSet().hasSkills(this.skillSet);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsSkillPredicate // instanceof handles nulls
                && skillSet.hasSameSkillSet(((PersonContainsSkillPredicate) other).skillSet)); // state check
    }

}
