package eu.over9000.redtrack.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * Tray icon
 */
public class TrayIcon {
	
	private final Stage stage;

	public TrayIcon(final Stage stage) {
		this.stage = stage;
	}

	public void addToTray() {
		try {
			java.awt.Toolkit.getDefaultToolkit();

			if (!java.awt.SystemTray.isSupported()) {
				System.out.println("No system tray support, application exiting.");
				Platform.exit();
			}

			final java.awt.SystemTray tray = java.awt.SystemTray.getSystemTray();
			final java.awt.TrayIcon trayIcon = new java.awt.TrayIcon(ImageIO.read(getClass().getResource("/images/icon.png")).getScaledInstance(tray.getTrayIconSize().width, tray.getTrayIconSize().height, java.awt.Image.SCALE_SMOOTH));

			trayIcon.addMouseListener(new MouseAdapter() {
				public void mouseClicked(final MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						Platform.runLater(TrayIcon.this::showStage);
					}
				}
			});

			final java.awt.MenuItem openItem = new java.awt.MenuItem("Open Redtrack");
			openItem.addActionListener(event -> Platform.runLater(this::showStage));

			final java.awt.MenuItem exitItem = new java.awt.MenuItem("Exit");
			exitItem.addActionListener(event -> {
				Platform.exit();
				tray.remove(trayIcon);
			});

			final java.awt.PopupMenu popup = new java.awt.PopupMenu();
			popup.add(openItem);
			popup.addSeparator();
			popup.add(exitItem);
			trayIcon.setPopupMenu(popup);

			tray.add(trayIcon);
		} catch (java.awt.AWTException | IOException e) {
			System.out.println("Unable to init system tray");
			e.printStackTrace();
		}
	}

	private void showStage() {
		stage.show();
		stage.toFront();
		stage.requestFocus();
	}
}
