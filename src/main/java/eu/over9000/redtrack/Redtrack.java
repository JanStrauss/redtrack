package eu.over9000.redtrack;

import javafx.application.Application;

import eu.over9000.redtrack.persistence.Configuration;
import eu.over9000.redtrack.ui.MainWindow;

/**
 * Created by Jan on 12.07.2015.
 */
public class Redtrack {

	public static void main(String[] args) {
		Configuration.load();
		Application.launch(MainWindow.class, args);
		Configuration.save();
	}

}
