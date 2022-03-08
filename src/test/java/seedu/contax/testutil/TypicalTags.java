package seedu.contax.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.contax.model.AddressBook;
import seedu.contax.model.tag.Tag;

public class TypicalTags {
    public static final Tag CLIENTS = new TagBuilder().build();
    public static final Tag FAMILY = new TagBuilder().withName("family").build();
    public static final Tag COLLEAGUES = new TagBuilder().withName("colleagues").build();
    public static final Tag NEIGHBOURS = new TagBuilder().withName("neighbours").build();

    public static AddressBook getTagOnlyAddressBook() {
        AddressBook ab = new AddressBook();
        for (Tag tag : getTypicalTags()) {
            ab.addTag(tag);
        }

        return ab;
    }

    public static List<Tag> getTypicalTags() {
        return new ArrayList<>(Arrays.asList(CLIENTS, FAMILY, COLLEAGUES, NEIGHBOURS));
    }
}
