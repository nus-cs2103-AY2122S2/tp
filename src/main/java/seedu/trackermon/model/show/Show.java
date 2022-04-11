package seedu.trackermon.model.show;

import static seedu.trackermon.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.trackermon.model.tag.Tag;

/***
 * Represents a Show in Trackermon.
 */
public class Show {

    //Identity field
    private final Name name;
    private final Status status;

    //Data field
    private final Set<Tag> tags = new HashSet<>();
    private final Comment comment;
    private final Rating rating;

    /**
     * Constructs a {@code Show} with the provided {@code Name}, {@code Status}, {@code Set<Tag>},
     * {@code Comment}, {@code Rating}
     * @param name provided {@code Name}
     * @param tags provided {@code Set<Tag>}
     * @param comment provided {@code Comment}
     * @param rating provided {@code Rating}
     */
    public Show(Name name, Status status, Set<Tag> tags, Comment comment, Rating rating) {
        requireAllNonNull(name, status, tags, comment, rating);
        this.name = name;
        this.status = status;
        this.tags.addAll(tags);
        this.comment = comment;
        this.rating = rating;
    }

    public Name getName() {
        return name;
    }

    public Status getStatus() {
        return status;
    }

    public Rating getRating() {
        return rating;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public Comment getComment() {
        return comment;
    }

    /**
     * Returns true if both show have the same name.
     * This defines a weaker notion of equality between two show.
     */
    public boolean isSameShow(Show otherShow) {
        if (otherShow == this) {
            return true;
        }

        return otherShow != null
                && otherShow.getName().equals(getName());
    }

    /**
     * Returns true if both shows have the same identity and data fields.
     * This defines a stronger notion of equality between two shows.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Show)) {
            return false;
        }

        Show otherShow = (Show) other;
        boolean isSameName = otherShow.getName().equals(getName());
        boolean isSameTags = otherShow.getTags().equals(getTags());
        boolean isSameStatus = otherShow.getStatus().equals(getStatus());
        boolean isSameComment = otherShow.getComment().equals(getComment());
        boolean isSameRating = otherShow.getRating().equals(getRating());
        return isSameName && isSameTags && isSameStatus && isSameComment && isSameRating;
    }

    /**
     * Returns the hashcode of the {@code Show}.
     */
    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, tags);
    }

    /**
     * Returns the {@code String} representation of the {@code Show}.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName().toString());
        builder.append("; Status: " + getStatus().toString());
        builder.append("; Rating: " + getRating().toString());
        builder.append("; Comment: " + getComment().toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(x -> builder.append("[" + x + "]"));
        }
        builder.append(";");
        return builder.toString();
    }

    /**
     * Compare this name with other lexicographically
     */
    public int compareNames(Show other) {
        return this.name.compareTo(other.name);
    }

    /**
     * Compare this status with other lexicographically
     */
    public int compareStatus(Show other) {
        return this.status.compareTo(other.status);
    }

    /**
     * Compare this rating with other by rating integer value
     */
    public int compareRating(Show other) {
        return this.rating.compareTo(other.rating);
    }

    /**
     * Compare this tag with other by number of tags
     */
    public int compareTags(Show other) {
        return Integer.compare(this.tags.size(), other.tags.size());
    }



}
