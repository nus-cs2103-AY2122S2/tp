package seedu.address.model.interview;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.*;

import seedu.address.model.applicant.*;
import seedu.address.model.tag.Tag;


/**
 * Represents an Interview in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Interview {

    //Data fields
    private final Applicant applicant;
    private final Date date;
    //private final Position position;

    /**
     * Every field must be present and not null.
     */
    public Interview(Applicant applicant, Date date) {
        requireAllNonNull(applicant, date);
        this.applicant = applicant;
        this.date = date;
        //this.position = position;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public Date getDate() {
        return date;
    }

//    public Position getPosition() {
//        return email;
//    }

    /**
     * Returns true if both interviews have the same data fields.
     * This defines a stronger notion of equality between two interviews.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Interview)) {
            return false;
        }

        Interview otherInterview = (Interview) other;
        return otherInterview.getApplicant().equals(getApplicant())
                && otherInterview.getDate().equals(getDate());
                //&& otherInterview.getPosition().equals(getPosition());
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(applicant.getName())
                .append("; Date: ")
                .append(getDate());
                //.append("; Position: ")
                //.append(getEmail());
        return builder.toString();
    }

}
