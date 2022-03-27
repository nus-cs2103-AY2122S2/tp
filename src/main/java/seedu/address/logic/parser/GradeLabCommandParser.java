package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LAB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABMARK;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GradeLabCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.lab.LabMark;

public class GradeLabCommandParser implements Parser<GradeLabCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the GradeLabCommand
     * and returns a GradeLabCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public GradeLabCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_LAB, PREFIX_LABMARK);

        if (!ArgumentTokenizer.arePrefixesPresent(argMultimap, PREFIX_LAB, PREFIX_LABMARK)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeLabCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, GradeLabCommand.MESSAGE_USAGE), pe);
        }

        int labNumber = ParserUtil.parseLab(argMultimap.getValue(PREFIX_LAB).get()).labNumber;
        LabMark labMark = ParserUtil.parseLabMark(argMultimap.getValue(PREFIX_LABMARK).get());

        return new GradeLabCommand(index, labNumber, labMark);
    }

}
