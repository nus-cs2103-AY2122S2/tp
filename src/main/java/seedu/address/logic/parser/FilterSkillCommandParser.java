package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.stream.Collectors;

import seedu.address.logic.commands.FilterSkillCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonContainsSkillPredicate;
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;

/**
 * Parses input arguments and creates a new FilterSkillCommand object
 */
public class FilterSkillCommandParser implements Parser<FilterSkillCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FilterSkillCommand
     * and returns a FilterSkillCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FilterSkillCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FilterSkillCommand.MESSAGE_USAGE));
        }

        String[] keywords = trimmedArgs.split("\\s+");

        SkillSet skillSet = new SkillSet(Arrays.stream(keywords)
                .map(Skill::new)
                .collect(Collectors.toSet()));

        return new FilterSkillCommand(new PersonContainsSkillPredicate(skillSet));
    }

}

