package dz.minagri.stat.views;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

@SpringView(name = "roleForm")
@Secured("ROLE_ADMIN")
public class SecRoleFormView extends VerticalLayout implements View {
	
    SecurityService securityService;

    @PropertyId("authority")
    TextField authorityField;
    @PropertyId("description")
    TextField descriptionField;

    @Autowired
    public SecRoleFormView(SecurityService securityService) {
        this.securityService = securityService;
    }
    Binder<Role> binder = new Binder<>(Role.class);
    
    @PostConstruct
    void init(){
        setMargin(true);
        setSizeFull();
        FormLayout content = new FormLayout();
        content.addStyleName(ValoTheme.FORMLAYOUT_LIGHT);

        authorityField = new TextField("Authority");
        content.addComponent(authorityField);

        descriptionField = new TextField("Description");
        content.addComponent(descriptionField);
       
//    	private Binder<Personne> binder = new Binder<>(Personne.class);
//        binder.setItemDataSource(new Role("authority","description"));
        binder.bindInstanceFields(this);

        Button ok = new Button("Save Role");
//        ok.addClickListener((new Button.ClickListener) { e ->
//            try {
//                binder.commit();
//                securityService.createRole(authorityField.getValue(), descriptionField.getValue());
//                Notification.show("Saved!");
//                getUI().getNavigator().navigateTo("roles");
//            } catch (FieldGroup.CommitException ex) {
//                Notification.show("Check fields", Notification.Type.ERROR_MESSAGE);
//            } catch (Exception ex) {
//                Notification.show("Unexpected error: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
//            }
//        });
        ok.addClickListener(e -> this.addNewRole());
        ok.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        content.addComponent(ok);
        Label titleLabel = new Label("Create new role");
        titleLabel.addStyleName(ValoTheme.LABEL_H2);
        titleLabel.addStyleName(ValoTheme.LABEL_COLORED);
        addComponent(titleLabel);
        addComponent(content);
        setExpandRatio(content, 1.0f);
    }

    /**
	 * @return
	 */
	private void addNewRole() {
		 binder.validate();
		 try {
			if(binder.validate().isOk()) {
				 securityService.createRole(authorityField.getValue(), descriptionField.getValue());
					Notification.show("Saved!");
			      getUI().getNavigator().navigateTo("roles");
			 }
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

}
