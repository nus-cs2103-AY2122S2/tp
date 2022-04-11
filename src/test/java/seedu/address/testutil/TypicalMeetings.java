package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class containing a list of {@code Meeting} objects to be used in tests.
 */
public class TypicalMeetings {

    public static final Meeting WITH_ALICE = new MeetingBuilder()
            .withStartDateTime("2022-01-01T12:06:52.783166")
            .withEndDateTime("2022-01-02T12:06:52.783295")
            .withClient(new ClientBuilder()
                    .withName("Alice Pauline")
                    .withPhone("94351253")
                    .withEmail("alice@example.com")
                    .withAddress("123, Jurong West Ave 6, #08-111")
                    .build())
            .withLabel("Lunch")
            .build();

    public static final Meeting WITH_BENSON = new MeetingBuilder()
            .withStartDateTime("2023-01-03T12:07:01.722322")
            .withEndDateTime("2023-01-04T12:07:01.722430")
            .withClient(new ClientBuilder()
                    .withName("Benson Meier")
                    .withPhone("98765432")
                    .withEmail("johnd@example.com")
                    .withAddress("311, Clementi Ave 2, #02-25")
                    .build())
            .withLabel("")
            .build();


    private TypicalMeetings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical clients and meetings.
     */
    public static AddressBook getTypicalAddressBookWithMeetings() {
        AddressBook ab = new AddressBook();
        for (Client client : TypicalClients.getTypicalClients()) {
            ab.addClient(client);
        }

        for (Meeting meeting: getTypicalMeetings()) {
            ab.addMeeting(meeting);
        }

        return ab;
    }

    public static List<Meeting> getTypicalMeetings() {
        return new ArrayList<>(Arrays.asList(WITH_ALICE, WITH_BENSON));
    }
}
