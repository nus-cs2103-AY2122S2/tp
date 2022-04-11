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
import seedu.address.model.PersonComparator;
import seedu.address.model.person.Person;

public class SortCommandParser implements Parser<SortCommand> {

    public static final Comparator<Person> NAME_COMPARATOR =
            Comparator.comparing(person -> person.getName().fullName.toLowerCase());
    public static final Comparator<Person> ADDRESS_COMPARATOR =
            Comparator.comparing(person -> person.getAddress().value.toLowerCase());
    public static final Comparator<Person> EMAIL_COMPARATOR =
            Comparator.comparing(person -> person.getEmail().value.toLowerCase());
    public static final Comparator<Person> PHONE_COMPARATOR =
            Comparator.comparing(person -> person.getPhone().value.toLowerCase());
    public static final Comparator<Person> FAVOURITE_COMPARATOR =
            Comparator.comparing(person -> person.getFavourite().isUnfavourited());
    public static final Comparator<Person> USER_TYPE_COMPARATOR =
            Comparator.comparing(person -> person.getUserType().value.toLowerCase());
    public static final Comparator<Person> NUM_PROPERTIES_COMPARATOR =
            Comparator.comparingInt(person -> person.getProperties().size());

    public static final Comparator<Person> NAME_COMPARATOR_REVERSE = NAME_COMPARATOR.reversed();
    public static final Comparator<Person> ADDRESS_COMPARATOR_REVERSE = ADDRESS_COMPARATOR.reversed();
    public static final Comparator<Person> EMAIL_COMPARATOR_REVERSE = EMAIL_COMPARATOR.reversed();
    public static final Comparator<Person> PHONE_COMPARATOR_REVERSE = PHONE_COMPARATOR.reversed();
    public static final Comparator<Person> FAVOURITE_COMPARATOR_REVERSE = FAVOURITE_COMPARATOR.reversed();
    public static final Comparator<Person> USER_TYPE_COMPARATOR_REVERSE = USER_TYPE_COMPARATOR.reversed();
    public static final Comparator<Person> NUM_PROPERTIES_COMPARATOR_REVERSE = NUM_PROPERTIES_COMPARATOR.reversed();

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        List<Comparator<Person>> comparators = parseComparators(List.of(trimmedArgs.split("\\s+")));
        return new SortCommand(new PersonComparator(comparators));
    }

    /**
     * Parses a {@code List<String>} of keywords into a {@code List<Comparator<Person>>}.
     *
     * @throws ParseException if an invalid keyword is specified.
     */
    private static List<Comparator<Person>> parseComparators(List<String> keywords) throws ParseException {
        requireNonNull(keywords);
        assert (!keywords.isEmpty());

        List<Comparator<Person>> comparatorList = new ArrayList<>();

        for (String keyword : keywords) {
            comparatorList.add(parseComparator(keyword));
        }

        return comparatorList;
    }

    /**
     * Parses the given {@code String} into a {@code Comparator<Person>}.
     *
     * @throws ParseException if the given keyword is invalid.
     */
    private static Comparator<Person> parseComparator(String keyword) throws ParseException {
        requireNonNull(keyword);
        keyword = keyword.toLowerCase();

        if (keyword.startsWith(String.valueOf(SORT_REVERSE))) {
            switch (keyword.substring(1)) {
            case SORT_BY_NAME:
                return NAME_COMPARATOR_REVERSE;
            case SORT_BY_PHONE:
                return PHONE_COMPARATOR_REVERSE;
            case SORT_BY_EMAIL:
                return EMAIL_COMPARATOR_REVERSE;
            case SORT_BY_ADDRESS:
                return ADDRESS_COMPARATOR_REVERSE;
            case SORT_BY_FAVOURITE:
                return FAVOURITE_COMPARATOR_REVERSE;
            case SORT_BY_USER_TYPE:
                return USER_TYPE_COMPARATOR_REVERSE;
            case SORT_BY_NUM_PROPERTIES:
                return NUM_PROPERTIES_COMPARATOR_REVERSE;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        } else {
            switch (keyword) {
            case SORT_BY_NAME:
                return NAME_COMPARATOR;
            case SORT_BY_PHONE:
                return PHONE_COMPARATOR;
            case SORT_BY_EMAIL:
                return EMAIL_COMPARATOR;
            case SORT_BY_ADDRESS:
                return ADDRESS_COMPARATOR;
            case SORT_BY_FAVOURITE:
                return FAVOURITE_COMPARATOR;
            case SORT_BY_USER_TYPE:
                return USER_TYPE_COMPARATOR;
            case SORT_BY_NUM_PROPERTIES:
                return NUM_PROPERTIES_COMPARATOR;
            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
            }
        }
    }

}
