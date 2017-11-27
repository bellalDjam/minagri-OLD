package dz.minagri.stat;

import java.util.Timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Label;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import dz.minagri.stat.comp.Menu;
import dz.minagri.stat.comp.form.LoginForm;
import dz.minagri.stat.views.AccessDeniedView;
import dz.minagri.stat.views.ErrorView;

@SpringUI
//No @Push annotation, we are going to enable it programmatically when the user logs on
@Theme(ValoTheme.THEME_NAME) // Looks nicer
//@SpringViewDisplay
public class SecuredUI extends UI implements ViewDisplay{

	private static final long serialVersionUID = 6127778860033857631L;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	BackendService backendService;
	@Autowired
	SpringViewProvider viewProvider;
	// @Autowired// private Panel viewProvider;
	@Autowired
	ErrorView errorView;
	
	String loggedInUser;
	
	@Autowired 
	SecurityService securityService;

	private Label timeAndUser;
	private Menu menu;
	private Timer timer;

	@Override
	protected void init(VaadinRequest request) {
		Responsive.makeResponsive(this);
		getPage().setTitle("MINAGRI-STAT");
		if (SecurityUtils.isLoggedIn()) {
			showMain();
		} else {
			showLogin();
		}
	}

	private void showLogin() {
		setContent(new LoginForm(this::login));
	}

	private void showMain() {
		addStyleName(ValoTheme.UI_WITH_MENU);

		VerticalLayout layout = new VerticalLayout();
		layout.setMargin(false);
		layout.setSpacing(false);
		//     layout.setSizeUndefined();
		
		layout.setStyleName("main-screen");
		layout.setSizeFull();

		MenuBar menuBar = new MenuBar();
//		menuBar.addStyleName(ValoTheme.MENU_PART);
        menuBar.setWidth(100.0f, Unit.PERCENTAGE);
        
        MenuBar.MenuItem adminItem = menuBar.addItem(SecurityContextHolder.getContext().getAuthentication().getName(), FontAwesome.COG, null);
        adminItem.addItem("IN-BOX", FontAwesome.GROUP, event -> getNavigator().navigateTo("wilayaForm") );
        adminItem.addItem("Roles", FontAwesome.GROUP, event -> getNavigator().navigateTo("role") );
        adminItem.addItem("Users", FontAwesome.USER, event -> getNavigator().navigateTo("account") );
        adminItem.addItem("Outils Admin", FontAwesome.GROUP, event -> getNavigator().navigateTo("admintools") );
        adminItem.addItem("TestForm", FontAwesome.USER, event -> getNavigator().navigateTo("adminview") );
        adminItem.addItem("Carte Fellah", FontAwesome.USER, event -> getNavigator().navigateTo("cartefellah") );
        adminItem.addItem("wilayaForm", FontAwesome.GROUP, event -> getNavigator().navigateTo("wilayaForm") );
        adminItem.addItem("Fellah", FontAwesome.USER, event -> getNavigator().navigateTo("userview") );
        MenuBar.MenuItem RGA = menuBar.addItem("RGA", FontAwesome.STICKY_NOTE, event -> getNavigator().navigateTo("rgaview"));
        MenuBar.MenuItem HELP = menuBar.addItem("Info Utiles", FontAwesome.STICKY_NOTE, event -> getNavigator().navigateTo("About"));
        menuBar.addItem("Logout", FontAwesome.SIGN_OUT, event -> logout() );
       
        layout.addComponent(menuBar);

	     Panel viewContainer = new Panel();
	     viewContainer.setSizeFull();
	     layout.addComponent(viewContainer);
	     layout.setExpandRatio(viewContainer, 1.0f);
	
	     setContent(layout);
	     setErrorHandler(this::handleError);
	
	     Navigator navigator = new Navigator(this, viewContainer);
	     navigator.addProvider(viewProvider);
	     navigator.setErrorView(errorView);
	     viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
	    
	     
	     // Fire up a timer to demonstrate server push. Do NOT use timers in real-world applications, use a thread pool.
	     timer = new Timer();
//	     timer.schedule(new TimerTask() {
//	         @Override
//	         public void run() {
//	             updateTimeAndUser();
//	         }
//	     }, 1000L, 1000L);
	 }
	
	 @Override
	 public void detach() {
//	     timer.cancel();
	     super.detach();
	 }
	
//	 private void updateTimeAndUser() {
//	     // Demonstrate that server push works and that you can even access the security context from within the
//	     // access(...) method.
//	     access(() -> timeAndUser.setValue(String.format("time  %s and user is %s",
//	         LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")),
//	         SecurityContextHolder.getContext().getAuthentication().getName())));
//	 }

	private boolean login(String username, String password) {
		try {
			Authentication token = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			// Reinitialize the session to protect against session fixation attacks. This does not work
			// with websocket communication.
			VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
			SecurityContextHolder.getContext().setAuthentication(token);
			// Now when the session is reinitialized, we can enable websocket communication. Or we could have just
			// used WEBSOCKET_XHR and skipped this step completely.
			getPushConfiguration().setTransport(Transport.WEBSOCKET);
			getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
			// Show the main UI
			showMain();
			return true;
		} catch (AuthenticationException ex) {
			return false;
		}
	}

	private void logout() {
		getPage().reload();
		getSession().close();
	}

	private void handleError(com.vaadin.server.ErrorEvent event) {
		Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
		if (t instanceof AccessDeniedException) {
			Notification.show("You do not have permission to perform this operation",
					Notification.Type.WARNING_MESSAGE);
		} else {
			DefaultErrorHandler.doDefault(event);
		}
	}

	/* (non-Javadoc)
	 * @see com.vaadin.navigator.ViewDisplay#showView(com.vaadin.navigator.View)
	 */
	@Override
	public void showView(View view) {
		// TODO Auto-generated method stub

	}
}
