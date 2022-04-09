package seedu.unite.testutil;

import seedu.unite.model.tag.Tag;

public class TypicalTags {
    public static final Tag FRIEND = new TagBuilder().withTagName("friends").build();
    public static final Tag OWEMONEY = new TagBuilder().withTagName("owesMoney").build();
    public static final Tag NONEXIST = new TagBuilder().withTagName("nonExisted").build();
}
