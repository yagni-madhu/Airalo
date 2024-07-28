package apiendpoints;

import java.util.ResourceBundle;

public class Properties {
    public static ResourceBundle getUrl() {
        ResourceBundle resource = ResourceBundle.getBundle("routes"); //load property file directly from test.resources
        return resource;

    }
}
