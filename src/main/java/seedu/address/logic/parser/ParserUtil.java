package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_INDEX;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.commands.applicant.SortApplicant;
import seedu.address.logic.commands.job.FindJob;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.ApplicantStatus;
import seedu.address.model.applicant.DateApplied;
import seedu.address.model.applicant.InterviewDate;
import seedu.address.model.applicant.JobId;
import seedu.address.model.applicant.Nric;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobIdSamePredicate;
import seedu.address.model.job.JobStatus;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.NameJobContainsKeywordsPredicate;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {
    public static final String MESSAGE_INVALID_ID = "ID is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (trimmedIndex.isEmpty()) {
            throw new ParseException(MESSAGE_INVALID_COMMAND_FORMAT);
        } else if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
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
     * Parses a {@code String interviewDate} into an {@code InterviewDate}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code interviewDate} is invalid.
     */
    public static InterviewDate parseInterviewDate(String interviewDate) throws ParseException {
        requireNonNull(interviewDate);
        String trimmedInterviewDate = interviewDate.trim();
        if (!InterviewDate.isValidInterviewDate(trimmedInterviewDate)) {
            throw new ParseException(InterviewDate.MESSAGE_CONSTRAINTS);
        }
        return new InterviewDate(trimmedInterviewDate);
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
     * Parses a {@code String DateApplied} into a {@code DateApplied}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static DateApplied parseDateApplied(String dateApplied) throws ParseException {
        requireNonNull(dateApplied);
        String trimmedDate = dateApplied.trim();
        // Include bottom when DateApplied class is merged
        if (!DateApplied.isValidDateApplied(trimmedDate)) {
            throw new ParseException(DateApplied.MESSAGE_CONSTRAINTS);
        }
        return new DateApplied(trimmedDate);
    }

    /**
     * Parses a {@code String nric} into a {@code Nric}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code nric} is invalid.
     */
    public static Nric parseNric(String nric) throws ParseException {
        requireNonNull(nric);
        String trimmedNric = nric.trim();
        if (!Nric.isValidNric(trimmedNric)) {
            throw new ParseException(Nric.MESSAGE_CONSTRAINTS);
        }
        return new Nric(trimmedNric);
    }

    /**
     * Parses a {@code String job} into a {@code Job}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Job} is invalid.
     */
    public static JobId parseJob(String job) throws ParseException {
        requireNonNull(job);
        String trimmedJob = job.trim();
        if (!JobId.isValidJobId(trimmedJob)) {
            throw new ParseException(JobId.MESSAGE_CONSTRAINTS);
        }
        return new JobId(trimmedJob);
    }

    /**
     * Parses a {@code String qualification} into a {@code Qualification}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Qualification} is invalid.
     */
    public static Qualification parseQualification(String qualification) throws ParseException {
        requireNonNull(qualification);
        String trimmedQualification = qualification.trim();
        if (!Qualification.isValidQualification(trimmedQualification)) {
            throw new ParseException(Qualification.MESSAGE_CONSTRAINTS);
        }
        return new Qualification(trimmedQualification);
    }

    /**
     * Parses a {@code String salary} into a {@code Salary}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Salary} is invalid.
     */
    public static Salary parseSalary(String salary) throws ParseException {
        requireNonNull(salary);
        String trimmedSalary = salary.trim();
        int index = trimmedSalary.indexOf("-");
        String startingSalary = trimmedSalary.substring(0, index).trim();
        String endSalary = trimmedSalary.substring(index + 1).trim();
        if (!Salary.isValidSalary(startingSalary, endSalary, trimmedSalary)) {
            throw new ParseException(Salary.MESSAGE_CONSTRAINTS);
        }
        return new Salary(startingSalary, endSalary);
    }

    /**
     * Parses a {@code String jobTitle} into a {@code JobTitle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code JobTitle} is invalid.
     */
    public static JobTitle parseJobTitle(String jobTitle) throws ParseException {
        requireNonNull(jobTitle);
        String trimmedJobTitle = jobTitle.trim();
        if (!JobTitle.isValidJobTitle(trimmedJobTitle)) {
            throw new ParseException(JobTitle.MESSAGE_CONSTRAINTS);
        }
        return new JobTitle(trimmedJobTitle);
    }
    /**
     * Parses a {@code String applicationStatus} into a {@code ApplicationStatus}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Application Status} is invalid.
     */
    // TODO: - Add status not interviewed and interviewed
    //       - return Applicant once the class Application Status is merged
    public static String parseApplicantStatus(String applicationStatus) throws ParseException {
        requireNonNull(applicationStatus);
        String trimmedApplicationStatus = applicationStatus.trim();

        String numericStatus;

        switch(trimmedApplicationStatus) {
        case "rejected":
            numericStatus = "0";
            break;
        case "pending":
            numericStatus = "1";
            break;
        case "interviewed":
            numericStatus = "2";
            break;
        case "accepted":
            numericStatus = "3";
            break;
        default:
            numericStatus = "-1";
        }

        if (!ApplicantStatus.isValidStatus(numericStatus)) {
            throw new ParseException(ApplicantStatus.MESSAGE_CONSTRAINTS);
        }
        return numericStatus;
    }

    /**
     * Parses {@code String id} into an {@code Integer} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static String parseId(String id) throws ParseException {
        String trimmedId = id.trim();
        // TODO: check if the ID is valid (there is applicant associated with the ID)
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedId)) {
            throw new ParseException(MESSAGE_INVALID_ID);
        }
        return trimmedId;
    }

    /**
     * Parses {@code String jobstatus} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code Job Status} is invalid.
     */
    public static JobStatus parseJobStatus(String jobStatus) throws ParseException {
        requireNonNull(jobStatus);
        String trimmedJobStatus = jobStatus.trim();
        if (!JobStatus.isValid(jobStatus)) {
            throw new ParseException(JobStatus.MESSAGE_CONSTRAINTS);
        }
        return new JobStatus(trimmedJobStatus);
    }

    /**
     * Parses {@code String Position} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code position} is invalid.
     */
    public static Position parsePosition(String position) throws ParseException {
        requireNonNull(position);
        String trimmedPosition = position.trim();
        if (!Position.validPosition(position)) {
            throw new ParseException(Position.MESSAGE_CONSTRAINTS);
        }
        return new Position(trimmedPosition);
    }

    /**
     * Parses {@code String companyName} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the given {@code Company Name} is invalid.
     */
    public static CompanyName parseCompanyName(String companyName) throws ParseException {
        requireNonNull(companyName);
        String trimmedCompanyName = companyName.trim();
        if (!CompanyName.isValidCompanyName(trimmedCompanyName)) {
            throw new ParseException(CompanyName.MESSAGE_CONSTRAINTS);
        }
        return new CompanyName(trimmedCompanyName);
    }

    /**
     * Parse {@code String applicantComparator} and returns it. Leading and trailing whitespaces will
     * be trimmed.
     */
    public static String parseApplicantComparator(String applicantComparator) throws ParseException {
        requireNonNull(applicantComparator);
        String trimmedComparator = applicantComparator.trim();
        if (!SortApplicant.isValid(trimmedComparator)) {
            throw new ParseException(SortApplicant.MESSAGE_CONSTRAINTS);
        }
        return trimmedComparator;
    }

    /**
     * Based on the value of (@code String keyword, boolean isName, boolean isID}, the method creates an instance of
     * Predicate
     *
     * @throws ParseException if the given {@code predicate} is invalid.
     */
    public static Predicate<Job> parsePredicate(String keyword, boolean isName, boolean isID) throws ParseException {
        requireNonNull(keyword);
        String trimmedKeyword = keyword.trim();

        if (trimmedKeyword.isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
        }
        if (isName) {
            String[] jobTitleKeywords = trimmedKeyword.split("\\s+");
            return new NameJobContainsKeywordsPredicate(Arrays.asList(jobTitleKeywords));
        } else if (isID) {
            return new JobIdSamePredicate(parseId(trimmedKeyword));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindJob.MESSAGE_USAGE));
        }
    }
}
