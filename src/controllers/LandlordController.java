package controllers;

import java.util.ArrayList;

import models.Database;
import models.Property;
import views.LandlordView;

public class LandlordController extends GUIcontroller {

    private LandlordView view;
    private LoginController login;
    private String username;

    public LandlordController(Database model, LandlordView view, LoginController login, String username) {
        super(model);
        this.view = view;
        this.login = login;

    }

    public void start() {
        view.intializeDashboard();
        view.displayDashboard();
        // logouts by destroying current user frame and recreating intial login frame
        view.addLogoutListener(e -> {
            view.dispose();
            login.start();

        });
        view.formListener(e -> registerProperty());
        ArrayList<Property> properties = new ArrayList<>();
        properties.add(new Property("Apartment", true, 5, 5, false, 5, "NW", "Listed", "123 street", 5, 5.6, "gary"));
        properties.add(new Property("House", true, 55, 59, false, 5, "SW", "Listed", "1234 street", 52, 5.62, "sandy"));
        view.addChangeListingListener(e -> {
            view.displayListingChanges(properties);
        });
    }
    public void changeListing(int propertyiD, String newListing){
        model.changePropertyListing(propertyiD, newListing);
    }

    public void registerProperty() {
        // gets registration values in this order address number, addresss name,
        // bathrooms, bedrooms, property type, quadrant, furnished
        String value[] = view.getFormValues();
        int bedrooms;
        int bathrooms;
        Boolean furnished = false;
        try {
            bedrooms = Integer.parseInt(value[2]);
            bathrooms = Integer.parseInt(value[3]);
            if(value[6].equals("furnished")){
                furnished = true;
            }
            Property property = new Property(value[4], false, bedrooms, bathrooms, furnished, 
            0, value[5], "suspended", value[0]+value[1], 0, 0, username, false);
            model.registerProperty(property);
        } catch (NumberFormatException e) {
            view.formError("Non number put in number field");
            return;
        }

        if (!isStringAlpha(value[4]) || !isStringAlpha(value[1])) {
            view.formError("Non alphabetic character put in invalid field");
            return;
        }

    }

}
