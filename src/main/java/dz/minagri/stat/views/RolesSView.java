package dz.minagri.stat.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.model.Role;

@SpringView
public class RolesSView extends VerticalLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7857229841137841152L;
	
	SecurityService securityService;
    private Grid<Role> grid;
//
    @PropertyId("authority")
    private TextField authorityTextField;

    @PropertyId("description")
    private TextField descriptionTextField;

    private Binder<Role> binder = new Binder<>(Role.class);
    private HorizontalLayout formLayout;

    @Autowired
    public RolesSView(SecurityService securityService) {
        this.securityService = securityService;
    }
//
    private void configureGrid(){
        this.grid = new Grid<>(Role.class);
        grid.setSizeFull();
        grid.setResponsive(true);
        grid.setColumns("id", "authority", "description");
        grid.getEditor();
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
        grid.addSelectionListener(this::select);
        grid.getEditor().setEnabled(true);
//        grid.getEditorFieldGroup().addCommitHandler(new FieldGroup.CommitHandler() {
//            @Override
//            public void preCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
//                System.out.println("Pre edit!");
//            }
//            @Override
//            public void postCommit(FieldGroup.CommitEvent commitEvent) throws FieldGroup.CommitException {
//                // You can persist your data here
//                SecRole editedRole = (SecRole)grid.getEditedItemId();
////                securityService.updateSecRole(editedRole.getId(), editedRole.getAuthority(), editedRole.getDescription());
//                Notification.show("Role " + editedRole.getAuthority() + " was edited.");
//            }
//        });
    }

    @PostConstruct
    void init(){
        setMargin(true);
        setSpacing(true);
        setSizeFull();
//        configureGrid();
        configureFormLayout();
        Label captionLabel = new Label("List of roles");
        captionLabel.setStyleName(ValoTheme.LABEL_H2);
        captionLabel.setStyleName(ValoTheme.LABEL_COLORED);

        HorizontalLayout northLayout = new HorizontalLayout();
        northLayout.addComponent(captionLabel);
        northLayout.setWidth("100%");
        Button newRole = new Button("New role", clickEvent -> UI.getCurrent().getNavigator().navigateTo("roleForm") );
        newRole.setIcon(FontAwesome.PLUS);
        newRole.addStyleName(ValoTheme.BUTTON_PRIMARY);
        northLayout.addComponent(newRole);
        northLayout.setComponentAlignment(newRole, Alignment.MIDDLE_RIGHT);
        northLayout.setComponentAlignment(captionLabel, Alignment.BOTTOM_LEFT);

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setSpacing(true);

        verticalLayout.addComponent(northLayout);
//        verticalLayout.addComponent(grid);
//        verticalLayout.addComponent(formLayout);
//
//        verticalLayout.setExpandRatio(grid, 1.0f);
        addComponent(verticalLayout);

        refresh();

    }

    private void configureFormLayout() {
        //form
//        formLayout = new HorizontalLayout();
//        formLayout.setSpacing(true);
//        formLayout.setVisible(false);
//
//        authorityTextField = new TextField("Authority");
//        formLayout.addComponent(authorityTextField);
//
//        descriptionTextField = new TextField("Description");
//        formLayout.addComponent(descriptionTextField);
//
//        Button save = new Button("Save", event -> save(event));
//        save.setIcon(FontAwesome.SAVE);
//        save.addStyleName(ValoTheme.BUTTON_PRIMARY);
//        formLayout.addComponent(save);
//        Button deleteRole = new Button("Delete", this::delete );
//        deleteRole.setIcon(FontAwesome.TIMES);
//        deleteRole.addStyleName(ValoTheme.BUTTON_DANGER);
//        formLayout.addComponent(deleteRole);
//
//        formLayout.setComponentAlignment(save, Alignment.BOTTOM_LEFT);
//        formLayout.setComponentAlignment(deleteRole, Alignment.BOTTOM_RIGHT);
//
//        binder.bindMemberFields(this);

    }

    private void select(SelectionEvent event) {
//        if (event.getSelected().isEmpty()) {
//            formLayout.setVisible(false);
//        } else {
//            formLayout.setVisible(true);
//            binder.setItemDataSource((SecRole) event.getSelected().iterator().next());
//        }
    }

    private void save(Button.ClickEvent event) {
//        try {
//            binder.commit();
//            SecRole secRole = binder.getItemDataSource().getBean();
////            securityService.updateSecRole(secRole);
//            refresh();
//        } catch (FieldGroup.CommitException ex) {
//            Notification.show("Check fields", Notification.Type.ERROR_MESSAGE);
//        } catch (Exception ex) {
//            Notification.show("Unexpected error: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
//        }
    }

    private void delete(Button.ClickEvent event) {
//        try {
//            SecRole secRole = binder.getItemDataSource().getBean();
//            securityService.deleteSecRole(secRole);
//            Notification.show("Role deleted!");
//            refresh();
//        } catch (Exception ex) {
//            Notification.show("Unexpected error: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
//        }
    }

    private void refresh() {
//        secRoleContainer.removeAllItems();
//        secRoleContainer.addAll(securityService.findAllSecRoles());
//        grid.select(null);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        //NOP
    }
}
