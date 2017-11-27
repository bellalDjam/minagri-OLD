/**
 * 
 */
package dz.minagri.stat.comp.form;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.repositories.WilayaRepository;
import dz.minagri.stat.views.AccountView;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
@SpringView(name = WilayaForm.VIEW_NAME)
public class WilayaForm extends HorizontalLayout implements View {

	private static final long serialVersionUID = -2232944159159521701L;
	
	public static final String VIEW_NAME = "wilayaForm";
	@Autowired
//	private final  WilayaRepository repository;
	private   WilayaRepository repository;
	
	private Grid<Wilaya> grid = new Grid<>(Wilaya.class);
	
	private Binder<Wilaya> binder = new Binder<>(Wilaya.class);

	private TextField nomWilaya = new TextField("Wilaya");
	private TextField codeWilaya = new TextField("Code Wilaya");
	private TextField totarea = new TextField("Superficie");
	private TextField totpopulation = new TextField("Populations");
	
	private TextField filter = new TextField("Filtre  :  "+" ");


	private Button addNewBtn= new Button("Nouveau Ajout", FontAwesome.PLUS);
	private Button save= new Button("Sauver", FontAwesome.SAVE);
	private Button cancel= new Button("Annuler",FontAwesome.TIMES);
	private Button delete= new Button("Suprimer", FontAwesome.TRASH_O);
	private Button clearBtn= new Button( FontAwesome.TIMES);



	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();

	private Wilaya wilaya;

	@PostConstruct
	void init() {
//	public WilayaForm(WilayaRepository repository) {

//		this.repository = repository;
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
		
		
		
		binder.forField(nomWilaya)
		.withValidator(text -> text.length() >= 3, "plusque 4 carrectéres")
				.asRequired("Champ Obligatoire")
				.bind(Wilaya::getNomWilaya, Wilaya::setNomWilaya);

		binder.forField(totarea)
		 .withValidator(string -> string != null && !string.isEmpty(), "plusque 4 carrectéres")
		    .withConverter(new StringToIntegerConverter("Introduire un Nombre"))
		    .withValidator(integer -> integer > 0, "Introduire un Nombre")
				.bind(Wilaya::getTotarea, Wilaya::setTotarea);
		
		binder.forField(totpopulation)
		.withConverter(new StringToIntegerConverter("Introduire un Nombre"))
		.bind(Wilaya::getTotpopulation, Wilaya::setTotpopulation);
		
		binder.forField(codeWilaya)
		.withConverter(new StringToLongConverter("Introduire un Nombre"))
		.withValidator(code -> code > 0, "Introduire un Nombre")
		.bind(Wilaya::getCodeWilaya, Wilaya::setCodeWilaya);

		// addComponent(lblTitle);

		filter.setPlaceholder("Filtre");
		filter.addValueChangeListener(e -> updatelist(filter.getValue()));
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Effacer la Recherche");
		clearBtn.addClickListener(e -> filter.clear());
		
		addNewBtn.setDescription("Ajouter un nouvelle wilaya");
		addNewBtn.addClickListener(e -> {
			vlform.setVisible(true);
			grid.asSingleSelect().clear();
			binder.setBean(null);
//			setObject(new Wilaya());
			binder.setBean(null);
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
		HorizontalLayout hl = new HorizontalLayout(nomWilaya, codeWilaya);
		HorizontalLayout hl1 = new HorizontalLayout(totarea, totpopulation);
		actions.addComponents(save, cancel, delete);
		vlform.addComponents(hl, hl1, actions);
		fLayout.addComponentsAndExpand(vlGrid, vlform);
		fLayout.setExpandRatio(vlGrid, 1.0f);

		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.setStyleName(ValoTheme.BUTTON_QUIET);
		
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		// wire action buttons to save, delete and reset
//		save.addClickListener(e -> this.save());
//		save.addClickListener(e -> binder.writeBeanIfValid(wilaya));
		save.addClickListener(e -> {
			if(wilaya==null || wilaya.getId() == null ) {
				this.saveN();
				
			}else { 
				this.save();
				}
		});
		
		delete.addClickListener(e -> this.delete());
		cancel.addClickListener(e -> this.cancel());

		addComponent(fLayout);
		binder.bindInstanceFields(this);

		vlform.setVisible(false);


//		grid.setSelectionMode(SelectionMode.SINGLE);
//		grid.getEditor().setEnabled(true);
		grid.asSingleSelect().addValueChangeListener(event -> {
			if (event.getValue()== null) {
				vlform.setVisible(false);
			} else {
				vlform.setVisible(true);
				setObject(repository.findOneBynomWilaya(event.getValue().getNomWilaya()));
			}
		});
	}

	/**
	 * @param grid2
	 */
	private void updateGrid(Grid<Wilaya> grid2, String filterText) {
		
		grid2.setSizeFull();
		grid2.setResponsive(true);
		updatelist(filterText);
		grid2.setSelectionMode(SelectionMode.SINGLE);
		grid2.setColumns("nomWilaya", "codeWilaya"
				, "totarea", "totpopulation","communes");
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
			grid.setItems(repository.findByNomWilayaStartsWithIgnoreCase(filterText));
		}
	}
	@Transactional
	private void delete() {
		repository.delete(wilaya.getId());
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}

	public void setObject(Wilaya wilaya) {
		this.wilaya = wilaya;
		binder.readBean(wilaya);
//		binder.setBean(wilaya);
		// Show delete button for only customers already in the database
		delete.setVisible((wilaya.getId() != null));
		vlform.setVisible(true);
		nomWilaya.selectAll();
	}

	private void save() {
		 if(binder.validate().isOk()) {
		repository.save(wilaya);
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
		 }
	}
	private void saveN() {
		wilaya = new Wilaya(nomWilaya.getValue(), Long.parseLong(codeWilaya.getValue())
		, Integer.parseInt(totarea.getValue()), Integer.parseInt(totpopulation.getValue()));
//		 binder.writeBeanIfValid(adress);
//		 service.create(adress);
		repository.save(wilaya);
		vlform.setVisible(false);
		grid.setItems(repository.findAll());
	}

	private void cancel() {
		grid.setItems(repository.findAll());
		vlform.setVisible(false);
	}
}
