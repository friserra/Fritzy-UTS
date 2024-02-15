package controller;

import javafx.collections.*;
import javafx.event.*;
import javafx.fxml.*;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import java.io.*;
import java.text.*;
import au.edu.uts.ap.javafx.*;
import model.*;

public class AgencyController extends Controller<Agency> {
	public final Agency getAgency() { return model; }

	@FXML private void handleExploreFlights(ActionEvent e) throws Exception {
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(getAgency().getFlights(), "/view/Flights/ExploreFlightsView.fxml", "Explore Flights", s);
	}

	@FXML private void handleExploreDestinations(ActionEvent e) throws Exception {
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getAgency().getDestinations(), "/view/Destinations/ExploreDestinationsView.fxml", "Explore Destinations", s);
	}

	@FXML private void handleBookTrip(ActionEvent e) throws Exception {
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/trip_icon.png"));
		ViewLoader.showStage(new Trip(getAgency()), "/view/Trip/BookTripView.fxml", "Book a Trip", s);
	}

	@FXML private void handleExit(ActionEvent e) {
		stage.close();
	}
}
