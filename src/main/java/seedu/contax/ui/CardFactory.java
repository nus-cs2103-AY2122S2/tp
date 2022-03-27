package seedu.contax.ui;

/**
 * Specifies a factory interface for creating {@code RecyclableCard} objects.
 *
 * @param <T> The type of model the cards created by this factory is for.
 */
@FunctionalInterface
public interface CardFactory<T> {
    RecyclableCard<T> createCard();
}
