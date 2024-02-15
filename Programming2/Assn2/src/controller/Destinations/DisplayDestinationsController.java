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
import javafx.beans.value.*;

public class DisplayDestinationsController extends Controller<Destinations>{
    @FXML private TextField filterTf;
	@FXML private TableView<Destination> filteredDestinationsTv;

    public final Destinations getDestinations() { return model; }
    
    @FXML private void initialize() {
		filterTf.textProperty().addListener(
			(o, oldText, newText) -> {
				if (newText != null) {
					filteredDestinationsTv.itemsProperty().unbind();
					filteredDestinationsTv.setItems(getDestinations().getFilteredDestinations(newText));
				} else {
					filteredDestinationsTv.itemsProperty().unbind();
					filteredDestinationsTv.setItems(getDestinations().getDestinations());
				}
			}
		);
	}

    @FXML private void handleClose(ActionEvent e) {
		stage.close();
	}
}
