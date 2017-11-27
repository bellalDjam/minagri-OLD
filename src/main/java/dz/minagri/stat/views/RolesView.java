package dz.minagri.stat.views;

import java.time.LocalDate;
import java.util.EnumSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.selection.SelectionEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.comp.form.SignUpForm.ChangeHandler;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.PersonneRepository;
import dz.minagri.stat.repositories.RoleRepository;

@SpringView(name = RolesView.VIEW_NAME)
public class RolesView extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3320061303561952390L;
	public static final String VIEW_NAME = "role";
	@Autowired
    SecurityService securityService;
	
    @Autowired
	private  RoleRepository repository; 
	private Role role;
//
    private TextField authority= new TextField("Authority");;
    private TextField description= new TextField("Description");;
    private TextField filter = new TextField("Recheche :  ");
    private Label lblTitle;
/*Binder*/
    private Binder<Role> binder = new Binder<>(Role.class);
    /* Action buttons */
	private Button addNewBtn= new Button("Nouveau Role", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);

	private Grid<Role> grid = new Grid<>(Role.class);

	private NativeSelect<TypePersonne> typePersonne = new NativeSelect<TypePersonne>("Gender");

	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private HorizontalLayout actions = new HorizontalLayout();
	private CssLayout headactions = new CssLayout();
//
    @Autowired
    public RolesView(SecurityService securityService) {
        this.securityService = securityService;
    }

    @PostConstruct
    void init(){
    	//INITIAL SETUP
    	Responsive.makeResponsive();
    	
		setSpacing(false);
		setMargin(false);
		setSizeFull();
		description.setRequiredIndicatorVisible(true);
		authority.setRequiredIndicatorVisible(true);
		binder.forField(authority).withValidator(
        		text -> text.length() >= 4,
          "plusque 4 carrectéres")
        .bind(Role::getAuthority, Role::setAuthority);
		fLayout = new HorizontalLayout();
		fLayout.setResponsive(true);
		fLayout.setMargin(false);
		fLayout.setSizeFull();
		fLayout.addStyleName("light");

		//UI COMPONENTS
		lblTitle = new Label("Ajout Role");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);

		filter.setPlaceholder("Recherche");
		filter.addValueChangeListener(e -> updateRolelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer La Recherche");
		clearBtn.addClickListener(e -> filter.clear());
		
		addNewBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            setRole(new Role());
        });
		
		
		headactions.addComponents(filter,clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewBtn);
		hlasearch.setComponentAlignment(addNewBtn, Alignment.TOP_RIGHT);
		hlasearch.setResponsive(true);
		updateGrid(grid,"");
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch,grid);
		//		addComponent(vlGrid);
		
		//Set up form
		typePersonne.setItems(EnumSet.allOf(TypePersonne.class));
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(authority,description,typePersonne,actions);
		fLayout.addComponentsAndExpand(vlGrid,vlform);
		fLayout.setExpandRatio(vlGrid,  1.0f);
		

		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		// wire action buttons to save, delete and reset
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.addClickListener(e -> this.cancel());
		cancel.setStyleName(ValoTheme.BUTTON_QUIET);
		
		addComponent(fLayout);
		binder.bindInstanceFields(this);
		
		vlform.setVisible(false);

		 grid.asSingleSelect().addValueChangeListener(event -> {
	            if (event.getValue() == null) {
	            	vlform.setVisible(false);
	            } else {
	            setRole(event.getValue());
	            }
	        });

    }


//    private void save(Button.ClickEvent event) {
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
//    }

//    private void delete(Button.ClickEvent event) {
//        try {
//            SecRole secRole = binder.getItemDataSource().getBean();
//            securityService.deleteSecRole(secRole);
//            Notification.show("Role deleted!");
//            refresh();
//        } catch (Exception ex) {
//            Notification.show("Unexpected error: " + ex.getMessage(), Notification.Type.ERROR_MESSAGE);
//        }
//    }

    /**
	 * @return
	 */
	private void delete() {
		repository.delete(role);
		grid.setItems(repository.findAll());
		grid.select(null);
		vlform.setVisible(false);
	}
	/**
	 * @return
	 */
	private void save() {
		if (binder.validate().isOk()) {
		repository.save(role);
		grid.setItems(repository.findAll());
		}
		vlform.setVisible(false);
	}
	private void cancel() {
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	public void setRole(Role role) {
		this.role = role;
		binder.setBean(role);
		securityService.updateRole(role);
		// Show delete button for only customers already in the database
		delete.setVisible((role.getId()!=null));
		vlform.setVisible(true);
		authority.selectAll();
	}

    private void updateGrid(Grid<Role> grid, String filterText) {
    	grid.setSizeFull();
        grid.setResponsive(true);
        updateRolelist(filterText);
        grid.setColumns("id", "authority", "description");
        grid.setSelectionMode(Grid.SelectionMode.SINGLE);
		
		
    }
    
    /**
	 * @param filterText
	 */
	private void updateRolelist(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repository.findAll());
		}
		else {
			grid.setItems(repository.findAllByAuthorityContaining(filterText));
		}
	}
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
	public interface ChangeHandler {

		void onChange();
	}
	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
		save.addClickListener(e -> h.onChange());
		delete.addClickListener(e -> h.onChange());
	}
	
}
