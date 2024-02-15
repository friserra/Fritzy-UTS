package controller.Destinations;

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

public class ExploreDestinationsController extends Controller<Destinations> {
	public final Destinations getDestinations() { return model; }

	@FXML private void handleViewAllDestinations(ActionEvent e) throws Exception {
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayDestinationsView.fxml", "Display Destinations", s);
	}

	@FXML private void handleViewFilteredDestinations(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getDestinations(), "/view/Destinations/DisplayFilteredDestinationsView.fxml", "Display Destinations Filtered", s);
	}

	@FXML private void handleAddDestination(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getDestinations(), "/view/Destinations/AddDestinationView.fxml", "Add Destination", s);
	}

	@FXML private void handleRemoveDestination(ActionEvent e) throws Exception{
		Stage s = new Stage();
		s.getIcons().add(new Image("/image/destinations_icon.png"));
		ViewLoader.showStage(getDestinations(), "/view/Destinations/RemoveDestinationView.fxml", "Remove Destination", s);
	}

	@FXML private void handleClose(ActionEvent e){
		stage.close();
	}
}
