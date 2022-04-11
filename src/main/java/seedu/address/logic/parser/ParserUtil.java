package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.Email;
import seedu.address.model.student.GitHub;
import seedu.address.model.student.Name;
import seedu.address.model.student.Telegram;
import seedu.address.model.tutorialgroup.TutorialGroup;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroSignedIntegerLessThanOrEqualToIntegerLimit(trimmedIndex)) {
            throw new ParseException(Index.MESSAGE_CONSTRAINT);
        }

        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
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
     * Parses a {@code String telegram} into a {@code Telegram}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code telegram} is invalid.
     */
    public static Telegram parseTelegram(String telegram) throws ParseException {
        if (telegram == null) {
            return new Telegram(null);
        }
        String trimmedTelegram = telegram.trim();
        if (!Telegram.isValidTelegram(trimmedTelegram)) {
            throw new ParseException(Telegram.MESSAGE_CONSTRAINTS);
        }
        return new Telegram(trimmedTelegram);
    }

    /**
     * Parses a {@code String gitHub} into an {@code GitHub}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code gitHub} is invalid.
     */
    public static GitHub parseGitHub(String gitHub) throws ParseException {
        if (gitHub == null) {
            return new GitHub(null);
        }
        String trimmedGitHub = gitHub.trim();
        if (!GitHub.isValidGitHub(trimmedGitHub)) {
            throw new ParseException(GitHub.MESSAGE_CONSTRAINTS);
        }
        return new GitHub(trimmedGitHub);
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
     * Parses a {@code String tutorialGroup} into a {@code TutorialGroup}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorialGroup} is invalid.
     */
    public static TutorialGroup parseTutorialGroup(String tutorialGroup) throws ParseException {
        requireNonNull(tutorialGroup);
        String trimmedTutorialGroup = tutorialGroup.trim();
        if (!TutorialGroup.isValidTutorialGroupName(trimmedTutorialGroup)) {
            throw new ParseException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        return new TutorialGroup(trimmedTutorialGroup.toUpperCase());
    }

    /**
     * Parses {@code Collection<String> tutorialGroups} into a {@code Set<TutorialGroup>}.
     */
    public static Set<TutorialGroup> parseTutorialGroups(Collection<String> tutorialGroups) throws ParseException {
        requireNonNull(tutorialGroups);
        final Set<TutorialGroup> tutorialGroupSet = new HashSet<>();
        for (String tutorialGroupName : tutorialGroups) {
            tutorialGroupSet.add(parseTutorialGroup(tutorialGroupName));
        }
        return tutorialGroupSet;
    }
}
