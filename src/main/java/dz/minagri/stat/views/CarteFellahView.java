/**
 * 
 */
package dz.minagri.stat.views;



import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.Column;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.TextRenderer;

import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Zone;
import dz.minagri.stat.repositories.CarteFellahRepository;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.ZoneRepository;

/**
 * @author bellal djamel
 *
 */
//@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = CarteFellahView.VIEW_NAME)
public class CarteFellahView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3619365588168665581L;

	public static final String VIEW_NAME = "cartefellah";
	@Autowired
	CarteFellahRepository carteFellahRepository;
	@Autowired
	CommuneRepository cRepo;
	@Autowired
	ZoneRepository zRepo;

	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

	@PostConstruct
	void init() {
		removeAllComponents();
		refresh();
	}
	//	 @SuppressWarnings("rawtypes")
	private void refresh() {

		removeAllComponents();

		Grid<CarteFellah> grid = new Grid<>(CarteFellah.class);
		updateGrid(grid);
		VerticalLayout blayout = new VerticalLayout();

		//Button Add CarteFellah
		final Button addNewCf= new Button("Carte Fellah",new Button.ClickListener() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3831247956259012521L;

			@Override
			public void buttonClick(ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Enregistrer une Carte Fellah");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidthUndefined();
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Center it in the browser window
				subWindow.center();
				subWindow.setModal(true);
				// Put some components in it
				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h = new HorizontalLayout();
				h.setSpacing(true);
				h.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);

				final ComboBox<Commune> comzone= new ComboBox<Commune> ();
				final ComboBox<Zone> zcomb= new ComboBox<Zone> ();
				zcomb.setItemCaptionGenerator(Zone::getName);
				zcomb.setItems(zRepo.findAll());
				zcomb.setPlaceholder("Zone");
				final TextField numS12= new TextField();
				numS12.setPlaceholder("Num S12");
				final DateField  rDate = new DateField ("Delivrée Le");
				rDate.setValue(LocalDate.now());
				rDate.setLocale(getLocale());
				final Button btnConfirm;
				final Button btnCancel;
				//retrieve All Commune 
				List<Commune> coms = cRepo.findAll();

				comzone.setPlaceholder("Code-Com");
				comzone.setItemCaptionGenerator(p->p.getCodeCommune().toString());
				comzone.setItems(coms);
				comzone.addValueChangeListener(new ValueChangeListener<Commune>() {

					private static final long serialVersionUID = 1L;

					@Override
					public void valueChange(ValueChangeEvent<Commune> event) {
						//First Populate combobox for select Zones
						if(comzone.getValue()!=null) {
							List<Zone> zones = comzone.getValue().getZones();
							zcomb.setVisible(true);
							zcomb.setPlaceholder("Zone");
							zcomb.setItemCaptionGenerator(p->p.getName());
							zcomb.setItems(zones);
							zcomb.addValueChangeListener(new ValueChangeListener<Zone>() {

								private static final long serialVersionUID = 1L;

								@Override
								public void valueChange(ValueChangeEvent<Zone> event){
									if(zcomb.getValue()!=null) {


									}
								}

							});
							//End of Zone Listener
						}
					}
				});
				//End of Commune Listener

				btnConfirm = new Button("Sauver",new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 7296499167824024636L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (zcomb.getValue()!=null && numS12.getValue()!=null ) {
							CarteFellah	ca=		new CarteFellah();
							ca.setNumS12(numS12.getValue());
							ca.setZone(zcomb.getValue());
							ca.setRegistrationDate(rDate.getValue());

							carteFellahRepository.save(ca);
						}
						subWindow.close();
						removeAllComponents();
						updateGrid(grid);
						refresh();

					}
				});
				btnCancel = new Button("Cancel",new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						removeAllComponents();
						updateGrid(grid);
						refresh();
					}
				});
				h01.addComponents(btnConfirm,btnCancel);
				h.addComponent(rDate);
				h0.addComponents(numS12,comzone,zcomb);

				subContent.addComponents(h0,h,h01);
				UI.getCurrent().addWindow(subWindow);

			}
		});
		blayout.addComponents(addNewCf,grid);
		blayout.setSizeFull();
		blayout.setMargin(true);
		blayout.setSpacing(false);

		addComponent(blayout);

	}
	/**
	 * @param grid
	 */
	private void updateGrid(Grid<CarteFellah> grid) {
		grid.setSizeFull();
		grid.setItems(carteFellahRepository.findAll());
		grid.setSelectionMode(SelectionMode.SINGLE);
		grid.setColumns("numS12", "zone","registrationDate");

//		Column<CarteFellah, Zone> coln =grid.addColumn(CarteFellah::getZone);
//		coln.setRenderer(zone -> zone.getName(), new TextRenderer());
//		coln.setCaption("Zone");
//		coln.setEditorComponent(new ZoneField(), CarteFellah::setZone);
//		grid.addColumn(carte->carte.getZone().getName()).setCaption("Zone").setId("zone");
//		grid.getColumn("zone").seti
//		grid.addClomn
//		grid.setColumns("numS12", "zone","registrationDate");
	}


	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {

	}

}
