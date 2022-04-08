package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_JANICE_STREET;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_SHOPSG;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_APPLIED;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_ENTRY;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCompanyCommand.EditCompanyDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Company;
import seedu.address.testutil.CompanyBuilder;
import seedu.address.testutil.EditCompanyDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCompanyCommand.
 */
public class EditCompanyCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        showCompany(model);
    }

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Company editedCompany = new CompanyBuilder().build();
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(editedCompany).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_ENTRY, descriptor);

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_EDIT_COMPANY_SUCCESS, editedCompany);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompany(expectedModel);
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);
        showCompany(expectedModel);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastCompany = Index.fromOneBased(model.getFilteredCompanyList().size());
        Company lastCompany = model.getFilteredCompanyList().get(indexLastCompany.getZeroBased());

        CompanyBuilder companyInList = new CompanyBuilder(lastCompany);
        Company editedCompany = companyInList.withName(VALID_COMPANY_JANICE_STREET).withPhone(VALID_PHONE_B)
                .withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_APPLIED).build();

        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_JANICE_STREET)
                .withPhone(VALID_PHONE_B).withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_APPLIED).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(indexLastCompany, descriptor);

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_EDIT_COMPANY_SUCCESS, editedCompany);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompany(expectedModel);
        expectedModel.setCompany(lastCompany, editedCompany);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_ENTRY, new EditCompanyDescriptor());
        Company editedCompany = model.getFilteredCompanyList().get(INDEX_FIRST_ENTRY.getZeroBased());

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_EDIT_COMPANY_SUCCESS, editedCompany);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompany(expectedModel);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showCompanyAtIndex(model, INDEX_FIRST_ENTRY);

        Company companyInFilteredList = model.getFilteredCompanyList().get(INDEX_FIRST_ENTRY.getZeroBased());
        Company editedCompany = new CompanyBuilder(companyInFilteredList).withName(VALID_COMPANY_SHOPSG).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_FIRST_ENTRY,
                new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_SHOPSG).build());

        String expectedMessage = String.format(EditCompanyCommand.MESSAGE_EDIT_COMPANY_SUCCESS, editedCompany);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        showCompany(expectedModel);
        expectedModel.setCompany(model.getFilteredCompanyList().get(0), editedCompany);

        assertCommandSuccess(editCompanyCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateCompanyUnfilteredList_failure() {
        Company firstCompany = model.getFilteredCompanyList().get(INDEX_FIRST_ENTRY.getZeroBased());
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder(firstCompany).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(INDEX_SECOND_ENTRY, descriptor);

        assertCommandFailure(editCompanyCommand, model, EditCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_duplicateCompanyFilteredList_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_ENTRY);

        // edit company in filtered list into a duplicate in address book
        Company companyInList = model.getAddressBook().getCompanyList().get(INDEX_SECOND_ENTRY.getZeroBased());
        EditCompanyCommand editCommand = new EditCompanyCommand(INDEX_FIRST_ENTRY,
                new EditCompanyDescriptorBuilder(companyInList).build());

        assertCommandFailure(editCommand, model, EditCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

    @Test
    public void execute_invalidCompanyIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        EditCompanyDescriptor descriptor = new EditCompanyDescriptorBuilder()
                .withName(VALID_COMPANY_JANICE_STREET).build();
        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editCompanyCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidCompanyIndexFilteredList_failure() {
        showCompanyAtIndex(model, INDEX_FIRST_ENTRY);
        Index outOfBoundIndex = INDEX_SECOND_ENTRY;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getCompanyList().size());

        EditCompanyCommand editCompanyCommand = new EditCompanyCommand(outOfBoundIndex,
                new EditCompanyDescriptorBuilder().withName(VALID_COMPANY_JANICE_STREET).build());

        assertCommandFailure(editCompanyCommand, model, Messages.MESSAGE_INVALID_ENTRY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final EditCompanyCommand standardCommand = new EditCompanyCommand(INDEX_FIRST_ENTRY, DESC_BIG_BANK);

        // same values -> returns true
        EditCompanyDescriptor copyDescriptor = new EditCompanyDescriptor(DESC_BIG_BANK);
        EditCompanyCommand commandWithSameValues = new EditCompanyCommand(INDEX_FIRST_ENTRY, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCompanyCommand(INDEX_SECOND_ENTRY, DESC_BIG_BANK)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCompanyCommand(INDEX_FIRST_ENTRY, DESC_JANICE_STREET)));
    }

    private void showCompany(Model model) {
        model.showCompanyList(Model.PREDICATE_SHOW_UNARCHIVED_ONLY);
    }

}
