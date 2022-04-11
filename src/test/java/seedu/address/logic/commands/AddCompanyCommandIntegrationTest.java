package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEntries.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.entry.Company;
import seedu.address.testutil.CompanyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCompanyCommand}.
 */
public class AddCompanyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newCompany_success() {
        Company validCompany = new CompanyBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addCompany(validCompany);

        assertCommandSuccess(new AddCompanyCommand(validCompany), model,
                String.format(AddCompanyCommand.MESSAGE_SUCCESS, validCompany), expectedModel);
    }

    @Test
    public void execute_duplicateCompany_throwsCommandException() {
        Company companyInList = model.getAddressBook().getCompanyList().get(0);
        assertCommandFailure(new AddCompanyCommand(companyInList), model, AddCompanyCommand.MESSAGE_DUPLICATE_COMPANY);
    }

}
