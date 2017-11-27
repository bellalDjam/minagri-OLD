/**
 * 
 */
package dz.minagri.stat.comp.form;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.event.ShortcutAction;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.repositories.AdresseRepository;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.services.AdresseService;

/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AddressForm.VIEW_NAME)
public class AddressForm extends HorizontalLayout implements View{

	private static final long serialVersionUID = 1371079240255048702L;

	public static final String VIEW_NAME = "address";

	@Autowired
	private AdresseRepository adRepo;
	@Autowired
	private CommuneRepository comRepo;
	@Autowired
	AdresseService service;
	private Binder<Adresse> binder = new Binder<>(Adresse.class);
	private Adresse adress;
	private Commune comm;
//Layout Content
	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Header Action Search */
	private HorizontalLayout actions = new HorizontalLayout();
	private CssLayout headactions = new CssLayout();
	private TextField filter = new TextField("Recherche  :  ");
	private Button addNewBtn= new Button("Nouveau ", FontAwesome.PLUS);
	//Grid
	private Grid<Adresse> grid = new Grid<>(Adresse.class);

	//action Buttons
	private Button save = new Button("Sauver");
	private Button cancel = new Button("Annuler");
	private Button delete = new Button("Suprimer",VaadinIcons.TRASH);
	private Button addAdresseBtn = new Button("Nouveau..");
	private Button clearBtn = new Button(VaadinIcons.CLOSE);

	//Select Fields & Commune ComboBox 
	private ComboBox<Commune> commune = new ComboBox<>("Commune");
	private TextField rue = new TextField("rue");
	private TextField numero = new TextField("numero");
	private TextField boitePostale = new TextField("boitePostale");
	private TextField codePostal = new TextField("codePostal");

	//Label
	private Label lblTitle;

	@PostConstruct
	void init() {
		Responsive.makeResponsive();

		setMargin(false);
		setSpacing(false);
		setSizeFull();

		HorizontalLayout h1 = new HorizontalLayout(commune,rue, numero );
		HorizontalLayout h2 = new HorizontalLayout(codePostal,boitePostale);
		
		numero.setRequiredIndicatorVisible(true);
		rue.setRequiredIndicatorVisible(true);
		commune.setRequiredIndicatorVisible(true);

		commune.setPlaceholder("Choisir la commune");
		commune.setItemCaptionGenerator(p->p.getName());
		commune.setItems( comRepo.findAll());
		
		binder.forField(codePostal).withConverter(new StringToLongConverter
				("Introduir un Nombre")).bind(Adresse::getCodePostal,Adresse::setCodePostal);
		binder.forField(rue).withValidator(
				text -> text.length() >= 4,
				"plusque 4 carrectéres")
		.bind(Adresse::getRue, Adresse::setRue);
		binder.forField(numero).withValidator(
				text -> text.length() >= 1,
				"plusque 4 carrectéres")
		.bind(Adresse::getNumero, Adresse::setNumero);
//		binder.forField(commune);
		binder.forField(commune).withValidator(
				commune -> commune != null,
				"Choix obligatoir d'une commune")
		.bind(Adresse::getCommune, Adresse::setCommune);
		
		fLayout = new HorizontalLayout();
		fLayout.setResponsive(true);
		fLayout.setMargin(false);
		fLayout.setSizeFull();
		fLayout.addStyleName("light");

		//UI COMPONENTS
		lblTitle = new Label("Ajout Adresse");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);

		filter.setPlaceholder("Recherche");
		filter.addValueChangeListener(e -> updatelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer La Recherche");
		clearBtn.addClickListener(e -> filter.clear());		
	
		addNewBtn.setDescription("Ajouter un nouvelle Adresse");
		addNewBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			vlform.setVisible(true);
//			fieldsClear();
//			binder.removeBean();
			binder.setBean(null);
		});

		headactions.addComponents(filter,clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewBtn);
		hlasearch.setComponentAlignment(addNewBtn, Alignment.TOP_RIGHT);
		hlasearch.setResponsive(true);
		updateGrid(grid,"");
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch,grid);
		
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(h1,h2,actions);
		fLayout.addComponentsAndExpand(vlGrid,vlform);
		fLayout.setExpandRatio(vlGrid,  1.0f);
		binder.bindInstanceFields(this);

		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		// wire action buttons to save, delete and reset
		save.addClickListener(e -> {
			if(adress==null || adress.getId() == null ) {
				this.saveN();
				
			}else { 
				this.save();
				}
		});
		
		delete.addClickListener(e -> this.delete());
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.addClickListener(e -> this.cancel());
		cancel.setStyleName(ValoTheme.BUTTON_QUIET);

		addComponent(fLayout);
		

		vlform.setVisible(false);

		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue() == null) {
				vlform.setVisible(false);
			} else {
				setPOJO(event.getValue());
			}
		});

	}

	/**
	 * @return
	 */
	private void delete() {
		service.delete(adress.getId());
		grid.setItems(adRepo.findAll());
		grid.deselectAll();
		vlform.setVisible(false);
	}

	private void save() {
		 if(binder.validate().isOk()) {
//			 service.create(adress);
			 adRepo.save(adress);
			vlform.setVisible(false);
			grid.setItems(adRepo.findAll());
	}
	}
	private void saveN() {
		 adress = new Adresse(numero.getValue(), rue.getValue()
					, Long.parseLong(codePostal.getValue()), commune.getValue());
//		 binder.writeBeanIfValid(adress);
//		 service.create(adress);
		 adRepo.save(adress);
		vlform.setVisible(false);
		grid.setItems(adRepo.findAll());
	}
	private void cancel() {
		grid.setItems(adRepo.findAll());
		vlform.setVisible(false);
	}
	public void setPOJO(Adresse adress) {
		this.adress = adress;
		binder.setBean(adress);
		
		// Show delete button for only customers already in the database
		delete.setVisible((adress.getId()!=null));
		vlform.setVisible(true);
		rue.selectAll();
	}

	private void updateGrid(Grid<Adresse> grid, String filterText) {
		grid.setSizeFull();
		grid.setResponsive(true);
		updatelist(filterText);
		grid.setColumns("numero", "rue","codePostal","boitePostale");
		grid.setSelectionMode(Grid.SelectionMode.SINGLE);

	}

	/**
	 * @param filterText
	 */
	private void updatelist(String filterText) {
		if (StringUtils.isEmpty(filterText)) {
			grid.setItems(adRepo.findAll());
		}
		else {
			grid.setItems(adRepo.findByRueContaining(filterText));
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