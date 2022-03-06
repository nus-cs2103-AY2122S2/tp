package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.model.tag.Skill;

/**
 * Tests that a {@code Person}'s {@code Skill} matches any of the keywords given.
 */
public class PersonContainsSkillPredicate implements Predicate<Person> {
    private final Skill skill;

    public PersonContainsSkillPredicate(Skill skill) {
        this.skill = skill;
    }

    @Override
    public boolean test(Person person) {
        return person.hasSkill(this.skill);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsSkillPredicate // instanceof handles nulls
                && skill.equals(((PersonContainsSkillPredicate) other).skill)); // state check
    }

}
