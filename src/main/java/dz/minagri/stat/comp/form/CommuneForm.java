/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.EnumSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.WilayaRepository;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class CommuneForm extends HorizontalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6063020473820997484L;
	public static final String VIEW_NAME = "communeForm";
	
	private final CommuneRepository repository;
	private final  WilayaRepository wilRepo;
	
	private ComboBox<Wilaya> selectW = new ComboBox<Wilaya>();
	
	private Grid<Commune> grid = new Grid<>(Commune.class);
	
	private Binder<Commune> binder = new Binder<>(Commune.class);

	/* Fields to edit properties in Customer entity */
	private Label lblTitle;

	private TextField name = new TextField("Commune");
	private TextField caract2 = new TextField("caract1");
	private TextField codeSubdiv = new TextField("Code Subdiv");
	private TextField codeCommune = new TextField("Code Commune");
	
	private TextField filter = new TextField("Filtre  :  "+" ");


	private Button addNewBtn= new Button("Nouveau Ajout", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);


	private NativeSelect<TypeCommune> typeCommune = new NativeSelect<TypeCommune>("Type Commune");

	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();

	private Commune commune;

	@SuppressWarnings("deprecation")
	@Autowired
	public CommuneForm(CommuneRepository repository
			, WilayaRepository wilRepo) {

		this.repository = repository;
		this.wilRepo = wilRepo;
		
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
		
		selectW = new ComboBox<Wilaya>("Wilaya");
		selectW.setItemCaptionGenerator(p->p.getNomWilaya());
		selectW.setItems(wilRepo.findAll());
		
		name.setPlaceholder("Nom Commune");
		codeCommune.setPlaceholder("Code commune");
		codeSubdiv.setPlaceholder("Code Subdiv");
		
		name.setRequiredIndicatorVisible(true);
		codeCommune.setRequiredIndicatorVisible(true);
		codeSubdiv.setRequiredIndicatorVisible(true);
		typeCommune.setRequiredIndicatorVisible(true);
		
		binder.forField(name).withValidator(
				text -> text.length() >= 3,
				"Donner plusque 3 carrectéres pour le nomde la Commune")
		.bind(Commune::getName, Commune::setName);
		
		binder.forField(codeCommune)
		.withValidator(string -> string != null && !string.isEmpty(), "plusque 4 carrectéres")
	    .withConverter(new StringToLongConverter("Introduire un Nombre"))
	    .withValidator(integer -> integer > 0, "Introduire un Nombre")
		.bind(Commune::getCodeCommune, Commune::setCodeCommune);
		
		binder.forField(codeSubdiv)
		.withValidator(string -> string != null && !string.isEmpty(), "plusque 4 carrectéres")
	    .withConverter(new StringToLongConverter("Introduire un Nombre"))
	    .withValidator(integer -> integer > 0, "Introduire un Nombre")
		.bind(Commune::getCodeSubdiv, Commune::setCodeSubdiv);
		
		
		clearBtn.setDescription("Clear the current filter");
		clearBtn.addClickListener(e -> filter.clear());
		addNewBtn.addClickListener(e -> {
            grid.asSingleSelect().clear();
            setPojo(new Commune());
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
		typeCommune.setItems(EnumSet.allOf(TypeCommune.class));
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(name,caract2,codeSubdiv,codeCommune,typeCommune,actions);
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
	            setPojo(event.getValue());
	            }
	        });
	}

	/**
	 * @param grid2
	 */
	private void updateGrid(Grid<Commune> grid2, String filterText) {
		grid2.setSizeFull();
		grid2.setResponsive(true);
		updatePersonnelist(filterText);
		grid2.setSelectionMode(SelectionMode.SINGLE);
		grid2.setColumns("name", "caract1","codeCommune","codeSubdiv","typeCommune");
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
			grid.setItems(repository.findAllByNameContainingIgnoreCase(filterText));
		}
	}
	private void delete() {
		repository.delete(commune);
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	public void setPojo(Commune commune) {
		this.commune = commune;
		if(commune.getId()!=null) {
			binder.setBean(commune);
		}
		
		

		// Show delete button for only customers already in the database
		delete.setVisible((commune.getId()!=null));
		vlform.setVisible(true);
		name.selectAll();
	}
	private void save() {
		repository.save(commune);
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
