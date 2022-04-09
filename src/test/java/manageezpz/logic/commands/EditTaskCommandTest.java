package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TIME_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_INVALID_TIME_RANGE;
import static manageezpz.logic.commands.CommandTestUtil.DEADLINE_TASK;
import static manageezpz.logic.commands.CommandTestUtil.EVENT_TASK;
import static manageezpz.logic.commands.CommandTestUtil.TODO_TASK;
import static manageezpz.logic.commands.CommandTestUtil.VALID_DATE;
import static manageezpz.logic.commands.CommandTestUtil.VALID_TASK_DESCRIPTION;
import static manageezpz.logic.commands.EditTaskCommand.MESSAGE_EDIT_TASK_NO_EMPTY_VALUES;
import static manageezpz.logic.commands.EditTaskCommand.MESSAGE_EDIT_TODO_TASK_NO_DATE_AND_TIME_VALUES;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.task.Date;
import manageezpz.model.task.Deadline;
import manageezpz.model.task.Description;
import manageezpz.model.task.Event;
import manageezpz.model.task.Time;
import manageezpz.model.task.Todo;

public class EditTaskCommandTest {
    private static final String UPDATED_DESC_STRING = "i have been updated";

    private static final String UPDATED_TIME_STRING = "2200";
    private static final String UPDATED_START_AND_END_TIME_STRING = "2000 2100";
    private static final String UPDATED_START_TIME_STRING = "2000";
    private static final String UPDATED_END_TIME_STRING = "2100";

    private static final String UPDATED_DATE_STRING = "2022-02-02";

    private static final HashMap<String, Boolean> defaultPrefixStatusHash = new HashMap<>();

    static {
        defaultPrefixStatusHash.put("description", true);
        defaultPrefixStatusHash.put("date", true);
        defaultPrefixStatusHash.put("datetime", true);
    }

    private final EditTaskCommand editTaskCommand =
            new EditTaskCommand(Index.fromZeroBased(1), UPDATED_DESC_STRING, UPDATED_DATE_STRING,
                    UPDATED_START_AND_END_TIME_STRING, defaultPrefixStatusHash);

    private void initDefaultPrefixStatusHash() {
        defaultPrefixStatusHash.put("description", true);
        defaultPrefixStatusHash.put("date", true);
        defaultPrefixStatusHash.put("datetime", true);
    }

    @Test
    public void updateTodo_updateDescription_success() throws ParseException {
        initDefaultPrefixStatusHash();
        Description updatedDesc = new Description(UPDATED_DESC_STRING);
        defaultPrefixStatusHash.replace("date", false);
        defaultPrefixStatusHash.replace("datetime", false);
        Todo expectedTodo = new Todo(updatedDesc);
        Todo actualTodo = (Todo) editTaskCommand.updateTodo(TODO_TASK, UPDATED_DESC_STRING);
        assertEquals(expectedTodo.getDescription(),
                actualTodo.getDescription());
    }

    @Test
    public void updateDeadline_updateDate_success() throws ParseException {
        initDefaultPrefixStatusHash();
        Deadline currentTask = DEADLINE_TASK;
        Date updatedDate = new Date(UPDATED_DATE_STRING);
        defaultPrefixStatusHash.replace("description", false);
        defaultPrefixStatusHash.replace("datetime", false);
        Deadline expectedDeadline = new Deadline(currentTask.getDescription(), updatedDate, currentTask.getTime());
        Deadline actualDeadline = (Deadline) editTaskCommand.updateDeadline(currentTask, "",
                UPDATED_DATE_STRING, "");
        assertEquals(expectedDeadline.getDateTime(), actualDeadline.getDateTime());
    }

    @Test
    public void updateDeadline_updateDescriptionAndTime_success() throws ParseException {
        initDefaultPrefixStatusHash();
        Deadline currentTask = DEADLINE_TASK;
        Description updatedDesc = new Description(UPDATED_DESC_STRING);
        Time updatedTime = new Time(UPDATED_TIME_STRING);
        defaultPrefixStatusHash.replace("date", false);
        Deadline expectedDeadline = new Deadline(updatedDesc, currentTask.getDate(),
                updatedTime);
        Deadline actualDeadline = (Deadline) editTaskCommand.updateDeadline(currentTask, UPDATED_DESC_STRING,
                "", UPDATED_TIME_STRING);
        assertEquals(expectedDeadline.getDateTime(), actualDeadline.getDateTime());
        assertEquals(expectedDeadline.getDescription(), actualDeadline.getDescription());
    }

    @Test
    public void updateEvent_updateTime_success() throws ParseException {
        initDefaultPrefixStatusHash();
        Event currentTask = EVENT_TASK;
        defaultPrefixStatusHash.replace("description", false);
        defaultPrefixStatusHash.replace("date", false);
        Event expectedEvent = new Event(currentTask.getDescription(), currentTask.getDate(),
                new Time(UPDATED_START_TIME_STRING), new Time(UPDATED_END_TIME_STRING));
        Event actualEvent = (Event) editTaskCommand.updateEvent(currentTask, "", "",
                UPDATED_START_AND_END_TIME_STRING);
        assertEquals(expectedEvent.getDateTime(), actualEvent.getDateTime());
    }

    @Test
    public void updateEvent_updateDescriptionAndDate_success() throws ParseException {
        initDefaultPrefixStatusHash();
        Event currentTask = EVENT_TASK;
        Description updatedDesc = new Description(UPDATED_DESC_STRING);
        Date updatedDate = new Date(UPDATED_DATE_STRING);
        defaultPrefixStatusHash.replace("datetime", false);
        Event expectedEvent = new Event(updatedDesc, updatedDate,
                currentTask.getStartTime(), currentTask.getEndTime());
        Event actualEvent = (Event) editTaskCommand.updateEvent(currentTask, UPDATED_DESC_STRING, UPDATED_DATE_STRING,
                "");
        assertEquals(expectedEvent.getDateTime(), actualEvent.getDateTime());
        assertEquals(expectedEvent.getDescription(), actualEvent.getDescription());
    }

    @Test
    public void updateEvent_invalidTimeRange_failure() {
        initDefaultPrefixStatusHash();
        String updatedTimeString = "2200 2100";
        assertThrows(ParseException.class, () -> {
            editTaskCommand.updateEvent(EVENT_TASK, VALID_TASK_DESCRIPTION, VALID_DATE,
                    updatedTimeString);
        }, MESSAGE_INVALID_TIME_RANGE);
    }

    @Test
    public void updateEvent_invalidTimeFormat_failure() {
        initDefaultPrefixStatusHash();
        String updatedTimeString = "2200";
        assertThrows(ParseException.class, () -> {
            editTaskCommand.updateEvent(EVENT_TASK, VALID_TASK_DESCRIPTION, VALID_DATE,
                    updatedTimeString);
        }, MESSAGE_INVALID_TIME_FORMAT);
    }

    @Test
    public void updateDeadline_updateDescriptionButDatePrefixHasNoValue_failure() {
        initDefaultPrefixStatusHash();
        String updatedTimeString = "2200";
        assertThrows(ParseException.class, () -> {
            editTaskCommand.updateDeadline(DEADLINE_TASK, UPDATED_DESC_STRING, "",
                    updatedTimeString);
        }, MESSAGE_EDIT_TASK_NO_EMPTY_VALUES);
    }

    @Test
    public void updateTodo_updateDescriptionButDatePrefixExists_failure() {
        initDefaultPrefixStatusHash();
        assertThrows(ParseException.class, () -> {
            editTaskCommand.updateTodo(TODO_TASK, UPDATED_DESC_STRING);
        }, MESSAGE_EDIT_TODO_TASK_NO_DATE_AND_TIME_VALUES);
    }
}