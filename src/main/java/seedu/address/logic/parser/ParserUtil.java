package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALNAME;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.FullMark;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.Weightage;
import seedu.address.model.attendance.Comment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.NusNetId;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorial.Day;
import seedu.address.model.tutorial.Time;
import seedu.address.model.tutorial.TutorialName;
import seedu.address.model.tutorial.Venue;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
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
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
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
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String name} into a {@code AssessmentName}.
     */
    public static AssessmentName parseAssessmentName(String name) throws ParseException {
        requireNonNull(name);
        final String trimmedName = name.trim();
        if (!AssessmentName.isValidAssessmentName(trimmedName)) {
            throw new ParseException(AssessmentName.MESSAGE_CONSTRAINTS);
        }
        return new AssessmentName(trimmedName);
    }

    /**
     * Parses {@code String weight} into a {@code Weightage}.
     */
    public static Weightage parseWeightage(String weight) throws ParseException {
        requireNonNull(weight);
        final String trimmedWeight = weight.trim();
        if (!Weightage.isValidWeightage(trimmedWeight)) {
            throw new ParseException(Weightage.MESSAGE_CONSTRAINTS);
        }
        return new Weightage(trimmedWeight);
    }

    /**
     * Parses {@code String fm} into a {@code FullMark}.
     */
    public static FullMark parseFullMark(String fm) throws ParseException {
        requireNonNull(fm);
        final String trimmedFm = fm.trim();
        if (!FullMark.isValidFullMark(trimmedFm)) {
            throw new ParseException(FullMark.MESSAGE_CONSTRAINTS);
        }
        return new FullMark(fm);
    }
    /**
    * Parses a {@code String tutorial} into an {@code Tutorial}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tutorial} is invalid.
     */
    public static TutorialName parseTutorialName(String tutorialName) throws ParseException {
        requireNonNull(tutorialName);
        String trimmedTutorialName = tutorialName.trim();
        if (!TutorialName.isValidTutorialName(trimmedTutorialName)) {
            throw new ParseException(TutorialName.MESSAGE_CONSTRAINTS);
        }
        return new TutorialName(trimmedTutorialName);
    }

    /**
     * Parses a {@code String venue} into an {@code Venue}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code venue} is invalid.
     */
    public static Venue parseVenue(String venue) throws ParseException {
        requireNonNull(venue);
        String trimmedVenue = venue.trim();
        if (!Venue.isValidVenue(trimmedVenue)) {
            throw new ParseException(Venue.MESSAGE_CONSTRAINTS);
        }
        return new Venue(trimmedVenue);
    }

    /**
     * Parses a {@code String day} into an {@code Day}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code day} is invalid.
     */
    public static Day parseDay(String day) throws ParseException {
        requireNonNull(day);
        String trimmedDay = day.trim();
        if (!Day.isValidDay(trimmedDay)) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        return new Day(trimmedDay);
    }

    /**
     * Parses a {@code String time} into an {@code Time}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code time} is invalid.
     */
    public static Time parseTime(String time) throws ParseException {
        requireNonNull(time);
        String trimmedTime = time.trim();
        if (!Time.isValidTime(trimmedTime)) {
            throw new ParseException(Time.MESSAGE_CONSTRAINTS);
        }
        return new Time(trimmedTime);
    }

    /**
     * Parses a {@code String studentId} into an {@code NusNetId}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code studentId} is invalid.
     */
    public static NusNetId parseStudentId(String studentId) throws ParseException {
        requireNonNull(studentId);
        String trimmedId = studentId.trim();
        if (!NusNetId.isValidId(trimmedId)) {
            throw new ParseException(NusNetId.MESSAGE_CONSTRAINTS);
        }
        return new NusNetId(trimmedId);
    }

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddStudentCommand(Name name, NusNetId studentId, TutorialName tutorialName) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(name, studentId, tutorialName);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getStudentDetails(Name name, NusNetId studentId, TutorialName tutorialName) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + name.toString() + " ");
        sb.append(PREFIX_STUDENTID + studentId.toString() + " ");
        sb.append(PREFIX_TUTORIALNAME + tutorialName.toString());
        return sb.toString();
    }


    /**
     * Parses a {@code String s} into a score String.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code s} is invalid.
     */
    public static String parseScore(String s) throws ParseException {
        requireNonNull(s);
        String trimmedScore = s.trim();
        if (!Score.isValidScore(s)) {
            throw new ParseException(Score.MESSAGE_CONSTRAINTS);
        }
        return trimmedScore;
    }

    /**
     * Parses a {@code String s} into a week integer.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code s} is invalid.
     */
    public static int parseWeek(String s) throws ParseException {
        requireNonNull(s);
        String trimmedScore = s.trim();
        if (!s.chars().allMatch(Character::isDigit)
                || !(Integer.parseInt(s) > 0)
                || !(Integer.parseInt(s) <= 60)) {
            throw new ParseException("Weeks should be presented in positive integers between 1-60!");
        }
        return Integer.parseInt(s);
    }

    /**
     * Parses a {@code String s} into a comment.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code s} is invalid.
     */
    public static Comment parseComment(String s) throws ParseException {
        requireNonNull(s);
        String trimmedComment = s.trim();
        if (!Comment.isValidComment(s)) {
            throw new ParseException("An empty comment cannot be added!");
        }
        return new Comment(trimmedComment);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
