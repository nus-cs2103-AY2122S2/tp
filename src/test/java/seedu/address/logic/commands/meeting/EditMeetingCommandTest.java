package seedu.address.logic.commands.meeting;

import static seedu.address.logic.commands.CommandTestUtil.VALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalMeetings.WITH_ALICE;
import static seedu.address.testutil.TypicalMeetings.WITH_BENSON;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;

public class EditMeetingCommandTest {

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptor = new EditMeetingCommand.EditMeetingDescriptor();
        editMeetingDescriptor.setStartDateTime(WITH_ALICE.getStartDateTime());
        editMeetingDescriptor.setEndDateTime(WITH_ALICE.getEndDateTime());
        editMeetingDescriptor.setLabel(WITH_ALICE.getLabel());

        assertThrows(NullPointerException.class, () -> new EditMeetingCommand(null,
                editMeetingDescriptor));
    }

    @Test
    public void constructor_nullEditMeetingDescriptor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EditMeetingCommand(Index.fromZeroBased(VALID_INDEX),
                null));
    }

    @Test
    public void equals() {
        Index validIndexAlice = Index.fromZeroBased(VALID_INDEX);
        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptorAlice =
                new EditMeetingCommand.EditMeetingDescriptor();
        editMeetingDescriptorAlice.setStartDateTime(WITH_ALICE.getStartDateTime());
        editMeetingDescriptorAlice.setEndDateTime(WITH_ALICE.getEndDateTime());
        editMeetingDescriptorAlice.setLabel(WITH_ALICE.getLabel());

        Index validIndexBenson = Index.fromZeroBased(VALID_INDEX + 1);
        EditMeetingCommand.EditMeetingDescriptor editMeetingDescriptorBenson =
                new EditMeetingCommand.EditMeetingDescriptor();
        editMeetingDescriptorBenson.setStartDateTime(WITH_BENSON.getStartDateTime());
        editMeetingDescriptorBenson.setEndDateTime(WITH_BENSON.getEndDateTime());
        editMeetingDescriptorBenson.setLabel(WITH_BENSON.getLabel());


        EditMeetingCommand editAliceMeetingCommand = new EditMeetingCommand(validIndexAlice,
                editMeetingDescriptorAlice);
        EditMeetingCommand editBensonMeetingCommand = new EditMeetingCommand(validIndexBenson,
                editMeetingDescriptorBenson);

        // same object -> returns true
        Assertions.assertTrue(editAliceMeetingCommand.equals(editAliceMeetingCommand));

        // different types -> returns false
        Assertions.assertFalse(editAliceMeetingCommand.equals(1));

        // null -> returns false
        Assertions.assertFalse(editAliceMeetingCommand.equals(null));

        // different command -> returns false
        Assertions.assertFalse(editAliceMeetingCommand.equals(editBensonMeetingCommand));
    }
}
