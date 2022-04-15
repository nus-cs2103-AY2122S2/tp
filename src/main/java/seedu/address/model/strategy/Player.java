package seedu.address.model.strategy;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.util.Objects;

public class Player {
    public static final String MESSAGE_NAME_CONSTRAINTS = "Player name cannot be empty"
            + " and must be not more then 24 characters"
            + " and must not contain \"/\". \n";
    public static final String MESSAGE_X_CONSTRAINTS = "X coordinate must be an integer between 0 and 1000";
    public static final String MESSAGE_Y_CONSTRAINTS = "Y coordinate must be an integer between 0 and 600";

    private static final int DEF_XCOORD = 50;
    private static final int DEF_YCOORD = 500;
    private static final int X_LIMIT = 1000;
    private static final int Y_LIMIT = 600;

    private final String name;
    private int xCoord;
    private int yCoord;

    /**
     * Creates a new player without specifying the coordinates
     */
    public Player(String name) {
        requireNonNull(name);
        checkArgument(isValidPlayer(name), MESSAGE_NAME_CONSTRAINTS);
        this.name = name;
        this.xCoord = DEF_XCOORD;
        this.yCoord = DEF_YCOORD;
    }

    /**
     * Creates a new player with specified coordinates
     */
    public Player(String name, int xCoord, int yCoord) {
        requireNonNull(name);
        checkArgument(isValidPlayer(name), MESSAGE_NAME_CONSTRAINTS);
        checkArgument(isValidXCoord(xCoord), MESSAGE_X_CONSTRAINTS);
        checkArgument(isValidYCoord(yCoord), MESSAGE_Y_CONSTRAINTS);
        this.name = name;
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    /**
     * Returns true if the given player name is a valid player name.
     */
    public static boolean isValidPlayer(String playerName) {
        return !playerName.isEmpty() && playerName.length() <= 50 && !playerName.contains("/");
    }

    public static boolean isValidXCoord(int xCoord) {
        return xCoord >= 0 && xCoord <= X_LIMIT;
    }

    public static boolean isValidYCoord(int yCoord) {
        return yCoord >= 0 && yCoord <= Y_LIMIT;
    }

    //Getters
    public String getName() {
        return name;
    }

    public int getXCoord() {
        return xCoord;
    }

    public int getYCoord() {
        return yCoord;
    }

    //Setters
    public void setXCoord(int xCoord) {
        checkArgument(isValidXCoord(xCoord), MESSAGE_X_CONSTRAINTS);
        this.xCoord = xCoord;
    }

    public void setYCoord(int yCoord) {
        checkArgument(isValidYCoord(yCoord), MESSAGE_Y_CONSTRAINTS);
        this.yCoord = yCoord;
    }

    //uti methods
    public Player toCopy() {
        return new Player(name, xCoord, yCoord);
    }

    /**
     * Returns true if both players have the same name
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Player)) {
            return false;
        }

        Player otherPlayer = (Player) other;
        return otherPlayer.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, xCoord, yCoord);
    }
}
