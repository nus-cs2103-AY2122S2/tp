package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.InsurancePackagesSet;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.InsurancePackage;

public class DeletePackageCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new InsurancePackagesSet());
    private InsurancePackage validPackage = new InsurancePackage("TestName", "TestDesc");
    private InsurancePackage validPackageTwo = new InsurancePackage("TestNameThisWillNotExistInside",
            "TestDesc");

    @Test
    public void deleteExistingPackage_success() {
        model.addInsurancePackage(validPackage);
        try {
            model.deleteInsurancePackage(validPackage);
            assert !model.hasInsurancePackage(validPackage);
        } catch (Exception err) {
            fail();
        }
    }

    @Test
    public void deleteNonExistingPackage_noExceptionThrown() {
        assert !model.hasInsurancePackage(validPackageTwo);
        try {
            model.deleteInsurancePackage(validPackageTwo);
        } catch (Exception err) {
            fail();
        }
    }
}
