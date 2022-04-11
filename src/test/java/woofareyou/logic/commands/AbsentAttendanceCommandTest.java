package woofareyou.logic.commands;

import static woofareyou.logic.commands.CommandTestUtil.assertCommandFailure;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.logic.commands.CommandTestUtil.showPetAtIndex;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_FOURTH_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_THIRD_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import woofareyou.commons.util.AttendanceUtil;
import woofareyou.logic.commands.AbsentAttendanceCommand.AbsentAttendanceDescriptor;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.PetBook;
import woofareyou.model.UserPrefs;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.AbsentAttendanceDescriptorBuilder;
import woofareyou.testutil.PetBuilder;

public class AbsentAttendanceCommandTest {

    private static final String DATE_STUB = LocalDate.now().toString();
    private static final String MESSAGE_DATE_STUB = LocalDate.now().format(AttendanceUtil.ATTENDANCE_DATE_FORMATTER);

    private static AbsentAttendanceDescriptor descriptor;

    private Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @BeforeAll
    static void setupDescriptors() {
        descriptor = new AbsentAttendanceDescriptorBuilder().withDate(DATE_STUB).build();
    }

    // Pet does not have any attendance entry.
    @Test
    public void execute_markPetAsAbsentUnfilteredList_success() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(firstPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_FIRST_PET,
                descriptor
        );

        String expectedMessage = generateExpectedSuccessMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(firstPet, petToMarkAbsent);

        assertCommandSuccess(absentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPetAsAbsentFilteredList_success() {
        showPetAtIndex(model, INDEX_FIRST_PET);
        execute_markPetAsAbsentUnfilteredList_success();
    }


    // Pet has an absent attendance entry.
    @Test
    public void execute_markAbsentPetAbsentAgainUnfilteredList_failure() {
        Pet thirdPet = model.getFilteredPetList().get(INDEX_THIRD_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(thirdPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_THIRD_PET,
                descriptor
        );

        String expectedMessage = generateExpectedFailureMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(thirdPet, petToMarkAbsent);

        assertCommandFailure(absentAttendanceCommand, model, expectedMessage);
    }

    @Test
    public void execute_markAbsentPetAbsentAgainFilteredList_failure() {
        showPetAtIndex(model, INDEX_THIRD_PET);

        Pet thirdPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(thirdPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_FIRST_PET,
                descriptor
        );

        String expectedMessage = generateExpectedFailureMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(thirdPet, petToMarkAbsent);

        assertCommandFailure(absentAttendanceCommand, model, expectedMessage);
    }


    // Pet has a present attendance entry with a transport arrangement.
    @Test
    public void execute_markPetWithTransportAbsentUnfilteredList_success() {
        Pet secondPet = model.getFilteredPetList().get(INDEX_SECOND_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(secondPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_SECOND_PET,
                descriptor
        );

        String expectedMessage = generateExpectedSuccessMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(secondPet, petToMarkAbsent);

        assertCommandSuccess(absentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPetWithTransportAbsentFilteredList_success() {
        showPetAtIndex(model, INDEX_SECOND_PET);

        Pet secondPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(secondPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_FIRST_PET,
                descriptor
        );

        String expectedMessage = generateExpectedSuccessMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(secondPet, petToMarkAbsent);

        assertCommandSuccess(absentAttendanceCommand, model, expectedMessage, expectedModel);
    }


    // Pet has a present attendance entry without a transport arrangement.
    @Test
    public void execute_markPetWithoutTransportAbsentUnfilteredList_success() {
        Pet fourthPet = model.getFilteredPetList().get(INDEX_FOURTH_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(fourthPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_FOURTH_PET,
                descriptor
        );

        String expectedMessage = generateExpectedSuccessMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs());
        expectedModel.setPet(fourthPet, petToMarkAbsent);

        assertCommandSuccess(absentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_markPetWithoutTransportAbsentFilteredList_success() {
        showPetAtIndex(model, INDEX_FOURTH_PET);

        Pet fourthPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet petToMarkAbsent = new PetBuilder(fourthPet)
                .withAbsentAttendanceEntry(DATE_STUB)
                .build();

        AbsentAttendanceCommand absentAttendanceCommand = new AbsentAttendanceCommand(
                INDEX_FIRST_PET,
                descriptor
        );

        String expectedMessage = generateExpectedSuccessMessage(petToMarkAbsent, MESSAGE_DATE_STUB, descriptor);

        Model expectedModel = new ModelManager(new PetBook(model.getPetBook()), new UserPrefs(),
                model.getLastUsedPredicate());
        expectedModel.setPet(fourthPet, petToMarkAbsent);

        assertCommandSuccess(absentAttendanceCommand, model, expectedMessage, expectedModel);
    }

    private static String generateExpectedSuccessMessage(Pet pet, String messageDate,
                                                         AbsentAttendanceDescriptor descriptor) {
        return String.format(AbsentAttendanceCommand.MESSAGE_ABSENT_ATTENDANCE_SUCCESS,
                pet.getName(), messageDate, descriptor);
    }

    private static String generateExpectedFailureMessage(Pet pet, String messageDate,
                                                         AbsentAttendanceDescriptor descriptor) {
        return String.format(AbsentAttendanceCommand.MESSAGE_ABSENT_ATTENDANCE_FAILURE,
                pet.getName(), messageDate, descriptor);
    }
}
