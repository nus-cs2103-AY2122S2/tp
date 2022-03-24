package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.PersonFlagComparator;
import seedu.address.model.person.comparators.PersonNameComparator;
import seedu.address.model.person.comparators.PersonPrevDateMetComparator;

public class SortCommandParser implements Parser<SortCommand> {

    enum SortArg {
        MEETING,
        NAME,
        PREV,
        SALARY
    }

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a Sort Command object for execution.
     *
     * @param args arguments to be parsed for SortCommand.
     * @return SortCommand with parsed Comparator.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        String[] sortVariable = trimmedArgs.split("\\s+");

        if (sortVariable.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Comparator<Person> sortComparator = parseComparatorForSort(sortVariable[0]);

        return new SortCommand(sortComparator);
    }

    /**
     * Parses the given {@code String} of arguments to determine corresponding Comparator.
     *
     * @param arg arguments to be parsed to determine Comparator.
     * @return Comparator based on arg
     * @throws ParseException if the user input does not conform the expected format.
     */
    public Comparator<Person> parseComparatorForSort(String arg) throws ParseException {
        SortArg userInput;
        try {
            userInput = SortArg.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }
        switch(userInput) {
        case NAME:
            return new PersonNameComparator();
        case PREV:
            return new PersonPrevDateMetComparator();
        default:
            return new PersonFlagComparator();
        }
    }

}
