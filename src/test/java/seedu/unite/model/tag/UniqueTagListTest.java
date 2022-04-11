package seedu.unite.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.unite.testutil.Assert.assertThrows;
import static seedu.unite.testutil.TypicalTags.FRIEND;
import static seedu.unite.testutil.TypicalTags.OWEMONEY;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.unite.model.tag.exceptions.DuplicateTagException;
import seedu.unite.model.tag.exceptions.TagNotFoundException;
import seedu.unite.testutil.TagBuilder;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {
        assertFalse(uniqueTagList.contains(FRIEND));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(FRIEND);
        assertTrue(uniqueTagList.contains(FRIEND));
    }

    @Test
    public void contains_tagWithSameIdentityFieldsInList_returnsTrue() {
        uniqueTagList.add(FRIEND);
        Tag editedFriend = new TagBuilder().withTagName(FRIEND.getTagName()).build();
        assertTrue(uniqueTagList.contains(editedFriend));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(FRIEND);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(FRIEND));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, FRIEND));
    }

    @Test
    public void setTag_nullEditedTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(FRIEND, null));
    }

    @Test
    public void setTag_targetTagNotInList_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.setTag(FRIEND, FRIEND));
    }

    @Test
    public void setTag_editedTagIsSameTag_success() {
        uniqueTagList.add(FRIEND);
        uniqueTagList.setTag(FRIEND, FRIEND);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(FRIEND);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasSameIdentity_success() {
        uniqueTagList.add(FRIEND);
        Tag editedAlice = new TagBuilder(FRIEND).withTagName(FRIEND.getTagName())
                .build();
        uniqueTagList.setTag(FRIEND, editedAlice);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(editedAlice);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(FRIEND);
        uniqueTagList.setTag(FRIEND, OWEMONEY);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(OWEMONEY);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(FRIEND);
        uniqueTagList.add(OWEMONEY);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(FRIEND, OWEMONEY));
    }

    @Test
    public void remove_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.remove(null));
    }

    @Test
    public void remove_tagDoesNotExist_throwsTagNotFoundException() {
        assertThrows(TagNotFoundException.class, () -> uniqueTagList.remove(FRIEND));
    }

    @Test
    public void remove_existingTag_removesTag() {
        uniqueTagList.add(FRIEND);
        uniqueTagList.remove(FRIEND);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(FRIEND);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(OWEMONEY);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(FRIEND);
        List<Tag> tagList = Collections.singletonList(OWEMONEY);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(OWEMONEY);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(FRIEND, FRIEND);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () ->
                uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
}
