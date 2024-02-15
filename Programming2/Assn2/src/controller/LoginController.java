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
import model.Exceptions.*;


public class LoginController extends Controller<Agency>{
	@FXML private TextField usernameTf;
	@FXML private PasswordField passwordPf;
	@FXML private Button loginBtn;

	public final Agency getAgency() { return model; }
	private String getUsername() { return usernameTf.getText(); }
	private String getPassword() { return passwordPf.getText(); }

	private boolean validUsernameLen() { return getUsername().length() >= 1; }
	private boolean validPasswordLen() { return getPassword().length() >= 1; }

	private void updateLoginBtn() {
		loginBtn.setDisable(!(validUsernameLen() && validPasswordLen()));
	}

	@FXML private void initialize() {
		usernameTf.textProperty().addListener((o, oldText, newText) -> updateLoginBtn());
		passwordPf.textProperty().addListener((o, oldText, newText) -> updateLoginBtn());
	}

	@FXML private void handleLogin(ActionEvent e) throws Exception {
		Agency agency = getAgency();
		Administrators administrators = agency.getAdministrators();
		
		try {
			agency.setLoggedInUser(administrators.getAdministrator(getUsername(), getPassword()));
			stage.close();
			Stage mainMenuStage = new Stage();
			mainMenuStage.getIcons().add(new Image("/image/agency_icon.png"));
			ViewLoader.showStage(agency, "/view/AgencyView.fxml", "Prog2 Travel Agency", mainMenuStage);
		} catch (InvalidCredentialsException error) {
			Stage errorStage = new Stage();
			errorStage.getIcons().add(new Image("/image/error_icon.png"));
			ViewLoader.showStage(new ErrorModel(error, "Incorrect username or password"), "/view/Error/ErrorView.fxml", "Error", errorStage);
		}
	}

	@FXML private void handleExit(ActionEvent e) {
		stage.close();
	}
}

