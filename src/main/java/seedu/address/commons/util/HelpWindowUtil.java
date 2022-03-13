package seedu.address.commons.util;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

public class HelpWindowUtil {

    private String url;

    public HelpWindowUtil(String url) {
        this.url = url;
    }

    /**
     * Converts string into URI, and opens in User's default web browser.
     *
     * @throws IOException when there is an error opening the website.
     */
    public void goToUrl() throws IOException {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        try {
            desktop.browse(convertToUrl());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Converts string into URI.
     *
     * @return the URI given.
     * @throws IllegalArgumentException if wrong input given.
     */
    public URI convertToUrl() throws IllegalArgumentException {
        return URI.create(this.url);
    }

    public String getUrl() {
        return url;
    }
}
