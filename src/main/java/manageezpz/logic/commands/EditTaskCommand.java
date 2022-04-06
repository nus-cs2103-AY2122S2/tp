package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.commons.core.Messages.*;
import static manageezpz.commons.util.CollectionUtil.requireAllNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_AT_DATETIME;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DATE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.util.HashMap;
import java.util.List;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.ParserUtil;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.task.*;
import manageezpz.model.task.exceptions.DuplicateTaskException;

/**
 * Edits the details of an existing task in the address book.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "editTask";

    public static final String EXAMPLE_ONE = COMMAND_WORD + " 1 " + PREFIX_DESCRIPTION + "Eat bananas";

    public static final String EXAMPLE_TWO = COMMAND_WORD + " 2 " + PREFIX_DESCRIPTION + "Eat Apple "
            + PREFIX_DATE + "2022-09-05 " + PREFIX_AT_DATETIME + "1800";

    public static final String EXAMPLE_THREE = COMMAND_WORD + " 3 " + PREFIX_DESCRIPTION + "Midterm Exam "
            + PREFIX_DATE + "2022-04-06 " + PREFIX_AT_DATETIME + "1800 2000";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list.\n"
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must exist in the Address Book) "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_DATE + "DATE "
            + PREFIX_AT_DATETIME + "TIME\n"
            + "At least one of " + PREFIX_DESCRIPTION + " " + PREFIX_DATE
            + " " + PREFIX_AT_DATETIME + " must have a value.\n"
            + "For an event task, a start time and an end time "
            + "separated with an empty space must be provided "
            + "instead of a single time value.\n"
            + "Example 1: " + EXAMPLE_ONE + "\n"
            + "Example 2: " + EXAMPLE_TWO + "\n"
            + "Example 3: " + EXAMPLE_THREE;

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Update Task success: %1$s";

    private final Index index;
    private final String desc;
    private final String date;
    private final String time;
    private final HashMap<String, Boolean> prefixStatusHash;

    /**
     * Constructor to initialize an instance of EditTaskCommand class
     * with the given index and updated description, date and time
     * information.
     *
     * @param index Index of the Task to edit information
     * @param desc New description of the Task
     * @param date New date of the Task
     * @param time New time of the Task
     */
    public EditTaskCommand(Index index, String desc, String date, String time, HashMap<String, Boolean> prefixStatusHash) {
        requireAllNonNull(index, desc, date, time);
        this.index = index;
        this.desc = desc;
        this.date = date;
        this.time = time;
        this.prefixStatusHash = prefixStatusHash;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(String.format(MESSAGE_INVALID_TASK_DISPLAYED_INDEX, MESSAGE_USAGE));
        }

        Task currentTask = lastShownList.get(index.getZeroBased());
        Task updatedTask;

        try {
            if (currentTask.getType().equalsIgnoreCase("todo")) {
                updatedTask = updateTodo((Todo) currentTask, this.desc);
            } else if (currentTask.getType().equalsIgnoreCase("deadline")) {
                updatedTask = updateDeadline((Deadline) currentTask, this.desc, this.date, this.time);
            } else if (currentTask.getType().equalsIgnoreCase("event")) {
                updatedTask = updateEvent((Event) currentTask, this.desc, this.date, this.time);
            } else {
                // Should not reach this as there are only three types of tasks
                throw new CommandException(MESSAGE_INVALID_TASK_TYPE);
            }

            model.setTask(currentTask, updatedTask);
            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, updatedTask));
        } catch (ParseException pe) {
            throw new CommandException(pe.getMessage() + "\n\n" + EditTaskCommand.MESSAGE_USAGE, pe);
        } catch (DuplicateTaskException de) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TASK, this.desc) + MESSAGE_USAGE);
        }
    }

    boolean ensureFormatCompliance(HashMap<String, Boolean> prefixStatusHash,
                                   String desc, String date, String time) {
        boolean isFormatOkay = true;
        HashMap<String, String> inputStatusHash = new HashMap<>();
        inputStatusHash.put("description", desc);
        inputStatusHash.put("date", date);
        inputStatusHash.put("datetime", time);

        String[] statusArr = {"description", "date", "datetime"};

        for (String s : statusArr) {
            boolean status = prefixStatusHash.get(s);
            String input = inputStatusHash.get(s).trim();
            if (status && input.isEmpty()) {
                isFormatOkay = false;
                break;
            }
        }
        return isFormatOkay;
    }

    private Task updateTodo(Todo currentTask, String desc) throws ParseException {
        Todo updatedToDoTask = new Todo(currentTask);
        if (prefixStatusHash.get("date") || prefixStatusHash.get("datetime") ) {
            throw new ParseException(MESSAGE_EDIT_TODO_TASK_NO_DATE_AND_TIME_VALUES);
        }

        if (!desc.isEmpty()) {
            Description newDesc = ParserUtil.parseDescription(desc);
            updatedToDoTask.setDescription(newDesc);
        } else {
            throw new ParseException(MESSAGE_EMPTY_DESCRIPTION);
        }

        return updatedToDoTask;
    }

    private Task updateDeadline(Deadline currentTask, String desc, String date, String time) throws ParseException {
        Deadline updatedDeadlineTask = new Deadline(currentTask);

        if (!ensureFormatCompliance(prefixStatusHash, desc, date, time)) {
            throw new ParseException(MESSAGE_EDIT_TASK_NO_EMPTY_VALUES);
        }

        if (!desc.isEmpty()) {
            Description newDesc = ParserUtil.parseDescription(desc);
            updatedDeadlineTask.setDescription(newDesc);
        }

        if (!date.isEmpty()) {
            Date newDate = ParserUtil.parseDate(date);
            updatedDeadlineTask.setDate(newDate);
        }

        if (!time.isEmpty()) {
            Time newTime = ParserUtil.parseTime(time);
            updatedDeadlineTask.setTime(newTime);
        }

        return updatedDeadlineTask;
    }

    private Task updateEvent(Event currentTask, String desc, String date, String time) throws ParseException {
        Event updatedEventTask = new Event(currentTask);

        if (!ensureFormatCompliance(prefixStatusHash, desc, date, time)) {
            throw new ParseException(MESSAGE_EDIT_TASK_NO_EMPTY_VALUES);
        }

        if (!desc.isEmpty()) {
            Description newDesc = ParserUtil.parseDescription(desc);
            updatedEventTask.setDescription(newDesc);
        }

        if (!date.isEmpty()) {
            Date newDate = ParserUtil.parseDate(date);
            updatedEventTask.setDate(newDate);
        }

        if (!time.isEmpty()) {
            String[] newStartEndTimeStrParts = time.split(" ");

            if (newStartEndTimeStrParts.length != 2) {
                throw new ParseException(MESSAGE_INVALID_TIME_FORMAT);
            }

            Time newStartTime = ParserUtil.parseTime(newStartEndTimeStrParts[0]);
            Time newEndTime = ParserUtil.parseTime(newStartEndTimeStrParts[1]);

            if (newEndTime.getParsedTime().compareTo(newStartTime.getParsedTime()) < 1) {
                throw new ParseException(MESSAGE_INVALID_TIME_RANGE);
            }

            updatedEventTask.setStartTime(newStartTime);
            updatedEventTask.setEndTime(newEndTime);
        }

        return updatedEventTask;
    }
}
