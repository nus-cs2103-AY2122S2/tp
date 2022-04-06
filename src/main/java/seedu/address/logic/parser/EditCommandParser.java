package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMAND_OPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GITHUB_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SKILL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEAM;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.team.Skill;
import seedu.address.model.team.SkillSet;
import seedu.address.model.team.Team;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        String indexDelimeter = "\\s+";
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_COMMAND_OPTION, PREFIX_NAME,
            PREFIX_PHONE, PREFIX_EMAIL, PREFIX_GITHUB_USERNAME, PREFIX_TEAM, PREFIX_SKILL);
        LinkedList<Index> indices;

        Index index;
        boolean isBatchEdit = isMultipleArgs(argMultimap.getPreamble(), indexDelimeter);
        if (isBatchEdit) {
            try {
                indices = ParserUtil.parseIndices(argMultimap.getPreamble(), indexDelimeter);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
            }
        } else {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
            }
        }

        boolean isResetMode = false;
        if (argMultimap.getValue(PREFIX_COMMAND_OPTION).isPresent()) {
            int optionDeclarationNumber = argMultimap.getAllValues(PREFIX_COMMAND_OPTION).size();
            // if there are multiple '-' present
            if (optionDeclarationNumber != 1) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_OPTIONS));
            }
            String option = argMultimap.getValue(PREFIX_COMMAND_OPTION).get();
            // if there there is any other option declared other than the one and only currently supported option, 'r'
            if (!option.equals(EditCommand.RESET_ARG)) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE_OPTIONS));
            }
            isResetMode = option.equals(EditCommand.RESET_ARG);
        }

        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editPersonDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editPersonDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editPersonDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_GITHUB_USERNAME).isPresent()) {
            editPersonDescriptor.setGithubUsername(
                ParserUtil.parseGithubUsername(argMultimap.getValue(PREFIX_GITHUB_USERNAME).get()));
        }

        if (argMultimap.getValue(PREFIX_SKILL).isPresent()) {
            List<String> separatedArgs = argMultimap.getValuesWithRegex(PREFIX_SKILL, "\\s?,\\s?");
            Set<Skill> skillSet = new HashSet<>();
            if (!(separatedArgs.size() == 1 && separatedArgs.get(0).equals(""))) {
                for (String arg : separatedArgs) {
                    Skill skill = ParserUtil.parseSkill(arg);
                    skillSet.add(skill);
                }
            }
            SkillSet skills = new SkillSet(skillSet);
            editPersonDescriptor.setSkillSet(skills);
        }

        if (argMultimap.getValue(PREFIX_TEAM).isPresent()) {
            List<String> separatedArgs = argMultimap.getValuesWithRegex(PREFIX_TEAM, "\\s?,\\s?");
            Set<Team> teams = new HashSet<>();
            if (!(separatedArgs.size() == 1 && separatedArgs.get(0).equals(""))) {
                for (String arg : separatedArgs) {
                    Team team = ParserUtil.parseTeam(arg);
                    teams.add(team);
                }
            }
            editPersonDescriptor.setTeams(teams);
        }

        if (!editPersonDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        EditCommand editCommand;

        if (isBatchEdit) {
            try {
                indices = ParserUtil.parseIndices(argMultimap.getPreamble(), indexDelimeter);
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE),
                    pe);
            }
            editCommand = new EditCommand(indices, editPersonDescriptor, isResetMode);
        } else {
            try {
                index = ParserUtil.parseIndex(argMultimap.getPreamble());
            } catch (ParseException pe) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
            }
            editCommand = new EditCommand(index, editPersonDescriptor, isResetMode);
        }
        return editCommand;

    }

    /**
     * Parses {@code Collection<String> teams} into a {@code Set<Team>} if {@code teams} is non-empty.
     * If {@code teams} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Team>} containing zero teams.
     */
    private Optional<Set<Team>> parseTeamsForEdit(Collection<String> teams) throws ParseException {
        assert teams != null;

        if (teams.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> set = teams.size() == 1 && teams.contains("") ? Collections.emptySet() : teams;
        return Optional.of(ParserUtil.parseTeams(set));
    }

    /**
     * Parses {@code Collection<String> skillset} into a {@code Set<Skill>} if {@code skill} is non-empty.
     * If {@code skill} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Skill>} containing zero tags.
     */
    private Optional<SkillSet> parseSkillSetForEdit(Collection<String> skill) throws ParseException {
        assert skill != null;

        if (skill.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> skillSet = skill.size() == 1 && skill.contains("") ? Collections.emptySet() : skill;
        return Optional.of(ParserUtil.parseSkillSet(skillSet));
    }

    private boolean isMultipleArgs(String argsWithDelimiter, String regex) {
        String[] args = argsWithDelimiter.split(regex, 0);
        return args.length > 1;
    }

}
