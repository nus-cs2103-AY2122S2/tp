package seedu.address.model.role;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class RoleNameContainsKeywordsPredicate implements Predicate<Role> {
    private final List<String> roleNameKeywords;

    public RoleNameContainsKeywordsPredicate(List<String> roleNameKeywords) {
        this.roleNameKeywords = roleNameKeywords;
    }

    @Override
    public boolean test(Role role) {

        return roleNameKeywords.isEmpty() || roleNameKeywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(role.getName().fullName, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                // instanceof handles nulls
                || (other instanceof RoleNameContainsKeywordsPredicate
                // state check
                && roleNameKeywords.equals(((RoleNameContainsKeywordsPredicate) other).roleNameKeywords));
    }
}
