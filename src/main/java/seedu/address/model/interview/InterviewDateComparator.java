package seedu.address.model.interview;

import java.util.Comparator;

public class InterviewDateComparator implements Comparator<Interview> {
    private final String sortArgument;

    public InterviewDateComparator(String sortArgument) {
        this.sortArgument = sortArgument;
    }

    @Override
    public int compare(Interview o1, Interview o2) {
        if (sortArgument.equals("asc")) {
            return o1.getDate().compareTo(o2.getDate());
        } else {
            return o2.getDate().compareTo(o1.getDate());
        }
    }
}
