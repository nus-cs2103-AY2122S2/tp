package seedu.address.model.tag;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.person.Person;

public class TagContainsKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        System.out.println("predicate! " + person.getTags());
        System.out.println("keyword! " + keywords);
        return keywords.stream()
                .anyMatch(keyword -> processTagSet(person, keyword));
    }

    private boolean processTagSet(Person person, String keyword) {
        requireNonNull(person);
        requireNonNull(keyword);
        boolean isMatch = false;
        Set<Tag> tags = person.getTags();
        for (Tag tag : tags) {
            if (keyword.equals(tag.tagName)) {
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TagContainsKeywordsPredicate) other).keywords)); // state check
    }
}
