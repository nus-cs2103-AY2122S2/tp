package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;
import static seedu.address.logic.parser.ParserUtil.arePrefixesPresent;

import seedu.address.logic.commands.AddStudentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.tutorial.TutorialName;


/**
 * Parses input arguments and creates a new AddStudentCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentCommand
     * and returns an AddStudentCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_TUTORIALNAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_STUDENTID, PREFIX_TUTORIALNAME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        NusNetId studentId = ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get());
        TutorialName tutorialName = ParserUtil.parseTutorialName(argMultimap.getValue(PREFIX_TUTORIALNAME).get());

        return new AddStudentCommand(name, studentId, tutorialName);
    }

}
