package controller.Error;

import javafx.fxml.*;
import javafx.event.*;
import javafx.scene.control.*;
import au.edu.uts.ap.javafx.*;
import model.Exceptions.*;

public class ErrorController extends Controller<ErrorModel>{
	@FXML private Label exceptionLbl;

	public final ErrorModel getErrorModel() { return model; }

	@FXML private void initialize() {
		exceptionLbl.textProperty().set(getErrorModel().getException().getClass().getSimpleName());
	}

	@FXML private void handleClose(ActionEvent e) {
		stage.close();
	}
}
