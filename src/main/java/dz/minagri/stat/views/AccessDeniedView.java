package dz.minagri.stat.views;

import org.springframework.stereotype.Component;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Component // No SpringView annotation because this view can not be navigated to
@UIScope
public class AccessDeniedView extends VerticalLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3348842858614746336L;

	public AccessDeniedView() {
        setMargin(true);
        Label lbl = new Label("PAS D'AUTORIZATION POUR CETTE PAGE Consulter votre Administrateur");
        lbl.addStyleName(ValoTheme.LABEL_FAILURE);
        lbl.setSizeUndefined();
        addComponent(lbl);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}
