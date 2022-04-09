package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Email;
import seedu.address.model.person.GithubUsername;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.team.Skill;
import seedu.address.model.team.Team;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses multiple {@code oneBasedIndex} in string format and returns a list of {@code Index}
     *
     * @param oneBasedIndices the string representation of indices separated with the given regex
     * @param regex           regular expression to separate the indices in String
     * @return the list of indices
     * @throws ParseException if one of the arguments cannot be interpreted as an {@code Index}
     */
    public static LinkedList<Index> parseIndices(String oneBasedIndices, String regex) throws ParseException {
        String[] numbers = oneBasedIndices.split(regex);
        LinkedList<Index> indices = new LinkedList<>();
        for (String number : numbers) {
            number = number.trim();
            Index indexToAdd = parseIndex(number);
            // ignore duplicate indexes
            if (indices.contains(indexToAdd)) {
                continue;
            }
            indices.add(indexToAdd);
        }
        return indices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String username} into an {@code GithubUsername}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code username} is invalid.
     */
    public static GithubUsername parseGithubUsername(String username) throws ParseException {
        requireNonNull(username);
        String trimmedUsername = username.trim();
        if (!GithubUsername.isValidUsername(trimmedUsername)) {
            throw new ParseException(GithubUsername.MESSAGE_CONSTRAINTS);
        }
        return new GithubUsername(trimmedUsername);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String team} into a {@code Team}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code team} is invalid.
     */
    public static Team parseTeam(String team) throws ParseException {
        requireNonNull(team);
        String trimmed = team.trim();
        if (!Team.isValidTeamName(trimmed)) {
            throw new ParseException(Team.MESSAGE_CONSTRAINTS);
        }
        return new Team(trimmed);
    }

    /**
     * Parses a {@code String skill} into a {@code skill}.'
     * <p>
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code skill} is invalid.
     */
    public static Skill parseSkill(String skill) throws ParseException {
        requireNonNull(skill);
        String[] trimmedSkill = skill.trim().split("_");
        if (trimmedSkill.length != 2) {
            throw new ParseException(Skill.SKILL_INPUT_CONSTRAINTS);
        }
        String skillName = trimmedSkill[0];
        String trimmedSkillName = skillName.trim();
        if (!Skill.isValidSkillProficiencyInteger(trimmedSkill[1])) {
            throw new ParseException(Skill.PROFICIENCY_CONSTRAINTS_INTEGER);
        }

        if (!StringUtil.isNonZeroUnsignedInteger(trimmedSkill[1])) {
            throw new ParseException(Skill.PROFICIENCY_CONSTRAINTS_INTEGER);
        }

        int skillProficiency = Integer.parseInt(trimmedSkill[1]);

        if (!Skill.isValidSkillName(trimmedSkillName)) {
            throw new ParseException(Skill.NAME_CONSTRAINTS);
        }

        if (!Skill.isValidSkillProficiencyRange(skillProficiency)) {
            throw new ParseException(Skill.PROFICIENCY_CONSTRAINTS_RANGE);
        }

        return new Skill(trimmedSkillName, skillProficiency);
    }

    /**
     * Parses {@code Collection<String> teams} into a {@code Set<Team>}.
     */
    public static Set<Team> parseTeams(Collection<String> teams) throws ParseException {
        requireNonNull(teams);
        final Set<Team> teamSet = new HashSet<>();
        for (String teamName : teams) {
            teamSet.add(parseTeam(teamName));
        }
        return teamSet;
    }


    /**
     * Converts userInput of team arguments to {@code Set} of {@code Team}s.
     *
     * @param optionalTeamNames The userInput provided. Is empty if the user did not provide any team argument.
     * @param separator         The Regex used to parse multiple teamNames.
     * @return Set of teams.
     * @throws ParseException If a teamName cannot be parsed.
     */
    public static Set<Team> parseTeamsWithRegex(Optional<String> optionalTeamNames, String separator)
        throws ParseException {
        Set<Team> teamSet = new HashSet<>();
        if (!optionalTeamNames.isPresent()) {
            return teamSet;
        }
        String teamNames = optionalTeamNames.get();
        String[] separatedTeamNames = teamNames.split(separator, 0);
        if (!(separatedTeamNames.length == 1 && separatedTeamNames[0].equals(""))) {
            for (String teamName : separatedTeamNames) {
                Team team = ParserUtil.parseTeam(teamName);
                teamSet.add(team);
            }
        }
        return teamSet;
    }

    /**
     * Converts userInput of skills to {@code Set} of {@code Skill}s
     *
     * @param optionalSkillNamesWithLvl UserInput of the skills.
     * @param separator Regex to split the userInput and identify each skill.
     * @return The {@code Set} of {@code Skill} identified from UserInput.
     * @throws ParseException If the skill with level specified cannot be parsed.
     */
    public static Set<Skill> parseSkillsWithRegex(Optional<String> optionalSkillNamesWithLvl, String separator)
        throws ParseException {
        Set<Skill> skillSet = new HashSet<>();
        if (!optionalSkillNamesWithLvl.isPresent()) {
            return skillSet;
        }
        String skillNamesWithLvl = optionalSkillNamesWithLvl.get();
        String[] separatedSkillNameWithLvl = skillNamesWithLvl.split(separator, 0);
        if (!(separatedSkillNameWithLvl.length == 1 && separatedSkillNameWithLvl[0].equals(""))) {
            for (String skillNameWithLvl : separatedSkillNameWithLvl) {
                Skill skill = ParserUtil.parseSkill(skillNameWithLvl);
                skillSet.add(skill);
            }
        }
        return skillSet;
    }

}
