/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.SecurityService;
import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.MoyenContact;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.RoleRepository;
import dz.minagri.stat.views.AdminView;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class AccountForm extends FormLayout {

	private static final long serialVersionUID = -5630202457474863365L;

	private TextField username = new TextField("username");
	private TextField password = new TextField("password");
	private TextField firstName = new TextField("firstName");
	private TextField lastName = new TextField("lastName");
	private TextField shortInfo = new TextField("shortInfo");
	private TextField fax = new TextField("fax");
	private TextField tel = new TextField("tel");
	private TextField email = new TextField("email");
	private TextField gsm = new TextField("gsm");

	private TextField filter = new TextField("Filtre  :  " + " ");
	
	private Label rolLab = new Label();
	private Button save = new Button("Enregistrer");
	private Button delete = new Button("Suprimer");
	private Button cancel = new Button("Annuler");
	
	private DateField birthDate = new DateField("Né(e) Le");
	private DateField registrationDate = new DateField("Enregistré Le");
	
	private Grid<Account> grid = new Grid<>(Account.class);
	private NativeSelect<TypeAccount> nsTypeAccount = new NativeSelect<>("typeAccount");
	private NativeSelect<Gender> nsGender = new NativeSelect<>("gender");
	
	private List<Role> roles;

	private Account account;
	private Account manager;
	private Role role;
	private MoyenContact mc;
	
	private final RoleRepository roleRepository;
	private final AccountRepository accountRepository;
	
	private Binder<Account> binder = new Binder<>(Account.class);
	private SecurityService securityService;
	private AdminView abLayout;

	public AccountForm(AdminView abLayout, AccountRepository accountRepository
			, RoleRepository roleRepository,SecurityService securityService
			,Binder<Account> binder) {
		this.binder = binder;
		this.abLayout = abLayout;
		this.accountRepository = accountRepository;
		this.securityService =securityService;
		this.roleRepository = roleRepository;

		
		Responsive.makeResponsive();
		nsTypeAccount.setItems(TypeAccount.values());
		nsGender.setItems(Gender.values());

		save.setStyleName(ValoTheme.BUTTON_PRIMARY);
		save.setClickShortcut(KeyCode.ENTER);

//		rolLab.setCaption(caption);
		setSizeUndefined();
		
		HorizontalLayout hl1 = new HorizontalLayout(username, password, nsTypeAccount);
		HorizontalLayout hl2 = new HorizontalLayout(firstName, lastName);
		HorizontalLayout hl23 = new HorizontalLayout(birthDate, nsGender);
		HorizontalLayout hl3 = new HorizontalLayout(gsm, tel, fax);
//		HorizontalLayout hl33 = new HorizontalLayout(gsm, tel, fax);
		HorizontalLayout hl4 = new HorizontalLayout(email,rolLab);
		HorizontalLayout hl5 = new HorizontalLayout( shortInfo);
		HorizontalLayout buttons = new HorizontalLayout(save, delete,cancel);
		addComponents(hl1, hl2, hl23,hl3, hl4, hl5,buttons);
		binder.forField(email).withValidator(new EmailValidator("SVP Introduire un Email valable"));
		 addComponent(buttons);
		binder.bindInstanceFields(this);
		
		password.setVisible(false);
		save.addClickListener(e -> this.save());
		delete.addClickListener(e -> this.delete());
		delete.setStyleName(ValoTheme.BUTTON_DANGER);
		cancel.addClickListener(e -> this.cancel());
		cancel.setStyleName(ValoTheme.BUTTON_QUIET);
	}

	public void setAccount(Account account) {
		this.account = account;

		roles = account.getRoles();
		rolLab.setCaption(roles.toString());
		
		// Showaccount.isPersisted() delete button for only customers already in the database
		if(account.isPersisted()) {
			List<Role> role=new ArrayList<>();
			role.add(securityService.createRole("ROLE_USER", "Premier inscription"));
			account.setRoles(role);
			delete.setVisible(account.isPersisted());
		}
		binder.setBean(account);
		
		setVisible(true);
		
		username.selectAll();
	}

	private void delete() {
		accountRepository.delete(account);
		abLayout.updateList();
		setVisible(false);
	}

	private void save() {
//		account.setRoles(roles);
		accountRepository.save(account);
		abLayout.updateList();
		setVisible(false);
	}
	private void cancel() {
		abLayout.updateList();
		setVisible(false);
	}

	/**
	 * 
	 */
	public void showPassTextF(boolean hidePw) {
		if (!hidePw ) {
			password.setVisible(false);
		}else {
			password.setVisible(true);
		}
		
	}
	
}
