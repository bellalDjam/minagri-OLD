/**
 * 
 */
package dz.minagri.stat;

import java.util.List;

import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.server.Responsive;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dz.minagri.stat.enumeration.Gender;
import dz.minagri.stat.enumeration.TypeAccount;
import dz.minagri.stat.model.Account;
import dz.minagri.stat.model.MoyenContact;
import dz.minagri.stat.model.Personne;
import dz.minagri.stat.model.Role;
import dz.minagri.stat.repositories.AccountRepository;
import dz.minagri.stat.repositories.PersonneRepository;
import dz.minagri.stat.repositories.RoleRepository;
import dz.minagri.stat.views.AdminView;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class AccountFFForm extends HorizontalLayout implements View{


	private static final long serialVersionUID = 5616814356623063149L;
	public static final String VIEW_NAME = "AccountFFForm";
	
	private MoyenContact moyenContact; 
	private Binder<Account> binder = new Binder<>(Account.class);
	private AdminView abLayout;
//
//	private final PersonneRepository repository; 
//	private final SecurityService securityService;
	
	private VerticalLayout vlGrid = new VerticalLayout();
	private VerticalLayout vlform = new VerticalLayout();
	private HorizontalLayout hlasearch = new HorizontalLayout();
	private HorizontalLayout fLayout ;
	
	private TextField username = new TextField("username");
	private TextField password = new TextField("password");
//	private TextField rpassword = new TextField("rpassword");
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
	
	private NativeSelect<TypeAccount> nsTypeAccount = new NativeSelect<>("typeAccount");
	private NativeSelect<Gender> nsGender = new NativeSelect<>("gender");
	private Account manager;
	private List<Role> roles;
//	private final RoleRepository roleRepository;
//	private final AccountRepository accountRepository;
	private Account account;
	private Role role;
	/* Action buttons */
	private CssLayout actions = new CssLayout();
	private CssLayout headactions = new CssLayout();
	
//	public AccountFFForm() {
////		this.abLayout = abLayout;
//////		this.binder = binder;
////		this.securityService = securityService;
//		
//		
//		
//		//INITIAL SETUP
//		Responsive.makeResponsive();
//		setSpacing(false);
//		setMargin(false);
//		setSizeUndefined();
//		
//		fLayout = new HorizontalLayout();
//		
//		
//		
//	}
}
