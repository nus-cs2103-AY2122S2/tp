package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTasks.getTypicalTagList;
import static seedu.address.testutil.TypicalTasks.getTypicalTaskList;

import java.util.Collections;

import org.junit.jupiter.api.Test;


public class TagListTest {

    private TagList tagList = new TagList(new TaskList());

    @Test
    public void constructor() {
        assertEquals(Collections.emptySet(), tagList.getTagList());
    }

    @Test
    public void resetTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> tagList.resetTags(null));
    }

    @Test
    public void resetTags_withValidReadOnlyTaskList_replacesTags() {
        TaskList newData = getTypicalTaskList();
        tagList.resetTags(newData);
        assertEquals(getTypicalTagList(), tagList);
    }

}
