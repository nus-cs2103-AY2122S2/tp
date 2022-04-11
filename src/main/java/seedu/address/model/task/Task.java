package seedu.address.model.task;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * Represents a Task in NUSClasses.
 * Task consists of a String object representing a name and a LocalDateTime object representing the date and time.
 */
public class Task {
    public static final DateTimeFormatter FORMAT_TIME = DateTimeFormatter.ofPattern("h.mm a");
    public static final DateTimeFormatter FORMAT_DAY_OF_WEEK = DateTimeFormatter.ofPattern("EEE, h.mm a");
    public static final DateTimeFormatter FORMAT_MONTH = DateTimeFormatter.ofPattern("dd MMM, h.mm a");
    public static final DateTimeFormatter FORMAT_YEAR = DateTimeFormatter.ofPattern("dd MMM yyyy, h.mm a");
    private static final int MAX_LENGTH = 100;
    private static final int MIN_LENGTH = 3;
    public static final String NAME_LENGTH_ERROR = "The name of the tasks must be at least "
            + MIN_LENGTH + " characters long and at most " + MAX_LENGTH + " characters long";
    private String name;
    private LocalDateTime dateTime;
    private LocalDateTime endDateTime;
    private List<Person> people;
    private Set<Tag> tags;
    private Link link;
    private boolean isMarkDone;

    /**
     * Constructs Task that takes in the following parameters.
     *
     * @param name Name of task.
     * @param people People to be added to the list.
     * @param dateTime LocalDateTime object representing Date and Time for Task.
     * @param tags Tags for the tasks.
     * @param link Link to be added to the task.
     * @param isMarkDone true if task is done, else false.
     */
    public Task(String name, LocalDateTime dateTime, LocalDateTime endDateTime, List<Person> people, Set<Tag> tags,
                Link link, boolean isMarkDone) {
        this.name = name;
        this.dateTime = dateTime;
        this.endDateTime = endDateTime;
        this.people = new ArrayList<>(people);
        this.tags = tags;
        this.link = link;
        this.isMarkDone = isMarkDone;
    }

    /**
     * Constructor for Task with people but no endDateTime.
     */
    public Task(String name, LocalDateTime dateTime, List<Person> people, Set<Tag> tags, Link link,
                boolean isMarkDone) {
        this(name, dateTime, null, people, tags, link, isMarkDone);
    }

    /**
     * Constructs Task with endDateTime but no people.
     */
    public Task(String name, LocalDateTime dateTime, LocalDateTime endDateTime, Set<Tag> tags, Link link,
                boolean isMarkDone) {
        this(name, dateTime, endDateTime, new ArrayList<>(), tags, link, isMarkDone);
    }

    /**
     * Constructs Task without people or endDateTime.
     */
    public Task(String name, LocalDateTime dateTime, Set<Tag> tags, Link link, boolean isMarkDone) {
        this(name, dateTime, null, new ArrayList<>(), tags, link, isMarkDone);
    }



    /**
     * Changes name of Task.
     *
     * @param name new Name to be changed.
     */
    public void changeName(String name) {
        this.name = name;
    }

    /**
     * Adds a person to the list of people associated with the task.
     *
     * @param person Person to add.
     */
    public void addPerson(Person person) {
        people.add(person);
    }

    /**
     * Removes a person from the list of people associated with the task.
     *
     * @param person Person to remove.
     */
    public void removePerson(Person person) {
        people.remove(person);
    }

    /**
     * Updates a person in the list of people associated with the task.
     *
     * @param person The person to update.
     * @param editedPerson The edited person.
     */
    public void updatePerson(Person person, Person editedPerson) {
        int index = people.indexOf(person);
        if (index != -1) {
            people.set(index, editedPerson);
        }
    }

    /**
     * Changes DateTime of Task.
     *
     * @param newDateTime LocalDateTime of new DateTime.
     */
    public void changeDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }

    /**
     * Changes EndDateTime of Task.
     *
     * @param endDateTime LocalDateTime of new endDateTime.
     */
    public void changeEndDateTime(LocalDateTime endDateTime) {
        this.endDateTime = endDateTime;
    }

    @Override
    public String toString() {
        return this.name + " " + dateTime.format(FORMAT_YEAR);
    }

    /**
     * Checks if this task has an end date time or not.
     * @return true if this task has an end date time, false otherwise.
     */
    public boolean hasEndDateTime() {
        return this.endDateTime != null;
    }

    /**
     * Returns a user-friendly representation of the dateTime and endDateTime.
     */
    public String getDateTimeString() {
        if (endDateTime == null) {
            return getDeadline();
        }
        return getDateTimeRange();
    }

    private String getDeadline() {
        return String.format("Due: %s", getUserFriendlyDateTime(dateTime));
    }

    private String getDateTimeRange() {
        assert endDateTime != null;
        LocalDateTime date = dateTime.toLocalDate().atStartOfDay();
        LocalDateTime endDate = endDateTime.toLocalDate().atStartOfDay();

        String result;
        if (Duration.between(date, endDate).toDays() == 0) {
            result = String.format("%s - %s", getUserFriendlyDateTime(dateTime), endDateTime.format(FORMAT_TIME));
        } else {
            result = String.format("%s - %s", getUserFriendlyDateTime(dateTime), getUserFriendlyDateTime(endDateTime));
        }
        return String.format("From: %s", result);
    }

    /**
     * Methods for generating date representation relative to current calendar day.
     *
     * @param dateTime The dateTime to be processed.
     * @return User-friendly dateTime string.
     */
    static String getUserFriendlyDateTime(LocalDateTime dateTime) {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        LocalDateTime date = dateTime.toLocalDate().atStartOfDay();
        long daysFrom = Duration.between(today, date).toDays();
        String result;
        if (daysFrom == 0) { // today
            result = String.format("Today, %s", dateTime.format(FORMAT_TIME));
        } else if (daysFrom == 1) { // tomorrow
            result = String.format("Tomorrow, %s", dateTime.format(FORMAT_TIME));
        } else if (daysFrom >= 2 && daysFrom <= 7) { // this week
            result = dateTime.format(FORMAT_DAY_OF_WEEK);
        } else if (date.getYear() == today.getYear()) { // this year
            result = dateTime.format(FORMAT_MONTH);
        } else { // next year onwards
            result = dateTime.format(FORMAT_YEAR);
        }
        return result;
    }


    /**
     * Returns a user-friendly representation of the endDateTime.
     */
    public String getEndDateTimeString() {
        return this.endDateTime.format(FORMAT_YEAR);
    }


    /**
     * Returns DateTime of Task.
     *
     * @return DateTime object of Task.
     */
    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    /**
     * Returns endDateTime of Task.
     *
     * @return endDateTime object of Task.
     */
    public LocalDateTime getEndDateTime() {
        return this.endDateTime;
    }


    /**
     * Returns name of Task.
     *
     * @return Name of Task.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns Tag of Task.
     *
     * @return Tag of Task.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Sets new tags for Task object
     *
     * @param tags new Set of tags to replace previous ones
     */
    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    /**
     * Returns list of People assigned to Task.
     *
     * @return List of People.
     */
    public List<Person> getPeople() {
        return this.people;
    }

    /**
     * Returns true if both tasks have the same name.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }
        return otherTask != null
                && otherTask.getName().equals(getName());
    }

    /**
     * Returns the number of people assigned to Task.
     *
     * @return Number of people.
     */
    public int getNoOfPeople() {
        return this.people.size();
    }

    /**
     * Returns a copy-paste friendly string containing all the emails related to this task.
     * The emails will be joined with a comma separator (e.g. "e1234578@u.nus.edu.sg, e12121212@u.nus.edu.sg").
     *
     * @return The generated email string
     */
    public String getEmails() {
        String[] emails = this.people.stream().map(p -> p.getEmail().toString()).toArray(String[]::new);
        return String.join(", ", emails);
    }

    /**
     * Checks if this task contains the person.
     * @param p the person to check for
     * @return true if this task contains the person, false otherwise.
     */
    public boolean containsPerson(Person p) {
        return this.people.contains(p);
    }

    /**
     * Returns the zoom link assigned to Task.
     *
     * @return A link.
     */
    public Link getLink() {
        return link;
    }

    /**
     * Sets the isMarkDone as true to show that the task is done.
     */
    public void markTask() {
        this.isMarkDone = true;
    }

    /**
     * Sets the isMarkDone as false to show that the task is not done.
     */
    public void unmarkTask() {
        this.isMarkDone = false;
    }

    /**
     * Returns the status if the task is mark done.
     */
    public boolean isTaskMark() {
        return isMarkDone;
    }

    /**
     * Returns true if endDateTime is earlier than dateTime
     *
     * @return True if endDateTime is earlier, false if endDateTime is later
     */
    public boolean hasInvalidDateRange() {
        return endDateTime != null && dateTime.compareTo(endDateTime) >= 0;
    }

    /**
     * Returns if a given string is a valid length.
     */
    public static boolean isValidLength(String testString) {
        return (testString.length() >= MIN_LENGTH && testString.length() <= MAX_LENGTH);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getName().equals(this.getName())
                && otherTask.getDateTime().equals(this.getDateTime())
                && otherTask.getPeople().equals(this.getPeople());
    }
}
