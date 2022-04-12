package seedu.address.model.applicant;

import java.util.Comparator;

public class ApplicantNameComparator implements Comparator<Applicant> {
    private final String sortArgument;

    public ApplicantNameComparator(String sortArgument) {
        this.sortArgument = sortArgument;
    }

    @Override
    public int compare(Applicant o1, Applicant o2) {
        if (sortArgument.equals("asc")) {
            return o1.getName().fullName.compareTo(o2.getName().fullName);
        } else {
            return o2.getName().fullName.compareTo(o1.getName().fullName);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return obj == this
                || (obj instanceof ApplicantNameComparator
                && sortArgument.equals(((ApplicantNameComparator) obj).sortArgument));
    }

    @Override
    public String toString() {
        return this.sortArgument;
    }
}
