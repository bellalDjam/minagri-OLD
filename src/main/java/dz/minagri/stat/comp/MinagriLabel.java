/**
 * 
 */
package dz.minagri.stat.comp;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.server.ExternalResource;
import com.vaadin.server.Responsive;
import com.vaadin.server.ThemeResource;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * @author bellal djamel
 *
 */
@SpringComponent
@UIScope
public class MinagriLabel {
	
	private Timer timer;
	private Label timeAndUser;

	public Label LabelHeader(String str, String stylName, String stylsize) {
		Label lab = new Label(str);
		lab.setResponsive(true);
		// will change late
		lab.addStyleName(stylName);
		lab.addStyleName(stylsize);
		lab.setSizeUndefined();
		Responsive.makeResponsive(lab);
		return lab;
	}

	public HorizontalLayout headerFactory() {
		
		HorizontalLayout up = new HorizontalLayout();
		HorizontalLayout upLogo = new HorizontalLayout();
		HorizontalLayout upperSection = new HorizontalLayout();
		VerticalLayout vLayout = new VerticalLayout();
	
		vLayout.setSpacing(false);
		vLayout.setWidth("100%");
		vLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		vLayout.setMargin(false);
		vLayout.addComponent(LabelHeader("République Algérienne Démocratique et Populaire", "colored", "h5"));
		vLayout.addComponent(LabelHeader("Ministère de l’Agriculture , du Développement " + "Rural et de la Pêche",
				"colored", "h5"));
		vLayout.addComponent(LabelHeader(
				"Direction des statistiques Agricoles et des " + "Systémes d'Information (DSASI)", "colored", "h5"));
		up.addComponent(vLayout);
		up.setWidth("100%");
		up.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		Image image = new Image(null, new ThemeResource("img/logo.png"));
		upLogo.addComponent(image);
		upLogo.setDefaultComponentAlignment(Alignment.TOP_RIGHT);

		up.setSpacing(false);
		upLogo.setSpacing(false);
		upperSection.setWidth("100%");
		upperSection.setSpacing(false);
		upperSection.setMargin(false);

		
		upperSection.addComponents(up, upLogo);
		upperSection.setExpandRatio(up, 1.0f);
		return upperSection;
	}
	
	public Label userAndTimeLable() {
		
		 timer = new Timer();
	     timer.schedule(new TimerTask() {
	         @Override
	         public void run() {
	             updateTimeAndUser(timeAndUser);
	         }
	     }, 1000L, 1000L);
	     return timeAndUser;
	}
	 private void updateTimeAndUser(Label lab) {
	     // Demonstrate that server push works and that you can even access the security context from within the
	     // access(...) method.
		 lab.setValue(String.format("time  %s and user is %s",
				 ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-mm-dd")),
	         SecurityContextHolder.getContext().getAuthentication().getName()));
	 }
}
