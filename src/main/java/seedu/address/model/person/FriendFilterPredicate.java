package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Locale;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.model.tag.Tag;


/**
 * Tests that a {@code Person}'s {@code name, tags, logs title} matches any of the keywords given.
 */
public class FriendFilterPredicate implements Predicate<Person> {
    private final Set<FriendName> nameKeywords;
    private final Set<LogName> logTitleKeywords;
    private final Set<Tag> tagKeywords;

    /**
     * Constructs a FriendFilterPredicate based on the given keywords.
     */
    public FriendFilterPredicate(Set<FriendName> nameKeywords, Set<LogName> logTitleKeywords, Set<Tag> tagKeywords) {
        requireAllNonNull(nameKeywords, logTitleKeywords, tagKeywords);
        this.nameKeywords = nameKeywords;
        this.logTitleKeywords = logTitleKeywords;
        this.tagKeywords = tagKeywords;
    }

    @Override
    public boolean test(Person person) {

        //check if (substring) name of a person matches name keyword
        boolean nameMatch = nameKeywords.stream().anyMatch(nameKeyword -> {
            assert (!nameKeyword.equals(" ") && !nameKeyword.equals(""));
            return containsIgnoreCase(person.getName().fullName, nameKeyword.fullName);
        });

        //check if (substring) logs title of a person matches keyword
        boolean logTitleMatch = logTitleKeywords.stream().anyMatch(logTitleKeyword -> {
            return person.getLogs().stream().anyMatch(log ->
                    containsIgnoreCase(log.getTitle().toString(), logTitleKeyword.fullName));
        });

        //check if (substring) tag of a person matches tag keyword
        boolean tagMatch = tagKeywords.stream().anyMatch(tagKeyword -> {
            return person.getTags().stream().anyMatch(tag ->
                    containsIgnoreCase(tag.tagName, tagKeyword.tagName));
        });

        return nameMatch || logTitleMatch || tagMatch;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FriendFilterPredicate // instanceof handles nulls
                && nameKeywords.equals(((FriendFilterPredicate) other).nameKeywords)
                && logTitleKeywords.equals(((FriendFilterPredicate) other).logTitleKeywords)
                && tagKeywords.equals(((FriendFilterPredicate) other).tagKeywords)); // state check
    }

    /**
     * Checks if a string contains a given substring.
     */
    private boolean containsIgnoreCase(String str, String substring) {
        return str.toLowerCase(Locale.ROOT).contains(substring.toLowerCase(Locale.ROOT));
    }


}
