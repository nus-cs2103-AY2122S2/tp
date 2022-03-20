package seedu.address.testutil;

import java.util.Optional;

import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.StudentId;
import seedu.address.model.student.Telegram;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_STUDENT_ID = "E0123456";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";

    private Name name;
    private StudentId studentId;
    private Email email;
    private Optional<Telegram> telegram;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        studentId = new StudentId(DEFAULT_STUDENT_ID);
        email = new Email(DEFAULT_EMAIL);
        telegram = Optional.empty();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        studentId = studentToCopy.getStudentId();
        email = studentToCopy.getEmail();
        telegram = studentToCopy.getTelegram();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withStudentId(String studentId) {
        this.studentId = new StudentId(studentId);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Telegram} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegram(String telegram) {
        this.telegram = Optional.of(new Telegram(telegram));
        return this;
    }

    public Student build() {
        return new Student(studentId, name, email, telegram);
    }

}
