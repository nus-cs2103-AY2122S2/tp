package seedu.trackermon.commons.core;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;

/**
 * A functionality class that is used to get class path location depending on
 * whether it is from a Jar file or the editor.
 */
public class JarTools {

    //@@author JonathanHoshi-reused
    //Reused from https://stackoverflow.com/a/936738
    // with minor modifications
    private static File getClassLocation(Class cls, boolean trmjar) {
        ClassLoader clsldr; // class loader
        URL urlobj; // url object
        String exturl; // external form of URL
        String lwrurl; // lowercase external form of URL
        File rtnfil; // return file

        if ((clsldr = cls.getClassLoader()) == null) {
            clsldr = ClassLoader.getSystemClassLoader();
        }

        if ((urlobj = clsldr.getResource(cls.getName().replace('.', '/') + ".class")) == null) {
            return null;
        }

        exturl = urlobj.toExternalForm();
        lwrurl = exturl.toLowerCase();
        while (lwrurl.startsWith("jar:") || lwrurl.startsWith("file:/")) {
            if (lwrurl.startsWith("jar:")) {
                // strip encapsulating "jar:" and "!/..." from JAR url
                if (lwrurl.indexOf("!/") != -1) {
                    exturl = exturl.substring(4, (exturl.indexOf("!/")));
                } else { // strip encapsulating "jar:"
                    exturl = exturl.substring(4);
                }
            }
            if (lwrurl.startsWith("file:/")) {
                exturl = exturl.substring(6); // strip encapsulating "file:/"
                if (!exturl.startsWith("/")) {
                    exturl = ("/" + exturl);
                }
                while (exturl.length() > 1 && exturl.charAt(1) == '/') {
                    exturl = exturl.substring(1);
                }
            }
            lwrurl = exturl.toLowerCase();
        }
        try {
            exturl = java.net.URLDecoder.decode(exturl, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        rtnfil = new File(exturl);
        if (lwrurl.endsWith(".class") || (trmjar && lwrurl.endsWith(".jar"))) {
            rtnfil = rtnfil.getParentFile();
        }
        if (rtnfil.exists()) {
            rtnfil = rtnfil.getAbsoluteFile();
        }
        return rtnfil;
    }
    //@@author

    /**
     * Returns the actual class file path location. This will return you the file path of the Jar file
     * when executing from a Jar file.
     * @param cls the class whose file path to obtain.
     * @return the file path of where the class is stored.
     */
    public static String getClassLocationString(Class cls) {
        return getClassLocation(cls, true).toPath().toString();
    }

    /**
     * Returns the status of the application is running from a Jar file
     * @return whether application is running from a Jar file
     */
    public static boolean runningFromJarFile() {
        String resourcePath = JarTools.class.getResource("JarTools.class").toString();
        return resourcePath.startsWith("jar:") || resourcePath.startsWith("rsrc:");
    }
}
