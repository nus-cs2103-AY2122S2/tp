package unibook.ui.util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import unibook.commons.util.TriFunction;

/**
 * Util class for VBox filler functions.
 */
public class CustomVBoxListFiller {

    /**
     * Fills a vbox from given list with {@code Node} objects genreated using {@code converter}.
     * @param vbox to fill.
     * @param underlyingList to fill from.
     * @param converter to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillVBoxFromList(VBox vbox, List<T> underlyingList, Function<T, ? extends Node> converter) {
        for (T item : underlyingList) {
           vbox.getChildren().add(converter.apply(item));
        }
    }

    /**
     * Fills a vbox from given list with {@code Node} objects genreated using {@code converter}, with indexes.
     * @param vbox to fill.
     * @param underlyingList to fill from.
     * @param converter to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillVBoxFromList(VBox vbox, List<T> underlyingList, BiFunction<T, Integer,
                                                                                    ? extends Node> converter) {
        for (int i = 0; i < underlyingList.size(); i++) {
            vbox.getChildren().add(converter.apply(underlyingList.get(i), i));
        }
    }

    /**
     * Fills a vbox from given list with {@code Node} objects genreated using {@code converter}, with indexes, and flag
     * to represent if only one item in the list of children in vbox.
     * @param vbox to fill.
     * @param underlyingList to fill from.
     * @param converter to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillVBoxFromList(VBox vbox, List<T> underlyingList, TriFunction<T, Integer, Boolean,
                                                                                        ? extends Node> converter) {
        for (int i = 0; i < underlyingList.size(); i++) {
            vbox.getChildren().add(converter.apply(underlyingList.get(i), i, underlyingList.size() == 1));
        }
    }
}
