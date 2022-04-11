package seedu.address.logic.parser.job;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.job.AddJob;
import seedu.address.model.applicant.Qualification;
import seedu.address.model.job.CompanyName;
import seedu.address.model.job.Job;
import seedu.address.model.job.JobTitle;
import seedu.address.model.job.Position;
import seedu.address.model.job.Salary;
import seedu.address.model.person.Address;

class AddJobParserTest {
    private AddJobParser parser = new AddJobParser();

    @Test
    void parse_allCompulsoryFieldsPresent_success() {
        String arg = " " + PREFIX_JOBTITLE + "Software Engineer "
                   + PREFIX_COMPANY_NAME + "BitService Pte Ltd "
                   + PREFIX_ADDRESS + "311, Clementi Ave 2 "
                   + PREFIX_QUALIFICATION + "Bachelors Degree "
                   + PREFIX_JOB_POSITION + "ft "
                   + PREFIX_SALARY + "3000-4000";

        Job job = new Job(new JobTitle("Software Engineer"), new CompanyName("BitService Pte Ltd"),
                new Address("311, Clementi Ave 2"),
                new Qualification("Bachelors Degree"), new Position("ft"),
                new Salary("3000", "4000")
        );
        assertParseSuccess(parser, arg, new AddJob(job));
    }
}
