package seedu.address.logic.commands.job;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMPANY_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOB_POSITION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUALIFICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.applicant.JobId;
import seedu.address.model.job.Job;

public class AddJob extends Command {
    public static final String COMMAND_WORD = "addjob";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Job to ReCLIne. "
            + "Parameters: "
            + "*" + PREFIX_JOBTITLE + "JOB TITLE "
            + "*" + PREFIX_COMPANY_NAME + "COMPANY "
            + "*" + PREFIX_ADDRESS + "ADDRESS "
            + "*" + PREFIX_QUALIFICATION + "QUALIFICATION "
            + "*" + PREFIX_JOB_POSITION + "POSITION "
            + "*" + PREFIX_SALARY + "SALARY \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_JOBTITLE + "Devops Engineer "
            + PREFIX_COMPANY_NAME + "Ebiz Pte Ltd "
            + PREFIX_ADDRESS + "59 Hougang Road Blk 38 "
            + PREFIX_QUALIFICATION + "Bachelors in Computer Science "
            + PREFIX_JOB_POSITION + "ft "
            + PREFIX_SALARY + "3000-4000 ";

    public static final String MESSAGE_SUCCESS = "New job added: %1$s";
    public static final String MESSAGE_DUPLICATE_JOB = "This job already exists in the ReCLIne. "
            + "Jobs are considered to be duplicate if they have the same Company Name and Job Title.";

    private final Job toAdd;

    /**
     * Constructs an AddJob to add the specified {@code Job}
     */
    public AddJob(Job job) {
        requireNonNull(job);
        toAdd = job;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasJob(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_JOB);
        }

        Job jobWithId = new Job(toAdd.getJobTitle(), toAdd.getCompany(), new JobId(model.getIdCount()),
                toAdd.getAddress(), toAdd.getQualification(), toAdd.getJobStatus(), toAdd.getPosition(),
                toAdd.getSalary());
        model.addJob(jobWithId);
        return new CommandResult(String.format(MESSAGE_SUCCESS, jobWithId), false, true, true);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddJob // instanceof handles nulls
                && toAdd.equals(((AddJob) other).toAdd));
    }
}
