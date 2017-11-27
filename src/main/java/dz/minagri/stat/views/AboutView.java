package dz.minagri.stat.views;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.Version;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.CustomLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AboutView.VIEW_NAME)
public class AboutView extends VerticalLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1490393649716159615L;
	public static final String VIEW_NAME = "About";
	Authentication user =SecurityContextHolder.getContext().getAuthentication();
//	System.out.println(user.getPrincipal());
    public AboutView() {
        CustomLayout aboutContent = new CustomLayout("aboutview");
        aboutContent.setStyleName("Info-content");

        // you can add Vaadin components in predefined slots in the custom
        // layout
        aboutContent.addComponent(
                new Label(FontAwesome.INFO_CIRCLE.getHtml()
                        + " Regroupe les infos utiles pour l'utilisation de l'application "+user.getPrincipal()+"user.getPrincipal()"
                        + Version.getFullVersion(), ContentMode.HTML), "info");

        setSizeFull();
        setStyleName("about-view");
        addComponent(aboutContent);
        setComponentAlignment(aboutContent, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void enter(ViewChangeEvent event) {
    }

}
