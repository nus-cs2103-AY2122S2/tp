package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_BIG_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.ALICE;
import static seedu.address.testutil.TypicalEntries.BIG_BANK;
import static seedu.address.testutil.TypicalEntries.DBSSS;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CompanyBuilder;

public class CompanyTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Company company = new CompanyBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> company.getTags().remove(0));
    }

    @Test
    public void isSameEntry() {
        // same object -> returns true
        assertTrue(DBSSS.isSameEntry(DBSSS));

        // null -> returns false
        assertFalse(DBSSS.isSameEntry(null));

        // same name, all other attributes different -> returns true
        Company editedCompany = new CompanyBuilder(DBSSS).withPhone(VALID_PHONE_B).withEmail(VALID_EMAIL_B)
                .withAddress(VALID_ADDRESS_B).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(DBSSS.isSameEntry(editedCompany));

        // different name, all other attributes same -> returns false
        editedCompany = new CompanyBuilder(DBSSS).withName(VALID_COMPANY_BIG_BANK).build();
        assertFalse(DBSSS.isSameEntry(editedCompany));

        // name differs in case, all other attributes same -> returns true
        editedCompany = new CompanyBuilder(BIG_BANK).withName(VALID_COMPANY_BIG_BANK.toLowerCase()).build();
        assertTrue(BIG_BANK.isSameEntry(editedCompany));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_COMPANY_BIG_BANK + " ";
        editedCompany = new CompanyBuilder(BIG_BANK).withName(nameWithTrailingSpaces).build();
        assertFalse(BIG_BANK.isSameEntry(editedCompany));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Company dbsssCopy = new CompanyBuilder(DBSSS).build();
        assertTrue(DBSSS.equals(dbsssCopy));

        // same object -> returns true
        assertTrue(DBSSS.equals(DBSSS));

        // null -> returns false
        assertFalse(DBSSS.equals(null));

        // different type -> returns false
        assertFalse(DBSSS.equals(5));

        // different person -> returns false
        assertFalse(DBSSS.equals(BIG_BANK));

        // different name -> returns false
        Company editedCompany = new CompanyBuilder(DBSSS).withName(VALID_NAME_BOB).build();
        assertFalse(DBSSS.equals(editedCompany));

        // different phone -> returns false
        editedCompany = new CompanyBuilder(DBSSS).withPhone(VALID_PHONE_B).build();
        assertFalse(DBSSS.equals(editedCompany));

        // different email -> returns false
        editedCompany = new CompanyBuilder(DBSSS).withEmail(VALID_EMAIL_B).build();
        assertFalse(DBSSS.equals(editedCompany));

        // different address -> returns false
        editedCompany = new CompanyBuilder(DBSSS).withAddress(VALID_ADDRESS_B).build();
        assertFalse(ALICE.equals(editedCompany));

        // different tags -> returns false
        editedCompany = new CompanyBuilder(DBSSS).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DBSSS.equals(editedCompany));
    }
}
