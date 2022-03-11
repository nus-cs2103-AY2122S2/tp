package seedu.address.model.company;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

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

    private final List<String> companyNameKeywords;

    /**
     * Constructor for {@code CompanyNamecontainsKeywordsPredicate} that stores keywords
     * {@code roleNameKeywords} and {@code companyNameKeywords}.
     *
     * @param companyNameKeywords List of strings representing company name keywords entered by user
     */

    public CompanyNameContainsKeywordsPredicate(List<String> companyNameKeywords) {
        this.companyNameKeywords = companyNameKeywords;
    }


    @Override
    public boolean test(Company company) {
        return companyNameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(company.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof CompanyNameContainsKeywordsPredicate
                // state check
                && companyNameKeywords.equals(((CompanyNameContainsKeywordsPredicate) other).companyNameKeywords));
    }

}
