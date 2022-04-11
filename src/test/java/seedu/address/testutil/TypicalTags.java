package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Test} objects to be used in tests.
 */
public class TypicalTags {
    public static final String VALID_TAGNAME_FRIENDS = "friends";
    public static final String VALID_TAGNAME_COLLEAGUES = "colleagues";
    public static final String VALID_TAGNAME_NEIGHBOURS = "neighbours";
    public static final String VALID_TAGNAME_TEST = "test";
    public static final String VALID_TAGNAME_OWESMONEY = "owesMoney";
    public static final Tag VALID_TAG_FRIENDS = new Tag(VALID_TAGNAME_FRIENDS);
    public static final Tag VALID_TAG_COLLEAGUES = new Tag(VALID_TAGNAME_COLLEAGUES);
    public static final Tag VALID_TAG_NEIGHBOURS = new Tag(VALID_TAGNAME_NEIGHBOURS);
    public static final Tag VALID_TAG_TEST = new Tag(VALID_TAGNAME_TEST);
    public static final Tag VALID_TAG_OWESMONEY = new Tag(VALID_TAGNAME_OWESMONEY);

    /**
     * Returns an {@code AddressBook} with all the typical tags.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }
        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(VALID_TAG_FRIENDS, VALID_TAG_OWESMONEY));
    }
}
