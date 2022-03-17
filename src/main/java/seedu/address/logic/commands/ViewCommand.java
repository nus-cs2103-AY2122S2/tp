package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE_TEAM = "To view team, parameters: " + PREFIX_TEAM + "[TEAM_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers"
            + " OR "
            + COMMAND_WORD + " " + PREFIX_TEAM;

    public static final String MESSAGE_USAGE_PLAYER = "To view player, parameters: " + PREFIX_PLAYER + "[PLAYER_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PLAYER + " LBJ"
            + " OR "
            + COMMAND_WORD + " " + PREFIX_PLAYER;

    public static final String MESSAGE_USAGE_LINEUP = "To view lineup, parameters: " + PREFIX_LINEUP + "[LINEUP_NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers " + PREFIX_LINEUP + " starting5"
            + " OR "
            + COMMAND_WORD + " " + PREFIX_TEAM + " Lakers " + PREFIX_LINEUP;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the summarised information of "
            + "team, lineup and player.\n"
            + MESSAGE_USAGE_TEAM + "\n"
            + MESSAGE_USAGE_PLAYER + "\n"
            + MESSAGE_USAGE_LINEUP;

    private final Predicate<Person> predicate;
    private final List<String> keywords;
    public static String SUCCESS_MESSAGE = "Listed all persons!";

    public ViewCommand(Predicate<Person> predicate, List<String> keywords) {
        requireNonNull(predicate);
        this.predicate = predicate;
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        changeSuccessMessage(model);
        return new CommandResult(SUCCESS_MESSAGE);
    }

    private void changeSuccessMessage(Model model) {
        if (keywords.contains(PREFIX_PLAYER.toString()) && keywords.size() > 1) {
            SUCCESS_MESSAGE = String.format(
                    Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
        } else {
            SUCCESS_MESSAGE = "Listed all persons!";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
