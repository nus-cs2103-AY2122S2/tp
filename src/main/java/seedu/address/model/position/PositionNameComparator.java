package seedu.address.model.position;

import java.util.Comparator;

public class PositionNameComparator implements Comparator<Position> {
    private final String sortArgument;

    public PositionNameComparator(String sortArgument) {
        this.sortArgument = sortArgument;
    }

    @Override
    public int compare(Position o1, Position o2) {
        if (sortArgument.equals("asc")) {
            return o1.getPositionName().positionName.compareTo(o2.getPositionName().positionName);
        } else {
            return o2.getPositionName().positionName.compareTo(o1.getPositionName().positionName);
        }
    }
}
