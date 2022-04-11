package manageezpz.storage;

import static manageezpz.testutil.Assert.assertThrows;
import static manageezpz.testutil.TypicalPersons.ALICE;
import static manageezpz.testutil.TypicalPersons.BOB;
import static manageezpz.testutil.TypicalPersons.CARL;
import static manageezpz.testutil.TypicalTasks.GET_DRINK;
import static manageezpz.testutil.TypicalTasks.HOUSE_VISTING;
import static manageezpz.testutil.TypicalTasks.WEEKLY_QUIZ;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import manageezpz.commons.exceptions.IllegalValueException;
import manageezpz.model.person.Person;
import manageezpz.model.task.Todo;


public class JsonAdaptedTaskTest {

    private static final Person VALID_ASSIGNEE_ALICE = ALICE;
    private static final Person VALID_ASSIGNEE_BOB = BOB;
    private static final Person VALID_ASSIGNEE_CARL = CARL;
    private static final List<Person> PERSON_LIST = Arrays.asList(VALID_ASSIGNEE_ALICE,
            VALID_ASSIGNEE_BOB, VALID_ASSIGNEE_CARL);
    private static final ObservableList<Person> VALID_PERSON_LIST = FXCollections.observableArrayList(PERSON_LIST);

    private static final String VALID_STATUS = " ";
    private static final String VALID_TAG = "";
    private static final String VALID_PRIORITY = "NONE";

    private static final String VALID_TYPE_TODO = "todo";
    private static final String VALID_TODO_DESCRIPTION = "Weekly Quiz";

    private static final String VALID_TYPE_DEADLINE = "deadline";
    private static final String VALID_DEADLINE_DESCRIPTION = "Get Drink";
    private static final String VALID_DEADLINE_DATE = "2022-04-11";
    private static final String VALID_DEADLINE_TIME = "1800";

    private static final String VALID_TYPE_EVENT = "event";
    private static final String VALID_EVENT_DESCRIPTION = "House Visiting";
    private static final String VALID_EVENT_DATE = "2022-09-15";
    private static final String VALID_EVENT_STARTTIME = "1800";
    private static final String VALID_EVENT_ENDTIME = "2000";

    private static final String INVALID_DESCRIPTION = "";
    private static final String INVALID_TYPE = "IMPORTANT";
    private static final String INVALID_DATE = "2022-02-30";
    private static final String INVALID_STATUS = "done";
    private static final String INVALID_PRIORITY = "IMPORTANT";
    private static final String INVALID_DEADLINETIME = "2420";
    private static final String INVALID_EVENTSTARTTIME = "2420";
    private static final String INVALID_EVENTENDTIME = "2420";

    @Test
    public void toModelType_validTodoTaskDetails_constructor() throws Exception {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(WEEKLY_QUIZ);
        assertEquals(WEEKLY_QUIZ, todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_validDeadlineTaskDetails_constructor() throws Exception {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(GET_DRINK);
        assertEquals(GET_DRINK, todoTask.toModelType(VALID_PERSON_LIST));
    }


    @Test
    public void toModelType_validEventTaskDetails_constructor() throws Exception {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(HOUSE_VISTING);
        assertEquals(HOUSE_VISTING, todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_validMarkedTodoTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, "X",
                VALID_TODO_DESCRIPTION, null, null, null,
                null, VALID_TAG, VALID_PRIORITY);
        Todo quizTaskMarked = new Todo(WEEKLY_QUIZ);
        quizTaskMarked.setTaskDone();
        assertEquals(quizTaskMarked, todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_validTodoTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                VALID_TODO_DESCRIPTION, null, null, null,
                null, VALID_TAG, VALID_PRIORITY);
        assertEquals(WEEKLY_QUIZ, todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_validDeadlineTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask deadlineTask = new JsonAdaptedTask(VALID_TYPE_DEADLINE, VALID_STATUS,
                VALID_DEADLINE_DESCRIPTION, VALID_DEADLINE_DATE,
                VALID_DEADLINE_TIME, null,
                null, VALID_TAG, VALID_PRIORITY);
        assertEquals(GET_DRINK, deadlineTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_validEventTaskDetails_returnsTask() throws Exception {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT,
                VALID_STATUS, VALID_EVENT_DESCRIPTION, VALID_EVENT_DATE,
                null, VALID_EVENT_STARTTIME, VALID_EVENT_ENDTIME,
                VALID_TAG, VALID_PRIORITY);
        assertEquals(HOUSE_VISTING, eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidDescription_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                INVALID_DESCRIPTION, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_DESCRIPTION_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullDescription_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                null, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_DESCRIPTION_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidType_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(INVALID_TYPE, VALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INCORRECT_TYPE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullType_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(null, VALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_TYPE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, null,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_STATUS_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, INVALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_STATUS_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullTag_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", null, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_TAG_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, null);
        String expectedMessage = JsonAdaptedTask.NULL_PRIORITY_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedTask todoTask = new JsonAdaptedTask(VALID_TYPE_TODO, VALID_STATUS,
                VALID_TODO_DESCRIPTION, "", "", "",
                "", VALID_TAG, INVALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_PRIORITY_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> todoTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullDeadlineDate_throwsIllegalValueException() {
        JsonAdaptedTask deadlineTask = new JsonAdaptedTask(VALID_TYPE_DEADLINE, VALID_STATUS,
                VALID_DEADLINE_DESCRIPTION, null, VALID_DEADLINE_TIME, null,
                null, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_DEADLINE_DATE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> deadlineTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidDeadlineDate_throwsIllegalValueException() {
        JsonAdaptedTask deadlineTask = new JsonAdaptedTask(VALID_TYPE_DEADLINE, VALID_STATUS,
                VALID_DEADLINE_DESCRIPTION, INVALID_DATE, VALID_DEADLINE_TIME, null,
                null, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_DEADLINE_DATE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> deadlineTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullDeadlineTime_throwsIllegalValueException() {
        JsonAdaptedTask deadlineTask = new JsonAdaptedTask(VALID_TYPE_DEADLINE, VALID_STATUS,
                VALID_DEADLINE_DESCRIPTION, VALID_DEADLINE_DATE, null, null,
                null, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_DEADLINE_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> deadlineTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidDeadlineTime_throwsIllegalValueException() {
        JsonAdaptedTask deadlineTask = new JsonAdaptedTask(VALID_TYPE_DEADLINE, VALID_STATUS,
                VALID_DEADLINE_DESCRIPTION, VALID_DEADLINE_DATE, INVALID_DEADLINETIME, null,
                null, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_DEADLINE_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> deadlineTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullEventDate_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, null, null, VALID_EVENT_STARTTIME,
                VALID_EVENT_ENDTIME, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_EVENT_DATE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidEventDate_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, INVALID_DATE, null, VALID_EVENT_STARTTIME,
                VALID_EVENT_ENDTIME, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_EVENT_DATE_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullEventStartTime_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, VALID_EVENT_DATE, null, null,
                VALID_EVENT_ENDTIME, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_EVENT_START_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidEventStartTime_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, VALID_EVENT_DATE, null, INVALID_EVENTSTARTTIME,
                VALID_EVENT_ENDTIME, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_EVENT_START_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_nullEventEndTime_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, VALID_EVENT_DATE, null, VALID_EVENT_STARTTIME,
                null, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.NULL_EVENT_END_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

    @Test
    public void toModelType_invalidEventEndTime_throwsIllegalValueException() {
        JsonAdaptedTask eventTask = new JsonAdaptedTask(VALID_TYPE_EVENT, VALID_STATUS,
                VALID_EVENT_DESCRIPTION, VALID_EVENT_DATE, null, VALID_EVENT_STARTTIME,
                INVALID_EVENTENDTIME, VALID_TAG, VALID_PRIORITY);
        String expectedMessage = JsonAdaptedTask.INVALID_EVENT_END_TIME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, () -> eventTask.toModelType(VALID_PERSON_LIST));
    }

}
