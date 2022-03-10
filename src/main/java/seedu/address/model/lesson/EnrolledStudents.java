package seedu.address.model.lesson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.model.person.Person;

public class EnrolledStudents {
    private final List<Person> enrolledStudents;

    public EnrolledStudents() {
        this.enrolledStudents = new ArrayList<>();
    }

    public List<Person> getEnrolledStudents() {
        return enrolledStudents;
    }

    public boolean addStudent(Person student) {
        return this.enrolledStudents.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnrolledStudents)) {
            return false;
        }
        EnrolledStudents otherEnrolledStudents = (EnrolledStudents) o;
        List<Person> otherStudentsList = otherEnrolledStudents.getEnrolledStudents();
        if (this.enrolledStudents.size() == otherStudentsList.size()) {
            HashSet<Person> compareMap = new HashSet<>(enrolledStudents);
            for (Person student: otherStudentsList) {
                if (compareMap.add(student)) { // if returns true, a new Person is being added
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(enrolledStudents);
    }
}
