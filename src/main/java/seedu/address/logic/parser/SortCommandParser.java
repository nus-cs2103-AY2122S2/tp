package seedu.address.logic.parser;


import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.SORT_BY_ADDRESS;
import static seedu.address.logic.commands.SortCommand.SORT_BY_EMAIL;
import static seedu.address.logic.commands.SortCommand.SORT_BY_FAVOURITE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NAME;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NUM_PROPERTIES;
import static seedu.address.logic.commands.SortCommand.SORT_BY_PHONE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_USER_TYPE;
import static seedu.address.logic.commands.SortCommand.SORT_REVERSE;

import java.util.Comparator;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new SortCommand((person1, person2) -> 0);
        }

        Comparator<Person> comparator = parseComparatorArgs(trimmedArgs.split(" "));
        return new SortCommand(comparator);
    }

    /**
     * Parses a chain of arguments into a Comparator that compares {@code Person} objects
     * according to the given arguments in the specified order.
     */
    private static Comparator<Person> parseComparatorArgs(String... args) throws ParseException {
        Comparator<Person> chainedComparator = (person1, person2) -> 0;

        for (String arg : args) {
            arg = arg.toLowerCase();
            boolean isReverse = false;

            if (arg.startsWith(String.valueOf(SORT_REVERSE))) {
                arg = arg.substring(1);
                isReverse = true;
            }

            Comparator<Person> comparator = parseComparator(arg, isReverse);
            chainedComparator = chainedComparator.thenComparing(comparator);
        }

        return chainedComparator;
    }

    /**
     * Parses the given argument into a Comparator.
     *
     * @param sortBy The field to sort by.
     * @param isReverse Whether to sort in reverse order.
     * @return A Comparator that compares {@code Person} objects based on the given field.
     * @throws ParseException if the given field is invalid.
     */
    private static Comparator<Person> parseComparator(String sortBy, boolean isReverse) throws ParseException {
        Comparator<Person> comparator;

        switch (sortBy) {
        case SORT_BY_NAME:
            comparator = Comparator.comparing(person -> person.getName().fullName.toLowerCase());
            break;
        case SORT_BY_PHONE:
            comparator = Comparator.comparing(person -> person.getPhone().value.toLowerCase());
            break;
        case SORT_BY_EMAIL:
            comparator = Comparator.comparing(person -> person.getEmail().value.toLowerCase());
            break;
        case SORT_BY_ADDRESS:
            comparator = Comparator.comparing(person -> person.getAddress().value.toLowerCase());
            break;
        case SORT_BY_FAVOURITE:
            comparator = Comparator.comparing(person -> person.getFavourite().getStatus());
            break;
        case SORT_BY_USER_TYPE:
            comparator = Comparator.comparing(person -> person.getUserType().toString().toLowerCase());
            break;
        case SORT_BY_NUM_PROPERTIES:
            comparator = Comparator.comparingInt(person -> person.getProperties().size());
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return isReverse ? comparator.reversed() : comparator;
    }

}
