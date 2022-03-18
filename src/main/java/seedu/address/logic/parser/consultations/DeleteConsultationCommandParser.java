package seedu.address.logic.parser.consultations;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.consultation.DeleteConsultationCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteConsultationCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the DeleteConsultationCommand
     * and returns an DeleteConsultationCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteConsultationCommand parse(String args) throws ParseException {
        try {
            Index targetIndex = ParserUtil.parseIndex(args);
            return new DeleteConsultationCommand(targetIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteConsultationCommand.MESSAGE_USAGE), pe);
        }


    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
