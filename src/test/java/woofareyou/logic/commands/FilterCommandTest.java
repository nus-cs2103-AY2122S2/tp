package woofareyou.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static woofareyou.commons.core.Messages.FILTER_MESSAGE_SUCCESS;
import static woofareyou.logic.commands.CommandTestUtil.assertCommandSuccess;
import static woofareyou.testutil.TypicalIndexes.INDEX_FIRST_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_SECOND_PET;
import static woofareyou.testutil.TypicalIndexes.INDEX_THIRD_PET;
import static woofareyou.testutil.TypicalPets.getTypicalPetBook;

import java.time.LocalDate;
import java.util.Collections;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import woofareyou.commons.util.FilterUtil;
import woofareyou.logic.parser.exceptions.ParseException;
import woofareyou.model.Model;
import woofareyou.model.ModelManager;
import woofareyou.model.UserPrefs;
import woofareyou.model.filter.AppointmentContainsFilterWordPredicate;
import woofareyou.model.filter.DateContainsFilterDatePredicate;
import woofareyou.model.filter.OwnerNameContainsFilterWordPredicate;
import woofareyou.model.filter.TagContainsFilterWordPredicate;
import woofareyou.model.pet.Pet;
import woofareyou.testutil.PetBuilder;

public class FilterCommandTest {
    private static final String DATE_STUB = LocalDate.now().toString();
    private static final String PICKUP_TIME_STUB = "09:00";
    private static final String DROPOFF_TIME_STUB = "17:30";
    private static final String APPOINTMENT_DATE_TIME_STUB = "02-04-2022 09:30";
    private static final String APPOINTMENT_LOCATION_STUB = "NUS Vet Clinic";
    private static final String PARSE_EX_THROWN_MESSAGE = "Should not have thrown parse exception.";
    private static final Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());
    private static final Model expectedModel = new ModelManager(getTypicalPetBook(), new UserPrefs());

    @Test
    public void equals_date_predicate() {
        try {
            DateContainsFilterDatePredicate firstDatePredicate =
                    new DateContainsFilterDatePredicate("today");
            DateContainsFilterDatePredicate secondDatePredicate =
                    new DateContainsFilterDatePredicate("22-03-2022");

            FilterCommand filterFirstCommand = new FilterCommand(firstDatePredicate);
            FilterCommand filterSecondCommand = new FilterCommand(secondDatePredicate);

            // same object -> returns true
            assertTrue(filterFirstCommand.equals(filterFirstCommand));

            // same values -> returns true
            FilterCommand copyFirstFilterCommand = new FilterCommand(firstDatePredicate);
            assertTrue(filterFirstCommand.equals(copyFirstFilterCommand));

            // different types -> returns false
            assertFalse(filterFirstCommand.equals(1));

            // null -> returns false
            assertFalse(filterFirstCommand.equals(null));

            // different pet -> returns false
            assertFalse(filterFirstCommand.equals(filterSecondCommand));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void equals_app_predicate() {
        try {
            AppointmentContainsFilterWordPredicate firstAppPredicate =
                    new AppointmentContainsFilterWordPredicate("today");
            AppointmentContainsFilterWordPredicate secondAppPredicate =
                    new AppointmentContainsFilterWordPredicate("22-03-2022");

            FilterCommand filterFirstCommand = new FilterCommand(firstAppPredicate);
            FilterCommand filterSecondCommand = new FilterCommand(secondAppPredicate);

            // same object -> returns true
            assertTrue(filterFirstCommand.equals(filterFirstCommand));

            // same values -> returns true
            FilterCommand copyFirstFilterCommand = new FilterCommand(firstAppPredicate);
            assertTrue(filterFirstCommand.equals(copyFirstFilterCommand));

            // different types -> returns false
            assertFalse(filterFirstCommand.equals(1));

            // null -> returns false
            assertFalse(filterFirstCommand.equals(null));

            // different pet -> returns false
            assertFalse(filterFirstCommand.equals(filterSecondCommand));
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void equals_owner_predicate() {
        OwnerNameContainsFilterWordPredicate firstOwnerPredicate =
                new OwnerNameContainsFilterWordPredicate("first");
        OwnerNameContainsFilterWordPredicate secondOwnerPredicate =
                new OwnerNameContainsFilterWordPredicate("Second");

        FilterCommand filterFirstCommand = new FilterCommand(firstOwnerPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondOwnerPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand copyFirstFilterCommand = new FilterCommand(firstOwnerPredicate);
        assertTrue(filterFirstCommand.equals(copyFirstFilterCommand));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different pet -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void equals_tags_predicate() {
        TagContainsFilterWordPredicate firstTagPredicate =
                new TagContainsFilterWordPredicate("first");
        TagContainsFilterWordPredicate secondTagPredicate =
                new TagContainsFilterWordPredicate("second");

        FilterCommand filterFirstCommand = new FilterCommand(firstTagPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondTagPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand copyFirstFilterCommand = new FilterCommand(firstTagPredicate);
        assertTrue(filterFirstCommand.equals(copyFirstFilterCommand));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different pet -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @BeforeAll
    public static void setAttendance() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet firstPetToMarkPresent = new PetBuilder(firstPet)
                .withPresentAttendanceEntry(DATE_STUB, PICKUP_TIME_STUB, DROPOFF_TIME_STUB)
                .build();

        Pet secondPet = model.getFilteredPetList().get(INDEX_SECOND_PET.getZeroBased());
        Pet secondPetToMarkPresent = new PetBuilder(secondPet)
                .withPresentAttendanceEntry(DATE_STUB, PICKUP_TIME_STUB, DROPOFF_TIME_STUB)
                .build();

        model.setPet(firstPet, firstPetToMarkPresent);
        model.setPet(firstPet, firstPetToMarkPresent);

        expectedModel.setPet(firstPet, firstPetToMarkPresent);
        expectedModel.setPet(secondPet, secondPetToMarkPresent);
    }

    @Test
    public void execute_noFilteredDateMatch_noPetsFound() {
        try {
            DateContainsFilterDatePredicate predicate =
                    new DateContainsFilterDatePredicate("22-03-2021");
            String expectedMessage =
                    String.format(FILTER_MESSAGE_SUCCESS, 0, FilterUtil.successMessageMatch(predicate));
            FilterCommand command = new FilterCommand(predicate);
            expectedModel.updateFilteredPetList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredPetList());
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void execute_attendanceDateMatch_multiplePetsFound() {
        Model model = new ModelManager(getTypicalPetBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalPetBook(), new UserPrefs());

        try {
            DateContainsFilterDatePredicate predicate =
                    new DateContainsFilterDatePredicate("today");
            String expectedMessage =
                    String.format(FILTER_MESSAGE_SUCCESS, 2, FilterUtil.successMessageMatch(predicate));
            FilterCommand command = new FilterCommand(predicate);
            expectedModel.updateFilteredPetList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void execute_ownerNameMatch_noPetsFound() {
        OwnerNameContainsFilterWordPredicate predicate = new OwnerNameContainsFilterWordPredicate("Poopoo");
        String expectedMessage =
                String.format(FILTER_MESSAGE_SUCCESS, 0, FilterUtil.successMessageMatch(predicate));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPetList());
    }

    @Test
    public void execute_ownerNameMatch_onePetFound() {
        OwnerNameContainsFilterWordPredicate predicate = new OwnerNameContainsFilterWordPredicate("Alice Paul");
        String expectedMessage =
                String.format(FILTER_MESSAGE_SUCCESS, 1, FilterUtil.successMessageMatch(predicate));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @BeforeAll
    public static void setTags() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_SECOND_PET.getZeroBased());
        Pet firstPetSetTag = new PetBuilder(firstPet).withTags("Golden Retriever").build();

        Pet secondPet = model.getFilteredPetList().get(INDEX_THIRD_PET.getZeroBased());
        Pet secondPetSetTag = new PetBuilder(secondPet).withTags("Golden Retriever").build();

        model.setPet(firstPet, firstPetSetTag);
        model.setPet(secondPet, secondPetSetTag);

        expectedModel.setPet(firstPet, firstPetSetTag);
        expectedModel.setPet(secondPet, secondPetSetTag);
    }

    @Test
    public void execute_tagMatch_zeroPetsFound() {
        TagContainsFilterWordPredicate predicate = new TagContainsFilterWordPredicate("Nothing");
        String expectedMessage =
                String.format(FILTER_MESSAGE_SUCCESS, 0, FilterUtil.successMessageMatch(predicate));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPetList());
    }

    @Test
    public void execute_tagMatch_multiplePetsFound() {
        TagContainsFilterWordPredicate predicate = new TagContainsFilterWordPredicate("Golden Retriever");
        String expectedMessage =
                String.format(FILTER_MESSAGE_SUCCESS, 2, FilterUtil.successMessageMatch(predicate));
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPetList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @BeforeAll
    public static void setApp() {
        Pet firstPet = model.getFilteredPetList().get(INDEX_FIRST_PET.getZeroBased());
        Pet firstPetSetTag = new PetBuilder(firstPet)
                .withAppointment(APPOINTMENT_DATE_TIME_STUB, APPOINTMENT_LOCATION_STUB).build();

        Pet secondPet = model.getFilteredPetList().get(INDEX_THIRD_PET.getZeroBased());
        Pet secondPetSetTag = new PetBuilder(secondPet)
                .withAppointment(APPOINTMENT_DATE_TIME_STUB, APPOINTMENT_LOCATION_STUB).build();

        model.setPet(firstPet, firstPetSetTag);
        model.setPet(secondPet, secondPetSetTag);

        expectedModel.setPet(firstPet, firstPetSetTag);
        expectedModel.setPet(secondPet, secondPetSetTag);
    }

    @Test
    public void execute_appMatch_zeroPetsFound() {
        try {
            AppointmentContainsFilterWordPredicate predicate = new AppointmentContainsFilterWordPredicate("today");
            String expectedMessage =
                    String.format(FILTER_MESSAGE_SUCCESS, 0, FilterUtil.successMessageMatch(predicate));
            FilterCommand command = new FilterCommand(predicate);
            expectedModel.updateFilteredPetList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
            assertEquals(Collections.emptyList(), model.getFilteredPetList());
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }

    @Test
    public void execute_appMatch_multiplePetsFound () {
        try {
            AppointmentContainsFilterWordPredicate predicate = new AppointmentContainsFilterWordPredicate("02-04-2022");
            String expectedMessage =
                    String.format(FILTER_MESSAGE_SUCCESS, 2, FilterUtil.successMessageMatch(predicate));
            FilterCommand command = new FilterCommand(predicate);
            expectedModel.updateFilteredPetList(predicate);
            assertCommandSuccess(command, model, expectedMessage, expectedModel);
        } catch (ParseException e) {
            fail(PARSE_EX_THROWN_MESSAGE);
        }
    }
}
