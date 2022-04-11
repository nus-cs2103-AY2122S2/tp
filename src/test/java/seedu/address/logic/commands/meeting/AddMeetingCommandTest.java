package seedu.address.logic.commands.meeting;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.ModelStub;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.module.Module;
import seedu.address.testutil.meeting.AddMeetingDescriptorBuilder;
import seedu.address.testutil.meeting.MeetingBuilder;

public class AddMeetingCommandTest {
    private static final Index firstModule = Index.fromZeroBased(0);

    @Test
    public void constructor_nullMeeting_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddMeetingCommand(null));
    }

    @Test
    public void execute_meetingAcceptedByModel_addSuccessful() throws Exception {
        final ModelStubAcceptingMeetingAddedWithDefaultModule modelStub =
                new ModelStubAcceptingMeetingAddedWithDefaultModule();
        final Meeting validMeeting = new MeetingBuilder().build();
        final AddMeetingDescriptorBuilder addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(validMeeting);
        addMeetingDescriptorBuilder.withModule(firstModule);

        final CommandResult commandResult = new AddMeetingCommand(addMeetingDescriptorBuilder.build())
                .execute(modelStub);

        assertEquals(String.format(AddMeetingCommand.MESSAGE_SUCCESS, validMeeting),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validMeeting), modelStub.meetingsAdded);
    }

    @Test
    public void execute_duplicateMeeting_throwsCommandException() {
        final Meeting validMeeting = new MeetingBuilder().build();
        final AddMeetingDescriptorBuilder addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(validMeeting);
        addMeetingDescriptorBuilder.withModule(firstModule);
        final AddMeetingCommand addMeetingCommand = new AddMeetingCommand(addMeetingDescriptorBuilder.build());
        final ModelStub modelStub = new ModelStubWithMeetingAndModule(validMeeting);

        assertThrows(CommandException.class,
                AddMeetingCommand.MESSAGE_DUPLICATE_MEETING, () -> addMeetingCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        final Meeting cs2103 = new MeetingBuilder().withName("CS2103").build();
        final Meeting cs2101 = new MeetingBuilder().withName("CS2101").build();
        AddMeetingDescriptorBuilder addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(cs2103);
        addMeetingDescriptorBuilder.withModule(firstModule);
        final AddMeetingCommand addCS2103Command = new AddMeetingCommand(addMeetingDescriptorBuilder.build());
        addMeetingDescriptorBuilder = new AddMeetingDescriptorBuilder(cs2101);
        addMeetingDescriptorBuilder.withModule(firstModule);
        final AddMeetingCommand addCS2101Command = new AddMeetingCommand(addMeetingDescriptorBuilder.build());

        // same object -> returns true
        assertTrue(addCS2103Command.equals(addCS2103Command));

        // same values -> returns true
        final AddMeetingDescriptorBuilder addMeetingDescriptorBuilderCopy = new AddMeetingDescriptorBuilder(cs2103);
        addMeetingDescriptorBuilderCopy.withModule(firstModule);
        final AddMeetingCommand addCS2103CommandCopy = new AddMeetingCommand(addMeetingDescriptorBuilderCopy.build());
        assertTrue(addCS2103Command.equals(addCS2103CommandCopy));

        // different types -> returns false
        assertFalse(addCS2103Command.equals(1));

        // null -> returns false
        assertFalse(addCS2103CommandCopy.equals(null));

        // different meeting -> returns false
        assertFalse(addCS2103Command.equals(addCS2101Command));
    }

    /**
     * A Model stub that contains a single meeting.
     */
    private class ModelStubWithMeetingAndModule extends ModelStub {
        private final Meeting meeting;
        private final ObservableList<Module> internalList = FXCollections.observableArrayList();

        ModelStubWithMeetingAndModule(Meeting meeting) {
            requireNonNull(meeting);
            this.meeting = meeting;
        }

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return this.meeting.equals(meeting);
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            internalList.add(new Module("CS2102"));
            return internalList;
        }

    }

    /**
     * A Model stub that always accept the Meeting being added.
     */
    private class ModelStubAcceptingMeetingAddedWithDefaultModule extends ModelStub {
        final ArrayList<Meeting> meetingsAdded = new ArrayList<>();
        private final ObservableList<Module> internalList = FXCollections.observableArrayList();

        @Override
        public boolean hasMeeting(Meeting meeting) {
            requireNonNull(meeting);
            return meetingsAdded.stream().anyMatch(meeting::equals);
        }

        @Override
        public void addMeeting(Meeting meeting) {
            requireNonNull(meeting);
            meetingsAdded.add(meeting);
        }

        @Override
        public ObservableList<Module> getFilteredModuleList() {
            internalList.add(new Module("CS2102"));
            return internalList;
        }
    }

}
