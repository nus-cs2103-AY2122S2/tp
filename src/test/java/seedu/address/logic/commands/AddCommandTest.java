package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.lineup.Lineup;
import seedu.address.model.lineup.LineupName;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.schedule.Schedule;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.ScheduleBuilder;

public class AddCommandTest {
    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand((Person) null));
    }

    @Test
    public void constructor_nullLineup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand((Lineup) null));
    }

    @Test
    public void constructor_nullSchedule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand((Schedule) null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validPerson).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_ADD_PERSON_SUCCESS, validPerson),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    /*
    @Test
    public void execute_lineupAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingLineupAdded modelStub = new ModelStubAcceptingLineupAdded();
        Lineup validLineup = new LineupBuilder().build();

        CommandResult commandResult = new AddCommand(validLineup).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_ADD_LINEUP_SUCCESS, validLineup),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validLineup), modelStub.lineupsAdded);
    }
    */

    @Test
    public void execute_scheduleAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingScheduleAdded modelStub = new ModelStubAcceptingScheduleAdded();
        Schedule validSchedule = new ScheduleBuilder().build();

        CommandResult commandResult = new AddCommand(validSchedule).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_ADD_SCHEDULE_SUCCESS, validSchedule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSchedule), modelStub.schedulesAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePersonJerseyNumber_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person duplicateJerseyNumberPerson = new PersonBuilder(validPerson).withName("Lebron James").build();
        ModelStub modelStub = new ModelStubWithPerson(validPerson);
        AddCommand addCommand = new AddCommand(duplicateJerseyNumberPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_JERSEY_NUMBER,
                () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_PersonCapacityFull_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        Person extraPerson1 = new PersonBuilder(validPerson).withName("Extraaa1").build();
        ModelStub modelStub = new ModelStubWithPerson(validPerson);
        ModelStubWithPerson stub =  (ModelStubWithPerson) modelStub;
        stub.cheatIncrement();
        AddCommand addCommand = new AddCommand(extraPerson1);

        assertThrows(CommandException.class, AddCommand.MESSAGE_FULL_CAPACITY_REACHED,
                () -> addCommand.execute(modelStub));
    }

    /*
    @Test
    public void execute_duplicateLineup_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validPerson);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_PERSON, () -> addCommand.execute(modelStub));
    }
    */

    @Test
    public void execute_duplicateSchedule_throwsCommandException() {
        Schedule validSchedule = new ScheduleBuilder().build();
        AddCommand addCommand = new AddCommand(validSchedule);
        ModelStub modelStub = new ModelStubWithSchedule(validSchedule);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_SCHEDULE,
                () -> addCommand.execute(modelStub));
    }

    @Test
    public void execute_notDuplicateSchedule_Success() throws Exception {
        ModelStubAcceptingScheduleAdded modelStub = new ModelStubAcceptingScheduleAdded();
        Schedule validSchedule = new ScheduleBuilder().build();
        Schedule anotherSchedule = new ScheduleBuilder(validSchedule).withScheduleDescription("Serious").build();
        new AddCommand(validSchedule).execute(modelStub);
        CommandResult commandResult = new AddCommand(anotherSchedule).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_ADD_SCHEDULE_SUCCESS, anotherSchedule),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSchedule, anotherSchedule), modelStub.schedulesAdded);
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);
        Schedule training = new ScheduleBuilder().withScheduleName("free throw shooting").build();
        Schedule competition = new ScheduleBuilder().withScheduleName("three point contest").build();
        AddCommand addTrainingCommand = new AddCommand(training);
        AddCommand addCompetitionCommand = new AddCommand(competition);

        // same object -> returns true
        assertEquals(addAliceCommand, addAliceCommand);
        assertEquals(addCompetitionCommand, addCompetitionCommand);

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        AddCommand addTrainingCopy = new AddCommand(training);
        assertEquals(addAliceCommand, addAliceCommandCopy);
        assertEquals(addTrainingCommand, addTrainingCopy);

        // different types -> returns false
        assertNotEquals(1, addAliceCommand);
        assertNotEquals(1, addCompetitionCommand);

        // null -> returns false
        assertNotEquals(null, addAliceCommand);
        assertNotEquals(null, addCompetitionCommand);

        // different person -> returns false
        assertNotEquals(addAliceCommand, addBobCommand);
        assertNotEquals(addTrainingCopy, addCompetitionCommand);
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("this method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("addPerson should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("getAddressBook should not be called.");
        }

        // Person level op
        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("hasPerson should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("deletePerson should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPersonName(Name targetName) {
            throw new AssertionError("hasPersonName should not be called.");
        }

        @Override
        public boolean hasLineupName(LineupName targetName) {
            throw new AssertionError("hasLineupName should not be called.");
        }

        @Override
        public void addLineup(Lineup toAdd) {
            throw new AssertionError("addLineup should not be called.");
        }

        @Override
        public void deleteLineup(Lineup lineup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void putPersonIntoLineup(Person player, Lineup lineup) {
            throw new AssertionError("putPersonIntoLineup should not be called.");
        }

        @Override
        public void setLineup(Lineup target, Lineup editedLineup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePersonFromLineup(Person player, Lineup lineup) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean isPersonInLineup(Person person, Lineup lineup) {
            throw new AssertionError("isPersonInLineup method should not be called.");
        }

        @Override
        public boolean hasJerseyNumber(Person person) {
            throw new AssertionError("hasJerseyNumber should not be called.");
        }

        @Override
        public String getAvailableJerseyNumber() {
            throw new AssertionError("getAvailableJerseyNumber should not be called.");
        }

        @Override
        public boolean isFull() {
            throw new AssertionError("isFull should not be called.");
        }

        @Override
        public Person getPerson(Name targetName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Lineup getLineup(LineupName targetName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void refresh() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            throw new AssertionError("hasSchedule should not be called.");
        }

        @Override
        public void deleteSchedule(Schedule target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addSchedule(Schedule schedule) {
            throw new AssertionError("addSchedule should not be called.");
        }

        @Override
        public void setSchedule(Schedule target, Schedule editedSchedule) {
            throw new AssertionError("setSchedule should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Schedule> getFilteredScheduleList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredScheduleList(Predicate<Schedule> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;
        private static final int CAPACITY = 2;
        private int size = 0;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
            this.size++;
        }

        public void cheatIncrement() {
            this.size++;
        }

        @Override
        public boolean isFull() {
            return this.size == CAPACITY;
        }

        @Override
        public String getAvailableJerseyNumber() {
            return "%1$s";
        }

        @Override
        public boolean hasJerseyNumber(Person person) {
            return this.person.getJerseyNumber().equals(person.getJerseyNumber());
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>(2);

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public boolean isFull() {
            return this.personsAdded.size() == 2;
        }

        @Override
        public boolean hasJerseyNumber(Person person) {
            return personsAdded.stream().anyMatch(player -> player.getJerseyNumber().equals(person.getJerseyNumber()));
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
    /*
    private class ModelStubAcceptingLineupAdded extends ModelStub {
        final ArrayList<Lineup> lineupsAdded = new ArrayList<>();

        @Override
        public boolean hasLineupName(Lineup lineup) {
            requireNonNull(lineup);
            return lineupsAdded.stream().anyMatch(lineup::isSameLineup);
        }

        @Override
        public void addLineup(Lineup lineup) {
            requireNonNull(lineup);
            lineupsAdded.add(lineup);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }
    */

    /**
     * A Model stub that contains a single schedule.
     */
    private class ModelStubWithSchedule extends ModelStub {
        private final Schedule schedule;

        ModelStubWithSchedule(Schedule schedule) {
            requireNonNull(schedule);
            this.schedule = schedule;
        }

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return this.schedule.equals(schedule);
        }
    }

    /**
     * A Model stub that always accept the schedule being added.
     */
    private class ModelStubAcceptingScheduleAdded extends ModelStub {
        final ArrayList<Schedule> schedulesAdded = new ArrayList<>();

        @Override
        public boolean hasSchedule(Schedule schedule) {
            requireNonNull(schedule);
            return schedulesAdded.stream().anyMatch(s -> s.equals(schedule));
        }

        @Override
        public void addSchedule(Schedule schedule) {
            requireNonNull(schedule);
            schedulesAdded.add(schedule);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}
