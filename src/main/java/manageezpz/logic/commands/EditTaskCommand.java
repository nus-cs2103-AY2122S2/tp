package manageezpz.logic.commands;

import static manageezpz.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static manageezpz.commons.core.Messages.MESSAGE_TASK_UPDATE_SUCCESS;
import static manageezpz.commons.core.Messages.MESSAGE_UNEXPECTED_ERROR;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import manageezpz.commons.core.index.Index;
import manageezpz.logic.commands.exceptions.CommandException;
import manageezpz.logic.parser.ParserUtil;
import manageezpz.logic.parser.exceptions.ParseException;
import manageezpz.model.Model;
import manageezpz.model.task.*;

/**
 * Edits the details of an existing task in the address book.
 */

public class EditTaskCommand extends Command {
    public static final String COMMAND_WORD = "editTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must exist in the Address Book) "
            + "[" + PREFIX_DESCRIPTION + " NAME] \n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_DESCRIPTION + " Eat bananas ";

    private final Index index;
    private final String desc;
    private final String time;
    private final String date;

    /**
     * A constructor to store information that is used in editing a task.
     */

    public EditTaskCommand(Index index, String desc, String time, String date) {
        this.index = index;
        this.desc = desc;
        this.time = time;
        this.date = date;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        try {
            Task currentTask = model.getFilteredTaskList().get(index.getZeroBased());
            if (currentTask.getType().equalsIgnoreCase("todo")) {
                return handleTodo((Todo) currentTask, model, this.desc);
            } else if (currentTask.getType().equalsIgnoreCase("deadline")) {
                return handleDeadline((Deadline) currentTask, model, this.desc, this.date, this.time);
            } else if (currentTask.getType().equalsIgnoreCase("event")) {
                return handleEvent((Event) currentTask, model, this.desc, this.date, this.time);
            } else {
                throw new CommandException(MESSAGE_UNEXPECTED_ERROR);
            }
        } catch (Exception e) {
            throw new CommandException(MESSAGE_USAGE);
        }
    }

    private Description parseDesc(String desc) throws ParseException {
        Description parseDescResult;
        try {
            parseDescResult = ParserUtil.parseDescription(desc);
            return parseDescResult;
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE), pe);
        }
    }

    private Time parseTime (String time) throws ParseException {
        Time parseTimeResult;
        try {
            parseTimeResult = ParserUtil.parseTime(time);
            return parseTimeResult;
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE), pe);
        }
    }

    private Date parseDate(String date) throws ParseException {
        Date parseDateResult;
        try {
            parseDateResult = ParserUtil.parseDate(date);
            return parseDateResult;
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditTaskCommand.MESSAGE_USAGE), pe);
        }
    }

    private CommandResult handleTodo(Todo currentTask, Model model, String desc) throws ParseException {
        try {
            Description taskDesc = ParserUtil.parseDescription(desc);
            Todo newTask = new Todo(taskDesc);
            model.setTask(currentTask, newTask);
            return new CommandResult(String.format(MESSAGE_TASK_UPDATE_SUCCESS, newTask));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditTaskCommand.MESSAGE_USAGE), pe);
        }
    }


    private CommandResult handleDeadline(Deadline currentTask, Model model, String desc, String date, String time)
            throws ParseException {

        Description dl_description;
        Time dl_time;
        Date dl_date;

        if (desc.isEmpty()) {
            dl_description = currentTask.getDescription();
        } else {
           dl_description = parseDesc(desc);
        }

        if (time.isEmpty()) {
            dl_time = currentTask.getTime();
        } else {
            dl_time = parseTime(time);
        }

        if (date.isEmpty()) {
            dl_date = currentTask.getDate();
        } else {
            dl_date = parseDate(date);
        }

        Deadline newTask = new Deadline(dl_description, dl_date, dl_time);
        model.setTask(currentTask, newTask);
        return new CommandResult(String.format(MESSAGE_TASK_UPDATE_SUCCESS, newTask));
    }

    private CommandResult handleEvent(Event currentTask, Model model, String desc, String time, String date)
            throws ParseException {
        if (!desc.isEmpty() && time.isEmpty() && date.isEmpty()) {
            try {
                Description taskDesc = ParserUtil.parseDescription(desc);
                Event newTask = new Event(taskDesc, currentTask.getDate(),
                        currentTask.getStartTime(), currentTask.getEndTime());
                model.setTask(currentTask, newTask);
                return new CommandResult(String.format(MESSAGE_TASK_UPDATE_SUCCESS, newTask));
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        EditTaskCommand.MESSAGE_USAGE), pe);
            }
        } else {
            throw new ParseException("time and date update not supported yet.");
        }
    }
}
