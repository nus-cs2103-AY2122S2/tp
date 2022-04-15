package seedu.address.logic.commands.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalTasks.SAMPLE_TASK_ONE;
import static seedu.address.testutil.TypicalTasks.SAMPLE_TASK_THREE;
import static seedu.address.testutil.TypicalTasks.SAMPLE_TASK_TWO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.task.Task;

public class ComparatorFactoryTest {

    /** List used for comparison of comparators in the test */
    private List<Task> list = new ArrayList<>(Arrays.asList(SAMPLE_TASK_ONE, SAMPLE_TASK_TWO, SAMPLE_TASK_THREE));

    @Test
    public void createComparator_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                ComparatorFactory.createComparator(null, null));

        assertThrows(NullPointerException.class, () ->
                ComparatorFactory.createComparator(null, SortOrder.ASCENDING));

        assertThrows(NullPointerException.class, () ->
                ComparatorFactory.createComparator(SortKey.DEADLINE, null));
    }

    @Test
    public void createComparator_validArguments_returnsValidComparator() {

        // Sort Key: Name and Sort Order: Ascending
        List<Task> expectedNameAscendingList = new ArrayList<>(list);
        List<Task> outputNameAscendingList = new ArrayList<>(list);

        outputNameAscendingList.sort(
                Comparator.comparing(Task::getName).thenComparing(Task::getDeadline));
        expectedNameAscendingList.sort(
                ComparatorFactory.createComparator(SortKey.NAME, SortOrder.ASCENDING));
        assertEquals(outputNameAscendingList, expectedNameAscendingList);


        // Sort Key: Deadline and Sort Order: Ascending
        List<Task> expectedDeadlineAscendingList = new ArrayList<>(list);
        List<Task> outputDeadlineAscendingList = new ArrayList<>(list);

        outputDeadlineAscendingList.sort(
                Comparator.comparing(Task::getDeadline).thenComparing(Task::getName));
        expectedDeadlineAscendingList.sort(
                ComparatorFactory.createComparator(SortKey.DEADLINE, SortOrder.ASCENDING));

        assertEquals(outputDeadlineAscendingList, expectedDeadlineAscendingList);

        // Sort Key: Priority and Sort Order: Ascending
        List<Task> expectedPriorityAscendingList = new ArrayList<>(list);
        List<Task> outputPriorityAscendingList = new ArrayList<>(list);

        outputPriorityAscendingList.sort(
                Comparator.comparing(Task::getPriority).thenComparing(Task::getName));
        expectedPriorityAscendingList.sort(
                ComparatorFactory.createComparator(SortKey.PRIORITY, SortOrder.ASCENDING));

        assertEquals(outputPriorityAscendingList, expectedPriorityAscendingList);

        // Sort Key: Priority and Sort Order: Descending
        List<Task> expectedPriorityDescendingList = new ArrayList<>(list);
        List<Task> outputPriorityDescendingList = new ArrayList<>(list);

        outputPriorityDescendingList.sort(
                Comparator.comparing(Task::getPriority).thenComparing(Task::getName).reversed());
        expectedPriorityDescendingList.sort(
                ComparatorFactory.createComparator(SortKey.PRIORITY, SortOrder.DESCENDING));

        assertEquals(outputPriorityDescendingList, expectedPriorityDescendingList);
    }
}
