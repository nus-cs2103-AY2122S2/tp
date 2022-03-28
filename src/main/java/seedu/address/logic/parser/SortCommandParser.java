package seedu.address.logic.parser;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.SortCommand.SORT_BY_ADDRESS;
import static seedu.address.logic.commands.SortCommand.SORT_BY_EMAIL;
import static seedu.address.logic.commands.SortCommand.SORT_BY_FAVOURITE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NAME;
import static seedu.address.logic.commands.SortCommand.SORT_BY_NUM_PROPERTIES;
import static seedu.address.logic.commands.SortCommand.SORT_BY_PHONE;
import static seedu.address.logic.commands.SortCommand.SORT_BY_USER_TYPE;
import static seedu.address.logic.commands.SortCommand.SORT_REVERSE;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            return new SortCommand((person1, person2) -> 0);
        }

        Comparator<Person> comparator = parseComparators(List.of(trimmedArgs.split(" ")));
        return new SortCommand(comparator);
    }

    /**
     * Parses a {@code List<String>} of keywords into a {@code Comparator<Person>} that compares {@code Person}
     * objects according to the given keywords in the specified order.
     *
     * @throws ParseException if an invalid keyword is specified.
     */
    private static Comparator<Person> parseComparators(List<String> keywords) throws ParseException {
        requireNonNull(keywords);

        if (keywords.isEmpty()) {
            return (person1, person2) -> 0;
        }

        List<Comparator<Person>> comparatorList = new ArrayList<>();

        for (String keyword : keywords) {
            comparatorList.add(parseComparator(keyword));
        }

        return comparatorList.stream().reduce(Comparator::thenComparing).get();
    }

    /**
     * Parses the given {@code String} into a {@code Comparator<Person>}.
     *
     * @throws ParseException if the given keyword is invalid.
     */
    private static Comparator<Person> parseComparator(String keyword) throws ParseException {
        requireNonNull(keyword);
        Comparator<Person> parsedComparator;
        boolean isReverse = false;

        if (keyword.startsWith(String.valueOf(SORT_REVERSE))) {
            keyword = keyword.substring(1);
            isReverse = true;
        }

        switch (keyword.toLowerCase()) {
        case SORT_BY_NAME:
            parsedComparator = Comparator.comparing(person -> person.getName().fullName.toLowerCase());
            break;
        case SORT_BY_PHONE:
            parsedComparator = Comparator.comparing(person -> person.getPhone().value.toLowerCase());
            break;
        case SORT_BY_EMAIL:
            parsedComparator = Comparator.comparing(person -> person.getEmail().value.toLowerCase());
            break;
        case SORT_BY_ADDRESS:
            parsedComparator = Comparator.comparing(person -> person.getAddress().value.toLowerCase());
            break;
        case SORT_BY_FAVOURITE:
            parsedComparator = Comparator.comparing(person -> person.getFavourite().isUnfavourited());
            break;
        case SORT_BY_USER_TYPE:
            parsedComparator = Comparator.comparing(person -> person.getUserType().toString().toLowerCase());
            break;
        case SORT_BY_NUM_PROPERTIES:
            parsedComparator = Comparator.comparingInt(person -> person.getProperties().size());
            break;
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        return isReverse ? parsedComparator.reversed() : parsedComparator;
    }

}
