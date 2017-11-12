package com.lauzhack.axa;

import com.vaadin.data.Validator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout implements View {

	protected static final String STUDENTREGISTERVIEW = "studentregister";
	protected static final String HOSTREGISTERVIEW = "hostregister";
	protected static final String STUDENTPROFILEVIEW = "studentprofile";
	protected static final String HOSTPROFILEVIEW = "hostprofile";
	static Label error = new Label();
	
	public LoginView() {
		setMargin(true);
    	setWidth("100%");
    	setHeight("100%");
    	HorizontalLayout full = new HorizontalLayout();
    	full.setWidth("100%");
    	full.setHeight("100%");
    	full.setStyleName("white");
    	
    	VerticalLayout centervert = new VerticalLayout();
    	centervert.setHeight("100%");
    	centervert.setWidth("40%");
    	FormLayout center = new FormLayout();
    	center.setSpacing(true);
    	
    	Label welcome = new Label("Welcome to Homely!");
    	welcome.setStyleName("h1");
    	center.addComponent(welcome);
    	
		TextField tf1 = new TextField("Username");
		tf1.setIcon(FontAwesome.USER);
		tf1.validate();
		center.addComponent(tf1);

		PasswordField tf2 = new PasswordField("Password");
		tf2.setIcon(FontAwesome.KEY);
		tf2.validate();
		center.addComponent(tf2);
		
		HorizontalLayout loginerror = new HorizontalLayout();
    	Button login = new Button("LOGIN", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	checkLogin(tf1, tf2);
        	}
        });
    	login.setIcon(FontAwesome.PLAY_CIRCLE);
    	login.setStyleName("friendly");
    	loginerror.addComponent(login);
    	error = new Label("");
    	loginerror.addComponent(error);
    	center.addComponent(loginerror);
    	
    	Label ask = new Label("Not Registered Yet?");
    	ask.setStyleName("small");
    	center.addComponent(ask);
    	Button registerStudent = new Button("Register as a student!", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	getUI().getNavigator().navigateTo(STUDENTREGISTERVIEW);
        	}
        });
    	registerStudent.setIcon(FontAwesome.CLONE);
    	registerStudent.setStyleName("primary");
    	center.addComponent(registerStudent);
    	
    	Button registerHost = new Button("Register as a Host!", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	getUI().getNavigator().navigateTo(HOSTREGISTERVIEW);
        	}
        });
    	registerHost.setIcon(FontAwesome.CLONE);
    	registerHost.setStyleName("primary");
    	center.addComponent(registerHost);
		centervert.addComponent(center);
		
    	full.addComponent(centervert);
    	VerticalLayout pic = new VerticalLayout();
    	pic.setWidth("60%");
    	pic.setHeight("100%");
    	pic.addComponent(new Image(null, new ThemeResource("img/2.PNG")));
    	full.addComponent(pic);
    	addComponent(full);
    	full.setComponentAlignment(centervert, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
	}

	private void checkLogin(TextField tf, PasswordField pf) {
		final String username = tf.getValue();
		final String password = pf.getValue();
		String type = "";
		Login login = new Login();
		getUI().getSession().setAttribute("username", username);
		type = login.checkLogin(username, password);
		if (type.toLowerCase().equals("host")) {
			getUI().getNavigator().getCurrentView();
			getUI().getNavigator().navigateTo(HOSTPROFILEVIEW);
		}
		else if (type.toLowerCase().equals("student")){
			getUI().getNavigator().navigateTo(STUDENTPROFILEVIEW);
		}
		else {
			error.setCaption("Wrong Credentials");
		}
	}
}