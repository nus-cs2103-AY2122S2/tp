package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInsurancePackages.GOLD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;

public class CsvAdaptedInsurancePackageTest {

    private static final String EMPTY_INSURANCE_PACKAGE_NAME = "";
    private static final String VALID_INSURANCE_PACKAGE_NAME = GOLD.getPackageName();
    private static final String VALID_INSURANCE_PACKAGE_DESC = GOLD.getPackageDescription();

    @Test
    public void toModelType_validPackageDetails_returnsPackage() throws Exception {
        CsvAdaptedInsurancePackage p = new CsvAdaptedInsurancePackage(
                VALID_INSURANCE_PACKAGE_NAME, VALID_INSURANCE_PACKAGE_DESC);
        assertEquals(GOLD, p.toModelType());
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        CsvAdaptedInsurancePackage p = new CsvAdaptedInsurancePackage(null, VALID_INSURANCE_PACKAGE_DESC);
        String expectedMessage = CsvAdaptedInsurancePackage.MISSING_NAME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, p::toModelType);
    }

    @Test
    public void toModelType_emptyName_throwsIllegalValueException() {
        CsvAdaptedInsurancePackage p = new CsvAdaptedInsurancePackage(
                EMPTY_INSURANCE_PACKAGE_NAME, VALID_INSURANCE_PACKAGE_DESC);
        String expectedMessage = CsvAdaptedInsurancePackage.MISSING_NAME_MESSAGE_FORMAT;
        assertThrows(IllegalValueException.class, expectedMessage, p::toModelType);
    }
}
