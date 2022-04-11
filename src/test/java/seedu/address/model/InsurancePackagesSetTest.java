package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInsurancePackages.GOLD;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.InsurancePackage;

public class InsurancePackagesSetTest {

    private final InsurancePackagesSet packagesSet = new InsurancePackagesSet();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), packagesSet.getPackagesList());
    }


    @Test
    public void hasPackage_nullPackage_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> packagesSet.hasPackage(null));
    }

    @Test
    public void hasPackage_packageNotInInsurancePackagesSet_returnsFalse() {
        assertFalse(packagesSet.hasPackage(GOLD));
    }

    @Test
    public void hasPackage_packageInInsurancePackagesSet_returnsTrue() {
        packagesSet.addPackage(GOLD);
        assertTrue(packagesSet.hasPackage(GOLD));
    }

    @Test
    public void hasPackage_packageWithSameName_returnsTrue() {
        packagesSet.addPackage(GOLD);
        InsurancePackage editedGold = new InsurancePackage(
                GOLD.getPackageName(), GOLD.getPackageDescription() + "append random string");
        assertTrue(packagesSet.hasPackage(GOLD));
    }
}
