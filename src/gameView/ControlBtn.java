package gameView;

import javafx.scene.control.Button;

public class ControlBtn extends Button{
	public ControlBtn(String btnText) {
		setText(btnText);
		getStyleClass().add("btn");
		setPrefWidth(150);
	}

}
