package controller.Trip;

import javafx.collections.*;
import javafx.collections.ListChangeListener.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import java.io.*;
import java.text.*;
import java.util.InputMismatchException;

import au.edu.uts.ap.javafx.*;
import model.*;
import model.Exceptions.ErrorModel;

public class DisplayTripController extends Controller<Trip> {
    @FXML private ListView<Itinery> itineryLv;

    public final Trip getTrip() { return model; }
    
    private String checkClasses(ObservableList<Itinery> selection) throws InputMismatchException {
        Itinery previous = null;
        
        for (Itinery i : selection) {
            if (previous == null) {
                previous = i;
                continue;
            }

            if (!i.getClass().getName().equals(previous.getClass().getName())) { throw new InputMismatchException(); }
        }

        return previous.getClass().getName();
    }

    private void viewFlights(ObservableList<Itinery> selection) throws Exception {
        Flights selectedTripFlights = new Flights(getTrip().getAgency());
        ObservableList<Flight> tripFlights = getTrip().getFlights().getFlights();

        for (Itinery i : selection) {
            for (Flight f : tripFlights) {
                if (f.equals(i)) {
                    selectedTripFlights.addFlight(f);
                    continue;
                }
            }
        }

        Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(selectedTripFlights, "/view/Flights/DisplayFlightsView.fxml", "Display Flights", s);
    }

    private void viewDestinations(ObservableList<Itinery> selection) throws Exception {
        Destinations selectedTripDestinations = new Destinations(getTrip().getAgency());
        ObservableList<Destination> tripDestinations = getTrip().getDestinations().getDestinations();

        for (Itinery i : selection) {
            for (Destination d : tripDestinations) {
                if (d.equals(i)) {
                    selectedTripDestinations.addDestination(d);
                    continue;
                }
            }
        }

        Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(selectedTripDestinations, "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", s);
    }

    @FXML private void initialize() {
        itineryLv.setItems(getTrip().getItinery());
        itineryLv.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
        getTrip().getDestinations().getDestinations().addListener((ListChangeListener<Destination>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    itineryLv.setItems(getTrip().getItinery());
                }
            }
        });

        getTrip().getFlights().getFlights().addListener((ListChangeListener<Flight>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    itineryLv.setItems(getTrip().getItinery());
                }
            }
        });
    }

    @FXML private void handleViewIndividual(ActionEvent e) throws Exception {
        ObservableList<Itinery> selection = itineryLv.getSelectionModel().getSelectedItems();
        
        try {
            String cls = checkClasses(selection);
            if (cls.contains("Flight")) {
                viewFlights(selection);
            } else if (cls.contains("Destination")) {
                viewDestinations(selection);
            }
        } catch (InputMismatchException error) {
            Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "You must only select Destinations or Flights, not both"), "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }

    @FXML private void handleClose(ActionEvent e) {
        stage.close();
    }
}
