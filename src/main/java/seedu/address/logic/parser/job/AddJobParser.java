package seedu.address.logic.parser.job;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import java.util.stream.Stream;

import seedu.address.logic.commands.job.AddJob;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.Prefix;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;

public class AddJobParser implements Parser<AddJob> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddJob
     * and returns an AddJob object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    @Override
    public AddJob parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_JOBTITLE, PREFIX_COMPANY_NAME,
                        PREFIX_ADDRESS, PREFIX_QUALIFICATION,
                        PREFIX_JOB_POSITION, PREFIX_SALARY
                );

        if (!arePrefixesPresent(argMultimap, PREFIX_JOBTITLE, PREFIX_COMPANY_NAME, PREFIX_ADDRESS, PREFIX_QUALIFICATION,
                PREFIX_JOB_POSITION, PREFIX_SALARY)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddJob.MESSAGE_USAGE));
        }

        JobTitle jobTitle = ParserUtil.parseJobTitle(argMultimap.getValue(PREFIX_JOBTITLE).get());
        CompanyName company = ParserUtil.parseCompanyName(argMultimap.getValue(PREFIX_COMPANY_NAME).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Qualification qualification = ParserUtil.parseQualification(argMultimap.getValue(PREFIX_QUALIFICATION).get());
        Position position = ParserUtil.parsePosition(argMultimap.getValue(PREFIX_JOB_POSITION).get());
        Salary salary = ParserUtil.parseSalary(argMultimap.getValue(PREFIX_SALARY).get());

        Job job = new Job(jobTitle, company, address,
                qualification, position, salary
        );

        return new AddJob(job);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
