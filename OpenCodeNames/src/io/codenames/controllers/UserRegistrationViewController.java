package io.codenames.controllers;

import java.io.IOException;
import java.net.URL;
import java.rmi.Naming;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import io.codenames.serverinterfaces.PlayersHandlerInterface;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;

public class UserRegistrationViewController implements Initializable  {

	private PlayersHandlerInterface playerhandler;
	private Preferences pref;
	@FXML
	TextField userName;

	@FXML
	PasswordField userPass;

	@FXML
	PasswordField userConfirmPass;
	/**
	 * Action listener for User Registration button
	 * @param event
	 */
	@FXML 
	protected void registerAction(ActionEvent event) {
		try {
			if(!userPass.getText().equals(userConfirmPass.getText())){
				JOptionPane.showMessageDialog(new JFrame(), "Password doesn't match confirm pass", "Error",
						JOptionPane.ERROR_MESSAGE);
			}else {
				if (playerhandler.register(userName.getText(), userPass.getText())) {
					ViewController viewcontroller = ViewController.getInstance();
					viewcontroller.addScreen("LoginView", FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml")));
					viewcontroller.activate("LoginView");
				}else{
					JOptionPane.showMessageDialog(new JFrame(), "User by that username already Exists", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Action listener for back button
	 * @param event
	 */
	
	@FXML 
	protected void backAction(ActionEvent event) {
				ViewController viewcontroller = ViewController.getInstance();
				try {
					viewcontroller.addScreen("LoginView", FXMLLoader.load(getClass().getResource("/fxml/LoginView.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
				viewcontroller.activate("LoginView");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try{
			this.pref = Preferences.userNodeForPackage(io.codenames.Main.class);
			playerhandler = (PlayersHandlerInterface) Naming.lookup("rmi://"+pref.get("rmiUri", "")+"/PlayersHandler");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
