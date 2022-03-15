package manageezpz.logic.commands;

import static java.util.Objects.requireNonNull;
import static manageezpz.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import manageezpz.logic.parser.Prefix;
import manageezpz.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {
    private static final String LIST_USAGE_GUIDE = "list: List down all tasks.";
    private static final String ONLY_ONE_ARGUMENT = "Only one argument allowed. No values after command";
    private static final String VALID_ARGUMENTS = "Valid Arguments: todo/, deadline/, event/, today/.";

    public static final String COMMAND_WORD = "list";
    public static final String MESSAGE_SUCCESS = "Listed all Tasks";
    public static final String MESSAGE_INVALID_ARGUMENTS
            = String.join("\n", LIST_USAGE_GUIDE, ONLY_ONE_ARGUMENT,VALID_ARGUMENTS);
    public static final String MESSAGE_MULTIPLE_ARGUMENTS = "Only one argument allowed for list";

    private Prefix option;

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
