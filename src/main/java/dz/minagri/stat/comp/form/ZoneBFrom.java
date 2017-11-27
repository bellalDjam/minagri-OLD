/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.converter.StringToLongConverter;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dz.minagri.stat.enumeration.TypeCommune;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.model.Zone;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.WilayaRepository;
import dz.minagri.stat.repositories.ZoneRepository;
import dz.minagri.stat.util.TextFieldUtils;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class ZoneBFrom extends HorizontalLayout implements View {

	private static final long serialVersionUID = 1L;
	public static final String VIEW_NAME = "zoneBForm";

	private final  WilayaRepository wilRepo;
	private final CommuneRepository comRepo;
	private final ZoneRepository zoneRepo;

//	private Button btnNewWilaya = new Button("+");
//	private Button btnNewZone = new Button("+");
//	private Button btnNewZone = new Button("+");
//		private Button save = new Button();
//		private Button cancel;

	private ComboBox<Wilaya> selectW = new ComboBox<Wilaya>();
	private ComboBox<Commune> selectC= new ComboBox<Commune>();
	private ComboBox<Zone> selectZ= new ComboBox<Zone>();

	private Grid<Wilaya> grid = new Grid<>(Wilaya.class);
	private Grid<Commune> grid1 = new Grid<>(Commune.class);
	private Grid<Zone> grid2 = new Grid<>(Zone.class);

//	private VerticalLayout vlGrid = new VerticalLayout();
	//	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private VerticalLayout fLayout ;

	public ZoneBFrom(WilayaRepository wilRepo,CommuneRepository comRepo,ZoneRepository zoneRepo) {
		//initial setup
		this.wilRepo =wilRepo;
		this.comRepo =comRepo;
		this.zoneRepo=zoneRepo;

		setSpacing(false);
		setMargin(false);
		setSizeFull();
		 
//		btnNewWilaya.addStyleName("friendly");
//		btnNewWilaya.setDescription("Clicker Remplire le formulaire"
//				+ " et sauver pour Une nouvelle Commune");
//		btnNewWilaya.addClickListener(e -> popupComBuild());
//		btnNewWilaya.setVisible(false);
//		btnNewZone.setVisible(false);
//		btnNewZone.addStyleName("friendly");
////		btnNewZone.addClickListener(e -> popupZoneBuild());
//		btnNewZone.setDescription("Clicker Remplire le formulaire"
//				+ " et sauver pour Une nouvelle Zone");
//		btnNewZone.addStyleName("small");
//		btnNewZone.setVisible(false);
//		btnNewZone.addStyleName("friendly");
//		grid.setVisible(false);
		
		grid1.setVisible(false);
		grid2.setVisible(false);
		
		fLayout = new VerticalLayout();

		fLayout.setMargin(false);
		fLayout.setMargin(true);
		fLayout.setSizeFull();
		fLayout.setWidth("100%");
		fLayout.addStyleName("light");

		hlasearch.setSpacing(true);
		hlasearch.setMargin(true);
		hlasearch.addComponents(selectW,selectC,selectZ);
		
		List<Wilaya> wils = wilRepo.findAll();
		selectW.setPlaceholder("Wilaya");
		selectW.setItemCaptionGenerator(p->p.getNomWilaya());
		selectW.setItems(wils);
		
		updateWGrid();
		
		
		fLayout.setSizeFull();
		fLayout.addComponents(hlasearch,grid,grid1,grid2);

		addComponent(fLayout);
		selectW.addSelectionListener(event -> {
//		selectW.addValueChangeListener(event -> {
            if (event.getValue() != null) {
            	showSubSelecW(true);
            updateCList(event.getValue());
            populateCombo(updateCList(event.getValue()));
            updateCGrid(updateCList(event.getValue()));
            }
            else 
            	showSubSelecW(false);
            
            	updateWGrid();
            	populateWCombo();
           grid.setVisible(true);
        });
		selectC.addSelectionListener(event -> {
//		selectC.addValueChangeListener(event -> {
            if (event.getValue() != null) {
            	grid.setVisible(false);
            	showSubSelecC(true);
            updateZList(event.getValue());
            populateCombo1(updateZList(event.getValue()));
            updateZGrid(updateZList(event.getValue()));
            }else
            	showSubSelecC(false);
        });
	}


	/**
	 * @param updateCList
	 */
	private void populateWCombo() {
		selectW.setItemCaptionGenerator(p->p.getNomWilaya());
		selectW.setItems(wilRepo.findAll());
	}
	private void populateCombo(List<Commune> updateCList) {
		selectC.setPlaceholder("Commune");
		selectC.setItemCaptionGenerator(p->p.getName());
		
		selectC.setItems(updateCList);
		
	}
	private void populateCombo1(List<Zone> updateZList) {
		selectZ.setPlaceholder("Zone");
		selectZ.setItemCaptionGenerator(p->p.getName());
		selectZ.setItems(updateZList);
	}

	/**
	 * @param value
	 */
	private List<Commune> updateCList(Wilaya value) {
	List<Commune> cL1=	new ArrayList<Commune>();
//	wilRepo.get
	cL1= wilRepo.findOne(value.getId()).getCommunes();
	return cL1;
	}
	private List<Zone> updateZList(Commune value) {
		List<Zone> cL=	new ArrayList<Zone>();
		cL= comRepo.findOne(value.getId()).getZones();
		return cL;
		}

	/**
	 * @param value
	 */
	private void updateZGrid(List<Zone> listZ) {
		grid2.setSizeFull();
		grid2.setResponsive(true);
		grid2.setItems(listZ);
		grid2.setColumns("name","remarque", "commune");
		grid2.setSelectionMode(SelectionMode.SINGLE);
	}

	/**
	 * @param value
	 */
	private void updateCGrid(List<Commune> ListC) {
		grid1.setSizeFull();
		grid1.setResponsive(true);
		grid1.setItems(ListC);
		grid1.setColumns("name","typeCommune", "codeSubdiv"
				,"codeCommune","caract1");
		grid1.setSelectionMode(SelectionMode.SINGLE);
	}


	private void showSubSelecW(boolean swho) {
		grid1.setVisible(swho);
		
	}
	
	private void showSubSelecC(boolean swho) {
		grid.setVisible(!swho);
		grid1.setVisible(!swho);
		grid2.setVisible(swho);
	}

	/**
	 * @param grid3
	 */
	
	private void updateWGrid() {
		grid.setSizeFull();
		grid.setResponsive(true);
		grid.setItems(wilRepo.findAll());
		grid.setColumns("nomWilaya", "codeWilaya"
				,"totarea","totpopulation");
		grid.setSelectionMode(SelectionMode.SINGLE);
		

	}
	/**
	 * @return
	 */




//	

	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {
	}
	public interface ChangeHandler {

		void onChange();
	}
	public void setChangeHandler(ChangeHandler h) {
		// ChangeHandler is notified when either save or delete
		// is clicked
//		save.addClickListener(e -> h.onChange());
//		delete.addClickListener(e -> h.onChange());
	}
//	private void popupComBuild() {
//		// Create a sub-window and set the content
//		Binder<Commune> binder =new Binder<>(Commune.class);
//		final Window subWindow = new Window("Nouvelle Commune");
//		VerticalLayout subContent = new VerticalLayout();
//		subContent.setWidthUndefined();
//		subContent.setMargin(true);
//		subContent.setSpacing(true);
//		subWindow.setContent(subContent);
//		// Put some components in it
//		// Center it in the browser window
//		subWindow.center();
//		subWindow.setModal(true);
//
//		selectW = new ComboBox<Wilaya>("Wilaya");
//		selectW.setItemCaptionGenerator(p->p.getNomWilaya());
//		selectW.setItems(wilRepo.findAll());
//		HorizontalLayout h1 = new HorizontalLayout();
//		
//		final TextField name = new TextField("Nom de la Commune");
//		name.setPlaceholder("Nom Commune");
//		final TextField codeCommune= new TextField("Code de la Commune");
//		codeCommune.setPlaceholder("Code commune");
//		final NativeSelect<TypeCommune> typeCommune = new NativeSelect<TypeCommune>("Type");
//		typeCommune.setItems(EnumSet.allOf(TypeCommune.class));
//		final TextField codeSubdiv= new TextField("Code de la Subdiv");
//		codeSubdiv.setPlaceholder("Code Subdiv");
//		
//		name.setRequiredIndicatorVisible(true);
//		codeCommune.setRequiredIndicatorVisible(true);
//		codeSubdiv.setRequiredIndicatorVisible(true);
//		typeCommune.setRequiredIndicatorVisible(true);
//		
//		binder.forField(name).withValidator(
//				text -> text.length() >= 3,
//				"Donner plusque 3 carrectéres pour le nomde la Commune")
//		.bind(Commune::getName, Commune::setName);
//		
//		binder.forField(codeCommune).withValidator(
//				text -> text.length() >= 1,
//				"plusque 4 carrectéres").withConverter(
//						new StringToLongConverter("Donner le Code de cette Commune"))
//		.bind(Commune::getCodeCommune, Commune::setCodeCommune);
//		
//		binder.forField(codeSubdiv).withValidator(
//				text -> text.length() >= 1,
//				"Donner un Nombre").withConverter(
//						new StringToLongConverter("Donner le Code de la Subdiv"))
//		.bind(Commune::getCodeSubdiv, Commune::setCodeSubdiv);
//		
////		final Button save ;
//
//		final Button cancel ;
//		HorizontalLayout h0 = new HorizontalLayout(selectW,codeCommune,name);
//		HorizontalLayout h01 = new HorizontalLayout(typeCommune,codeSubdiv);
//		
//		binder.bindInstanceFields(subContent);
//
//		
//		save = new Button("Sauver",new Button.ClickListener() {
//
//
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 4290310259348109186L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if (codeCommune.getValue()!=null && name.getValue()!=null 
//						&& typeCommune.getValue()!=null && codeSubdiv.getValue()!=null
//						&&selectW.getValue()!=null ) {
//					
//					Commune	com=new Commune();
//					Wilaya  wilCom=wilRepo.findOneBynomWilaya(selectW.getValue().getNomWilaya());
//
//					com.setWilaya(wilCom);
//					com.setName(name.getValue());
//					com.setCodeCommune(Long.parseLong(codeCommune.getValue()));
//					com.setTypeCommune(typeCommune.getValue());
//					com.setCodeSubdiv(Long.parseLong(codeSubdiv.getValue()));
//					
//					comRepo.save(com);
//					subWindow.close();
//					
//					binder.removeBean();
//					updateWGrid();
//					showSubSelecW(false);
//					populateWCombo();
//				}	
//				Notification.show("selectW.getValue()"+selectW.getValue());
//			}
//		});
//		cancel = new Button("Cancel",new Button.ClickListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7044268028795285553L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				subWindow.close();
//			}
//
//		});
//		cancel.addStyleName("danger");
//		save.addStyleName("primary");
//		CssLayout actions = new CssLayout(save, cancel);
//		
//		h1.addComponent(actions);
//
//		subContent.addComponents(h0,h01,h1);
//		
//		UI.getCurrent().addWindow(subWindow);
//		
//	}
//		private void popupZoneBuild() {
//		Binder<Zone> binder =new Binder<>(Zone.class);
//		// Create a sub-window and set the content
//		final Window subWindow = new Window("Nouvelle Zone");
//		VerticalLayout subContent = new VerticalLayout();
//		subContent.setWidth("90%");
//		subContent.setMargin(true);
//		subContent.setSpacing(true);
//		subWindow.setContent(subContent);
//		// Put some components in it
//		// Center it in the browser window
//		subWindow.center();
//		subWindow.setModal(true);
//
//		HorizontalLayout h0 = new HorizontalLayout();
//		h0.setSpacing(true);
//		h0.setMargin(true);
//		HorizontalLayout h01 = new HorizontalLayout();
//		
//		final TextField name = new TextField();
//		name.setPlaceholder("Nom Zone");
//		name.setRequiredIndicatorVisible(true);
//		final TextArea remarque = new TextArea();
//		remarque.setPlaceholder("Remarques");
//		
//		binder.forField(name).withValidator(
//				text -> text.length() >= 3,
//				"Donner plusque 3 carrectéres pour le nomde la Commune")
//		.bind(Zone::getName, Zone::setName);
//		
//		final Button save ;
//
//		final Button cancel ;
//		binder.bindInstanceFields(subContent);
//		
//		save = new Button("Sauver",new Button.ClickListener() {
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = 1L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//				if (name.getValue()!=null && selectC.getValue()!=null) {
//					
//					Zone	expz=		new Zone();
//					expz.setName(name.getValue());
//					expz.setRemarque(remarque.getValue());
//					Commune  com=comRepo.findByNameLike(selectC.getValue().getName());
//					expz.setCommune(com);
//					zoneRepo.save(expz);
//					subWindow.close();
//					binder.removeBean();
//				}							
//			}
//
//			
//		});
//		cancel = new Button("Cancel",new Button.ClickListener() {
//
//			/**
//			 * 
//			 */
//			private static final long serialVersionUID = -7044268028795285553L;
//
//			@Override
//			public void buttonClick(ClickEvent event) {
//
//				subWindow.close();
//			}
//
//		});
//		CssLayout actions = new CssLayout(save, cancel);
//		h0.addComponents(name,remarque);
//		h01.addComponent(actions);
//
//		subContent.addComponents(h0,h01);
//		UI.getCurrent().addWindow(subWindow);
//		updateWGrid();
//		showSubSelecW(false);
//	}
//	
		
}
