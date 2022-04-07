package unibook.ui.util;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.scene.Node;
import javafx.scene.layout.Pane;
import unibook.commons.util.TriFunction;

/**
 * Util class for {@code Pane} filler functions.
 */
public class CustomPaneListFiller {

    /**
     * Fills a {@code Pane} from given list with {@code Node} objects genreated using {@code converter}.
     *
     * @param pane           to fill.
     * @param underlyingList to fill from.
     * @param converter      to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillPaneFromList(Pane pane, List<T> underlyingList, Function<T, ? extends Node> converter) {
        if (underlyingList.size() == 0) {
            return;
        }
        pane.getChildren().clear();
        for (T item : underlyingList) {
            pane.getChildren().add(converter.apply(item));
        }
    }

    /**
     * Fills a {@code Pane} from given list with {@code Node} objects genreated using {@code converter}, with indexes.
     *
     * @param pane           to fill.
     * @param underlyingList to fill from.
     * @param converter      to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillPaneFromList(Pane pane, List<T> underlyingList, BiFunction<T, Integer,
        ? extends Node> converter) {
        if (underlyingList.size() == 0) {
            return;
        }
        pane.getChildren().clear();
        for (int i = 0; i < underlyingList.size(); i++) {
            pane.getChildren().add(converter.apply(underlyingList.get(i), i));
        }
    }

    /**
     * Fills a {@code Pane} from given list with {@code Node} objects genreated using {@code converter},
     * with indexes, and flag to represent if only one item in the list of children in pane.
     *
     * @param pane           to fill.
     * @param underlyingList to fill from.
     * @param converter      to use to convert a list item to node.
     * @param <T>
     */
    public static <T> void fillPaneFromList(Pane pane, List<T> underlyingList, TriFunction<T, Integer, Boolean,
        ? extends Node> converter) {
        if (underlyingList.size() == 0) {
            return;
        }
        pane.getChildren().clear();
        for (int i = 0; i < underlyingList.size(); i++) {
            pane.getChildren().add(converter.apply(underlyingList.get(i), i, underlyingList.size() == 1));
        }
    }
}
