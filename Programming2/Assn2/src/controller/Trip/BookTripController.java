package controller.Trip;

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
import model.Exceptions.DuplicateItemException;
import model.Exceptions.ErrorModel;
import model.Exceptions.InsufficientDestinationsException;

public class BookTripController extends Controller<Trip>{
    public final Trip getTrip() { return model; }

    @FXML private void handleAddDestination(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", s);
	}

    @FXML private void handleRemoveDestination(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getTrip().getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", s);
	}

    @FXML private void handleAddConnectingFlights(ActionEvent e) throws Exception {
        try {
            getTrip().addConnectingFlights();
        } catch (InsufficientDestinationsException error) {
            Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Connecting flights require more than one (1) destination in the trip"), "/view/Error/ErrorView.fxml", "Error", errorStage);
        } catch (DuplicateItemException error) {
            Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Duplicate flight found in trip"), "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }

    @FXML private void handleViewTrip(ActionEvent e) throws Exception{
        Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getTrip(), "/view/Trip/DisplayTripView.fxml", "Display Trip", s);
    }

    @FXML private void handleClose(ActionEvent e){
		stage.close();
	}
}
