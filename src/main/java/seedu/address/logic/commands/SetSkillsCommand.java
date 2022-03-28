package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public class SetSkillsCommand extends Command {
    public static final String COMMAND_WORD = "setskills";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the persons identified "
        + "by the index number used in the displayed person list. "
        + "Existing skillsets will be overwritten by the input values.\n"
        + "Parameters: INDEX... (must be a positive integer) "
        + PREFIX_SKILL + "SKILL NAME_SKILL PROFICIENCY...\n"
        + "Example: " + COMMAND_WORD + " 1 2 3 "
        + PREFIX_SKILL + "s/java_100 s/c_30";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return null;
    }
}
