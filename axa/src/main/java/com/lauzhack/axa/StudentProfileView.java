package com.lauzhack.axa;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.server.WrappedSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

public class StudentProfileView extends VerticalLayout implements View {

	static MongoClient mongo;
	static MongoDatabase db;
	static MongoCollection<Document> collection;
	static MongoCollection<Document> collection_ds;
	protected static final String LOGINVIEW = "";
	protected static final String HOSTSGRIDVIEW = "hostsgridview";
	
	public StudentProfileView() {
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		setWidth("100%");
		setHeight("100%");
		setMargin(true);
		FormLayout form = new FormLayout();
		List<Host> hosts = new ArrayList();
		form.setWidth("20%");
		form.setHeight("100%");

		Label welcome = new Label("Welcome "+((String)getUI().getSession().getAttribute("username"))+" to your Profile!");
		form.addComponent(welcome);
		hosts = getHostsAvailable();
		Label info = new Label("You have "+hosts.size()+" host matches!");
		form.addComponent(info);
		HorizontalLayout hor = new HorizontalLayout();
		hor.setSpacing(true);
    	Button seeDetails = new Button("Click for More!", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	getUI().getNavigator().navigateTo(HOSTSGRIDVIEW);
        	}
        });
    	seeDetails.setStyleName("primary");
    	hor.addComponent(seeDetails);
    	Button logout = new Button("Logout", new Button.ClickListener() {
            @Override
        	public void buttonClick(ClickEvent event) {
            	VaadinSession vSession = VaadinSession.getCurrent();
            	WrappedSession httpSession = vSession.getSession();
            	httpSession.invalidate();
            	Page.getCurrent().setLocation("/axa");
        	}
        });
    	logout.setStyleName("logout");
		hor.addComponent(logout);
    	form.addComponent(hor);
		form.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
		addComponent(form);
		setComponentAlignment(form, Alignment.MIDDLE_CENTER);
		getUI().getSession().setAttribute("hosts", hosts);
		String user = (String) getUI().getSession().getAttribute("username");
		System.out.println(user);
	}

	private List<Host> getHostsAvailable() {
		MongoClient mongo = new MongoClient(new ServerAddress("localhost", 27017));
        db = mongo.getDatabase("homely");
        collection = db.getCollection("hosts");
        FindIterable<Document> hostsAvailable= collection.find();
        List<Host> hostsList = new ArrayList<>();
        for (Document hoster : hostsAvailable) {
        	Host host = new Host();
        	host.setFirstName((String) hoster.get("firstName"));
        	host.setFamilyName((String)hoster.get("familyName"));
        	host.setCountry((String)hoster.get("country"));
        	host.setInterests((String)hoster.get("interests"));
        	host.setEmail((String)hoster.get("email"));
        	host.setTelephone((String)hoster.get("telephone"));
        	hostsList.add(host);
        }
        return hostsList;
	}
}
