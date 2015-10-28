package by.andrew.scrabl.control;

import by.andrew.scrabl.control.Server;
import by.andrew.scrabl.control.FXMLController.StartServer;
import Server.Main;
import by.andrew.scrabl.control.GameControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class FXMLController {
	public class StartServer extends Thread {
		private Server server;

		StartServer() {
			server = new Server();
			
		}
		public void run() {
			server.setTextArea(actiontarget);
			server.setGameControl(gameControl);
			server.waite();
		}
		public void stopServer() {
			server.stop();

		}

	}

	@FXML
	private Text actiontarget;
	private Main main;
	private StartServer tred;
	private GameControl gameControl;

	public void setMainApp(Main mainApp) {
		this.main = mainApp;
	}

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {

		gameControl = new GameControl();
		tred = new StartServer();

		tred.start();

	}

	@FXML
	protected void handleSubmitButtonAction2(ActionEvent event) {

		tred.stopServer();
		actiontarget.setText("Stop server");
	}
}