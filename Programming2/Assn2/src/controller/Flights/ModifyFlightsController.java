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
import model.Exceptions.*;
import javafx.beans.value.*;

public class ModifyFlightsController extends Controller<Flights> {
	@FXML private TextField airlineTf;
	@FXML private TextField flightNoTf;
	@FXML private TextField takeoffTf;
	@FXML private TextField landingTf;
	@FXML private TextField costTf;
	@FXML private Button addFlightBtn;
	@FXML private Button removeFlightBtn;

	public final Flights getFlights() { return model; }
	private String getAirline() { return airlineTf.getText(); }
	private int getFlightNo() { return Integer.parseInt(flightNoTf.getText()); }
	private String getTakeoff() { return takeoffTf.getText(); }
	private String getLanding() { return landingTf.getText(); }
	private double getCost() { return Double.parseDouble(costTf.getText()); }

	private boolean validAirlineLen() { return getAirline().length() >= 1; }
	private boolean validFlightNoLen() { return flightNoTf.getText().length() >= 1; }
	private boolean validTakeoffLen() { return getTakeoff().length() >= 1; }
	private boolean validLandingLen() { return getLanding().length() >= 1; }
	private boolean validCostLen() { return costTf.getText().length() >= 1; }

	private void updateBtns() {
		addFlightBtn.setDisable(!(validAirlineLen() && validFlightNoLen() && validTakeoffLen() && validLandingLen() && validCostLen()));
		removeFlightBtn.setDisable(!(validTakeoffLen() && validLandingLen()));
	}

	@FXML private void initialize() {
		airlineTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
		flightNoTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
		takeoffTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
		landingTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
		costTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
	}

	@FXML private void handleAddFlight(ActionEvent e) throws Exception {
		Flights flights = getFlights();

		try {
			flights.addFlight(new Flight(getAirline(), getFlightNo(), getTakeoff(), getLanding(), getCost()));
			stage.close();
		} catch (DuplicateItemException error) {
			Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Flight already exists in agency"), "/view/Error/ErrorView.fxml", "Error", errorStage);
		} catch (NumberFormatException error) {
			Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Please enter a double"), "/view/Error/ErrorView.fxml", "Error", errorStage);
		}
	}

	@FXML private void handleRemoveFlight(ActionEvent e) throws Exception {
		Flights flights = getFlights();

		try {
			flights.removeFlight(flights.getFlight(getTakeoff(), getLanding()));
			stage.close();
		} catch (ItemNotFoundException error) {
			Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Flight does not exist in agency"), "/view/Error/ErrorView.fxml", "Error", errorStage);
		}
	}

	@FXML private void handleClose(ActionEvent e) {
		stage.close();
	}
}
