package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLASS_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DisenrolCommand;
import seedu.address.logic.commands.EnrolCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates either a EnrolCommand or DisenrolCommand
 * Since both commands takes in the same argument, this class serves as an abstraction for the parsing of both
 * EnrolCommand and DisenrolCommand
 */
public class GenericEnrolCommandParser {


    /**
     * Parses the given {@code String} of arguments in the context of the EnrolCommand or DisenrolCommand
     * and returns a EnrolCommand or DisenrolCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public static Command parse(String command, String args, Model model, String usage) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_CLASS_INDEX, PREFIX_STUDENT);

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CLASS_INDEX, PREFIX_STUDENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    usage));
        }

        try {
            Index classGroupIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_CLASS_INDEX).get());
            ObservableList<Student> students = ParserUtil.parseStudents(
                    argMultimap.getValue(PREFIX_STUDENT).get(), model);
            switch (command) {
            case EnrolCommand.COMMAND_WORD:
                return new EnrolCommand(classGroupIndex, students);
            case DisenrolCommand.COMMAND_WORD:
                return new DisenrolCommand(classGroupIndex, students);
            default:
                throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
            }
        } catch (ParseException pe) {
            throw new ParseException(String.format("%s\n%s", pe.getMessage(), usage), pe);
        }
    }
}
