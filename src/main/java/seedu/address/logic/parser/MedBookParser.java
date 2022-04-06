package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandManager.parseAddCommandType;
import static seedu.address.logic.commands.CommandManager.parseDeleteCommandType;
import static seedu.address.logic.commands.CommandManager.parseEditCommandType;
import static seedu.address.logic.commands.CommandManager.parseFindCommandType;
import static seedu.address.logic.commands.CommandManager.parseViewCommandType;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TYPE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class MedBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(arguments, PREFIX_TYPE);

        try {
            switch (commandWord) {
            case AddCommand.COMMAND_WORD:
                if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
                    return parseAddCommandType(argMultimap.getValue(PREFIX_TYPE).get(), arguments);
                }
                return new AddCommandParser().parse(arguments);
            case EditCommand.COMMAND_WORD:
                return parseEditCommandType(arguments);

            case DeleteCommand.COMMAND_WORD:
                return parseDeleteCommandType(arguments);

            case FindCommand.COMMAND_WORD:
                return parseFindCommandType(arguments);

            case ViewCommand.COMMAND_WORD:
                if (argMultimap.getValue(PREFIX_TYPE).isPresent()) {
                    return parseViewCommandType(argMultimap.getValue(PREFIX_TYPE).get(), arguments);
                }
                return new ViewCommandParser().parse(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD:
                return new HelpCommand();

            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (Exception e) {
            throw new ParseException(e.getMessage());
        }
    }

}
