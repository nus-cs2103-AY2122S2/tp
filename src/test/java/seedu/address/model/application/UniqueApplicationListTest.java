package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_GARENA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LOCAL;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.GARENA;
import static seedu.address.testutil.TypicalApplications.GRAB;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.sort.ApplicationStatusComparator;
import seedu.address.logic.sort.InterviewSlotComparator;
import seedu.address.logic.sort.NameComparator;
import seedu.address.logic.sort.PriorityComparator;
import seedu.address.model.application.exceptions.ApplicationNotFoundException;
import seedu.address.model.application.exceptions.DuplicateApplicationException;
import seedu.address.testutil.ApplicationBuilder;

public class UniqueApplicationListTest {

    private final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();

    @Test
    public void contains_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.contains(null));
    }

    @Test
    public void contains_applicationNotInList_returnsFalse() {
        assertFalse(uniqueApplicationList.contains(GRAB));
    }

    @Test
    public void contains_applicationInList_returnsTrue() {
        uniqueApplicationList.add(GRAB);
        assertTrue(uniqueApplicationList.contains(GRAB));
    }

    @Test
    public void contains_applicationWithSameIdentityFieldsInList_returnsTrue() {
        uniqueApplicationList.add(GRAB);
        Application editedGrab = new ApplicationBuilder(GRAB)
                .withAddress(VALID_ADDRESS_GARENA).withPhone(VALID_PHONE_GARENA)
                .build();
        assertTrue(uniqueApplicationList.contains(editedGrab));
    }

    @Test
    public void add_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.add(null));
    }

    @Test
    public void add_duplicateApplication_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(GRAB);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.add(GRAB));
    }

    @Test
    public void setApplication_nullTargetApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(null, GRAB));
    }

    @Test
    public void setApplication_nullEditedApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplication(GRAB, null));
    }

    @Test
    public void setApplication_targetApplicationNotInList_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.setApplication(GRAB, GRAB));
    }

    @Test
    public void setApplication_editedApplicationIsSameApplication_success() {
        uniqueApplicationList.add(GRAB);
        uniqueApplicationList.setApplication(GRAB, GRAB);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GRAB);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasSameIdentity_success() {
        uniqueApplicationList.add(GRAB);
        Application editedGrab = new ApplicationBuilder(GRAB)
                .withAddress(VALID_ADDRESS_GARENA).withTags(VALID_TAG_LOCAL)
                .build();
        uniqueApplicationList.setApplication(GRAB, editedGrab);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(editedGrab);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasDifferentIdentity_success() {
        uniqueApplicationList.add(GRAB);
        uniqueApplicationList.setApplication(GRAB, GARENA);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GARENA);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplication_editedApplicationHasNonUniqueIdentity_throwsDuplicateApplicationException() {
        uniqueApplicationList.add(GRAB);
        uniqueApplicationList.add(GARENA);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList.setApplication(GRAB, GARENA));
    }

    @Test
    public void remove_nullApplication_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.remove(null));
    }

    @Test
    public void remove_applicationDoesNotExist_throwsApplicationNotFoundException() {
        assertThrows(ApplicationNotFoundException.class, () -> uniqueApplicationList.remove(GRAB));
    }

    @Test
    public void remove_existingApplication_removesApplication() {
        uniqueApplicationList.add(GRAB);
        uniqueApplicationList.remove(GRAB);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullUniqueApplicationList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList
                .setApplications((UniqueApplicationList) null));
    }

    @Test
    public void setApplications_uniqueApplicationList_replacesOwnListWithProvidedUniqueApplicationList() {
        uniqueApplicationList.add(GRAB);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GARENA);
        uniqueApplicationList.setApplications(expectedUniqueApplicationList);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.setApplications((List<Application>) null));
    }

    @Test
    public void setApplications_list_replacesOwnListWithProvidedList() {
        uniqueApplicationList.add(GRAB);
        List<Application> applicationList = Collections.singletonList(GARENA);
        uniqueApplicationList.setApplications(applicationList);
        UniqueApplicationList expectedUniqueApplicationList = new UniqueApplicationList();
        expectedUniqueApplicationList.add(GARENA);
        assertEquals(expectedUniqueApplicationList, uniqueApplicationList);
    }

    @Test
    public void setApplications_listWithDuplicateApplications_throwsDuplicateApplicationException() {
        List<Application> listWithDuplicateApplications = Arrays.asList(GRAB, GRAB);
        assertThrows(DuplicateApplicationException.class, () -> uniqueApplicationList
                .setApplications(listWithDuplicateApplications));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueApplicationList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void sortApplication_nullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.sort(new NameComparator(), null));
        assertThrows(NullPointerException.class, () -> uniqueApplicationList.sort(null,
                ListCommand.COMMAND_ORDER_WORD_ASCENDING));
    }

    @Test
    public void sortApplication_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> uniqueApplicationList.sort(new NameComparator(),
                "abc"));
    }

    @Test
    public void sortApplication_checkSorting_name() {
        UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();
        uniqueApplicationList.add(GRAB);
        uniqueApplicationList.add(GARENA);

        uniqueApplicationList.sort(new NameComparator(), ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(GARENA.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(new NameComparator(), ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(GRAB.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());
    }

    @Test
    public void sortApplication_checkSorting_interviewSlot() {
        final Application application1 = new ApplicationBuilder()
                .withName("Application A").withInterviewSlot("07-04-2022 12:45").build();
        final Application application2 = new ApplicationBuilder()
                .withName("Application B").withInterviewSlot("07-04-2022 13:45").build();
        final Application application3 = new ApplicationBuilder()
                .withName("Application C").withInterviewSlot("07-04-2022 13:45").build();
        final Application application4 = new ApplicationBuilder()
                .withName("Application D").withInterviewSlot("07-04-2022 13:45").build();

        final InterviewSlotComparator interviewSlotComparator = new InterviewSlotComparator();

        final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();
        uniqueApplicationList.add(application1);
        uniqueApplicationList.add(application2);
        uniqueApplicationList.add(application3);

        // Check interview slot
        uniqueApplicationList.sort(interviewSlotComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(interviewSlotComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        // Check wildcard ordering - order by name
        uniqueApplicationList.sort(interviewSlotComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application2.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(1).getName());
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(2).getName());

        // Applications with no date
        uniqueApplicationList.add(application4);

        uniqueApplicationList.sort(interviewSlotComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(interviewSlotComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application4.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());
    }

    @Test
    public void sortApplication_checkSorting_priority() {
        final Application application1 = new ApplicationBuilder()
                .withName("Application A").withTags("HIGH").build();
        final Application application2 = new ApplicationBuilder()
                .withName("Application B").withTags("MEDIUM").build();
        final Application application3 = new ApplicationBuilder()
                .withName("Application C").withTags("LOW").build();
        final Application application4 = new ApplicationBuilder()
                .withName("Application D").withTags("LOW").build();
        final Application application5 = new ApplicationBuilder()
                .withName("Application E").build();

        final PriorityComparator priorityComparator = new PriorityComparator();

        final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();
        uniqueApplicationList.add(application1);
        uniqueApplicationList.add(application2);
        uniqueApplicationList.add(application3);

        // Check priority sort
        uniqueApplicationList.sort(priorityComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(priorityComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        // Check wildcard ordering - order by name
        uniqueApplicationList.add(application4);
        uniqueApplicationList.sort(priorityComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application4.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(1).getName());
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        // Applications without priority
        uniqueApplicationList.add(application5);
        uniqueApplicationList.sort(priorityComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application5.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(priorityComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());
    }

    public void sortApplication_checkSorting_status() {
        final Application application1 = new ApplicationBuilder()
                .withName("Application A").withTags("NOT_APPLIED").build();
        final Application application2 = new ApplicationBuilder()
                .withName("Application B").withTags("APPLIED").build();
        final Application application3 = new ApplicationBuilder()
                .withName("Application C").withTags("INTERVIEWED").build();
        final Application application4 = new ApplicationBuilder()
                .withName("Application D").withTags("REJECTED").build();
        final Application application5 = new ApplicationBuilder()
                .withName("Application E").withTags("ACCEPTED").build();
        final Application application6 = new ApplicationBuilder()
                .withName("Application F").withTags("ACCEPTED").build();
        final Application application7 = new ApplicationBuilder()
                .withName("Application G").build();

        final ApplicationStatusComparator applicationStatusComparator = new ApplicationStatusComparator();

        final UniqueApplicationList uniqueApplicationList = new UniqueApplicationList();
        uniqueApplicationList.add(application1);
        uniqueApplicationList.add(application2);
        uniqueApplicationList.add(application3);
        uniqueApplicationList.add(application4);
        uniqueApplicationList.add(application5);

        // Check sorting
        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application5.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());
        assertEquals(application4.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(1).getName());
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(2).getName());
        assertEquals(application2.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(3).getName());
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(4).getName());

        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application5.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(4).getName());
        assertEquals(application4.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(3).getName());
        assertEquals(application3.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(2).getName());
        assertEquals(application2.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(1).getName());
        assertEquals(application1.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        // Check wildcard ordering - order by name
        uniqueApplicationList.add(application6);
        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application5.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());
        assertEquals(application6.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(1).getName());

        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application5.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(4).getName());
        assertEquals(application6.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(5).getName());

        // Applications without status tag
        uniqueApplicationList.add(application7);
        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_ASCENDING);
        assertEquals(application7.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(0).getName());

        uniqueApplicationList.sort(applicationStatusComparator, ListCommand.COMMAND_ORDER_WORD_DESCENDING);
        assertEquals(application7.getName(), uniqueApplicationList.asUnmodifiableObservableList().get(6).getName());
    }

}
