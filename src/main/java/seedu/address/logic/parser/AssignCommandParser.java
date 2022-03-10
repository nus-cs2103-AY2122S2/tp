package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AssignCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AssignCommandParser implements Parser<AssignCommand> {
    @Override
    public AssignCommand parse(String userInput) throws ParseException {
        ArgumentMultimap argMultiMap = ArgumentTokenizer.tokenize(userInput, PREFIX_STUDENT, PREFIX_LESSON);

        if (CheckPrefixes.arePrefixesAbsent(argMultiMap, PREFIX_STUDENT, PREFIX_LESSON)
                || !argMultiMap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }
        try {
            Index lessonIndex = ParserUtil.parseIndex(argMultiMap.getValue(PREFIX_LESSON).get());
            Index studentIndex = ParserUtil.parseIndex(argMultiMap.getValue(PREFIX_STUDENT).get());
            return new AssignCommand(studentIndex, lessonIndex);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignCommand.MESSAGE_USAGE));
        }
    }
}
