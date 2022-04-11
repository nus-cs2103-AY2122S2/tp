package seedu.address.testutil;

import seedu.address.model.tag.Tag;

public class TypicalTag {
    public static final Tag CEO = new TagBuilder().build();
    public static final Tag EXPERIENCE_SR = new TagBuilder().withText("10 year SR experience").build();
    public static final Tag JUNIOR_DEV = new TagBuilder().withText("IPO").build();
}
