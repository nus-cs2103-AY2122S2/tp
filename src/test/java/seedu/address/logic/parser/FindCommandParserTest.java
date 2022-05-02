package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.candidate.predicate.ApplicationStatusContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.AvailabilityContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.CandidateContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.CourseContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.InterviewStatusContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.PhoneContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.SeniorityContainsKeywordsPredicate;
import seedu.address.model.candidate.predicate.StudentIdContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindNameCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindNameCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Amy", "Bob")));
        assertParseSuccess(parser, " k/Amy k/Bob f/name", expectedFindNameCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "    k/Amy \t  k/Bob  \t  f/name \t", expectedFindNameCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCourseCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCourseCommand =
                new FindCommand(new CourseContainsKeywordsPredicate(Arrays.asList("info", "Computer science")));
        assertParseSuccess(parser, " k/info k/Computer science f/course", expectedFindCourseCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  \t  k/info \t  k/Computer science \t \t  f/course \t", expectedFindCourseCommand);
    }

    @Test
    public void parse_validArgs_returnsFindPhoneCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindPhoneCommand =
                new FindCommand(new PhoneContainsKeywordsPredicate(Arrays.asList("987", "923")));
        assertParseSuccess(parser, " k/987 k/923 f/phone", expectedFindPhoneCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "  k/987 \t \t k/923  \t  f/phone \t", expectedFindPhoneCommand);
    }

    @Test
    public void parse_validArgs_returnsFindEmailCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindEmailCommand =
                new FindCommand(new EmailContainsKeywordsPredicate(Arrays.asList("@gmail", "we@mail.com")));
        assertParseSuccess(parser, " k/@gmail k/we@mail.com f/email", expectedFindEmailCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "    k/@gmail \t  k/we@mail.com  \t  f/email \t", expectedFindEmailCommand);
    }

    @Test
    public void parse_validArgs_returnsFindStudentIdCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindStudentIdCommand =
                new FindCommand(new StudentIdContainsKeywordsPredicate(Arrays.asList("E0324", "149")));
        assertParseSuccess(parser, " k/E0324 k/149 f/studentid", expectedFindStudentIdCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "    k/E0324 \t \t  k/149  \t  f/studentid \t", expectedFindStudentIdCommand);
    }

    @Test
    public void parse_validArgs_returnsFindStatusCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindInterviewStatusCommand =
                new FindCommand(new InterviewStatusContainsKeywordsPredicate(Arrays.asList("pending", "reject")));
        assertParseSuccess(parser, " k/pending k/reject f/intstatus", expectedFindInterviewStatusCommand);

        // multiple whitespaces between keywords
        FindCommand expectedFindApplicationStatusCommand =
                new FindCommand(new ApplicationStatusContainsKeywordsPredicate(Arrays.asList("pending", "reject")));
        assertParseSuccess(parser, " k/  pending    k/  reject   f/     appstatus",
                expectedFindApplicationStatusCommand);
    }

    @Test
    public void parse_validArgs_returnsFindSeniorityCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindSeniorityCommand =
                new FindCommand(new SeniorityContainsKeywordsPredicate(Arrays.asList("2", "com1")));
        assertParseSuccess(parser, " k/2 k/com1 f/seniority", expectedFindSeniorityCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCandidateCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCandidateCommand =
                new FindCommand(new CandidateContainsKeywordsPredicate(Arrays.asList("Amy", "computer science")));
        assertParseSuccess(parser, " k/Amy k/computer science f/all", expectedFindCandidateCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                "    k/Amy \t  k/computer science  \t  f/all \t", expectedFindCandidateCommand);

        // no field specified
        assertParseSuccess(parser, "    k/Amy \t  k/computer science  \t  f/ \t", expectedFindCandidateCommand);

        // no field specified
        assertParseSuccess(parser, "    k/Amy \t  k/computer science  \t  \t", expectedFindCandidateCommand);

        // no field specified
        expectedFindCandidateCommand =
                new FindCommand(new CandidateContainsKeywordsPredicate(Arrays.asList("Mon", "Fri")));
        assertParseSuccess(parser, "    k/Mon \t  k/Fri  \t  \t", expectedFindCandidateCommand);
    }

    @Test
    public void parse_validArgs_returnsFindAvailabilityCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindAvailabilityCommand =
                new FindCommand(new AvailabilityContainsKeywordsPredicate(Arrays.asList("Mon", "Fri")));
        assertParseSuccess(parser, " k/Mon k/Fri f/avail", expectedFindAvailabilityCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser,
                "    k/Mon \t  k/Fri  \t  f/avail \t", expectedFindAvailabilityCommand);

        // multiple whitespaces in keywords
        assertParseSuccess(parser,
                "    k/  Mon \t  k/     Fri  \t  f/avail \t", expectedFindAvailabilityCommand);
    }
}
