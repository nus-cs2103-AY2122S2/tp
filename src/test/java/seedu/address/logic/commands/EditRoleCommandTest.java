package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_WHATSAPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STIPEND_SOFTWARE_ENGINEER;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ROLE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.CompanyList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;
import seedu.address.model.company.RoleManager;
import seedu.address.model.role.Role;
import seedu.address.testutil.EditRoleDescriptorBuilder;
import seedu.address.testutil.RoleBuilder;

public class EditRoleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedList_success() {
        Role editedRole = new RoleBuilder().build();
        EditRoleCommand.EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder(editedRole).build();
        EditRoleCommand editRoleCommand = new EditRoleCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_ROLE,
                descriptor);
        String expectedMessage = String.format(EditRoleCommand.MESSAGE_EDIT_ROLE_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new CompanyList(model.getCompanyList()), new UserPrefs());
        RoleManager expectedRM = expectedModel.getFilteredCompanyList().get(INDEX_FIRST_COMPANY.getZeroBased())
                .getRoleManager();
        Role expectedRole = expectedRM.getFilteredRoleList().get(INDEX_FIRST_ROLE.getZeroBased());
        expectedRM.setRole(expectedRole, editedRole);

        assertCommandSuccess(editRoleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Index indexLastCompany = Index.fromOneBased(model.getFilteredCompanyList().size());
        Company lastCompany = model.getFilteredCompanyList().get(indexLastCompany.getZeroBased());

        RoleManager roleManager = lastCompany.getRoleManager();
        Index indexLastRole = Index.fromOneBased(roleManager.getFilteredRoleList().size());
        Role lastRole = roleManager.getFilteredRoleList().get(indexLastRole.getZeroBased());

        RoleBuilder roleInList = new RoleBuilder(lastRole);
        Role editedRole = roleInList.withName(VALID_NAME_WHATSAPP)
                .withStatus(VALID_STATUS_SOFTWARE_ENGINEER).withStipend(VALID_STIPEND_SOFTWARE_ENGINEER)
                .build();

        EditRoleCommand.EditRoleDescriptor descriptor = new EditRoleDescriptorBuilder().withName(VALID_NAME_WHATSAPP)
                .withStatus(VALID_STATUS_SOFTWARE_ENGINEER).withStipend(VALID_STIPEND_SOFTWARE_ENGINEER).build();

        EditRoleCommand editRoleCommand = new EditRoleCommand(indexLastCompany, indexLastRole, descriptor);

        String expectedMessage = String.format(EditRoleCommand.MESSAGE_EDIT_ROLE_SUCCESS, editedRole);

        Model expectedModel = new ModelManager(new CompanyList(model.getCompanyList()), new UserPrefs());
        RoleManager expectedRM = expectedModel.getFilteredCompanyList().get(indexLastCompany.getZeroBased())
                .getRoleManager();
        expectedRM.setRole(lastRole, editedRole);

        assertCommandSuccess(editRoleCommand, model, expectedMessage, expectedModel);
    }



}
