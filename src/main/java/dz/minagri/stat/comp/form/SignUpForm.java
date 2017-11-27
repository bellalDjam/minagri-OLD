/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.renderers.LocalDateRenderer;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.model.MoyenContact;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.repositories.PersonneRepository;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class SignUpForm extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5316662209744734557L;
	public static final String VIEW_NAME = "signUpForm";

	private final PersonneRepository repository; 

	
	private MoyenContact moyenContact; 
	private Binder<Personne> binder = new Binder<>(Personne.class);

	/* Fields to edit properties in Customer entity */
	private Label lblTitle;

	private TextField firstName = new TextField("firstName");
	private TextField lastName = new TextField("lastName");
	private TextField tfPassword = new PasswordField("Password");
	private TextField filter = new TextField("Filtre  :  "+" ");

	private DateField dateNaissance = new DateField("Né(e) Le");

	private Button addNewBtn= new Button("Nouveau Ajout", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);

	private Grid<Personne> grid = new Grid<>(Personne.class);

	private NativeSelect<TypePersonne> typePersonne = new NativeSelect<TypePersonne>("Gender");

	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();

	private Personne personne;

	@SuppressWarnings("deprecation")
	@Autowired
	public SignUpForm(PersonneRepository repository) {

		this.repository = repository;
		
		//INITIAL SETUP
		setSpacing(false);
		setMargin(false);
		setSizeFull();
		fLayout = new HorizontalLayout();

		fLayout.setMargin(false);
		fLayout.setSizeFull();
		fLayout.setWidth("100%");
		fLayout.addStyleName("light");

		hlasearch.setSpacing(true);
		hlasearch.setMargin(true);

		//UI COMPONENTS
		lblTitle = new Label("Enregistrement Agent");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);

		filter.setPlaceholder("Filter by last name");
		filter.addValueChangeListener(e -> updatePersonnelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Clear the current filter");
		clearBtn.addClickListener(e -> filter.clear());
		addNewBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            setPersonne(new Personne());
        });
		
		
		headactions.addComponents(filter,clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewBtn);
		hlasearch.setComponentAlignment(addNewBtn, Alignment.TOP_RIGHT);

		updateGrid(grid,"");
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch,grid);
		//		addComponent(vlGrid);
		dateNaissance.setValue(LocalDate.now());
		dateNaissance.setLocale(getLocale());
		//Set up form
		typePersonne.setItems(EnumSet.allOf(TypePersonne.class));
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(firstName,lastName,tfPassword,dateNaissance,typePersonne,actions);
		fLayout.addComponentsAndExpand(vlGrid,vlform);
		fLayout.setExpandRatio(vlGrid,  1.0f);
		

		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		// wire action buttons to save, delete and reset
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
		cancel.addClickListener(e -> this.cancel());
		
		addComponent(fLayout);
		binder.bindInstanceFields(this);
		
		vlform.setVisible(false);

		 grid.asSingleSelect().addValueChangeListener(event -> {
	            if (event.getValue() == null) {
	            	vlform.setVisible(false);
	            } else {
	            setPersonne(event.getValue());
	            }
	        });
	}

	/**
	 * @param grid2
	 */
	private void updateGrid(Grid<Personne> grid2, String filterText) {
		grid2.setSizeFull();
		grid2.setResponsive(true);
		updatePersonnelist(filterText);
		grid2.setSelectionMode(SelectionMode.SINGLE);
		grid2.setColumns("firstName", "lastName","typePersonne");
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
	void updatePersonnelist(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repository.findAll());
		}
		else {
			grid.setItems(repository.findByLastNameStartsWithIgnoreCase(filterText));
		}
	}
	private void delete() {
		repository.delete(personne);
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	public void setPersonne(Personne personne) {
		this.personne = personne;
		binder.setBean(personne);

		// Show delete button for only customers already in the database
		delete.setVisible((personne.getId()!=null));
		vlform.setVisible(true);
		firstName.selectAll();
	}
	private void save() {
		repository.save(personne);
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	/**
	 * @return
	 */
	private void cancel() {
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
}

