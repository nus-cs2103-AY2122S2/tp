package seedu.address.logic;

/**
 * Indicates the type of CommandResult object.
 * HELP for HelpCommand.
 * EXIT for ExitCommand.
 * HISTORY for PreviousCommand.
 * UI_IRRELEVANT for anything else.
 */
public enum CommandRemark {
    HELP,
    EXIT,
    HISTORY,
    UI_IRRELEVANT
}
