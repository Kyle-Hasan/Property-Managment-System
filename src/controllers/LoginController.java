package controllers;

import models.Database;
import views.*;

public class LoginController extends GUIcontroller {
	private LoginView view;

	public LoginController(Database model, LoginView view) {
		super(model);
		this.view = view;

	}

	public void start() {
		view.initalizeFrame("Property App");
		view.intializeDashboard();
		view.displayDashboard();

		// uses lambdas to add event listeners

		view.addSelectLoginListener(e -> view.showLogin());
		view.addRenterListener(e -> {
			view.dispose();
			RenterController renter = new RenterController(model, new RenterView("Renter", 1200, 1200),this);
			renter.start();

		});
		view.addLoginListener(e -> {

			login(view.getUserText().strip(), view.getPasswordText());
		});
		view.endSession(e-> {
			view.dispose();
			start();
		});
		view.addSelectSignupListener(e -> view.signUpPanel());
		view.addSignupListener(e -> signup(view.getUserText().strip(), view.getPasswordText()));
	}

	public void signup(String username, String password) {

		if(username.isEmpty() && password.isEmpty()){
			view.displayEmptyTextError("Username and Password");
			return;
		}
		if(username.isEmpty()){
			view.displayEmptyTextError("Username");
			return;
		}
		if(password.isEmpty()){
			view.displayEmptyTextError("Password");
			return;
		}

		if(view.getEmail().length() == 0){
			view.displayEmptyTextError("Email");
			return;

		}

		if(model.validateAccount(username)){
			view.displaySignupError();
			return;
		}
		model.registerUser(username, password,view.getEmail(), view.getOptionsText());
		view.confirmation("Signup Successful");
		view.dispose();
		start();

	}

	public void login(String username, String password) {

		// this should validate the login, if the login is valid, create a new view and
		// controller
		// for the type of account logged into, for example if a landlord logs in create
		// a landlord viewer
		// and controller. The login validation should return a object for that user as
		// well or what the unique
		// key to access info about user on database
		// else if login is invalid(make this clear in the return), error message

		System.out.println("user " + username + " password " + password);
		// this should call a validate login function in the database, so
		// model.validateLogin(username,password)
		if (model.validateLogin(username, password)) {
			// deal with valid login
			System.out.println("valid");
			view.dispose();
			if (model.validateAccountType(username).equals("Landlord")) {
				LandlordController landlord = new LandlordController(model, new LandlordView("Landlord", 1200, 1200),
						this, username);
				landlord.start();
			} else if (model.validateAccountType(username).equals("Manager")) {
				ManagerController manager = new ManagerController(model, new ManagerView("Manager", 1200, 1200), this,
						username);
				manager.start();
			} else {
				RegisteredRenterController registeredRenter = new RegisteredRenterController(model,
						new RegisteredRenterView(username, 1200, 1200),
						this, username);
				registeredRenter.start();
			}
		} else {
			view.displayLoginError();

		}

	}

}
