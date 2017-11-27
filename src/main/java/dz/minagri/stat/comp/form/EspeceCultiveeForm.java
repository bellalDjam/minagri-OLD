/**
 * 
 */
package dz.minagri.stat.comp.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.model.EspeceCultivee;
import dz.minagri.stat.repositories.EspeceCultiveeRepository;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class EspeceCultiveeForm  extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 68792843065822371L;

	/**
	 * 
	 */
	public static final String VIEW_NAME = "especeCultiveeForm";

	private final EspeceCultiveeRepository repository; 


	private Binder<EspeceCultivee> binder = new Binder<>(EspeceCultivee.class);

	/* Fields to edit properties in Customer entity */
	private Label lblTitle;

	private TextField name = new TextField("Nomenclature");
	private TextField dureeCroissanceJour = new TextField("Croissance En Jours");
	private TextField filter = new TextField("Filtre  :  "+" ");


	private Button addNewBtn= new Button("Nouveau Ajout", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);

	private Grid<EspeceCultivee> grid = new Grid<>(EspeceCultivee.class);


	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();

	private EspeceCultivee espece;

	@SuppressWarnings("deprecation")
	@Autowired
	public EspeceCultiveeForm(EspeceCultiveeRepository repository) {

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
		binder.forField(name).withValidator(text -> text.length() >= 3, "plusque 4 carrectéres")
		.asRequired("Champ Obligatoire").bind(EspeceCultivee::getName, EspeceCultivee::setName);

		binder.forField(dureeCroissanceJour).withConverter(new StringToIntegerConverter("Introduire un Nombre"))
		.bind(EspeceCultivee::getDureeCroissanceJour, EspeceCultivee::setDureeCroissanceJour);
		//UI COMPONENTS
		lblTitle = new Label("Enregistrement Espece");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);

		filter.setPlaceholder("Filtrer");
		filter.addValueChangeListener(e -> updatePersonnelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer La Recherche");
		clearBtn.addClickListener(e -> filter.clear());
		addNewBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			setPersonne(new EspeceCultivee());
		});


		headactions.addComponents(filter,clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewBtn);
		hlasearch.setComponentAlignment(addNewBtn, Alignment.TOP_RIGHT);

		updateGrid(grid,"");
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch,grid);
		//		addComponent(vlGrid);
		//Set up form
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(name,dureeCroissanceJour,actions);
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
	private void updateGrid(Grid<EspeceCultivee> grid2, String filterText) {
		grid2.setSizeFull();
		grid2.setResponsive(true);
		updatePersonnelist(filterText);
		grid2.setSelectionMode(SelectionMode.SINGLE);
		grid2.setColumns("name", "dureeCroissanceJour","varieties");
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
			grid.setItems(repository.findByNameContainingIgnoreCase(filterText));
		}
	}
	private void delete() {
		repository.delete(espece);
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	public void setPersonne(EspeceCultivee espece) {
		this.espece = espece;
		binder.setBean(espece);

		// Show delete button for only customers already in the database
		delete.setVisible((espece.getId()!=null));
		vlform.setVisible(true);
		name.selectAll();
	}
	private void save() {
		repository.save(espece);
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

