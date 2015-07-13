package eu.over9000.redtrack.ui.textfields;

import javafx.scene.control.TextField;

/**
 * Created by Jan on 13.07.2015.
 */
public class LengthRestrictedTextField extends TextField {

	private static final int MAX_LENGTH = 5;

	public void replaceText(final int start, final int end, final String text) {
		final String oldValue = getText();

		super.replaceText(start, end, text);

		if (getText().length() > MAX_LENGTH) {
			setText(oldValue);
			positionCaret(oldValue.length());
		}
	}

	public void replaceSelection(final String text) {
		final String oldValue = getText();

		super.replaceSelection(text);

		if (getText().length() > MAX_LENGTH) {
			setText(oldValue);
			positionCaret(oldValue.length());
		}
	}
}
