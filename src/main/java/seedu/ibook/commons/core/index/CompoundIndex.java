package seedu.ibook.commons.core.index;

public class CompoundIndex {
    private Index first;
    private Index second;

    /**
     * CompoundIndex can only be created by calling {@link CompoundIndex#fromZeroBased(int, int)} or
     * {@link CompoundIndex#fromOneBased(int, int)}.
     */
    private CompoundIndex(int zeroBasedFirst, int zeroBasedSecond) {
        if (zeroBasedFirst < 0 || zeroBasedSecond < 0) {
            throw new IndexOutOfBoundsException();
        }

        this.first = Index.fromZeroBased(zeroBasedFirst);
        this.second = Index.fromZeroBased(zeroBasedSecond);
    }

    public int getZeroBasedFirst() {
        return first.getZeroBased();
    }

    public int getOneBasedFirst() {
        return first.getOneBased();
    }

    public int getZeroBasedSecond() {
        return second.getZeroBased();
    }

    public int getOneBasedSecond() {
        return second.getOneBased();
    }

    /**
     * Creates a new {@code Index} using a zero-based index.
     */
    public static CompoundIndex fromZeroBased(int zeroBasedFirst, int zeroBasedSecond) {
        return new CompoundIndex(zeroBasedFirst, zeroBasedSecond);
    }

    /**
     * Creates a new {@code Index} using a one-based index.
     */
    public static CompoundIndex fromOneBased(int oneBasedFirst, int oneBasedSecond) {
        return new CompoundIndex(oneBasedFirst - 1, oneBasedSecond - 1);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CompoundIndex)) {
            return false;
        }

        CompoundIndex otherCompoundIndex = (CompoundIndex) other;

        return first.equals(otherCompoundIndex.first)
                && second.equals(otherCompoundIndex.second);
    }
}
