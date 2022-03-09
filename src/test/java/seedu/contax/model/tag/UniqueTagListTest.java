package seedu.contax.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.contax.testutil.Assert.assertThrows;
import static seedu.contax.testutil.TypicalPersons.FRIENDS;
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

    @Test
    public void equals() {
        UniqueTagList tagList = new UniqueTagList();
        UniqueTagList list2 = new UniqueTagList();
        list2.add(FRIENDS);

        UniqueTagList list3 = new UniqueTagList();
        list3.add(FRIENDS);

        // Same tag list
        assertTrue(tagList.equals(tagList));
        assertTrue(tagList.equals(new UniqueTagList()));
        assertTrue(list2.equals(list3));

        // Null checking
        assertFalse(tagList.equals(null));
        assertFalse(tagList.equals(list2));
        assertFalse(tagList.equals(0));
    }

    @Test
    public void hashCodeTest() {
        UniqueTagList refList = new UniqueTagList();
        UniqueTagList list2 = new UniqueTagList();
        list2.add(FRIENDS);

        UniqueTagList list3 = new UniqueTagList();
        list3.add(FRIENDS);

        assertEquals(refList.hashCode(), refList.hashCode());
        assertEquals(refList.hashCode(), new UniqueTagList().hashCode());
        assertEquals(list2.hashCode(), list3.hashCode());

        assertNotEquals(refList.hashCode(), list2.hashCode());
    }
}
