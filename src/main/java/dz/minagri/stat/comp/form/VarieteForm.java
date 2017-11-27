/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
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

import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.enumeration.TypeCulture;
import dz.minagri.stat.model.EspeceCultivee;
import dz.minagri.stat.model.Variete;
import dz.minagri.stat.repositories.EspeceCultiveeRepository;
import dz.minagri.stat.repositories.VarieteRepository;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class VarieteForm extends HorizontalLayout implements View {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8403202607183008142L;

	/**
	 * 
	 */

	public static final String VIEW_NAME = "varieteform";

	private final VarieteRepository repository;
	private final EspeceCultiveeRepository espRepository;
	private Binder<Variete> binder = new Binder<>(Variete.class);

	/* Fields to edit properties in Customer entity */
	private Label lblTitle;

	private TextField name = new TextField("Nomenclature");
	private TextField dureeCroissanceJour = new TextField("Croissance En Jours");
	
	private TextField filter = new TextField("Filtre  :  " + " ");
	private ComboBox<EspeceCultivee> especeCultivee= new ComboBox<>("Espece Cultivee");
	private Button addNewBtn = new Button("Ajouter", FontAwesome.PLUS);
	private Button save = new Button("Sauver", FontAwesome.SAVE);
	private Button cancel = new Button("Annuler", FontAwesome.TIMES);
	private Button delete = new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn = new Button(FontAwesome.TIMES);

	private Grid<Variete> grid = new Grid<>(Variete.class);

	private NativeSelect<TypeCulture> typeCulture = new NativeSelect<TypeCulture>("type Culture");
	private NativeSelect<OrigineSemence> origineSemence = new NativeSelect<OrigineSemence>("Origine Semence");

	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();

	private Variete variete;

	@SuppressWarnings("deprecation")
	@Autowired
	public VarieteForm(VarieteRepository repository,EspeceCultiveeRepository espRepository) {

		this.repository = repository;
		this.espRepository = espRepository;
		Responsive.makeResponsive();
		// INITIAL SETUP
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
		especeCultivee.setPlaceholder("Choisir l'espece");
		especeCultivee.setRequiredIndicatorVisible(true);
		especeCultivee.setItemCaptionGenerator(p -> p.getName());
		List<EspeceCultivee> especList = new ArrayList<EspeceCultivee>();
		especList= espRepository.findAll();
		especeCultivee.setItems(especList);
		
		binder.forField(name).withValidator(text -> text.length() >= 3, "plusque 4 carrectéres")
				.asRequired("Champ Obligatoire").bind(Variete::getName, Variete::setName);

		binder.forField(dureeCroissanceJour).withConverter(new StringToIntegerConverter("Introduire un Nombre"))
				.bind(Variete::getDureeCroissanceJour, Variete::setDureeCroissanceJour);
		// UI COMPONENTS
		lblTitle = new Label("Enregistrement Cereales");
		lblTitle.addStyleName("h4");
		// addComponent(lblTitle);

		filter.setPlaceholder("Filtre");
		filter.addValueChangeListener(e -> updatelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer la Recherche");
		clearBtn.addClickListener(e -> filter.clear());
		addNewBtn.setDescription("Ajouter un nouveau Cereale");
		addNewBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			setObject(new Variete());
		});

		headactions.addComponents(filter, clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewBtn);
		hlasearch.setComponentAlignment(addNewBtn, Alignment.TOP_RIGHT);

		updateGrid(grid, "");
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch, grid);
		// addComponent(vlGrid);

		// Set up form
		HorizontalLayout hl = new HorizontalLayout(name, especeCultivee);
		HorizontalLayout hl1 = new HorizontalLayout(origineSemence, dureeCroissanceJour);
		typeCulture.setItems(EnumSet.allOf(TypeCulture.class));
		origineSemence.setItems(EnumSet.allOf(OrigineSemence.class));
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(hl, hl1,typeCulture, actions);
		fLayout.addComponentsAndExpand(vlGrid, vlform);
		fLayout.setExpandRatio(vlGrid, 1.0f);

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
				setObject(event.getValue());
			}
		});
	}

	/**
	 * @param grid2
	 */
	private void updateGrid(Grid<Variete> grid2, String filterText) {
		grid2.setSizeFull();
		grid2.setResponsive(true);
		updatelist(filterText);
		grid2.setSelectionMode(SelectionMode.SINGLE);
		grid2.setColumns("name","especeCultivee", "dureeCroissanceJour"
				, "typeCulture","origineSemence");
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

	void updatelist(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(repository.findAll());
		} else {
			grid.setItems(repository.findByNameContainingIgnoreCase(filterText));
		}
	}
	@Transactional
	private void delete() {
		repository.delete(variete.getId());
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}

	public void setObject(Variete cereale) {
		this.variete = cereale;
		binder.setBean(cereale);

		// Show delete button for only customers already in the database
		delete.setVisible((cereale.getId() != null));
		vlform.setVisible(true);
		name.selectAll();
	}

	private void save() {
		repository.save(variete);
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
