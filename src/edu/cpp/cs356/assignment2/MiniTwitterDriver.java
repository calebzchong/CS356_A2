package edu.cpp.cs356.assignment2;

import java.awt.EventQueue;

/**
 * The driver class.
 */
public class MiniTwitterDriver {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPanel.getInstance();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
