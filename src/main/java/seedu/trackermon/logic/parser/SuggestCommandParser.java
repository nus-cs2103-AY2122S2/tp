package seedu.trackermon.logic.parser;

import seedu.trackermon.commons.core.index.Index;
import seedu.trackermon.logic.commands.FindCommand;
import seedu.trackermon.logic.commands.SuggestCommand;
import seedu.trackermon.logic.parser.exceptions.ParseException;
import seedu.trackermon.model.show.NameContainsKeywordsPredicate;
import seedu.trackermon.model.show.RatingContainsKeywordsPredicate;
import seedu.trackermon.model.show.Show;
import seedu.trackermon.model.show.StatusContainsKeywordsPredicate;
import seedu.trackermon.model.show.TagsContainsKeywordsPredicate;
import seedu.trackermon.model.tag.Tag;



import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static seedu.trackermon.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackermon.logic.parser.CliSyntax.*;
import static seedu.trackermon.logic.parser.CliSyntax.PREFIX_RATING;

public class SuggestCommandParser implements Parser<SuggestCommand> {

    @Override
    public SuggestCommand parse(String args) throws ParseException {

        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_STATUS, PREFIX_TAG, PREFIX_RATING);
        
        String[] keywordsArr;
        List<Predicate<Show>> predicateArrayList = new ArrayList<>();

        boolean hasPrefix = false;
        boolean hasStatusPrefix = argumentMultimap.arePrefixesPresent(PREFIX_STATUS);
        boolean hasTagPrefix = argumentMultimap.arePrefixesPresent(PREFIX_TAG);
        boolean hasRatingPrefix = argumentMultimap.arePrefixesPresent(PREFIX_RATING);

        if (hasStatusPrefix) {
            hasPrefix = true;
            String input = argumentMultimap.getValue(PREFIX_STATUS).get();
            keywordsArr = getKeywords(input);
            predicateArrayList.add(new StatusContainsKeywordsPredicate(Arrays.asList(keywordsArr)));
        }

        if (hasTagPrefix) {
            hasPrefix = true;
            List<String> input = argumentMultimap.getAllValues(PREFIX_TAG);
            for (int i = 0; i < input.size(); i++) {
                if (!Tag.isValidTagName(input.get(i))) {
                    throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.TAG_ERROR));
                }
                predicateArrayList.add(new TagsContainsKeywordsPredicate(Arrays.asList(input.get(i))));
            }
        }

        if (hasRatingPrefix) {
            hasPrefix = true;
            String input = argumentMultimap.getValue(PREFIX_RATING).get();
            keywordsArr = getKeywords(input);
            predicateArrayList.add(new RatingContainsKeywordsPredicate(Arrays.asList(keywordsArr)));
        }

        return new SuggestCommand(index);
    }
}
