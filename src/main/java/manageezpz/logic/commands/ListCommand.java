package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static manageezpz.logic.parser.CliSyntax.PREFIX_EVENT;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODAY;
import static manageezpz.logic.parser.CliSyntax.PREFIX_TODO;

import manageezpz.logic.parser.Prefix;
import manageezpz.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_ALL_SUCCESS = "Listed all Tasks";
    public static final String MESSAGE_TODO_SUCCESS = "Listed all Todos";
    public static final String MESSAGE_DEADLINE_SUCCESS = "Listed all Deadlines";
    public static final String MESSAGE_EVENT_SUCCESS = "Listed all Events";
    public static final String MESSAGE_TODAY_SUCCESS = "Listed all Deadline/Events today";
    public static final String MESSAGE_INVALID_ARGUMENTS =
            String.join("\n",
                    "list: List down all tasks.",
                    "Only one argument allowed. No values after command",
                    "Valid Arguments: todo/, deadline/, event/, today/.");
    private Prefix option;
    private String messageSuccess;

    /**
     * Constructor for List Command, without any arguments which shows all tasks in the task list.
     */
    public ListCommand() {
        messageSuccess = MESSAGE_ALL_SUCCESS;
    }

    /**
     * Constructor for List Command, with Arguments.
     * @param option Arguments to show specific task.
     */
    public ListCommand(Prefix option) {
        this.option = option;
        if (PREFIX_TODO.equals(option)) {
            messageSuccess = MESSAGE_TODO_SUCCESS;
        } else if (PREFIX_DEADLINE.equals(option)) {
            messageSuccess = MESSAGE_DEADLINE_SUCCESS;
        } else if (PREFIX_EVENT.equals(option)) {
            messageSuccess = MESSAGE_EVENT_SUCCESS;
        } else if (PREFIX_TODAY.equals(option)) {
            messageSuccess = MESSAGE_TODAY_SUCCESS;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        String list;
        if (option == null) {
            list = model.listTasks();
        } else {
            list = model.listTasks(option);
        }
        String result = String.join("\n", messageSuccess, list);
        return new CommandResult(result);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ListCommand) {
            if (((ListCommand) obj).option == null) {
                return option == null;
            } else {
                return ((ListCommand) obj).option.equals(option);
            }
        } else {
            return false;
        }
    }
}
