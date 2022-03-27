package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.MarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

//@@author jxt00
/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    private static final Pattern MARK_COMMAND_FORMAT = Pattern.compile("(?<entityType>\\S+)(?<arguments>.*)");

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public MarkCommand parse(String args, Model model) throws ParseException {
        final Matcher matcher = MARK_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE));
        }

        final String arguments = matcher.group("arguments");
        ArgumentMultimap argMultimap = seedu.address.logic.parser.ArgumentTokenizer.tokenize(arguments,
                PREFIX_CLASS_INDEX, PREFIX_WEEK, PREFIX_STUDENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_CLASS_INDEX, PREFIX_WEEK, PREFIX_STUDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkCommand.MESSAGE_USAGE));
        }

        try {
            Index classGroupIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS_INDEX).get());
            Index weekIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_WEEK).get());
            ObservableList<Student> students = ParserUtil.parseStudents(
                    argMultimap.getValue(PREFIX_STUDENT).get(), model);
            return new MarkCommand(classGroupIndex, weekIndex, Optional.of(students));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
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
