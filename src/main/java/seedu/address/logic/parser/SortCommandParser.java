package seedu.address.logic.parser;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;
import seedu.address.model.person.comparators.PersonFlagComparator;
import seedu.address.model.person.comparators.PersonNameComparator;
import seedu.address.model.person.comparators.PersonPrevDateMetComparator;

import java.util.Comparator;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class SortCommandParser implements Parser<SortCommand> {

    enum sortArg {
        MEETING,
        NAME,
        PREV,
        SALARY
    }

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

    public Comparator<Person> parseComparatorForSort(String arg) throws ParseException {
        sortArg userInput;
        try {
            userInput = sortArg.valueOf(arg.toUpperCase());
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
