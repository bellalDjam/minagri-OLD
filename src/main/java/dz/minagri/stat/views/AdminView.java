/**
 * 
 */
package dz.minagri.stat.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.comp.form.ExploitantForm;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.RoleRepository;

/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AdminView.VIEW_NAME)
public class AdminView extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3097265203299694779L;
	public static final String VIEW_NAME = "adminview";

	@Autowired
	private SecurityService securityService;
	@Autowired
	private AccountRepository accRepo;
	@Autowired
	private RoleRepository rolRepo;

	private Grid<Account> grid = new Grid<>(Account.class);
	private Grid<Role> gridR = new Grid<>(Role.class);
	
//	private AccountForm accountForm = new AccountForm(this,accRepo, rolRepo);
//	private ComboBox<Role> cbRoles = new ComboBox<Role>();
	private TextField filterText = new TextField();
//	private AccountForm form = new AccountForm(this,accRepo,rolRepo,securityService);
	private Button addRoleBtn= new Button("Selectioner Roles");
	private Button newRoleBtn= new Button("Ajout Roles");
	@PostConstruct
	void init() {
		Responsive.makeResponsive();
	
		final VerticalLayout layout = new VerticalLayout();

		filterText.setPlaceholder("Recherche par username...");
		filterText.addValueChangeListener(e -> updateList());
		filterText.setValueChangeMode(ValueChangeMode.LAZY);
		Button addCustomerBtn = new Button("Nouveau Utilisateur");
		addCustomerBtn.addClickListener(e -> {
		 
			grid.asSingleSelect().clear();
//			cbRoles.setItemCaptionGenerator(p -> p.getAuthority());
//			cbRoles.setItems(rolRepo.findAll());
//			
////			cbRoles.addSelectionListener(e-> dosomthing());
//			form.addComponentAsFirst(cbRoles);
//			form.showPassTextF(true);
//		    form.setAccount(new Account());
		});
		
		CssLayout filtering = new CssLayout();
		
		Button clearFilterTextBtn = new Button(FontAwesome.TIMES);
		clearFilterTextBtn.setDescription("Effacer la recherche");
		clearFilterTextBtn.addClickListener(e -> filterText.clear());
		filtering.addComponents(filterText, clearFilterTextBtn);
		filtering.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		
		addRoleBtn.setDescription("Pour Selectioner un ou plusieurs"
				+ " Roles Cocher sur vos besoins, Sinon Ajouter un role Specefique");
		addRoleBtn.addClickListener(e-> {
			grid.setVisible(false);
			gridR.setVisible(true);
			gridR.setSelectionMode(SelectionMode.MULTI);
			gridR.setItems(rolRepo.findAll());
//			form.setVisible(true);
			
		});
		newRoleBtn.setDescription("Rajouter un Role selement s'il n'est dans la table");
		newRoleBtn.addClickListener(e-> {
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
			HorizontalLayout h = new HorizontalLayout();
			h.setSpacing(true);
			h.setMargin(true);
			HorizontalLayout h01 = new HorizontalLayout();
			h01.setSpacing(true);
			h01.setMargin(true);
			
			final TextField authority= new TextField();
			authority.setPlaceholder("Authorité");
			final TextField description= new TextField();
			description.setPlaceholder("Description");
			
			final Button btnConfirm ;
			final Button btnCancel;
			
//			final DateField  expirDate = new DateField ("Expire Le  :");
//			expirDate.setValue(LocalDate.now());
//			expirDate.setLocale(getLocale());
//			final DateField  effDate = new DateField ("Effectife jusqu'à :");
//			effDate.setValue(LocalDate.now());
//			effDate.setLocale(getLocale());
			
			btnConfirm = new Button("Sauver",new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -3467346755242247413L;

				@Override
				public void buttonClick(ClickEvent event) {
					if (authority.getValue()!=null && description.getValue()!=null ) {
						securityService.createRole(authority.getValue(), description.getValue());

					}
					subWindow.close();
					updateGridR(gridR);

				}
			});
			btnCancel = new Button("Cancel",new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = -1869481418782643154L;

				@Override
				public void buttonClick(ClickEvent event) {

					subWindow.close();
					updateGridR(gridR);
				}
			});
			h01.addComponents(btnConfirm,btnCancel);
			h0.addComponents(authority,description);

			subContent.addComponents(h0,h,h01);
			UI.getCurrent().addWindow(subWindow);
			
		});
		
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
		
		
		
		HorizontalLayout toolbar = new HorizontalLayout(filtering, addCustomerBtn,addRoleBtn,newRoleBtn);
		// add Grid to the layout
		
//		HorizontalLayout main = new HorizontalLayout(gridR,grid, form);
		
//		main.setSizeFull();
		grid.setSizeFull();
		grid.setResponsive(true);
		
//		form.setVisible(false);
		gridR.setVisible(false);
		
		grid.asSingleSelect().addValueChangeListener(event -> {
		    if (event.getValue() == null) {
		    	
//		        form.setVisible(false);
		        
		    } else {
//		    	form.showPassTextF(false);
//		        form.setAccount(event.getValue());
		    }
		});
		
//		main.setExpandRatio(grid, 1);

//		layout.addComponents(toolbar, main);

		// fetch list of Customers from service and assign it to Grid
		updateList();
		addComponent(layout);
	}

	/**
	 * @param gridR2
	 */
	protected void updateGridR(Grid<Role> grid) {
		grid.setSizeFull();
		grid.setItems(rolRepo.findAll());
		grid.setColumns("authority", "description");
		
	}

	/**
	 * @return
	 */
	private Object dosomthing() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 */
	public void updateList() {
		//TODO extend the search to other String properties
		// Proceed to define Columns By set Columns List<Account> directs will cause
				// LyzInitEx
				// grid.setItems(accountL);
		if(filterText.getValue().isEmpty()) {
			grid.setItems(accRepo.findAll());
		}else {
			grid.setItems(accRepo.findByUsernameContaining(filterText.getValue()));
		}
		
		grid.setColumns("username", "firstName", "username", "enabled", "createdBy", "createdAt", "shortInfo",
				"birthDate", "gender");
	}

	private void refresh() {
		ExploitantForm expF = new ExploitantForm();

		VerticalLayout root = new VerticalLayout();
		root.addComponent(new Label("Direction de statistique Agricoles R H Control"));
		addComponent(root);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}
}
