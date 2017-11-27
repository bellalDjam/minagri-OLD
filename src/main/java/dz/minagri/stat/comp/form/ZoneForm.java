/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.event.selection.SingleSelectionEvent;
import com.vaadin.event.selection.SingleSelectionListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
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
/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//@SpringView(name = RGAView.VIEW_NAME)
public class ZoneForm extends VerticalLayout implements View  {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4422515472136056523L;
	
	
	private final  WilayaRepository wilRepo;
	private final CommuneRepository comRepo;
	private final ZoneRepository zoneRepo;
	
	public static final String VIEW_NAME = "zoneform";
	//UI components
	Label lblTitle;
//	private ComboBox<Wilaya> cbWilaya;

	
	private Button btnNewWilaya;
	private Button btnNewCommune;
	private Button btnNewZone;
	private Button btnConfirm;
	private Button btnCancel;
	
	private TextField TypeCommune;
	private TextField codeSubdiv;

	private FormLayout form;
	private HorizontalLayout cbs;
	private HorizontalLayout headerW;
	private HorizontalLayout headerC;
	private HorizontalLayout headerZ ;
	private HorizontalLayout footer;
	private HorizontalLayout header;

	private ComboBox<Wilaya> selectW;
	private ComboBox<Commune> selectC;
	private ComboBox<Zone> selectZ;
	
	private Commune selectedC=new Commune();
	/**
	 * 
	 */	
	public ZoneForm(WilayaRepository wilRepo,CommuneRepository comRepo,ZoneRepository zoneRepo) {
		//initial setup
		this.wilRepo =wilRepo;
		this.comRepo =comRepo;
		this.zoneRepo=zoneRepo;
		
		setSpacing(false);
		setMargin(false);
		
		//UI Components
//		lblTitle = new Label("R-G-Admistratifs");
//		lblTitle.addStyleName("h4");
//		addComponent(lblTitle);
		//add Combobox 
//		final ComboBox<Wilaya> 
		selectW =   new ComboBox<>();
		selectW.setVisible(true);
		
//		final  ComboBox<Commune> 
		selectC = new ComboBox<>();
		selectC.setVisible(false);
		
//		final  ComboBox<Zone> 
		selectZ = new ComboBox<>();
		selectZ.setVisible(false);
	
		//First Populate selectW
		List<Wilaya> wils = wilRepo.findAll();
		selectW.setPlaceholder("Wilaya");
		selectW.setItemCaptionGenerator(p->p.getNomWilaya());
		selectW.setItems(wils);

		btnNewWilaya = new Button("+ Wilaya");
		btnNewWilaya.addStyleName("primary");
		btnNewWilaya.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {

				// Create a sub-window and set the content
				final Window subWindow = new Window("Nouvelle Wilaya");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("90%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it
				// Center it in the browser window
				subWindow.center();
				subWindow.setModal(true);
				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				final TextField nameWilaya = new TextField();
				nameWilaya.setPlaceholder("Nom Wilaya");
				final TextField codeWilaya= new TextField();
				codeWilaya.setPlaceholder("Code Wilaya");
				final Button save ;
				
				final Button cancel ;
				save = new Button("Sauver",new Button.ClickListener() {


					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (nameWilaya.getValue()!=null && codeWilaya.getValue()!=null ) {
							Wilaya	exp=		new Wilaya();
							exp.setNomWilaya(nameWilaya.getValue());
							exp.setCodeWilaya(Long.parseLong(codeWilaya.getValue()));
							//TODO Delete sysout
//							System.out.println(codeWilaya.getValue()+"codeWilaya.getValue()");
//							System.out.println(nameWilaya.getValue()+"nameWilaya.getValue()");
//							System.out.println(wilRepo+"wilRepo");
							wilRepo.save(exp);
							subWindow.close();
						}							
					}
				});
				cancel = new Button("Cancel",new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						//					refresh();
					}

				});
				//reset UI
				cancel.addStyleName("danger");
				CssLayout actions = new CssLayout(save, cancel);
				h0.addComponents(nameWilaya,codeWilaya);
				h1.addComponent(actions);

				subContent.addComponents(h0,h1);
				UI.getCurrent().addWindow(subWindow);

			}
		});
//		final  ComboBox<Commune> selectC = new ComboBox<>();
		selectW.addValueChangeListener(new com.vaadin.data.HasValue.ValueChangeListener<Wilaya>() {

			@Override
			public void valueChange(com.vaadin.data.HasValue.ValueChangeEvent<Wilaya> event) {
				if(selectW.getValue()!=null ) {
					List<Commune> coms = selectW.getSelectedItem().get().getCommunes();
					selectC.setVisible(true);
					btnNewCommune.setVisible(true);
						 Notification.show("coms = "+"  commune = "+coms);
						 selectC.setPlaceholder("Commune");
							selectC.setItemCaptionGenerator(p->p.getName());
							selectC.setItems(selectW.getValue().getCommunes());
							
				}
				
			}
		});
		selectC.addSelectionListener(new SingleSelectionListener<Commune>() {
			Commune selCom =selectedC;
			private static final long serialVersionUID = 1241443965637589435L;

			@Override
			public void selectionChange(SingleSelectionEvent<Commune> event) {
				if(selectC.getValue()!=null) {
					
					btnNewZone.setEnabled(true);
					List<Zone> zones = new ArrayList<>();
					if(null!=selectC.getSelectedItem().get().getZones()) {
						selCom = selectC.getValue();
						btnNewZone.setEnabled(true);
						selectZ.setVisible(true);
						zones = selectC.getValue().getZones();
						selectZ.setPlaceholder("Commune");
						selectZ.setItemCaptionGenerator(p->p.getName());
						selectZ.setItems(zones);
					}else {
						
					}
					
				}
				
			}
		});
		 
		btnNewCommune = new Button("+ Commune");
		btnNewCommune.addStyleName("primary");
		btnNewCommune.setVisible(false);
		btnNewCommune.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Nouvelle Commune");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("90%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it
				// Center it in the browser window
				subWindow.center();
				subWindow.setModal(true);

				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);
				HorizontalLayout h = new HorizontalLayout();
				h.setSpacing(true);
				h.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				
				final TextField nameCommune = new TextField();
				nameCommune.setPlaceholder("Nom Commune");
				
				List<Wilaya> wilayas =  wilRepo.findAll();
					selectW.setItems(wilayas);

					// Use the name property for item captions
					selectW.setItemCaptionGenerator(p -> p.getNomWilaya() + " " + p.getCodeWilaya());
				
				final TextField codecommune= new TextField();
				codecommune.setPlaceholder("Code commune");
				
				final NativeSelect<TypeCommune> typeCommune = new NativeSelect<TypeCommune>();
				typeCommune.setItems(EnumSet.allOf(TypeCommune.class));
				
				final TextField codeSubdiv= new TextField();
				codeSubdiv.setPlaceholder("Code Subdiv");

				final Button save ;

				final Button cancel ;

				save = new Button("Sauver",new Button.ClickListener() {


					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (nameCommune.getValue()!=null && codecommune.getValue()!=null 
								&& typeCommune.getValue()!=null && codeSubdiv.getValue()!=null
								&&selectW.getValue()!=null ) {
							Commune	exp=		new Commune();
							exp.setWilaya(selectW.getValue());
							exp.setName(nameCommune.getValue());
							exp.setCodeCommune(Long.parseLong(codecommune.getValue()));
							exp.setTypeCommune(typeCommune.getValue());
							exp.setCodeSubdiv(Long.parseLong(codeSubdiv.getValue()));
							
							comRepo.save(exp);
							subWindow.close();
						}							
					}
				});
				cancel = new Button("Cancel",new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						//					refresh();
					}

				});
				cancel.addStyleName("danger");
//				save.addStyleName(style);
				CssLayout actions = new CssLayout(save, cancel);
				h0.addComponents(selectW,codeSubdiv);
				h01.addComponents(nameCommune,codecommune);
				h.addComponent(typeCommune);
				h1.addComponent(actions);

				subContent.addComponents(h0,h01,h,h1);
				UI.getCurrent().addWindow(subWindow);
			}
		});
		
		btnNewZone=new Button("+ Zone");
		btnNewZone.addStyleName("primary");
		btnNewZone.setEnabled(false);
		btnNewZone.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Nouvelle Zone");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("90%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it
				// Center it in the browser window
				subWindow.center();
				subWindow.setModal(true);

				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				
				final TextField nameZone = new TextField();
				nameZone.setPlaceholder("Nom Zone");
				
				final TextArea remarque = new TextArea();
				remarque.setPlaceholder("Remarques");
				
				final Button save ;

				final Button cancel ;

				save = new Button("Sauver",new Button.ClickListener() {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (nameZone.getValue()!=null && selectC.getValue()!=null) {
							
							Zone	expz=		new Zone();
							expz.setName(nameZone.getValue());
							expz.setRemarque(remarque.getValue());
							Commune  com=comRepo.findByNameLike(selectC.getValue().getName());
							expz.setCommune(com);
							zoneRepo.save(expz);
							subWindow.close();
						}							
					}
				});
				cancel = new Button("Cancel",new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						//					refresh();
					}

				});
				cancel.addStyleName("danger");
//				save.addStyleName(style);
				CssLayout actions = new CssLayout(save, cancel);
				h0.addComponents(nameZone,remarque);
				h01.addComponent(actions);

				subContent.addComponents(h0,h01);
				UI.getCurrent().addWindow(subWindow);
			}
		});
		
		headerW = new HorizontalLayout();
		headerW.setMargin(false);
		headerW.setSpacing(false);
	
		headerC = new HorizontalLayout();
		headerC.setMargin(false);
		headerC.setSpacing(false);
		headerZ = new HorizontalLayout();
		header = new HorizontalLayout();
		header.addComponents(selectW,btnNewWilaya,selectC,btnNewCommune,selectZ,btnNewZone);
		addComponent(header);  

		form = new FormLayout();
		form.setMargin(false);
		form.setWidth("900");
		form.addStyleName("light");
		addComponent(form);  


//		List<Wilaya> wilayas = new ArrayList<>();
		//        wilayas = wilRepo.findAll();
//		cbWilaya= new ComboBox<Wilaya>("Wilaya");
		//        cbWilaya.setItems(wilayas);
		//        cbWilaya.setItemCaptionGenerator(Wilaya::getNomWilaya);

		btnConfirm = new Button("Confirm");
		btnConfirm.addStyleName("primary");
		btnConfirm.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
			
			}
		});
		btnCancel = new Button("Cancel");
		btnCancel.addStyleName("primary");
		btnCancel.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				//add class to teacher

				Notification.show("Class Added");

			}
		});

		//        cbs.addComponents(cbWilaya,btnNewWilaya);
		//        form.addComponent(cbs);
		//horizontal layout for button
		footer = new HorizontalLayout();
		footer.setMargin(new MarginInfo(true,false,true,false));
		footer.setSpacing(true);
		footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
		form.addComponent(footer);
		footer.addComponents(btnConfirm,btnCancel);

	}

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
	}

}
