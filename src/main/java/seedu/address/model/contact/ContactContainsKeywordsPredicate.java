package seedu.address.model.contact;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.ViewedNric;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Contact} matches any of the keywords given.
 */
public class ContactContainsKeywordsPredicate implements Predicate<Contact> {
    private final List<String> keywords;

    public ContactContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Contact contact) {
        return StringUtil.containsWordIgnoreCase(contact.getOwnerNric().toString(),
                    ViewedNric.getViewedNric().toString())
                && (keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getAddress().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getEmail().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getName().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(contact.getPhone().toString(), keyword))
                || keywords.stream()
                .anyMatch(keyword -> {
                    for (Tag tag : contact.getTags()) {
                        if (StringUtil.containsWordIgnoreCase(tag.toString(), keyword)) {
                            return true;
                        }
                    }
                    return false;
                }));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ContactContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((ContactContainsKeywordsPredicate) other).keywords)); // state check
    }

}
