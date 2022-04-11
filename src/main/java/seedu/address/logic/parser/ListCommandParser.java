package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.function.Predicate;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class ListCommandParser implements Parser<ListCommand> {

    public static final Predicate<Person> PREDICATE_SHOW_FLAGGED_PERSONS = person -> person.getFlag().isFlagged;
    public static final Predicate<Person> PREDICATE_SHOW_UNFLAGGED_PERSONS = person -> !person.getFlag().isFlagged;

    /**
     * Parses the given {@code String} of arguments in the context of the ListCommand
     * and returns a List Command object for execution.
     *
     * @param args arguments to be parsed for ListCommand.
     * @return ListCommand with parsed Predicate.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ListCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            return new ListCommand(PREDICATE_SHOW_ALL_PERSONS);
        }

        String[] listArgs = trimmedArgs.split("\\s+");

        if (listArgs.length != 1) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }

        switch(listArgs[0].toLowerCase()) {
        case "flag":
            return new ListCommand(PREDICATE_SHOW_FLAGGED_PERSONS);
        case "unflag":
            return new ListCommand(PREDICATE_SHOW_UNFLAGGED_PERSONS);
        default:
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE));
        }
    }
}
