package seedu.contax.testutil;

import seedu.contax.model.tag.Tag;

public class TypicalTags {
    public static final Tag CLIENTS = new TagBuilder().build();
    public static final Tag FAMILY = new TagBuilder().withName("family").build();
    public static final Tag COLLEAGUES = new TagBuilder().withName("colleagues").build();
    public static final Tag NEIGHBOURS = new TagBuilder().withName("neighbours").build();
}
