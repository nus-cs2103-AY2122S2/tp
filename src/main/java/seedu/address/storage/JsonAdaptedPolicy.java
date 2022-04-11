package seedu.address.storage;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.client.Name;
import seedu.address.model.policy.Policy;
import seedu.address.model.policy.Premium;

/**
 * Jackson-friendly version of {@link Policy}.
 */
class JsonAdaptedPolicy {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Policy's %s field is missing!";

    private final String policy;
    private final String company;
    private final String policyManager;
    private final String premium;

    /**
     * Constructs a {@code JsonAdaptedPolicy} with the given {@code policyName}, {@code company},
     * {@code policyManager} and {@code premium}.
     */
    @JsonCreator
    JsonAdaptedPolicy(@JsonProperty("policy") String policy, @JsonProperty("company") String company,
                      @JsonProperty("policyManager") String policyManager, @JsonProperty("premium") String premium) {
        this.policy = policy;
        this.company = company;
        this.policyManager = policyManager;
        this.premium = premium;
    }

    /**
     * Converts a given {@code Policy} into this class for Jackson use.
     */
    public JsonAdaptedPolicy(Policy source) {
        policy = source.getName().toString();
        company = source.getCompany().toString();
        policyManager = source.getPolicyManager().toString();
        premium = source.getPremium().toString();
    }

    /**
     * Converts this Jackson-friendly adapted Policy object into the model's {@code Policy} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Policy toModelType() throws IllegalValueException {
        if (policy == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "name"));
        }

        if (company == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "company"));
        }

        if (policyManager == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "policy manager"));
        }

        if (premium == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "premium"));
        }

        if (!Name.isValidName(policy) || !Name.isValidName(company) || !Name.isValidName(policyManager)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        if (!Premium.isValidPremium(premium)) {
            throw new IllegalValueException(Premium.MESSAGE_CONSTRAINTS);
        }

        return new Policy(new Name(policy), new Name(company), new Name(policyManager), new Premium(premium));
    }

}
