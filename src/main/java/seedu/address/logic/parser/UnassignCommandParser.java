package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnassignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class UnassignCommandParser implements Parser<UnassignCommand> {
    @Override
    public UnassignCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(userInput, PREFIX_STUDENT, PREFIX_LESSON);
        if (CheckPrefixes.arePrefixesAbsent(argumentMultimap, PREFIX_STUDENT, PREFIX_LESSON)
                || !argumentMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }
        try {
            Index lessonId = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_LESSON).get());
            Index studentId = ParserUtil.parseIndex(argumentMultimap.getValue(PREFIX_STUDENT).get());
            return new UnassignCommand(studentId, lessonId);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnassignCommand.MESSAGE_USAGE));
        }
    }
}
