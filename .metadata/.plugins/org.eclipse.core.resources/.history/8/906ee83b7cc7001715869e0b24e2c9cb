package com.lauzhack.axa;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class StartView extends VerticalLayout implements View {

	protected static final String LOGINVIEW = "login";	
	
	public StartView() {
		
		setMargin(true);
    	setWidth("100%");
    	setHeight("100%");
    	
        Image resource1 = new Image(null, new ThemeResource("img/homely.png"));
        resource1.addClickListener(e -> getUI().getNavigator().navigateTo(LOGINVIEW));
        addComponent(resource1);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {    	
	}
}
