package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASKNAME;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Link;
import seedu.address.model.task.Task;


/**
 * Represents an AddTaskCommand.
 */
public class AddTaskCommand extends Command {

    /* Message printed if wrong usage */
    public static final String MESSAGE_USAGE = "addt" + ": Adds a task to the NUS Classes. "
            + "Parameters: "
            + PREFIX_TASKNAME + "TASKNAME "
            + PREFIX_DATETIME + "DATETIME "
            + PREFIX_TAG + "TAG \n"
            + "Example: " + "addt" + " "
            + PREFIX_TASKNAME + "John Doe "
            + PREFIX_DATETIME + "25-12-2022 1800 "
            + PREFIX_TAG + "CS2103T"
            + " [" + PREFIX_LINK + "https://...]";

    public static final String COMMAND_WORD = "addt";
    public static final String ADD_TASK_SUCCESS = "Task added!";

    private final String taskName;
    private final LocalDateTime dateTime;
    private final Tag tag;
    private final Link link;

    /**
     * Constructor for AddTaskCommand. Takes in 4 parameters, taskName, dateTime and tags.
     * There can be multiple tags.
     *
     * @param taskName Name of Task.
     * @param dateTime LocalDateTime object to represent date time of Task.
     * @param tag Name of tag of the Task.
     */
    public AddTaskCommand(String taskName, LocalDateTime dateTime, Tag tag, Link link) {
        requireNonNull(taskName);
        requireNonNull(dateTime);
        requireNonNull(tag);

        this.taskName = taskName;
        this.dateTime = dateTime;
        this.tag = tag;
        this.link = link;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Task taskToBeAdded = new Task(taskName, dateTime, tag, link);
        model.getTaskList().addTask(taskToBeAdded);
        return new CommandResult(ADD_TASK_SUCCESS);
    }
}
