package eu.over9000.redtrack.ui.textfields;

import javafx.scene.control.TextField;

/**
 * Text field with length restriction
 */
public class LengthRestrictedTextField extends TextField {

	private static final int MAX_LENGTH = 255;

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
