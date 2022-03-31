package seedu.address.testutil;

import java.util.HashSet;

import seedu.address.model.person.FriendFilterPredicate;
import seedu.address.model.person.FriendName;
import seedu.address.model.person.LogName;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building FriendFilterPredicate objects.
 */
public class FriendFilterPredicateBuilder {

    private HashSet<FriendName> friendNames;
    private HashSet<LogName> logNames;
    private HashSet<Tag> tags;

    /**
     * Creates a {@code FriendFilterPredicateBuilder} with the default details.
     */
    public FriendFilterPredicateBuilder() {
        this.friendNames = new HashSet<FriendName>();
        this.logNames = new HashSet<LogName>();
        this.tags = new HashSet<Tag>();
    }

    /**
     * Adds a name keyword to the {@code FriendFilterPredicate} that we are building.
     */
    public FriendFilterPredicateBuilder withNameSubstring(String nameSubstring) {
        friendNames.add(new FriendName(nameSubstring));
        return this;
    }

    /**
     * Adds a log title keyword to the {@code FriendFilterPredicate} that we are building.
     */
    public FriendFilterPredicateBuilder withLogTitleSubstring(String logTitleSubstring) {
        logNames.add(new LogName(logTitleSubstring));
        return this;
    }

    /**
     * Adds a tag keyword to the {@code FriendFilterPredicate} that we are building.
     */
    public FriendFilterPredicateBuilder withTagSubstring(String tagSubstring) {
        tags.add(new Tag(tagSubstring));
        return this;
    }

    /**
     * Builds and returns the FriendFilterPredicate with the data in the builder.
     */
    public FriendFilterPredicate build() {
        return new FriendFilterPredicate(friendNames, logNames, tags);
    }

}
