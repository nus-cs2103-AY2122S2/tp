package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENT_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Arrays;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.predicates.CompoundedPredicates;
import seedu.address.model.person.predicates.EmailContainsKeywordsPredicate;
import seedu.address.model.person.predicates.GroupContainsKeywordsPredicate;
import seedu.address.model.person.predicates.ModContainsKeywordsPredicate;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;
import seedu.address.model.person.predicates.StudentNumberContainsKeywordsPredicate;
import seedu.address.model.person.predicates.TagsContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        // need to add whitespace for compatibility with tokenizer that was originally used for commands
        // with index preambles.
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(" " + trimmedArgs, PREFIX_NAME, PREFIX_STUDENT_NUMBER, PREFIX_EMAIL,
                        PREFIX_GROUP, PREFIX_MOD, PREFIX_TAG);

        CompoundedPredicates compPreds = new CompoundedPredicates();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_NAME).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new NameContainsKeywordsPredicate(Arrays.asList(keywords)));
        }
        if (argMultimap.getValue(PREFIX_STUDENT_NUMBER).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_STUDENT_NUMBER).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new StudentNumberContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_EMAIL).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new EmailContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_MOD).isPresent()) { // to be changed to MOD
            String[] keywords = argMultimap.getValue(PREFIX_MOD).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new ModContainsKeywordsPredicate(Arrays.asList(keywords)));

        }
        if (argMultimap.getValue(PREFIX_TAG).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_TAG).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new TagsContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (argMultimap.getValue(PREFIX_GROUP).isPresent()) {
            String[] keywords = argMultimap.getValue(PREFIX_GROUP).get().split("\\s+");
            checkKeywords(keywords);
            compPreds.addPredicate(new GroupContainsKeywordsPredicate(Arrays.asList(keywords)));
        }

        if (compPreds.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(compPreds);
    }


    /**
     * Checks if any keyword given is empty. If so, throw an exception.
     * @param keywords the keywords to check.
     * @return true when no empty keywords are found.
     * @throws ParseException when an empty keyword is given, ie find n/ a/
     */
    private boolean checkKeywords(String[] keywords) throws ParseException {
        for (String s : keywords) {
            if (s.trim().length() == 0) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            }
        }
        return true;
    }
}
