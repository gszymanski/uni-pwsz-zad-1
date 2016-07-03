import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDocumentController implements Initializable {
	@FXML private AnchorPane anchorPane;
	@FXML private Label inLabel;
	@FXML private TextField inTextField;
	@FXML private Label outLabel;
	@FXML private TextField outTextField;
	@FXML private ToggleGroup inMode;
	@FXML private ToggleGroup outMode;
	@FXML private RadioButton inCelsius;
	@FXML private RadioButton inFahrenheit;
	@FXML private RadioButton inKelvin;
	@FXML private RadioButton outCelsius;
	@FXML private RadioButton outFahrenheit;
	@FXML private RadioButton outKelvin;
	@FXML private Button button;

	public void initialize(URL url, ResourceBundle rb) {
	}

	@FXML
	private void buttonClick(ActionEvent event) {
		String in = ((RadioButton)inMode.getSelectedToggle()).getId();
		String out = ((RadioButton)outMode.getSelectedToggle()).getId();

		// check if units are the same
		if (in.substring(2).equals(out.substring(3))) {
			outTextField.setText(inTextField.getText());
			return;
		}

		double temp = 0;

		// convert to Kelvin
		if (in.equals("inKelvin"))
			temp = Double.parseDouble(inTextField.getText());
		else if (in.equals("inCelsius"))
			temp = Double.parseDouble(inTextField.getText()) + 273.15;
		else
			temp = (Double.parseDouble(inTextField.getText()) + 459.67) * (5.0 / 9.0);

		// convert to whatever the user wants
		if (out.equals("outKelvin"))
			outTextField.setText(Double.toString(temp));
		else if (out.equals("outCelsius"))
			outTextField.setText(Double.toString(temp - 273.15));
		else
			outTextField.setText(Double.toString((temp * (9.0 / 5.0)) - 459.67));
	}
}
