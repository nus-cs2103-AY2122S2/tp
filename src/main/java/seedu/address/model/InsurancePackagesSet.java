package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import seedu.address.model.person.InsurancePackage;

public class InsurancePackagesSet {

    private List<InsurancePackage> allPackages;

    public InsurancePackagesSet() {
        allPackages = new ArrayList<>();
    }

    public InsurancePackagesSet(InsurancePackagesSet s) {
        allPackages = s.allPackages;
    }

    /**
     * Replaces the contents of the packages list.
     */
    public void setPackages(List<InsurancePackage> allPackages) {
        this.allPackages = allPackages;
    }

    //// person-level operations

    /**
     * Returns true if a insurancePackage with the same identity exists in the set.
     */
    public boolean hasPackage(InsurancePackage insurancePackage) {
        requireNonNull(insurancePackage);
        return allPackages.contains(insurancePackage);
    }

    /**
     * Adds a package to the set of insurance packages, if it does not exist yet.
     */
    public void addPackage(InsurancePackage p) {
        if (!allPackages.contains(p)) {
            allPackages.add(p);
        }
    }

    public List<InsurancePackage> getPackagesList() {
        return allPackages;
    }


    /**
     * Replaces the given package in the list, given the package name, with the given package description.
     * {@code target} must exist in the address book.
     */
    public void setPackage(String targetPackageName, String newPackageDesc) {
        requireAllNonNull(targetPackageName, newPackageDesc);
        for (InsurancePackage p: allPackages) {
            if (p.getPackageName().equals(targetPackageName)) {
                p.setPackageDescription(newPackageDesc);
                break;
            }
        }
    }

    /**
     * Removes {@code key} from this {@code InsurancePackagesSet}.
     * {@code key} must exist in the set.
     */
    public void removePackage(InsurancePackage key) {
        allPackages.remove(key);
    }

    public int numPackages() {
        return allPackages.size();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof InsurancePackagesSet)) {
            return false;
        }

        InsurancePackagesSet otherPackage = (InsurancePackagesSet) other;
        return new HashSet<>(otherPackage.getPackagesList()).equals(new HashSet<>(allPackages));
    }
}
