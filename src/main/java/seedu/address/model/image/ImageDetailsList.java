package seedu.address.model.image;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageDetailsList implements Iterable<ImageDetails> {
    private final List<ImageDetails> images;

    public ImageDetailsList() {
        this.images = new ArrayList<>();
    }

    public ImageDetailsList(List<ImageDetails> images) {
        this.images = images;
    }

    /**
     * Creates an iterable list of image details, appended to this current list.
     * The returned list is a new list.
     *
     * @param appendedImages the images to append to this list.
     * @return the newly appended list.
     */
    public ImageDetailsList appendImageDetails(List<ImageDetails> appendedImages) {
        List<ImageDetails> newImages = new ArrayList<>();
        newImages.addAll(images);
        newImages.addAll(appendedImages);
        return new ImageDetailsList(newImages);
    }

    public List<ImageDetails> getImages() {
        return this.images;
    }

    public ImageDetails get(int i) {
        return this.images.get(i);
    }

    public boolean isEmpty() {
        return this.images.isEmpty();
    }

    /**
     * Returns the size of the list, indicated by the number of ImageDetails within the list.
     *
     * @return size of the list.
     */
    public int size() {
        return this.images.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < images.size(); i++) {
            sb.append(i + 1).append(": ").append(images.get(i)).append("\n");
        }
        return sb.toString();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ImageDetailsList // instanceof handles nulls
                && images.equals(((ImageDetailsList) other).images));
    }

    @Override
    public int hashCode() {
        return images.hashCode();
    }


    /**
     * Returns an iterator over elements of type {@code Images}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ImageDetails> iterator() {
        return this.images.iterator();
    }
}
