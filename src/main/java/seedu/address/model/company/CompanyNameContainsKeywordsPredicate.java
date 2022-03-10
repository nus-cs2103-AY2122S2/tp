package seedu.address.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.role.Role;

/**
 * Holds the keywords {@code roleNameKeywords} and {@code companyNameKeywords} in
 * order to look for matches with stored {@code Company} and its {@code Role}.
 * <p></p>
 * If {@code companyNameKeywords} is empty, look for matches only on {@code Role}
 * with {@code roleNameKeywords}.
 * <br>
 * If {@code roleNameKeywords} is empty, look for matches only on {@code Company}
 * with {@code companyNameKeywords}.
 * <br>
 * If both {@code companyNameKeywords} and {@code roleNameKeywords} are available,
 * within those {@code Company} that matches {@code companyNameKeywords}, look for
 * matches on {@code Role} with {@code roleNameKeywords}
 */
public class CompanyNameContainsKeywordsPredicate implements Predicate<Company> {
    private final List<String> roleNameKeywords;
    private final List<String> companyNameKeywords;

    /**
     * Constructor for {@code CompanyNamecontainsKeywordsPredicate} that stores keywords
     * {@code roleNameKeywords} and {@code companyNameKeywords}.
     *
     * @param roleNameKeywords List of strings representing role name keywords entered by user
     * @param companyNameKeywords List of strings representing company name keywords entered by user
     */
    public CompanyNameContainsKeywordsPredicate(List<String> roleNameKeywords, List<String> companyNameKeywords) {
        this.roleNameKeywords = roleNameKeywords;
        this.companyNameKeywords = companyNameKeywords;
    }

    /**
     * Returns a boolean value if the {@code Company} contains {@code Role} with
     * a name that matches one of the Strings in {@code roleNameKeywords}.
     *
     * @param company {@code Company} to be evaluated
     * @return Boolean if {@code Role} matches any of the {@code roleNameKeywords}
     */
    public boolean hasRoleNameKeywords(Company company) {
        List<Role> roles = company.getRoleManager().getRoles();

        return roleNameKeywords.stream().anyMatch(keyword -> roles.stream()
                .anyMatch(role -> StringUtil.containsWordIgnoreCase(role.getName().fullName, keyword)));
    }

    /**
     * Returns a boolean value when the {@code Company} name matches
     * one of the Strings in {@code companyNameKeywords}.
     *
     * @param company {@code Company} to be evaluated
     * @return Boolean if {@code Company} matches any of the {@code companyNameKeywords}
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
