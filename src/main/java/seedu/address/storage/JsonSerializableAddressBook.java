package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.client.Client;
import seedu.address.model.meeting.Meeting;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_CLIENT = "Clients list contains duplicate client(s).";
    public static final String MESSAGE_OVERLAPPING_MEETINGS = "Meeting list contains overlapping meetings.";
    public static final String MESSAGE_INVALID_MEETING_DATETIME =
            "Meeting list contains meeting with invalid datetime.";

    private final List<JsonAdaptedClient> clients = new ArrayList<>();
    private final List<JsonAdaptedMeeting> meetings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given clients and meetings.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("clients") List<JsonAdaptedClient> clients,
                                       @JsonProperty("meetings") List<JsonAdaptedMeeting> meetings) {
        this.clients.addAll(clients);
        this.meetings.addAll(meetings);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        clients.addAll(source.getClientList().stream().map(JsonAdaptedClient::new).collect(Collectors.toList()));
        meetings.addAll(source.getMeetingList().stream().map(JsonAdaptedMeeting::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedClient jsonAdaptedClient : clients) {
            Client client = jsonAdaptedClient.toModelType();
            if (addressBook.hasClient(client)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_CLIENT);
            }
            addressBook.addClient(client);
        }

        for (JsonAdaptedMeeting jsonAdaptedMeeting: meetings) {
            Meeting meeting = jsonAdaptedMeeting.toModelType();
            if (addressBook.isOverlapping(meeting)) {
                throw new IllegalValueException(MESSAGE_OVERLAPPING_MEETINGS);
            }
            if (!Meeting.isValidMeeting(meeting.getStartDateTime(), meeting.getEndDateTime())) {
                throw new IllegalValueException(MESSAGE_INVALID_MEETING_DATETIME);
            }

            addressBook.addMeeting(meeting);
        }

        return addressBook;
    }

}
