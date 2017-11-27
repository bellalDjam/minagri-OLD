package dz.minagri.stat.views;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AdminPView.VIEW_NAME)
public class AdminPView extends VerticalLayout implements View {

	public static final String VIEW_NAME = "adminpview";
    public AdminPView() {
        setMargin(true);
        addComponent(new Label("Admin view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // NOP
    }
}
