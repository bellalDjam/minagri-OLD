/**
 * 
 */
package dz.minagri.stat.views;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;

import com.vaadin.data.Binder;
import com.vaadin.data.BinderValidationStatus;
import com.vaadin.data.ValidationException;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.shared.ui.ValueChangeMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.comp.form.SignUpForm;
import dz.minagri.stat.comp.form.SignUpForm.ChangeHandler;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.MoyenContact;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.RoleRepository;

/**
 * @author bellal djamel
 *
 */

@SpringView(name = AccountView.VIEW_NAME)
//@RolesAllowed("ROLE_ADMIN")
public class AccountView extends HorizontalLayout implements View {

	private static final long serialVersionUID = -2826845349943378269L;

	public static final String VIEW_NAME = "account";

	@Autowired
	SecurityService securityService;
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private SignUpForm signUpForm;
	@Autowired
	public AccountView(SecurityService securityService) {
		this.securityService = securityService;
	}
	private Binder<Account> binder = new Binder<>(Account.class);
//	@Autowired
	private Account account;


	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	/* Action buttons */
	private HorizontalLayout actions = new HorizontalLayout();
	private CssLayout headactions = new CssLayout();

	private Grid<Account> accountGrid = new Grid<>(Account.class);
	private CheckBoxGroup<Role> rolCheB = new CheckBoxGroup<>("Pouvoir");

	private Button save = new Button("Sauver");
	private Button cancel = new Button("Annuler");
	private Button delete = new Button("Suprimer");
	private Button addNewAccounBtn = new Button("Nouveau Compte");
	private Button clearBtn = new Button(FontAwesome.TIMES);

	private TextField filter = new TextField("Recherche  :  ");
	private TextField username = new TextField("username");
	private TextField password = new TextField("password");
	private TextField firstName = new TextField("firstName");
	private TextField lastName = new TextField("lastName");
	private TextField shortInfo = new TextField("shortInfo");
	private TextField fax = new TextField("fax");
	private TextField tel = new TextField("tel");
	private TextField email = new TextField("email");
	private TextField gsm = new TextField("gsm");
	
	private DateField birthDate = new DateField("Né(e) Le");
	
	private NativeSelect<TypeAccount> typeAccount = new NativeSelect<>("typeAccount");
	private NativeSelect<Gender> gender = new NativeSelect<>("gender");
	
	private Label lblTitle;

	@PostConstruct
	void init() {
		Responsive.makeResponsive();

		setMargin(false);
		setSpacing(false);
		setSizeFull();
		
		HorizontalLayout hl1 = new HorizontalLayout(username, password, typeAccount);
		HorizontalLayout hl2 = new HorizontalLayout(firstName, lastName);
		HorizontalLayout hl23 = new HorizontalLayout(birthDate, gender);
		HorizontalLayout hl3 = new HorizontalLayout(gsm, tel, fax);
		HorizontalLayout hl4 = new HorizontalLayout(email);
		HorizontalLayout hl5 = new HorizontalLayout(rolCheB,shortInfo);
		
		
		fLayout = new HorizontalLayout();
		fLayout.setResponsive(true);
		fLayout.setMargin(false);
		fLayout.setSizeFull();
		fLayout.addStyleName("light");

		hlasearch.setSpacing(true);
		hlasearch.setMargin(true);
		hlasearch.setResponsive(true);

		//UI COMPONENTS
		lblTitle = new Label("Enregistrement Agent");
		lblTitle.addStyleName("h4");
		//        addComponent(lblTitle);
		
		rolCheB.setItems(roleRepo.findAll());
		rolCheB.deselectAll();
		
		filter.setPlaceholder("Filter by last name");
		filter.addValueChangeListener(e -> updateAccountList());
		filter.setValueChangeMode(ValueChangeMode.LAZY);

		clearBtn.setDescription("Clear the current filter");
		clearBtn.addClickListener(e -> filter.clear());
		addNewAccounBtn.addClickListener(e -> {
            accountGrid.asSingleSelect().clear();
            password.setVisible(true);
            setAccount(new Account());
        });
		
		
		headactions.addComponents(filter,clearBtn);
		headactions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		hlasearch.addComponents(headactions, addNewAccounBtn);
		hlasearch.setComponentAlignment(addNewAccounBtn, Alignment.TOP_RIGHT);

		updateAccountList();
		
		vlGrid.setSizeFull();
		vlGrid.addComponents(hlasearch,accountGrid);
		birthDate.setValue(LocalDate.now());
		birthDate.setLocale(getLocale());
		//Set up form
		typeAccount.setItems(EnumSet.allOf(TypeAccount.class));
		gender.setItems(EnumSet.allOf(Gender.class));
		
		actions.addComponentsAndExpand(save, cancel, delete);
		vlform.addComponents(hl1, hl2, hl23,hl3, hl4, hl5,actions);
		fLayout.addComponentsAndExpand(vlGrid,vlform);
		fLayout.setExpandRatio(vlGrid,1.0f);
		

		actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
		save.addClickListener(e -> {
			try {
				this.save();
			} catch (ValidationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		delete.addClickListener(e -> this.delete());
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.addClickListener(e -> this.cancel());
		cancel.setStyleName(ValoTheme.BUTTON_QUIET);
		
		addComponent(fLayout);
		
		binder.bindInstanceFields(this);
		
		vlform.setVisible(false);
		password.setVisible(false);
		
		 accountGrid.asSingleSelect().addValueChangeListener(event -> {
	            if (event.getValue() == null) {
	            	vlform.setVisible(false);
	            } else {
	            setAccount(event.getValue());
	            password.setVisible(false);
	            }
	        });
	}
	
	/**
	 * @param value
	 * @return
	 */
	private void updateAccountList() {
		if (StringUtils.isEmpty(filter.getValue())) {
			accountGrid.setItems(accountRepo.findAll());
		}
		else {
			accountGrid.setItems(accountRepo.findByUsernameContaining(filter.getValue()));
		}
		accountGrid.setSizeFull();
		accountGrid.setResponsive(true);
		accountGrid.setCaption("List des Agent");
		accountGrid.setSelectionMode(SelectionMode.SINGLE);
		accountGrid.setColumns("username", "firstName", "lastName", "enabled", "roles","typeAccount", "createdBy", "createdAt",
				"shortInfo");
	}
	
//	private void updateGrid(Grid<Account> accountGrid, String filterText) {
//		accountGrid.setSizeFull();
//		accountGrid.setResponsive(true);
//		accountGrid.setCaption("List des Agent");
//		updateAccountList(filterText);
//		accountGrid.setSelectionMode(SelectionMode.SINGLE);
//		accountGrid.setColumns("username", "firstName", "lastName", "enabled", "roles","typeAccount", "createdBy", "createdAt",
//				"shortInfo");
//	}
	/**
	 * @param account
	 */
	private void setAccount(Account account) {
		this.account = account;
		binder.setBean(account);
		// Show delete button for only customers already in the database
				delete.setVisible((account.getId()!=null));
				vlform.setVisible(true);
				username.selectAll();
	}

	/**
	 * @param event
	 * @return
	 */
	private void delete() {
		account.getRoles().clear();
		accountRepo.delete(account);
		accountGrid.setItems(accountRepo.findAll());
		vlform.setVisible(false);
	}

	/**
	 * @param event
	 * @return
	 */
	private void cancel() {
		accountGrid.setItems(accountRepo.findAll());
		vlform.setVisible(false);
	}

	/**
	 * @param event
	 * @return
	 * @throws ValidationException 
	 */
	private void save() throws ValidationException {
		BinderValidationStatus<Account> status = binder.validate();

		Account account = new Account();
		if (binder.writeBeanIfValid(account)) {
			account.setCreatedAt(LocalDate.now());
			account.setRegistrationDate(LocalDate.now());
			account.setCreatedBy(SecurityContextHolder.getContext().getAuthentication().getName());
//			account.setMoyenContact(new MoyenContact(fax.getValue(), tel.getValue()
//					, gsm.getValue(), email.getValue()));
			
			 List<Role> rolesToGrant = new ArrayList<>();
			rolCheB.getSelectedItems().forEach( role -> rolesToGrant.add((Role)role));
			
			account = securityService.createAccount(account);
             Notification.show("Created");
			
						for(Role rol : rolesToGrant) {
							account.getRoles().add(rol);
			}
						accountRepo.save(account);
//						binder.writeBean(account);
						rolCheB.clear();
			            if (status.hasErrors()) {
			            	  Notification.show("Validation error count: "
			            	    + status.getValidationErrors().size());
			            	}
			accountGrid.setItems(accountRepo.findAll());
			vlform.setVisible(false);
			 Notification.show("Created");
			 
		}
//		binder.

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
