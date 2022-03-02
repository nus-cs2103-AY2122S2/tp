
package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Skill;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class PersonContainsSkillPredicate implements Predicate<Person> {
    private final List<Skill> skills;
    private final List<String> keywords = new ArrayList<>();

    private final Skill skill;

    public PersonContainsSkillPredicate(List<Skill> skills) {
        this.skills = skills;
        this.skill = null;
    }

    public PersonContainsSkillPredicate(Skill skill) {
        this.skill = skill;
        this.skills = null;
    }

    @Override
    public boolean test(Person person) {
        return person.hasSkill(this.skill);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((PersonContainsSkillPredicate) other).keywords)); // state check
    }

}
