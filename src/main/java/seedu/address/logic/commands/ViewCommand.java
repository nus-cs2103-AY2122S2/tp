package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ALL_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINEUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLAYER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE_PLAYER = COMMAND_WORD
            + ": To view player\n"
            + "Parameters: "
            + PREFIX_PLAYER + "[NAME...]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_PLAYER + "Kevin Lebron"
            + " OR " + COMMAND_WORD + " " + PREFIX_PLAYER;

    public static final String MESSAGE_USAGE_LINEUP = COMMAND_WORD
            + ": To view lineup\n"
            + "Parameters: " + PREFIX_LINEUP + "[LINEUP NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_LINEUP + "starting5"
            + " OR " + COMMAND_WORD + " " + PREFIX_LINEUP;

    public static final String MESSAGE_USAGE_SCHEDULE = COMMAND_WORD
            + ": To view schedule\n"
            + "Parameters: " + PREFIX_SCHEDULE + "[SCHEDULE NAME]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_SCHEDULE + "finals";

    public static final String MESSAGE_ACTIVE_SCHEDULE_USAGE = COMMAND_WORD
            + ": To view all schedules\n"
            + "Parameters: " + PREFIX_SCHEDULE + " " + PREFIX_ALL_SCHEDULE + "all\n"
            + "To view archived schedules\n"
            + "Parameters: " + PREFIX_SCHEDULE + " " + PREFIX_ALL_SCHEDULE + "archive\n";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views information of players, lineups and schedules.\n"
            + MESSAGE_USAGE_PLAYER + "\n"
            + MESSAGE_USAGE_LINEUP + "\n"
            + MESSAGE_USAGE_SCHEDULE;

    private static String messageViewSuccess = "Listed all information!";

    private final Predicate<Person> predicate;
    private final Predicate<Schedule> predicateSchedule;
    private final List<String> keywords;

    /**
     * Constructs a view command.
     * @param predicate the view criteria.
     * @param keywords the keywords that define this type of view.
     */
    public ViewCommand(Predicate<Person> predicate, Predicate<Schedule> predicateSchedule, List<String> keywords) {
        //requireNonNull(predicate);
        this.predicate = predicate;
        this.predicateSchedule = predicateSchedule;
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
        if (predicate != null) {
            model.updateFilteredPersonList(predicate);
        }
        if (predicateSchedule != null) {
            model.updateFilteredScheduleList(predicateSchedule);
        }
        changeSuccessMessage(model);
        return new CommandResult(messageViewSuccess);
    }

    private void changeSuccessMessage(Model model) {
        if (keywords.size() > 1 && (keywords.contains("L/") || keywords.contains("P/"))) {
            messageViewSuccess = String.format(
                    Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size());
        } else if (keywords.contains("S/")) {
            messageViewSuccess = String.format(
                    Messages.MESSAGE_SCHEDULE_LISTED_OVERVIEW, model.getFilteredScheduleList().size());
        } else {
            StringBuilder sb = new StringBuilder();
            int i = 1;
            for (Lineup lineup : model.getLineups()) {
                sb.append(Integer.toString(i) + ". " + lineup.toString() + "\n");
                i++;
            }
            messageViewSuccess = sb.length() != 0 ? sb.toString() :
                    "There is currently no lineups available. Use the \"add\" command to add new lineups.";
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewCommand // instanceof handles nulls
                && predicate.equals(((ViewCommand) other).predicate)); // state check
    }
}
