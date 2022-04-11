package seedu.address.model.client;

import java.util.Arrays;

public enum SortCriteria {
    numPolicies("Number of Policies"),
    premium("Premium"),
    lastContacted("Last Contacted"),
    DEFAULT("Default");

    private String criteria;

    SortCriteria(String criteria) {
        this.criteria = criteria;
    }

    public static boolean isValidEnum(String attribute) {
        return Arrays.stream(SortCriteria.values()).anyMatch(e -> e.name().equals(attribute));
    }

    @Override
    public String toString() {
        return criteria;
    }
}
