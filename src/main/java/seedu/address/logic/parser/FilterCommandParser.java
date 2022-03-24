package seedu.address.logic.parser;

import seedu.address.logic.commands.FilterCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.pet.OwnerNameContainsFilterWordPredicate;
import seedu.address.model.pet.TagContainsFilterWordPredicate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.*;

public class FilterCommandParser implements Parser<FilterCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterCommand
     * and returns a FilterCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public FilterCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();

        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterCommand.MESSAGE_USAGE));
        }

        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_FILTER_BY_DATE, PREFIX_FILTER_TODAY, PREFIX_FILTER_BY_TAGS,
                        PREFIX_FILTER_BY_OWNER_NAME);

        String[] argSplitter = trimmedArgs.split("/");
        String filterWord = argSplitter[1];

        if (argMultimap.getValue(PREFIX_FILTER_BY_DATE).isPresent()) {
            return new FilterCommand(new OwnerNameContainsFilterWordPredicate(filterWord));
        } else if (argMultimap.getValue(PREFIX_FILTER_TODAY).isPresent()) {
            return new FilterCommand(new OwnerNameContainsFilterWordPredicate(filterWord));
        } else if (argMultimap.getValue(PREFIX_FILTER_BY_TAGS).isPresent()) {
            return new FilterCommand(new TagContainsFilterWordPredicate(filterWord));
        } else if (argMultimap.getValue(PREFIX_FILTER_BY_OWNER_NAME).isPresent()) {
            return new FilterCommand(new OwnerNameContainsFilterWordPredicate(filterWord));
        } else {
            return new FilterCommand(new OwnerNameContainsFilterWordPredicate(filterWord));
        }
    }
}
