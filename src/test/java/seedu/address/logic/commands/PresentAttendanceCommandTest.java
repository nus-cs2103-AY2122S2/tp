package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPetAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static seedu.address.testutil.TypicalPets.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.util.AttendanceUtil;
import seedu.address.logic.commands.PresentAttendanceCommand.PresentAttendanceDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.pet.Pet;
import seedu.address.testutil.PetBuilder;
import seedu.address.testutil.PresentAttendanceDescriptorBuilder;

public class PresentAttendanceCommandTest {

    private static final String DATE_STUB = LocalDate.now().toString();
    private static final String FAILURE_DATE_STUB = LocalDate.now().format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);
    private static final String PICKUP_TIME_STUB = "09:00";
    private static final String DROPOFF_TIME_STUB = "17:30";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_markPresentUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
            .withPresentAttendanceEntry(DATE_STUB, PICKUP_TIME_STUB, DROPOFF_TIME_STUB)
            .build();

        PresentAttendanceDescriptor presentAttendanceDescriptor = new PresentAttendanceDescriptorBuilder()
            .withDate(DATE_STUB)
            .withPickUpTime(PICKUP_TIME_STUB)
            .withDropOffTime(DROPOFF_TIME_STUB)
            .build();

        PresentAttendanceCommand presentAttendanceCommand = new PresentAttendanceCommand(
            INDEX_FIRST_PET,
            presentAttendanceDescriptor
        );

        String expectedMessage = String.format(
            PresentAttendanceCommand.MESSAGE_PRESENT_ATTENDANCE_SUCCESS,
            petToMarkPresent.getName(),
            FAILURE_DATE_STUB, presentAttendanceDescriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(presentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPresentFilteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);

        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkPresent = new PetBuilder(firstPet)
            .withPresentAttendanceEntry(DATE_STUB, PICKUP_TIME_STUB, DROPOFF_TIME_STUB)
            .build();

        PresentAttendanceDescriptor presentAttendanceDescriptor = new PresentAttendanceDescriptorBuilder()
            .withDate(DATE_STUB)
            .withPickUpTime(PICKUP_TIME_STUB)
            .withDropOffTime(DROPOFF_TIME_STUB)
            .build();

        PresentAttendanceCommand presentAttendanceCommand = new PresentAttendanceCommand(
            INDEX_FIRST_PET,
            presentAttendanceDescriptor
        );

        String expectedMessage = String.format(
            PresentAttendanceCommand.MESSAGE_PRESENT_ATTENDANCE_SUCCESS,
            petToMarkPresent.getName(),
            FAILURE_DATE_STUB,
            presentAttendanceDescriptor);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPet(firstPet, petToMarkPresent);

        assertCommandSuccess(presentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_alreadyMarkedPresentUnfilteredList_failure() {
        Pet secondPet = model.getFilteredPetList().get(INDEX_SECOND_PET.getZeroBased());

        PresentAttendanceDescriptor presentAttendanceDescriptor = new PresentAttendanceDescriptorBuilder()
            .withDate(DATE_STUB)
            .withPickUpTime(PICKUP_TIME_STUB)
            .withDropOffTime(DROPOFF_TIME_STUB)
            .build();

        PresentAttendanceCommand presentAttendanceCommand = new PresentAttendanceCommand(
            INDEX_SECOND_PET,
            presentAttendanceDescriptor
        );

        String failureMessage = String.format(
            PresentAttendanceCommand.MESSAGE_PRESENT_ATTENDANCE_FAILURE,
            secondPet.getName(),
            FAILURE_DATE_STUB,
            presentAttendanceDescriptor);

        assertCommandFailure(presentAttendanceCommand, model, failureMessage);
    }
}
