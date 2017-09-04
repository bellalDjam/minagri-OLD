package dz.minagri.stat.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView
public class AdminPView extends VerticalLayout implements View {

    public AdminPView() {
        setMargin(true);
        addComponent(new Label("Admin view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // NOP
    }
}
