/**
 * 
 */
package dz.minagri.stat.views;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.data.HasValue.ValueChangeEvent;
import com.vaadin.data.HasValue.ValueChangeListener;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dz.minagri.stat.comp.MinagriLabel;
import dz.minagri.stat.enumeration.EtatMauvHerb;
import dz.minagri.stat.enumeration.EtatSanitaire;
import dz.minagri.stat.enumeration.EtatVegetParcelle;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.OrigineSemence;
import dz.minagri.stat.enumeration.SampleType;
import dz.minagri.stat.enumeration.TypeExploitation;
import dz.minagri.stat.enumeration.TypePersonne;
import dz.minagri.stat.model.Adresse;
import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.EspeceCultivee;
import dz.minagri.stat.model.Exploitant;
import dz.minagri.stat.model.Exploitant.ExploitantStatus;
import dz.minagri.stat.model.Exploitation;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.model.Rga;
import dz.minagri.stat.model.Wilaya;
import dz.minagri.stat.model.Zone;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.EspeceCultiveeRepository;
import dz.minagri.stat.repositories.ExploitantRepository;
import dz.minagri.stat.repositories.ExploitationRepository;
import dz.minagri.stat.repositories.PersonneRepository;
import dz.minagri.stat.repositories.WilayaRepository;
import dz.minagri.stat.repositories.ZoneRepository;


/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = RGAView.VIEW_NAME)
public class RGAView extends CssLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -275612132918409997L;
	public static final String VIEW_NAME = "rgaview";

	@Autowired
	private WilayaRepository wilRepo;
	@Autowired
	private CommuneRepository comRepo;
	@Autowired
	private ZoneRepository zoneRepo;
	@Autowired
	private ExploitantRepository expRepo;
	@Autowired
	private ExploitationRepository exptaRepo;
	@Autowired
	private EspeceCultiveeRepository especultRepo;
	@Autowired
	private PersonneRepository 	personneRepo;
	@Autowired
	private MinagriLabel minLabel;
	// @Autowired
	// private ExploitantForm expF;

	private Rga rga;
	
	private VerticalLayout mainContainer = new VerticalLayout();
	private Panel geoExPanel = new Panel("INDICATION GEO D'EXPLOITATION");
	private Panel ppPanel = new Panel("INDICATION, ASPECT DE PRODUCTION ET ENSEMENCEMENT VARIETE ");
	private Panel aspExPanel = new Panel("ASPECT DE L'EXPLOITATION");
	private Panel preProdDestPanel = new Panel("DESTINATION DE LA PRECEDENTE COMPAGNE");
	private Panel beanPanel = new Panel("DENSITE ET NOMBRE DE GRAIN PAR EPIS ");
	private Panel invesPanel = new Panel("ENQUETE, PREDICTION ET SUPERVISION");
	final ComboBox<Exploitation> longcomb = new ComboBox<Exploitation>();
	final ComboBox<Exploitation> latcomb = new ComboBox<Exploitation>();
	final ComboBox<Wilaya> wcomb = new ComboBox<Wilaya>();
	final ComboBox<Commune> ccomb = new ComboBox<Commune>();
	final ComboBox<Zone> zcomb = new ComboBox<Zone>();

	final ComboBox<Exploitation> lieudit = new ComboBox<Exploitation>();
	final ComboBox<Exploitation> exploitationComb = new ComboBox<Exploitation>();

	private Button addExp = new Button("+ Exploitant");
	private Button addExpt = new Button("Exploitation");
	private Button save = new Button("Enregistrer L'Enquête");
	private Button cancel = new Button("Sortire");
	private Button showContent = new Button("Afficher Le Contenue");
	private Button addParcel = new Button("Ajouter Une Autre Parcele");
	private Button title = new Button();
	private Grid<Exploitation> exptGrid ;

	@PostConstruct
	void init() {
		removeAllComponents();

		refresh();
	}

	/**
	 * 
	 */
	private void refresh() {
		removeAllComponents();
		Responsive.makeResponsive(this);
		//add some Layout
		
		rga =new Rga();
		HorizontalLayout upperSection = new HorizontalLayout();
		upperSection.setWidth("100%");
		HorizontalLayout firstSection = new HorizontalLayout();
		firstSection.setWidth("100%");
		HorizontalLayout firstHLSection = new HorizontalLayout();
		firstHLSection.setWidth("100%");

		HorizontalLayout hLSection = new HorizontalLayout();
		hLSection.setWidth("100%");
		HorizontalLayout firstLSection = new HorizontalLayout();
		
		Panel insidPan = new Panel("TABLE D'EXPLOITATION");
		firstLSection.setWidth("100%");
		VerticalLayout panelContent = new VerticalLayout();
		
		HorizontalLayout hlpan = new HorizontalLayout();
		VerticalLayout vlparprod = new VerticalLayout();
		HorizontalLayout hlparprod = new HorizontalLayout();
		HorizontalLayout hlparprod1 = new HorizontalLayout();
		HorizontalLayout hlparprod2 = new HorizontalLayout();
		 exptGrid = new Grid<>(Exploitation.class);
		
		VerticalLayout vlBeans = new VerticalLayout();
		HorizontalLayout hlBeans = new HorizontalLayout();
		HorizontalLayout hlBeans2 = new HorizontalLayout();
		HorizontalLayout hlBeans1 = new HorizontalLayout();
		//for desrecoltpreced
		HorizontalLayout hlPrecedCulDest = new HorizontalLayout();
		//for investigation
		VerticalLayout vlinvest = new VerticalLayout();
		HorizontalLayout hlinvest = new HorizontalLayout();
		HorizontalLayout hlinvest1 = new HorizontalLayout();
		HorizontalLayout hlinvest2 = new HorizontalLayout();
		HorizontalLayout hlinvest3 = new HorizontalLayout();
		
		HorizontalLayout command = new HorizontalLayout();
		//native select for scnd  panel

		final	NativeSelect<EtatVegetParcelle> nsEtatvegeParcell = new NativeSelect<EtatVegetParcelle>("Etat Vegetatif");
		nsEtatvegeParcell.setItems(EnumSet.allOf(EtatVegetParcelle.class));
		final	NativeSelect<EtatMauvHerb> nsmauvherb= new NativeSelect<EtatMauvHerb>("Etat Mauvaises Herbes");
		nsmauvherb.setItems(EnumSet.allOf(EtatMauvHerb.class));
		final	NativeSelect<EtatSanitaire> nsetatSan= new NativeSelect<EtatSanitaire>("Etat Sanitaire");
		nsetatSan.setItems(EnumSet.allOf(EtatSanitaire.class));
		final RadioButtonGroup<String> single =
			    new RadioButtonGroup<>("Apparition d'un stress Hydrique");
			single.setItems("Oui", "Non");
		//Field for Parcel Production  ande assencemetnt pan
			final TextField surfaceTF =new TextField("surface Consacrée");
			final TextField rendementAttTF =new TextField("Rendement Attendu");
			final TextField irregationTf =new TextField("irregation");
			final TextField tfquantityprod =new TextField("quantité produite");
			
			final TextField tfrotationFrequency =new TextField("rotation");
			final TextField tfrotationdescription =new TextField("description");
			final DateField  dfplantationDate = new DateField ("plantée Le");
			final DateField  dfrecoltdate = new DateField ("recoltée Le");
			
			final ComboBox<EspeceCultivee> especultcombo= new ComboBox<EspeceCultivee>("Espece Cultivee");
			final ComboBox<EspeceCultivee> varsemcombo= new ComboBox<EspeceCultivee>("Variété Semée");
			final ComboBox<EspeceCultivee> especultcomboPre= new ComboBox<EspeceCultivee>("Précedente Cultural,,exp:PDT,Légu,jachè");
			final	NativeSelect<OrigineSemence> nsOrigineSemence= new NativeSelect<OrigineSemence>("Origine Semence");
			nsOrigineSemence.setItems(EnumSet.allOf(OrigineSemence.class));
			final RadioButtonGroup<String> radRot =
				    new RadioButtonGroup<>("Rotation de plantation");
			radRot.setItems("Oui", "Non");
			
			final ComboBox<Personne> cbxSupervisor= new ComboBox<Personne>("Agent");
			
		// hide Some Component
		exploitationComb.setEnabled(false);
		exploitationComb.addValueChangeListener(e->{
	            if (e.getValue() == null) {
	            	addExp.setVisible(true);
	            	}
//	            else {
//		            }
		        });
		addExpt.setEnabled(false);
		addExp.setEnabled(false);
		exploitationComb.setPlaceholder("Exploitations");
		lieudit.setPlaceholder("Lieu dit");
		longcomb.setPlaceholder("G P S Langitude");
		latcomb.setPlaceholder("G P S Latitude");
		
		title.setCaption("ENQUETE : Prédiction à dire "
				+ "d'expert des rendements des céréales le     :       "
				+ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy:MM:dd")));
		title.setSizeFull();
		title.setWidth("100%");
		
		//Component for firstSection
		// UI Components

//		 grid.addColumn(object -> object.getAddress().getCity())
		exptGrid.setSizeFull();
		exptGrid.setWidthUndefined();
		exptGrid.asSingleSelect().addValueChangeListener(event -> {
            if (event.getValue() == null) {
            	addExp.setVisible(false);
            } else {
            	addExp.setVisible(true);
            rga.setExploitation(event.getValue());
            }
        });
		//Subject Component RGA

		mainContainer.setWidth("100%");
		mainContainer.setSpacing(false);
		mainContainer.setMargin(false);
//		mainContainer.addComponent(hLSection);
		mainContainer.setDefaultComponentAlignment(Alignment.TOP_CENTER);


		upperSection.addComponent(minLabel.headerFactory());
		//Filling and attach the search comboBox lieudit, latcomb & longcomb 
		
		lieudit.setItemCaptionGenerator(p -> p.getLieuDit());
		lieudit.setItems(exptaRepo.findAll());
		latcomb.setItemCaptionGenerator(p -> p.getdLat().toString());
		latcomb.setItems(exptaRepo.findAll());
		longcomb.setItemCaptionGenerator(p -> p.getdLon().toString());
		longcomb.setItems(exptaRepo.findAll());
		firstHLSection.addComponents(lieudit,latcomb,longcomb,addExpt,addExp);

		//populating First Panel 

		panelContent.addComponents(wcomb,ccomb,
				zcomb,exploitationComb);

		panelContent.setSizeUndefined();
		hlpan.addComponents(panelContent,insidPan);
		hlpan.setSizeFull();	
		
		
//		hlpan1.setSizeFull();
		geoExPanel.setContent(hlpan);
		geoExPanel.setSizeFull(); 
		firstSection.addComponent(geoExPanel );
		firstSection.setSizeFull();
		mainContainer.addComponent(firstSection);
		//attache Component of the first panel productionparcel
				//TODO Don't forget to comp
		especultcombo.setItemCaptionGenerator(p -> p.getName());
		especultcombo.setItems(especultRepo.findAll());
		especultcomboPre.setItemCaptionGenerator(p -> p.getName());
		especultcomboPre.setItems(especultRepo.findAll());
		varsemcombo.setItemCaptionGenerator(p -> p.getName());
		varsemcombo.setItems(especultRepo.findAll());
		hlparprod1.addComponentsAndExpand(dfrecoltdate,irregationTf,tfquantityprod,rendementAttTF);
		hlparprod.addComponents(surfaceTF,dfplantationDate,especultcombo,varsemcombo,nsOrigineSemence);
		hlparprod2.addComponentsAndExpand(radRot,tfrotationFrequency,especultcomboPre,tfrotationdescription);
		vlparprod.addComponentsAndExpand(hlparprod,hlparprod1,hlparprod2);
		ppPanel.setContent(vlparprod);
		mainContainer.addComponent(ppPanel);
		//attache Component of the scd panel
		//TODO Don't forget to complete the Entity ExploitationGlobalAspect 
		hLSection.addComponents(nsEtatvegeParcell,nsmauvherb,nsetatSan,single);
		aspExPanel.setContent(hLSection);
		mainContainer.addComponent(aspExPanel);
		
		final TextField tfnbrBenEpi1 =new TextField("Nombre de grains 1er épis");
		final TextField tfnbrBenEpi2 =new TextField("Nombre de grains 2ème épis");
		final TextField tfnbrBenEpi3 =new TextField("Nombre de grains 3ème épis");
		final TextField tfnbrEpismm =new TextField("Nombre d'épis/M²");
		final	NativeSelect<SampleType> snsampleType= new NativeSelect<SampleType>("Moyen utilisé durant l'ênquete");
		snsampleType.setItems(EnumSet.allOf(SampleType.class));
		
		hlBeans.addComponentsAndExpand(snsampleType,tfnbrEpismm);
		hlBeans2.addComponentsAndExpand(new Label("Nombre des grains par épis  ( Prélever 3 epis de manière "
				+ "aléatoire et compter le nombre de grains par épis" ));
		hlBeans1.addComponentsAndExpand(tfnbrBenEpi1,tfnbrBenEpi2,tfnbrBenEpi3);
		//TODO Don't forget to complete the Entity ProductionParcel 
		vlBeans.addComponentsAndExpand(hlBeans,hlBeans2,hlBeans1);
		beanPanel.setContent(vlBeans);
		mainContainer.addComponent(beanPanel);
		
		final TextField tfforCCLS=new TextField("Livrée à la CCLS");
		final TextField tfforcustumor =new TextField("Vendue aux Particuliers");
		final TextField tffortransformer =new TextField("Vendue aux Transformateur");
		final TextField tflivestock =new TextField("Alimentation de bétails");
		final TextField tfautre =new TextField("Autres...(Préciser)");
		hlPrecedCulDest.addComponentsAndExpand(tfforCCLS,tfforcustumor,tffortransformer,tflivestock,tfautre);
		preProdDestPanel.setContent(hlPrecedCulDest);
		mainContainer.addComponent(preProdDestPanel);
		
		
		final TextField tfduration=new TextField("Durée de l'enquête en Min(depuis l'arrivée jusqu'au départ)");
		final TextField tfpassager =new TextField("Enquête effectuée par :");
		final TextField tfpassagerMob =new TextField("Numéro de tél Mobile Enquêteur");
		final TextField tfSupervisorMob =new TextField("Numéro de tél Mobile Superviseur");
		
		final	NativeSelect<TypePersonne> nsSupervisorQual= new NativeSelect<TypePersonne>("Qualité de l'enquêteur");
		nsSupervisorQual.setItems(EnumSet.allOf(TypePersonne.class));
		
		
//		cbxSupervisor.setItemCaptionGenerator(p -> p.getFirstName()+" "+p.getLastName());
//		cbxSupervisor.setItems(personneRepo.findAll());
		final DateField  dFpassage = new DateField ("Date du passage");
		//TODO Don't forget to complete the Entity Person 
		hlinvest.addComponentsAndExpand(dFpassage,tfduration);
		hlinvest1.addComponentsAndExpand(tfpassager,nsSupervisorQual,tfpassagerMob);
		hlinvest2.addComponentsAndExpand(cbxSupervisor,nsSupervisorQual,tfSupervisorMob);
		hlinvest3.addComponentsAndExpand(new TextArea("Exploitant")
				,new TextArea("Enquêteur")
				,new TextArea("Superviseur"));
		vlinvest.addComponentsAndExpand(hlinvest,hlinvest,hlinvest1,hlinvest2,new Label("Remarques, "
				+ "suggestions et doléance de l'exploitant, de l'enquêteur ou du superviseur :"),hlinvest3,
				new Label("Pour toute information(s) Complémentaire(s) "
						+ "  Contactez le point focal national : Mr TOUATI Saber, Mob 0554 28 21 94")
				,new Label( "TéL DSASI :023 50 31 22  Standar du Ministére",ContentMode.HTML));
		invesPanel.setContent(vlinvest);  
		mainContainer.addComponent(invesPanel); 
		
		cancel.setStyleName("danger");
		save.setStyleName("small");
		showContent.setStyleName("friendly");
		save.setStyleName("primary");
		command.addComponents(save, showContent,cancel);
		command.setComponentAlignment(cancel,Alignment.MIDDLE_RIGHT);
		mainContainer.addComponent(command); 

		// First Populate combobox for select Wilaya
		List<Wilaya> wils = wilRepo.findAll();
		wcomb.setPlaceholder("Wilaya");
		wcomb.setEmptySelectionAllowed(false);
		wcomb.setItemCaptionGenerator(p -> p.getNomWilaya());
		wcomb.setItems(wils);
		wcomb.addValueChangeListener(new ValueChangeListener<Wilaya>() {
			private static final long serialVersionUID = -932309083138550672L;

			@Override
			public void valueChange(ValueChangeEvent<Wilaya> event) {
				// First Populate combobox for select Commune
				if (wcomb.getValue() != null) {

					List<Commune> coms = wcomb.getValue().getCommunes();

					ccomb.setEnabled(true);
					ccomb.setPlaceholder("Commune");
					ccomb.setItemCaptionGenerator(p -> p.getName());
					ccomb.setItems(coms);
					ccomb.addValueChangeListener(new ValueChangeListener<Commune>() {
						private static final long serialVersionUID = -337005937614542385L;

						@Override
						public void valueChange(ValueChangeEvent<Commune> event) {
							// First Populate combobox for select Zones
							if (ccomb.getValue() != null) {
								List<Zone> zones = ccomb.getValue().getZones();
								zcomb.setVisible(true);
								zcomb.setPlaceholder("Zone");
								zcomb.setItemCaptionGenerator(p -> p.getName());
								zcomb.setItems(zones);
								zcomb.addValueChangeListener(new ValueChangeListener<Zone>() {

									private static final long serialVersionUID = 8487170273863056454L;

									@Override
									public void valueChange(ValueChangeEvent<Zone> event) {
										if (zcomb.getValue() != null
												&& !zcomb.getValue().getExploitations().isEmpty()) {
											List<Exploitation> exploitations = zcomb.getValue().getExploitations();
											exploitationComb.setEnabled(true);
											exploitationComb
											.setItemCaptionGenerator(p -> p.getNom() + "  " + p.getId());
											exploitationComb.setItems(exploitations);
											exptGrid.setItems(exploitations);
											exptGrid.setSelectionMode(SelectionMode.SINGLE);
//											exptGrid.addColumn(p -> String.valueOf(p.getId())).setCaption("Id")
//							                .setSortProperty("id");
//											exptGrid.addColumn(p -> String.valueOf(p.getNom())).setCaption("nom")
//							                .setSortProperty("nom");
//											exptGrid.addColumn(p -> String.valueOf(p.getSurface())).setCaption("surface")
//							                .setSortProperty("surface");
//											exptGrid.addColumn(p -> String.valueOf(p.getLieuDit())).setCaption("lieuDit")
//							                .setSortProperty("lieuDit");
//											exptGrid.addColumn(p -> String.valueOf(p.getdLat())).setCaption("dLat")
//							                .setSortProperty("dLat");
//											exptGrid.addColumn(p -> String.valueOf(p.getdLon())).setCaption("dLon")
//							                .setSortProperty("dLon");
											exptGrid.setColumns("id", "surface","nom","lieuDit", "dLat","dLon");

											insidPan.setContent(exptGrid);
											exptGrid.setWidth("100%");
											hlpan.setExpandRatio(insidPan, 1.0f);
										} else {
											Notification.show("Ajouter une nouvelle Exploitation SVP");
											
										}
										addExpt.setEnabled(true);
//										addExp.setEnabled(true);
									}
								});

								// End of Zone Listener
							}
						}
					});
					// End of Commune Listener
				}
			}

		});// End of Wilaya Listener

		// addExp.setP



		addExp.addClickListener(new Button.ClickListener() {
			private static final long serialVersionUID = -5933264914439254521L;

			@Override
			public void buttonClick(ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Nouveau Exploitant");
				VerticalLayout subContent = new VerticalLayout();
				subContent.setWidth("90%");
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it
				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);
				HorizontalLayout h3 = new HorizontalLayout();
				h3.setSpacing(true);
				h3.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);

				final TextField firstnameField = new TextField();
				firstnameField.setPlaceholder("Nom");

				final TextField lastnameField = new TextField();
				lastnameField.setPlaceholder("Prenom");

				final TextField nationalNumber = new TextField();
				nationalNumber.setPlaceholder("Num National");

				final DateField birthday = new DateField();

				final NativeSelect<Gender> gender = new NativeSelect<Gender>();
				gender.setItems(EnumSet.allOf(Gender.class));

				final ComboBox<CarteFellah> carteFellahBx = new ComboBox<CarteFellah>();
				carteFellahBx.setPlaceholder("CarteFellah");

				final NativeSelect<ExploitantStatus> exploitantStatus = new NativeSelect<ExploitantStatus>();
				exploitantStatus.setItems(EnumSet.allOf(ExploitantStatus.class));

				final DateField registrationDate = new DateField();

				final Button save;

				final Button cancel;

				subWindow.setModal(true);

				save = new Button("Sauver", new Button.ClickListener() {

					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (firstnameField.getValue() != null && lastnameField.getValue() != null
								&& birthday.getValue() != null) {
							Exploitant exp = new Exploitant();
							exp.getExploitations().add(exploitationComb.getValue());
							exp.setFirstname(firstnameField.getValue());
							exp.setLastname(lastnameField.getValue());
							exp.setBirthday(birthday.getValue());
							exp.setCarteFellah(carteFellahBx.getValue());
							exp.setNationalNumber(nationalNumber.getValue());
							exp.setGender(gender.getValue());
							exp.setRegistrationDate(registrationDate.getValue());
							exp.setExploitantStatus(exploitantStatus.getValue());

							expRepo.save(exp);
							subWindow.close();

							 refresh();
						}
					}
				});
				cancel = new Button("Cancel", new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						// refresh();
					}

				});
				cancel.addStyleName("danger");
				// save.addStyleName(style);
				CssLayout actions = new CssLayout(save, cancel);
				h01.addComponent(actions);
				h0.addComponents(nationalNumber, lastnameField);
				h1.addComponents(firstnameField, gender, birthday);
				h2.addComponents(exploitantStatus, carteFellahBx);
				h3.addComponents(registrationDate);

				subContent.addComponents(h0, h1, h2, h3, h01);
				UI.getCurrent().addWindow(subWindow);

			}

		});

		addExpt.addClickListener(new Button.ClickListener() {

			private static final long serialVersionUID = -6942760807436347335L;

			@Override
			public void buttonClick(ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Nouvelle Exploitation");
				VerticalLayout subContent = new VerticalLayout();
//				subContent.setWidthUndefined();
				subContent.setSizeUndefined();
				subContent.setMargin(true);
				subContent.setSpacing(true);
				subWindow.setContent(subContent);
				// Put some components in it
				// Center it in the browser window
				subWindow.center();

				HorizontalLayout h0 = new HorizontalLayout();
				h0.setSpacing(true);
				h0.setMargin(true);
				HorizontalLayout h1 = new HorizontalLayout();
				h1.setSpacing(true);
				h1.setMargin(true);
				HorizontalLayout h2 = new HorizontalLayout();
				h2.setSpacing(true);
				h2.setMargin(true);
				HorizontalLayout h3 = new HorizontalLayout();
				h3.setSpacing(true);
				h3.setMargin(true);
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);

				final TextField firstnameField = new TextField();
				firstnameField.setPlaceholder("Nom");
				final TextField surfaceField = new TextField();
				surfaceField.setPlaceholder("Surface");

				final TextField lieuDitField = new TextField();
				lieuDitField.setPlaceholder("lieuDit");
				final TextField coordGPSLatField = new TextField();
				coordGPSLatField.setPlaceholder("gpsLatitude");
				final TextField coordGPSLongField = new TextField();
				coordGPSLongField.setPlaceholder("gpsLongitud");
				final TextField descriptionField = new TextField();
				descriptionField.setPlaceholder("description");
				// Zone Entity
				// TODO AUTO complet cobobox
				final TextField zoneNameField = new TextField();
				zoneNameField.setPlaceholder("zone");
				//
				// Commune Entity
				// TODO AUTO complet cobobox
				final NativeSelect<TypeExploitation> typeExploitation = new NativeSelect<TypeExploitation>();
				typeExploitation.setItems(EnumSet.allOf(TypeExploitation.class));

				// Adress Entity
				// TODO AUTO complet cobobox
				final TextField streetField = new TextField();
				streetField.setPlaceholder("Rue");
				final TextField numberField = new TextField();
				numberField.setPlaceholder("Num");
				final TextField zipField = new TextField();
				zipField.setPlaceholder("Code Postal");
				// TODO Combobox for commune
				// final TextField commune = new TextField();

				final Button save;

				final Button cancel;

				subWindow.setModal(true);

				save = new Button("Sauver", new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (firstnameField.getValue() != null && surfaceField.getValue() != null
								&& lieuDitField.getValue() != null) {

							Adresse adr = new Adresse();
							adr.setRue(streetField.getValue());
							adr.setNumero(numberField.getValue());
							adr.setCodePostal(Long.valueOf(zipField.getValue()));
							adr.setCommune(ccomb.getValue());
							// Exploitation
							Exploitation exp = new Exploitation();
							exp.setAddress(adr);
							exp.setZone(zcomb.getValue());
							exp.setNom(firstnameField.getValue());
							exp.setSurface(surfaceField.getValue());
							exp.setLieuDit(lieuDitField.getValue());
							exp.setdLat(Double.valueOf(coordGPSLatField.getValue()));
							exp.setdLon(Double.valueOf(coordGPSLongField.getValue()));
							exp.setDescription(descriptionField.getValue());
							exptaRepo.save(exp);
							subWindow.close();

							refresh();
						}
					}
				});
				cancel = new Button("Cancel", new Button.ClickListener() {

					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {

						subWindow.close();
						// refresh();
					}

				});
				cancel.addStyleName("danger");
				// save.addStyleName(style);
				CssLayout actions = new CssLayout(save, cancel);
				h01.addComponent(actions);
				h0.addComponents(firstnameField, surfaceField, lieuDitField);
				h1.addComponents(typeExploitation, coordGPSLatField, coordGPSLongField);
				h2.addComponents(streetField, numberField, zipField);
				h3.addComponents(descriptionField);

				subContent.addComponents(h0, h1, h2, h3, h01);
				UI.getCurrent().addWindow(subWindow);

			}

		});

		addComponent(upperSection);
		addComponent(title);
		addComponent(firstHLSection);
		addComponent(firstSection);
		addComponent(mainContainer);
	}

	/**
	 * 
	 */

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
