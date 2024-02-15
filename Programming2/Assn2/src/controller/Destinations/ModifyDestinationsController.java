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
import model.Exceptions.*;
import javafx.beans.value.*;

public class ModifyDestinationsController extends Controller<Destinations> {
    @FXML private TextField nameTf;
    @FXML private TextField countryTf;
    @FXML private Button addDestinationBtn;
    @FXML private Button removeDestinationBtn;

    public final Destinations getDestinations() { return model; }
    private String getName() { return nameTf.getText(); }
    private String getCountry() { return countryTf.getText(); }

    private boolean validNameLen() { return getName().length() >= 1; }
    private boolean validCountryLen() { return getCountry().length() >= 1; }

    private void updateBtns() {
        addDestinationBtn.setDisable(!(validNameLen() && validCountryLen()));
        removeDestinationBtn.setDisable(!(validNameLen() && validCountryLen()));
    }

    @FXML private void initialize() {
        nameTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
        countryTf.textProperty().addListener((o, oldText, newText) -> updateBtns());
    }

    @FXML private void handleAddDestination(ActionEvent e) throws Exception {
        Destinations destinations = getDestinations();

        try {
            destinations.addDestination(new Destination(getName(), getCountry()));
            stage.close();
        } catch (DuplicateItemException error) {
            Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Destination already exists"), "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }

    @FXML private void handleRemoveDestination(ActionEvent e) throws Exception {
        Destinations destinations = getDestinations();

        try {
            destinations.removeDestination(destinations.destination(getName(), getCountry()));
            stage.close();
        } catch (ItemNotFoundException error) {
            Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Destination does not exist"), "/view/Error/ErrorView.fxml", "Error", errorStage);
        }
    }

    @FXML private void handleClose(ActionEvent e) {
		stage.close();
	}
}
