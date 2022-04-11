package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.MakeTeamCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new MakeTeamCommand object
 */
public class MakeTeamCommandParser implements Parser<MakeTeamCommand> {

    private final MakeTeamCommand.TeamAction action;

    /**
     * Constructs a MakeTeamCommandParser.
     *
     * @param action Action the {@code MakeTeamCommand} will carry out.
     */
    public MakeTeamCommandParser(MakeTeamCommand.TeamAction action) {
        this.action = action;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the MakeTeamCommand
     * and returns a MakeTeamCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public MakeTeamCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new MakeTeamCommand(index, action);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, MakeTeamCommand.MESSAGE_USAGE), pe);
        }
    }
}
