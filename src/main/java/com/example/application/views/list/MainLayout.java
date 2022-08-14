package com.example.application.views.list;

import com.example.application.security.SecurityService;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightCondition;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;

public class MainLayout extends AppLayout {

    private final SecurityService securityService;

    @Autowired
    public MainLayout(SecurityService service) {
        this.securityService = service;
        createHandler();
        createDrawer();
    }

    private void createHandler(){
        H1 logo = new H1("Vaadin CRM");
        logo.addClassNames("text-1","m-m");

        Button logout = new Button("Log out", e ->securityService.logout());
        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(), logo, logout );
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.expand(logo);
        header.setWidth("100%");
        header.addClassNames("py-0","px-m");

        addToNavbar(header);
    }
    
    private void createDrawer(){
        RouterLink listLink = new RouterLink("List",ListView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                listLink, new RouterLink("DashBoard", DashboardView.class)
        ));
    }
}
