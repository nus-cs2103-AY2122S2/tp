package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE_PLAYER =
            "To view player, parameters: " + PREFIX_PLAYER + "[PLAYER_NAME...]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PLAYER + "Kevin Lebron"
            + " OR " + COMMAND_WORD + " " + PREFIX_PLAYER;

    public static final String MESSAGE_USAGE_LINEUP =
            "To view lineup, parameters: " + PREFIX_LINEUP + "[LINEUP_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_LINEUP + "starting5"
            + " OR " + COMMAND_WORD + " " + PREFIX_LINEUP;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views information of lineups and players.\n"
            + MESSAGE_USAGE_PLAYER + "\n"
            + MESSAGE_USAGE_LINEUP;

    private static String successMessage = "Listed all persons!";

    private final Predicate<Person> predicate;
    private final List<String> keywords;

    /**
     * Constructs a view command.
     * @param predicate the view criteria.
     * @param keywords the keywords that define this type of view.
     */
    public ViewCommand(Predicate<Person> predicate, List<String> keywords) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.keywords = keywords;
    }

    /**
     * Executes the ViewCommand and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        changeSuccessMessage(model);
        return new CommandResult(successMessage);
    }

    private void changeSuccessMessage(Model model) {
        if (keywords.size() > 1 || keywords.contains("L/")) {
            successMessage = String.format(
                    Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
        } else {
            successMessage = "Listed all persons!";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
