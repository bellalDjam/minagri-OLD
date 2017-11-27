/**
 * 
 */
package dz.minagri.stat.views;

import java.util.EnumSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToDoubleConverter;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.enumeration.TypeDeSourceEaux;
import dz.minagri.stat.enumeration.TypeEaux;
import dz.minagri.stat.enumeration.TypeIrrigation;
import dz.minagri.stat.model.Irrigation;
import dz.minagri.stat.repositories.IrrigationRepository;

/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = IrrigationForm.VIEW_NAME)
public class IrrigationForm extends HorizontalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8021190218422435344L;
	public static final String VIEW_NAME = "irrigation";

	private  IrrigationRepository repository; 
	private  Irrigation irrigation;


	private DateField dateRealisation =new DateField("dateRealisation");
	private TextField name= new TextField("Description");
	private TextField otherEnergy= new TextField("otherEnergy");
	private TextField profondeur= new TextField("profondeur");
	private TextField consomationElectrique = new TextField("consomationElectrique :  ");
	private TextField consomationGasoil = new TextField("consomationGasoil :  ");
	private TextField stockCapacity =new TextField("stockCapacity");
	private TextField debit= new TextField("debit");

	private NativeSelect<TypeIrrigation> typeIrrigation = new NativeSelect<TypeIrrigation>("Type-Irrigation");
	private NativeSelect<TypeDeSourceEaux> typeDeSourceEaux = new NativeSelect<TypeDeSourceEaux>("Type De Source Eaux");
	private NativeSelect<TypeEaux> typeEaux = new NativeSelect<TypeEaux>("Type Eaux");

	private Label lblTitle;
	/*Binder*/
	private Binder<Irrigation> binder = new Binder<>(Irrigation.class);
	private TextField filter = new TextField("Recheche :  ");
	/* Action buttons */
	private Button addNewBtn= new Button("Nouveau ", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);

	private Grid<Irrigation> grid = new Grid<>(Irrigation.class);


	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private HorizontalLayout actions = new HorizontalLayout();
	private CssLayout headactions = new CssLayout();
	//

	public IrrigationForm(IrrigationRepository repository){
		this.repository = repository;
		//INITIAL SETUP
		Responsive.makeResponsive();

		setSpacing(false);
		setMargin(false);
		setSizeFull();

		binder.forField(consomationElectrique)
		.withConverter(new StringToIntegerConverter("Introduire un Numero"))
		.bind(Irrigation::getConsomationElectrique,
				Irrigation::setConsomationElectrique);
		binder.forField(profondeur)
		.withConverter(new StringToIntegerConverter("Introduire un Numero"))
		.bind(Irrigation::getProfondeur
				, Irrigation::setProfondeur);
		binder.forField(consomationGasoil)
		.withConverter(new StringToIntegerConverter("Introduire un Numero"))
		.bind(Irrigation::getConsomationGasoil,
				Irrigation::setConsomationGasoil);
		binder.forField(debit)
		.withConverter(new StringToDoubleConverter("Introduire un Numero"))
		.bind(Irrigation::getDebit,
				Irrigation::setDebit);
		binder.forField(stockCapacity)
		.withConverter(new StringToDoubleConverter("Introduire un Numero"))
		.bind(Irrigation::getStockCapacity,
				Irrigation::setStockCapacity);

		fLayout = new HorizontalLayout();
		fLayout.setResponsive(true);
		fLayout.setMargin(false);
		fLayout.setSizeFull();
		fLayout.addStyleName("light");

		//UI COMPONENTS
		lblTitle = new Label("Ajout Irrigation");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);

		filter.setPlaceholder("Recherche");
		filter.addValueChangeListener(e -> updateRolelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer La Recherche");
		clearBtn.addClickListener(e -> filter.clear());

		addNewBtn.addClickListener(e -> {
			grid.asSingleSelect().clear();
			setRole(new Irrigation());
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
		typeDeSourceEaux.setItems(EnumSet.allOf(TypeDeSourceEaux.class));
		typeEaux.setItems(EnumSet.allOf(TypeEaux.class));
		typeIrrigation.setItems(EnumSet.allOf(TypeIrrigation.class));

		HorizontalLayout h = new HorizontalLayout(profondeur,consomationElectrique);
		HorizontalLayout h1 = new HorizontalLayout(consomationGasoil,otherEnergy);
		HorizontalLayout h2= new HorizontalLayout(typeIrrigation,typeDeSourceEaux,typeEaux);
		HorizontalLayout h3= new HorizontalLayout(debit,stockCapacity);
		HorizontalLayout h4= new HorizontalLayout(dateRealisation,name);
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(h,h1,h2,h3,h4,actions);
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


	private void delete() {
		repository.delete(irrigation);
		grid.setItems(repository.findAll());
		grid.select(null);
		vlform.setVisible(false);
	}
	/**
	 * @return
	 */
	private void save() {
		if (binder.validate().isOk()) {
			repository.save(irrigation);
			grid.setItems(repository.findAll());
		}
		vlform.setVisible(false);
	}
	private void cancel() {
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
	public void setRole(Irrigation irrigation) {
		this.irrigation = irrigation;
		binder.setBean(irrigation);
		//		securityService.updateRole(irrigation);
		// Show delete button for only customers already in the database
		delete.setVisible((irrigation.getId()!=null));
		vlform.setVisible(true);
		profondeur.selectAll();
	}

	private void updateGrid(Grid<Irrigation> grid, String filterText) {
		grid.setSizeFull();
		grid.setResponsive(true);
		updateRolelist(filterText);
		grid.setColumns("profondeur", "dateRealisation","consomationElectrique", "consomationGasoil"
				,"otherEnergy", "stockCapacity", "debit","typeIrrigation","typeDeSourceEaux","typeEaux");
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
			//			grid.setItems(repository.findAllByAuthorityContaining(filterText));
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
