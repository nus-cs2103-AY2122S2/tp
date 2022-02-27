package seedu.address.testutil;

import seedu.address.model.CompanyList;
import seedu.address.model.company.Company;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code CompanyList ab = new AddressBookBuilder().withCompany("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private CompanyList companyList;

    public AddressBookBuilder() {
        companyList = new CompanyList();
    }

    public AddressBookBuilder(CompanyList companyList) {
        this.companyList = companyList;
    }

    /**
     * Adds a new {@code Company} to the {@code CompanyList} that we are building.
     */
    public AddressBookBuilder withCompany(Company company) {
        companyList.addCompany(company);
        return this;
    }

    public CompanyList build() {
        return companyList;
    }
}
