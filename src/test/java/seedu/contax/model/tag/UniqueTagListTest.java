package seedu.contax.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalTags.CLIENTS;
import static seedu.contax.testutil.TypicalTags.FAMILY;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.contax.model.tag.exceptions.DuplicateTagException;

class UniqueTagListTest {
    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(CLIENTS));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(CLIENTS);
        assertTrue(uniqueTagList.contains(CLIENTS));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(CLIENTS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(CLIENTS));
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_uniqueTagList_replaceOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(FAMILY);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(CLIENTS);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_duplicateTagList_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(CLIENTS, CLIENTS);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }
}
