package seedu.address.model.person.comparators;

import java.util.Comparator;

import seedu.address.model.person.Person;
import seedu.address.model.person.ScheduledMeeting;

public class PersonMeetingComparator extends PersonFlagComparator implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        int flagCompare = super.compare(person1, person2);
        if (flagCompare == 0) {
            ScheduledMeeting person1Meeting = person1.getScheduledMeeting();
            ScheduledMeeting person2Meeting = person2.getScheduledMeeting();
            if (person1Meeting.hasMeetingScheduled() && person2Meeting.hasMeetingScheduled()) {
                return person1Meeting.compare(person2Meeting);
            } else if (person1Meeting.hasMeetingScheduled()) {
                return -1;
            } else if (person2Meeting.hasMeetingScheduled()) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return flagCompare;
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof PersonMeetingComparator;
    }
}
