package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPLICATION_STATUS_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOBTITLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.application.Application;
import seedu.address.model.tag.ApplicationStatusTagType;
import seedu.address.model.tag.PriorityTagType;

/**
 * Adds an application to InternApply.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds an application to InternApply. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_JOBTITLE + "JOBTITLE "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]... "
            + "[" + PREFIX_PRIORITY_TAG + "PRIORITY_TAG] "
            + "[" + PREFIX_APPLICATION_STATUS_TAG + "APPLICATION_STATUS_TAG]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Shopee "
            + PREFIX_JOBTITLE + "Software Engineer Intern "
            + PREFIX_PHONE + "87438807 "
            + PREFIX_EMAIL + "hr@shopee.sg "
            + PREFIX_ADDRESS + "5 Science Park Dr, #06-40 "
            + PREFIX_TAG + "SoftwareEngineering "
            + PREFIX_TAG + "SingaporeBased "
            + PREFIX_PRIORITY_TAG + "HIGH "
            + PREFIX_APPLICATION_STATUS_TAG + "APPLIED";


    public static final String MESSAGE_SUCCESS = "New application added: %1$s";
    public static final String MESSAGE_DUPLICATE_APPLICATION = "A duplicate application with the same name, job title "
            + "and optional tags already exists in InternApply";

    public static final String MESSAGE_APPLICATION_STATUS_TAG = "Application status tag must be : "
            + ApplicationStatusTagType.getAllTypesInString();

    public static final String MESSAGE_PRIORITY_TAG = "Priority tag must be : "
            + PriorityTagType.getAllTypesInString();

    private final Application toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Application}
     */
    public AddCommand(Application application) {
        requireNonNull(application);
        toAdd = application;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasApplication(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_APPLICATION);
        }

        model.addApplication(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
