package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.RemoveStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;

public class RemoveStudentCommandParser implements Parser<RemoveStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the RemoveStudentCommand
     * and returns a RemoveStudentCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public RemoveStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_STUDENTID, PREFIX_TUTORIALNAME);

        if (arePrefixesPresent(argMultimap, PREFIX_STUDENTID)) {

            if (!arePrefixesPresent(argMultimap, PREFIX_STUDENTID, PREFIX_TUTORIALNAME)
                    || !argMultimap.getPreamble().isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveStudentCommand.MESSAGE_USAGE));
            }

            NusNetId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new RemoveStudentCommand(studentId, tutorialName);
        } else {
            Index index;
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveStudentCommand.MESSAGE_USAGE), pe);
            }

            if (!arePrefixesPresent(argMultimap, PREFIX_TUTORIALNAME)) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        RemoveStudentCommand.MESSAGE_USAGE));
            }

            TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());
            return new RemoveStudentCommand(index, tutorialName);
        }
    }

}
