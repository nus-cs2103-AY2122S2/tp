package seedu.address.model.student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import seedu.address.model.lesson.Lesson;

public class EnrolledLessons {
    private final List<Lesson> lessonsList;

    public EnrolledLessons() {
        lessonsList = new ArrayList<>();
    }

    public EnrolledLessons(List<Lesson> enrolledLessons) {
        lessonsList = enrolledLessons;
    }

    public List<Lesson> getLessonsList() {
        return this.lessonsList;
    }

    /**
     * Adds a lesson to the list of lessons.
     * @param lesson a lesson
     * @return a boolean stating if the lesson was successfully added
     */
    public boolean addLesson(Lesson lesson) {
        if (isEnrolled(lesson)) {
            return false;
        }
        return this.lessonsList.add(lesson);
    }

    public boolean isEnrolled(Lesson lesson) {
        return lessonsList.contains(lesson);
    }

    /**
     * Replaces the lesson that is edited to be the edited lesson.
     * @param target lesson that is edited
     * @param editedLesson the lesson after the edit
     */
    public void replaceEditedLesson(Lesson target, Lesson editedLesson) {
        for (int i = 0; i < lessonsList.size(); i++) {
            if (lessonsList.get(i).equals(target)) {
                lessonsList.set(i, editedLesson);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EnrolledLessons)) {
            return false;
        }
        EnrolledLessons otherEnrolledLesson = (EnrolledLessons) o;
        List<Lesson> otherLessonList = otherEnrolledLesson.getLessonsList();
        if (this.lessonsList.size() == otherLessonList.size()) {
            HashSet<Lesson> compareMap = new HashSet<>(lessonsList);
            for (Lesson lesson: otherLessonList) {
                if (compareMap.add(lesson)) { // if returns true, a new lesson is being added
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
        return Objects.hash(lessonsList);
    }
}
