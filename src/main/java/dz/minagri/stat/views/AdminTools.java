/**
 * 
 */
package dz.minagri.stat.views;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.comp.form.CommuneForm;
import dz.minagri.stat.comp.form.EspeceCultiveeForm;
import dz.minagri.stat.comp.form.SignUpForm;
import dz.minagri.stat.comp.form.VarieteForm;
import dz.minagri.stat.comp.form.WilayaForm;
import dz.minagri.stat.comp.form.ZoneBFrom;
import dz.minagri.stat.repositories.CommuneRepository;
import dz.minagri.stat.repositories.EspeceCultiveeRepository;
import dz.minagri.stat.repositories.IrrigationRepository;
import dz.minagri.stat.repositories.PersonneRepository;
import dz.minagri.stat.repositories.VarieteRepository;
import dz.minagri.stat.repositories.WilayaRepository;
import dz.minagri.stat.repositories.ZoneRepository;

/**
 * @author bellal djamel
 *
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AdminTools.VIEW_NAME)
public class AdminTools extends VerticalLayout implements View{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7504826731360694112L;

	public static final String VIEW_NAME = "admintools";

	@Autowired
	private SpringViewProvider viewProvider;
	@Autowired
	private  WilayaRepository wrepo;
	@Autowired
	private CommuneRepository crepo;
	@Autowired
	private ZoneRepository zoneRepo;
	@Autowired
	private PersonneRepository perRepo;
	@Autowired
	private VarieteRepository cerRepo;
	@Autowired
	private  EspeceCultiveeRepository espRepo;
	
	@Autowired
	private  IrrigationRepository irrigRepo; 
	
	Authentication user =SecurityContextHolder.getContext().getAuthentication();
	HorizontalSplitPanel lowerSection = new HorizontalSplitPanel();
	VerticalLayout menuLayout = new VerticalLayout();
	VerticalLayout contentLayout = new VerticalLayout();
	//	Label lblMenu;
	Button btnLogout;

	@PostConstruct
	void init() {
		removeAllComponents();
		showMainView();
	}
	private void showMainView() {
		//TODO will change later
		setSizeFull();
		btnLogout = new Button("Sign Out");
		btnLogout.addStyleName("small");
		btnLogout.addStyleName("friendly");
		btnLogout.setSizeUndefined();
		btnLogout.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				//logout using backend
				getUI().getPage().reload();
				getSession().close();

				//navigate back to login page
				getUI().getNavigator().navigateTo("login");
			}
		});
		//        lblMenu = new Label("Menu");
		//        lblMenu.addStyleName("colored");
		//        lblMenu.addStyleName("h2");
		//        lblMenu.setSizeFull();
		//menu section
		//        menuLayout.addComponent(lblMenu);
		menuLayout.setWidth("100%");
		//        menuLayout.setComponentAlignment(lblMenu, Alignment.MIDDLE_CENTER);
		menuLayout.setSizeFull();
		lowerSection.addComponent(menuLayout);
		lowerSection.addComponent(contentLayout);
		contentLayout.setSizeFull();
		lowerSection.setSizeFull();
		lowerSection.setSplitPosition(15);

		//        addComponent(upperSection);
		addComponent(lowerSection);
		setSizeFull();
		setExpandRatio(lowerSection,1);
		addStyleName(ValoTheme.UI_WITH_MENU);
	}

	public void setMenuTitle() {
		//set the menu title
		//	        menuLayout.addComponent(lblMenu);
		menuLayout.setWidth("100%");
		//	        menuLayout.setComponentAlignment(lblMenu, Alignment.MIDDLE_CENTER);
	}

	public void addDashboardOption(String caption) {
		//set menu buttons

		Button button = new Button(caption);
		button.setWidth("100%");
		button.setStyleName("borderless");
		menuLayout.addComponent(button);

		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				//remove everything from the content screen section
				contentLayout.removeAllComponents();   
				//	                addWelcomeText();
			}

		});
	}
	public Component getComponent(String componentName) {
		if (componentName.equals("ZoneBForm")) {
			return new ZoneBFrom(wrepo,crepo, zoneRepo);
		} else if (componentName.equals("SignUpForm")) {
			return new SignUpForm(perRepo);
		}else if (componentName.equals("irrigation")) {
			return new IrrigationForm(irrigRepo);
		}else if (componentName.equals("varieteform")) {
			return new VarieteForm( cerRepo,espRepo );
		}else if (componentName.equals("especeCultiveeForm")) {
			return new EspeceCultiveeForm( espRepo );
		}else if (componentName.equals("communeForm")) {
			return new CommuneForm( crepo,wrepo );
//		}else if (componentName.equals("wilayaForm")) {
//			return new WilayaForm( wrepo );
		}return btnLogout;
	}
	public void addMenuOption(String caption, String componentName) {
		Button button = new Button(caption);
		button.setWidth("100%");
		button.setStyleName("borderless");
		menuLayout.addComponent(button);
		button.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(Button.ClickEvent event) {
				contentLayout.removeAllComponents();
				contentLayout.addComponent(getComponent(componentName));
			}

		});
	}

	@Override
	public void enter(ViewChangeEvent event) {
		//reset screen
		menuLayout.removeAllComponents();
		contentLayout.removeAllComponents();

		//add components
		setMenuTitle();
		this.addDashboardOption("Dashboard");
		//        if (user.getName().equals("admin")){
		this.addMenuOption("Agents", "SignUpForm");
		this.addMenuOption("Wilaya", "wilayaForm");
		this.addMenuOption("Commune", "communeForm");
		this.addMenuOption("Zones", "ZoneForm");
		this.addMenuOption("Espece", "especeCultiveeForm");
		this.addMenuOption("Variétée", "varieteform");
		this.addMenuOption("Irrigation", "irrigation");
		this.addMenuOption("Espece", "especeCultiveeForm");
		this.addMenuOption("Variétée", "varieteform");
		this.addMenuOption("Agents", "SignUpForm");
		this.addMenuOption("Irrigation", "irrigation");
		

		//        }else{
		//            this.addMenuOption("Join Group", "ZoneForm");
		//            this.addMenuOption("Operations", "Operation");
		//        }
	}
}

