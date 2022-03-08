package seedu.address.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.role.Role;

/**
 * Tests that a {@code Company}'s {@code Name} matches any of the keywords given.
 */
public class CompanyNameContainsKeywordsPredicate implements Predicate<Company> {
    private final List<String> roleNameKeywords;
    private final List<String> companyNameKeywords;

    /**
     * @param roleNameKeywords List of strings representing role name keywords entered by user input
     * @param companyNameKeywords List of strings representing company name keywords entered by user input
     */
    public CompanyNameContainsKeywordsPredicate(List<String> roleNameKeywords, List<String> companyNameKeywords) {
        this.roleNameKeywords = roleNameKeywords;
        this.companyNameKeywords = companyNameKeywords;
    }

    /**
     * Returns true when the <code>Company</code> contains <code>Role</code> with
     * a name that matches one of the Strings in <code>roleNameKeywords</code>
     * @param company <code>Company</code> to be evaluated
     * @return
     */
    public boolean hasRoleNameKeywords(Company company) {
        List<Role> roles = company.getRoleManager().getRoles();

        return roleNameKeywords.stream().anyMatch(keyword -> roles.stream()
                .anyMatch(role -> StringUtil.containsWordIgnoreCase(role.getName().fullName, keyword)));
    }

    /**
     * Returns true when the <code>Company</code> name matches one of the Strings in <code>companyNameKeywords</code>
     * @param company <code>Company</code> to be evaluated
     * @return
     */
    public boolean hasCompanyNameKeywords(Company company) {
        return companyNameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(company.getName().fullName, keyword));
    }

    @Override
    public boolean test(Company company) {
        if (companyNameKeywords.isEmpty()) {
            return hasRoleNameKeywords(company);
        }

        if (roleNameKeywords.isEmpty()) {
            return hasCompanyNameKeywords(company);
        }

        return hasCompanyNameKeywords(company) && hasRoleNameKeywords(company);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof CompanyNameContainsKeywordsPredicate
                // state check
                && roleNameKeywords.equals(((CompanyNameContainsKeywordsPredicate) other).roleNameKeywords)
                && companyNameKeywords.equals(((CompanyNameContainsKeywordsPredicate) other).companyNameKeywords));
    }

}
