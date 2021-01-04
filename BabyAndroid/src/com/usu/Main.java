package com.usu;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	    UIThread uiThread = new UIThread();
	    uiThread.start();

	    uiThread.addEventHandler(new CustomEventHandler() {
			@Override
			public void onEvent(String event) {
				if (event.equals("touch")) {
					System.out.println("You touched the phone!");
				}
			}
		});

		uiThread.addEventHandler(new CustomEventHandler() {
			@Override
			public void onEvent(String event) {
				if (event.equals("swipe")) {
					System.out.println("You swiped!");
				}
			}
		});

		uiThread.addEventHandler(new CustomEventHandler() {
			@Override
			public void onEvent(String event) {
				if (event.equals("shake")) {
					System.out.println("You shook the phone!");
				}
			}
		});

		uiThread.addEventHandler(new CustomEventHandler() {
			@Override
			public void onEvent(String event) {
				if (event.equals("power")) {
					System.out.println("You shook the phone!");
				}
			}
		});

	    String userEvent = "";
		System.out.println("Welcome to Baby Android, please type in your event:");
	    while(!userEvent.equals("stop")) {
	    	Scanner scanner = new Scanner(System.in);
	    	userEvent = scanner.nextLine();
	    	uiThread.addEvent(userEvent);
		}
	    uiThread.stop();
    }
}
