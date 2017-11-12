package com.lauzhack.axa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.AxisType;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.Cursor;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.HorizontalAlign;
import com.vaadin.addon.charts.model.Legend;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsColumn;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsSpline;
import com.vaadin.addon.charts.model.StackLabels;
import com.vaadin.addon.charts.model.Stacking;
import com.vaadin.addon.charts.model.Tooltip;
import com.vaadin.addon.charts.model.VerticalAlign;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.ZoomType;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.addon.charts.model.style.Style;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class StudentRegisterView extends VerticalLayout implements View {

	protected static final String STUDENTPROFILE = "studentprofile";

	public StudentRegisterView() {
		setMargin(true);
    	setWidth("100%");
    	setHeight("100%");
    	
    	RegisterStudent regStud = new RegisterStudent();
    	HorizontalLayout hzr = new HorizontalLayout();
    	Student student = new Student();
    	hzr.setWidth("100%");
    	hzr.setHeight("100%");
    	//Form's left half
		FormLayout form = new FormLayout();
		form.setWidth("60%");
		form.setHeight("100%");
		
		TextField tf1 = new TextField("Your first name");
		tf1.setIcon(FontAwesome.USER);
        tf1.setBuffered(true);
        tf1.addValidator(new StringLengthValidator(
            "First name can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf1);

		TextField tf2 = new TextField("Your family name");
		tf2.setIcon(FontAwesome.USER);
        tf2.setBuffered(true);
        tf2.addValidator(new StringLengthValidator(
            "Family name can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf2);
		
		TextField tf15 = new TextField("Username");
		tf15.setIcon(FontAwesome.USER);
        tf15.setBuffered(true);
        tf15.addValidator(new StringLengthValidator(
            "Username can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf15);

		PasswordField tf16 = new PasswordField("Password");
		tf16.setIcon(FontAwesome.KEY);
        tf16.setBuffered(true);
        tf16.addValidator(new StringLengthValidator(
            "Password can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf16);
		
		
		TextArea tf3 = new TextArea("Complete address");
		tf3.setIcon(FontAwesome.ROAD);
        tf3.setBuffered(true);
        tf3.addValidator(new StringLengthValidator(
            "Address can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf3);

		TextField tf4 = new TextField("Postal code");
		tf4.setIcon(FontAwesome.ENVELOPE);
        tf4.setBuffered(true);
        tf4.addValidator(new StringLengthValidator(
            "Postal code can't be empty (input: {0})",
            1, 100, true));
		form.addComponent(tf4);
		
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		
		List countries = new ArrayList<>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\Kunwar_HRForecast\\Desktop\\Lauzhack_AXA\\axa\\src\\main\\resources\\countries.txt"));
			String line = br.readLine();
		    while (line != null) {
		    	countries.add(line);
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		ComboBox tf5 = new ComboBox("Country");
		tf5.setIcon(FontAwesome.FLAG);
		tf5.addItems(countries);
		form.addComponent(tf5);
	
		List nationalities = new ArrayList<>();
		try {
			br = new BufferedReader(new FileReader("C:\\Users\\Kunwar_HRForecast\\Desktop\\Lauzhack_AXA\\axa\\src\\main\\resources\\nationalities.txt"));
			String line = br.readLine();
		    while (line != null) {
		    	nationalities.add(line);
		        line = br.readLine();
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		ComboBox tf6 = new ComboBox("Nationality");
		tf6.setIcon(FontAwesome.USERS);
		tf6.addItems(nationalities);
		form.addComponent(tf6);
	
		List genders = new ArrayList<>();
		genders.add("Male");
		genders.add("Female");
		genders.add("Don't specify");
		
		ComboBox tf7 = new ComboBox("Gender");
		tf7.setIcon(FontAwesome.TRANSGENDER_ALT);
		tf7.addItems(genders);
		form.addComponent(tf7);
		
		DateField tf8 = new DateField("Date of Birth");
		tf8.setIcon(FontAwesome.CALENDAR_TIMES_O);
		form.addComponent(tf8);
		
		TextField tf9 = new TextField("Telephone");
		tf9.setIcon(FontAwesome.PHONE);
		form.addComponent(tf9);
		
		hzr.addComponent(form);
		hzr.setComponentAlignment(form, Alignment.MIDDLE_RIGHT);
		//Form's right half
		FormLayout formright = new FormLayout();
		
		formright.setWidth("60%");
		formright.setHeight("100%");
		
		TextField tf10 = new TextField("Email");
		tf10.setIcon(FontAwesome.MAIL_FORWARD);
        tf10.setBuffered(true);
        tf10.addValidator(new StringLengthValidator(
            "Email can't be empty (input: {0})",
            1, 100, true));
        
		formright.addComponent(tf10);
		
		TextField tf11 = new TextField("Religion");
		tf11.setIcon(FontAwesome.STAR);
		formright.addComponent(tf11);
		
		TextField tf12 = new TextField("Interests");
		tf12.setIcon(FontAwesome.MOTORCYCLE);
		formright.addComponent(tf12);
		
		TextField tf13 = new TextField("Languages known");
		tf13.setIcon(FontAwesome.LANGUAGE);
		formright.addComponent(tf13);
		
		TextArea tf14 = new TextArea("Why do you need a host family?");
		tf14.setIcon(FontAwesome.USER_PLUS);
		formright.addComponent(tf14);
		
		Button submit = new Button("Submit", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	student.setFirstName(tf1.getValue());
            	student.setFamilyName(tf2.getValue());
            	student.setAddress(tf3.getValue());
            	student.setPostalCode(Integer.parseInt(tf4.getValue()));
            	student.setCountry((String)tf5.getValue());
            	student.setNationality((String)tf6.getValue());
            	student.setGender((String)tf7.getValue());
            	student.setDob(tf8.getValue());
            	student.setTelephone(tf9.getValue());
            	student.setEmail(tf10.getValue());
            	student.setReligion(tf11.getValue());
            	student.setInterests(tf12.getValue());
            	student.setLanguages(tf13.getValue());
            	student.setReason(tf14.getValue());
            	student.setUserName(tf15.getValue());
            	student.setPassword(tf16.getValue());
            	int res = regStud.register(student);
            	if (res == 1) {
            		getUI().getSession().setAttribute("username", student.getUserName());
            		getUI().getNavigator().navigateTo(STUDENTPROFILE);
            	}
            	else {
            		getUI().getNavigator().navigateTo(STUDENTPROFILE);
            	}
        	}
        });
		submit.addStyleName("friendly");
		formright.addComponent(submit);
		
		hzr.addComponent(formright);
		hzr.setComponentAlignment(formright, Alignment.MIDDLE_LEFT);
		addComponent(hzr);

	}
	@Override
	public void enter(ViewChangeEvent event) {
	}

}