package controller.Flights;

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

public class ExploreFlightsController extends Controller<Flights>{
	public final Flights getFlights() { return model; }

	@FXML private void handleViewAllFlights(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFlightsView.fxml", "Display Flights", s);
	}

	@FXML private void handleViewFilteredFlights(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(getFlights(), "/view/Flights/DisplayFilteredFlightsView.fxml", "Display Flights Filtered", s);
	}

	@FXML private void handleAddFlight(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(getFlights(), "/view/Flights/AddFlightView.fxml", "Add Flight", s);
	}

	@FXML private void handleRemoveFlight(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/flights_icon.png"));
		ViewLoader.showStage(getFlights(), "/view/Flights/RemoveFlightView.fxml", "Remove Flight", s);
	}

	@FXML private void handleClose(ActionEvent e){
		stage.close();
	}
}
