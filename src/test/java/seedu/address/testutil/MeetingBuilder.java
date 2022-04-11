package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * A utility class to help with building Meeting objects.
 */
public class MeetingBuilder {
    public static final String DEFAULT_START_DATETIME = "2022-01-01T12:06:52.783166";
    public static final String DEFAULT_END_DATETIME = "2022-01-02T12:06:52.783295";
    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "94351253";
    public static final String DEFAULT_EMAIL = "alice@example.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_LABEL = "Lunch";

    public static final String DEFAULT_POLICY_NAME = "Big Insurance Policy";
    public static final String DEFAULT_COMPANY = "Big Insurance Company";
    public static final String DEFAULT_POLICY_MANAGER = "Vijay";
    public static final String DEFAULT_PREMIUM = "100";
    public static final String DEFAULT_NOTE = "Test Note";

    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Client client;
    private String label;


    /**
     * Creates a {@code MeetingBuilder} with the default details.
     */
    public MeetingBuilder() {
        startDateTime = LocalDateTime.parse(DEFAULT_START_DATETIME);
        endDateTime = LocalDateTime.parse(DEFAULT_END_DATETIME);
        client = new ClientBuilder()
                .withName(DEFAULT_NAME)
                .withPhone(DEFAULT_PHONE)
                .withEmail(DEFAULT_EMAIL)
                .withAddress((DEFAULT_ADDRESS))
                .build();
        label = DEFAULT_LABEL;
    }

    /**
     * Initializes the MeetingBuilder with the data of {@code meetingToCopy}.
     */
    public MeetingBuilder(Meeting meetingToCopy) {
        startDateTime = meetingToCopy.getStartDateTime();
        endDateTime = meetingToCopy.getEndDateTime();
        client = meetingToCopy.getClient();
        label = meetingToCopy.getLabel();
    }

    /**
     * Sets the {@code startDateTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withStartDateTime(String startDateTime) {
        this.startDateTime = LocalDateTime.parse(startDateTime);
        return this;
    }

    /**
     * Sets the {@code endDateTime} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withEndDateTime(String endDateTime) {
        this.endDateTime = LocalDateTime.parse(endDateTime);
        return this;
    }

    /**
     * Sets the {@code Client} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withClient(Client client) {
        this.client = client;
        return this;
    }

    /**
     * Sets the {@code label} of the {@code Meeting} that we are building.
     */
    public MeetingBuilder withLabel(String label) {
        this.label = label;
        return this;
    }

    public Meeting build() {
        return new Meeting(startDateTime, endDateTime, client, label);
    }

}
