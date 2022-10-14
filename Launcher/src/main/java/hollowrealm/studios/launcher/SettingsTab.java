package hollowrealm.studios.launcher;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTextField;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;

import static javafx.scene.layout.Region.USE_PREF_SIZE;
import static javafx.scene.paint.Color.RED;
import static javafx.scene.paint.Color.WHITE;

public class SettingsTab extends Tab {

	public final JFXTextField widthField = new JFXTextField();
	public final JFXTextField heightField = new JFXTextField();
	public final JFXSlider volumeSlider = new JFXSlider();

	public SettingsTab() {
		setClosable(false);
		setText("Settings");
		volumeSlider.setMin(0);
		volumeSlider.setMax(100);
		volumeSlider.setValue(Launcher.CONFIG.getDefaultVolume());
		widthField.setText(String.valueOf(Launcher.CONFIG.getWidth()));
		heightField.setText(String.valueOf(Launcher.CONFIG.getHeight()));
		VBox vBox = new VBox();
		vBox.setMinWidth(USE_PREF_SIZE);
		vBox.setMinHeight(USE_PREF_SIZE);
		vBox.setMaxWidth(USE_PREF_SIZE);
		vBox.setMaxHeight(USE_PREF_SIZE);
		vBox.setPrefWidth(800);
		vBox.setPrefHeight(500);
		vBox.getChildren().addAll(
				new Label("Volume"),
				volumeSlider,
				new Label("Width"),
				widthField,
				new Label("Height"),
				heightField
		);
		widthField.setOnKeyTyped(keyEvent -> {
			if (widthField.getBackground().equals(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY))))
				widthField.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		heightField.setOnKeyTyped(keyEvent -> {
			if (heightField.getBackground().equals(new Background(new BackgroundFill(RED, CornerRadii.EMPTY, Insets.EMPTY))))
				heightField.setBackground(new Background(new BackgroundFill(WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
		});
		setContent(vBox);
	}

}
