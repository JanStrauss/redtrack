package eu.over9000.redtrack.ui.textfields;

import javafx.scene.control.TextField;

/**
 * Text field with a not perfect number restriction.
 */
public class NumberTextField extends TextField {

	private static final String ALLOWED_CHARS = "(\\d|\\.)*";

	public void replaceText(final int start, final int end, final String text) {
		final String oldValue = getText();

		super.replaceText(start, end, text);

		if (!getText().matches(ALLOWED_CHARS)) {
			setText(oldValue);
			positionCaret(oldValue.length());
		}
	}

	public void replaceSelection(final String text) {
		final String oldValue = getText();

		super.replaceSelection(text);

		if (!getText().matches(ALLOWED_CHARS)) {
			setText(oldValue);
			positionCaret(oldValue.length());
		}
	}
}
