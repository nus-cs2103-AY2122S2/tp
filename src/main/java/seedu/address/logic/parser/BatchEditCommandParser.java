package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.BatchEditCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

public class BatchEditCommandParser implements Parser<BatchEditCommand> {
    @Override
    public BatchEditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_SKILL, PREFIX_TEAM);
        LinkedList<Index> indices;

        try {
            indices = ParserUtil.parseIndices(argMultimap.getPreamble(), " ");
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, BatchEditCommand.MESSAGE_USAGE), pe);
        }


        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_SKILL).isPresent()) {
            String[] separatedArgs = argMultimap.getValuesWithRegex(PREFIX_SKILL, " ");
            Set<Skill> skillSet = new HashSet<>();
            if (!(separatedArgs.length == 1 && separatedArgs[0].equals(""))) {
                for (String arg : separatedArgs) {
                    Skill skill = ParserUtil.parseSkill(arg);
                    skillSet.add(skill);
                }
            }
            SkillSet skills = new SkillSet(skillSet);
            editPersonDescriptor.setSkillSet(skills);
        }

        if (argMultimap.getValue(PREFIX_TEAM).isPresent()) {
            String[] separatedArgs = argMultimap.getValuesWithRegex(PREFIX_TEAM, " ");
            Set<Team> teams = new HashSet<>();
            if (!(separatedArgs.length == 1 && separatedArgs[0].equals(""))) {
                for (String arg : separatedArgs) {
                    Team team = ParserUtil.parseTeam(arg);
                    teams.add(team);
                }
            }
            editPersonDescriptor.setTeams(teams);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(BatchEditCommand.MESSAGE_NOT_EDITED);
        }
        return new BatchEditCommand(indices, editPersonDescriptor);
    }

}
