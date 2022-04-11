package unibook.ui.util;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import unibook.commons.util.TriFunction;

/**
 * Util class for adding custom {@code ListChangeListener} to a given pane object, so that it updates when
 * the underlying list updates.
 */
public class CustomListChangeListeners {

    /**
     * Adds a basic {@code ListChangeListener} to the given {@code underlyingList}. Updates the children nodes
     * of {@code pane} with nodes produced by the Function {@code toChildNode}.
     *
     * @param pane           the pane object whose children to update.
     * @param underlyingList list whose updates to are being tracked.
     * @param toChildNode    converter function that converts a given object of type U to a {@code Node}.
     * @param <T>            type of pane object.
     * @param <U>            type of object in the underlyingList.
     */
    public static <T extends Pane, U> void addBasicListChangeListener(T pane, ObservableList<U> underlyingList,
                                                                      Function<U, ? extends Node> toChildNode) {
        underlyingList.addListener(new ListChangeListener<>() {
            /**
             * Update the children of the given pane shown to viewer on any kind of change to underlyingList.
             */
            @Override
            public void onChanged(Change<? extends U> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        ArrayList<Node> nodes = new ArrayList<>();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            //save old objects at permuted positions
                            nodes.add(pane.getChildren().get(i));
                        }
                        int startIndex = c.getFrom();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren().set(c.getPermutation(i), nodes.get(startIndex - i));
                        }
                    } else if (c.wasUpdated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren()
                                .set(i, toChildNode.apply(underlyingList.get(i)));
                        }
                    } else {
                        //just refresh whole pane on add or remove
                        pane.getChildren().clear();
                        for (int i = 0; i < underlyingList.size(); i++) {
                            pane.getChildren().add(i, toChildNode.apply(underlyingList.get(i)));
                        }
                    }
                }
            }
        });
    }

    /**
     * Adds a basic {@code ListChangeListener} to a given pane object, that updates the children nodes of
     * the pane object, with the index of the object in the underlying list,
     * given any kind of change to the underlyingList of type U.
     *
     * @param pane           the pane object whose children to update.
     * @param underlyingList list whose updates to are being tracked.
     * @param toChildNode    converter function that converts a given object of type U to a {@code Node},
     *                       using a given index, to add to the children list of {@code pane}
     * @param <T>            type of pane object.
     * @param <U>            type of object in the underlyingList.
     */
    public static <T extends Pane, U> void addIndexedListChangeListener(T pane, ObservableList<U> underlyingList,
            BiFunction<U, Integer, ? extends Node> toChildNode) {
        underlyingList.addListener(new ListChangeListener<>() {
            /**
             * Update the children of the given pane shown to viewer on any kind of change to underlyingList.
             * Passes in the index of the affected item in underlying list to the {@code toChildNode} function.
             */
            @Override
            public void onChanged(Change<? extends U> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        ArrayList<Node> nodes = new ArrayList<>();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            //save old objects at permuted positions
                            nodes.add(pane.getChildren().get(i));
                        }
                        int startIndex = c.getFrom();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren().set(c.getPermutation(i), nodes.get(startIndex - i));
                        }
                    } else if (c.wasUpdated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren()
                                .set(i, toChildNode.apply(underlyingList.get(i), i));
                        }
                    } else {
                        //just refresh whole pane on add or remove
                        pane.getChildren().clear();
                        for (int i = 0; i < underlyingList.size(); i++) {
                            pane.getChildren().add(i, toChildNode.apply(underlyingList.get(i), i));
                        }
                    }
                }
            }
        });
    }

    /**
     * Adds a basic {@code ListChangeListener} to a given pane object, that updates the children nodes of
     * the pane object, with the index of the object in the underlying list, and a flag indicating if
     * it is the only object being shown.
     * given any kind of change to the underlyingList of type U.
     *
     * @param pane           the pane object whose children to update.
     * @param underlyingList list whose updates to are being tracked.
     * @param toChildNode    converter function that converts a given object of type U to a {@code Node},
     *                       using a given index and flag, to add to the children list of {@code pane}
     * @param <T>            type of pane object.
     * @param <U>            type of object in the underlyingList.
     */
    public static <T extends Pane, U> void addIndexedAndFlagListChangeListener(T pane, ObservableList<U> underlyingList,
                                                                               TriFunction<U, Integer, Boolean,
                                                                                   ? extends Node> toChildNode) {
        underlyingList.addListener(new ListChangeListener<>() {
            /**
             * Update the children of the given pane shown to viewer on any kind of change to underlyingList.
             * Passes in the index of the affected item in underlying list to the {@code toChildNode} function.
             */
            @Override
            public void onChanged(Change<? extends U> c) {
                while (c.next()) {
                    if (c.wasPermutated()) {
                        ArrayList<Node> nodes = new ArrayList<>();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            //save old objects at permuted positions
                            nodes.add(pane.getChildren().get(i));
                        }
                        int startIndex = c.getFrom();
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren().set(c.getPermutation(i), nodes.get(startIndex - i));
                        }
                    } else if (c.wasUpdated()) {
                        for (int i = c.getFrom(); i < c.getTo(); ++i) {
                            pane.getChildren()
                                .set(i, toChildNode.apply(underlyingList.get(i), i, underlyingList.size() == 1));
                        }
                    } else {
                        //just refresh whole pane on add or remove
                        pane.getChildren().clear();
                        for (int i = 0; i < underlyingList.size(); i++) {
                            pane.getChildren().add(i, toChildNode.apply(underlyingList.get(i),
                                i, underlyingList.size() == 1));
                        }
                    }
                }
            }
        });
    }
}
