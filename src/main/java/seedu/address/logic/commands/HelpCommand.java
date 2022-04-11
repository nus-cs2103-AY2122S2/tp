package seedu.address.logic.commands;

import java.util.Set;

import seedu.address.model.Model;
import seedu.address.model.team.Skill;


/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final Set<String> AVAILABLE_HELP_TOPICS = Set.of("add", "delete", "find", "filter", "list", "skill",
            "team", "unteam", "sort", "", "batchedit", "clear", "exit", "edit", "redo", "undo", "filterteam");
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions. Usage: <Help> "
            + "to get link to user guide or <Help> <Topic> to get help for particular topic \n"
            + "Example: " + COMMAND_WORD + "\nExample: " + COMMAND_WORD + " list\n";

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
    public static final String HELP_MESSAGE_ADD = AddCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_DELETE = DeleteCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_FIND = FindCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_FILTER = FilterSkillCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_LIST = ListCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_SORT = SortCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_TEAM = MakeTeamCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_UNTEAM = MakeTeamCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_CLEAR = ClearCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_EXIT = ExitCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_EDIT = EditCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_REDO = RedoCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_UNDO = UndoCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_SHOW = ShowCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_FILTERTEAM = FilterPastTeamCommand.MESSAGE_USAGE;
    public static final String HELP_MESSAGE_SKILL = Skill.MESSAGE_USAGE;

    public final String topic;
    public HelpCommand(String topic) {
        this.topic = topic;
    }

    @Override
    public CommandResult execute(Model model) {
        switch (topic) {
        case "add":
            return new CommandResult(HELP_MESSAGE_ADD, false, false);
        case "delete":
            return new CommandResult(HELP_MESSAGE_DELETE, false, false);
        case "find":
            return new CommandResult(HELP_MESSAGE_FIND, false, false);
        case "filter":
            return new CommandResult(HELP_MESSAGE_FILTER, false, false);
        case "list":
            return new CommandResult(HELP_MESSAGE_LIST, false, false);
        case "skill":
            return new CommandResult(HELP_MESSAGE_SKILL, false, false);
        case "team":
            return new CommandResult(HELP_MESSAGE_TEAM, false, false);
        case "unteam":
            return new CommandResult(HELP_MESSAGE_UNTEAM, false, false);
        case "sort":
            return new CommandResult(HELP_MESSAGE_SORT, false, false);
        case "clear":
            return new CommandResult(HELP_MESSAGE_CLEAR, false, false);
        case "exit":
            return new CommandResult(HELP_MESSAGE_EXIT, false, false);
        case "edit":
            return new CommandResult(HELP_MESSAGE_EDIT, false, false);
        case "undo":
            return new CommandResult(HELP_MESSAGE_UNDO, false, false);
        case "redo":
            return new CommandResult(HELP_MESSAGE_REDO, false, false);
        case "show":
            return new CommandResult(HELP_MESSAGE_SHOW, false, false);
        case "filterteam":
            return new CommandResult(HELP_MESSAGE_FILTERTEAM, false, false);
        default:
            return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }
        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }
        HelpCommand toCompare = (HelpCommand) other;
        return toCompare.topic.equals(toCompare.topic);

    }

}
