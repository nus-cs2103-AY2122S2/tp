package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.model.comparator.DeadlineComparator;

public class DeadlineList implements Comparable<DeadlineList> {
    public static final String MODEL_NAME = "deadline";
    private static final Deadline EMPTY_DEADLINE = new Deadline();

    private ArrayList<Deadline> deadlines = new ArrayList<>();

    public DeadlineList() {
        this.deadlines.add(new Deadline());
    }

    /**
     * Constructs a {@code DeadlineList} object.
     *
     * @param deadlines the list of the deadlines to be put into {@code DeadlineList}.
     */
    public DeadlineList(List<Deadline> deadlines) {
        requireNonNull(deadlines);
        this.deadlines.addAll(deadlines);
    }

    public ArrayList<Deadline> getDeadlines() {
        return deadlines;
    }

    /**
     * Adds the given deadlines to this list of deadlines and returns it in a new
     * {@code DeadlineList}.
     * @param deadlinesToAppend deadlines to add to this list.
     * @return new DeadlineList with the result.
     */
    public DeadlineList appendDeadlines(DeadlineList deadlinesToAppend) {
        if (this.size() == 0) {
            return deadlinesToAppend;
        }
        DeadlineList newDeadlines = new DeadlineList(this.deadlines);
        newDeadlines.deadlines.addAll(deadlinesToAppend.deadlines);
        return newDeadlines;
    }

    /**
     * Deletes the deadline at the given index of this list of deadlines and return it in
     * a new {@code DeadlineList}.
     * @param index index of deadline to delete.
     * @return new DeadlineList with the result.
     */
    public DeadlineList delete(int index) {
        DeadlineList newDeadlineList = new DeadlineList(this.deadlines);
        newDeadlineList.deadlines.remove(index);
        if (newDeadlineList.deadlines.isEmpty()) {
            return new DeadlineList();
        }
        return newDeadlineList;
    }

    /**
     * Produces a string representation of this {@code Deadline} object for printing to GUI.
     *
     * @return list view of deadlines in this {@code Deadline} object.
     */
    public String listFormat() {
        if (deadlines.size() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= deadlines.size(); i++) {
            result.append(deadlines.get(i - 1)).append("\n");
        }
        return result.toString();
    }

    /**
     * Gives the size of this deadline list, considering only deadlines given by user.
     */
    public int size() {
        return this.deadlines.get(0).equals(EMPTY_DEADLINE) ? 0
                : this.deadlines.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < deadlines.size(); i++) {
            result.append(deadlines.get(i));
            if (i < deadlines.size() - 1) {
                result.append(", ");
            }
        }
        return result.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeadlineList // instanceof handles nulls
                && deadlines.equals(((DeadlineList) other).deadlines)); // state check
    }

    @Override
    public int compareTo(DeadlineList other) {
        return Collections.min(this.deadlines, new DeadlineComparator())
                .compareTo(Collections.min(other.getDeadlines(), new DeadlineComparator()));
    }
}
