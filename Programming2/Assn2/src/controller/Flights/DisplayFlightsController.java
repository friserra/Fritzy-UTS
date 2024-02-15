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
import javafx.beans.value.*;

public class DisplayFlightsController extends Controller<Flights> {
	@FXML private TextField filterTf;
	@FXML private TableView<Flight> filteredFlightsTv;

	public final Flights getFlights() { return model; }

	@FXML private void initialize() {
		filterTf.textProperty().addListener(
			(o, oldText, newText) -> {
				if (newText != null) {
					filteredFlightsTv.itemsProperty().unbind();
					filteredFlightsTv.setItems(getFlights().getFilteredFlights(newText));
				} else {
					filteredFlightsTv.itemsProperty().unbind();
					filteredFlightsTv.setItems(getFlights().getFlights());
				}
			}
		);
	}

	@FXML private void handleClose(ActionEvent e) {
		stage.close();
	}
}
