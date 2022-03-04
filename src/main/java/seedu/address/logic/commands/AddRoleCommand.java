package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.company.Company;
import seedu.address.model.role.Role;
import seedu.address.model.role.RoleManager;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.*;

/**
 * Adds a Role to the company.
 */
public class AddRoleCommand extends Command {

    //
    public static final String COMMAND_WORD = "addRole";

    //
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a role to the company listed. "
            + "Parameters: "
            + PREFIX_NAME + "ROLENAME "
            + PREFIX_STATUS + "STATUS "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_STIPEND + "STIPEND "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Software Engineer "
            + PREFIX_STATUS + "pending "
            + PREFIX_DEADLINE + "31-01-2022 23:59 "
            + PREFIX_DESCRIPTION + "Frontend "
            + PREFIX_STIPEND + "1000 ";

    ////
    public static final String MESSAGE_SUCCESS = "New role added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This role has a duplicate already stored in this company";

    private final Index companyIndex;
    private final Role toAddRole;

    /**
     * Creates an AddRoleCommand to add the specified {@code Company}
     */
    public AddRoleCommand(Index companyIndex, Role role) {
        requireAllNonNull(companyIndex, role);
        this.companyIndex = companyIndex;
        this.toAddRole = role;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Company> companyList = model.getFilteredCompanyList();
        if (companyIndex.getZeroBased() >= companyList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
        }

        Company currCompany = companyList.get(companyIndex.getZeroBased());
        RoleManager roleManager = currCompany.getRoleManager();
        if (roleManager.hasRole(toAddRole)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        roleManager.addRole(toAddRole);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAddRole));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddRoleCommand // instanceof handles nulls
                && toAddRole.equals(((AddRoleCommand) other).toAddRole));
    }
}
