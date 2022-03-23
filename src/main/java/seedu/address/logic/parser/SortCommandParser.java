package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.HashSet;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonBySkillProficiencyComparator;
import seedu.address.model.person.PersonContainsSkillPredicate;
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;

/**
 * Parses input arguments and creates a new SortCommand object
 */
public class SortCommandParser implements Parser<SortCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the SortCommand
     * and returns a SortCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public SortCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, SortCommand.MESSAGE_USAGE));
        }

        Skill skillToFilter = new Skill(trimmedArgs);
        HashSet<Skill> skillSet = new HashSet<>();
        skillSet.add(skillToFilter);
        PersonContainsSkillPredicate predicate = new PersonContainsSkillPredicate(new SkillSet(skillSet));
        PersonBySkillProficiencyComparator comparator = new PersonBySkillProficiencyComparator(skillToFilter);

        return new SortCommand(predicate, comparator);
    }

}
