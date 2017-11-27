package dz.minagri.stat.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = "userview") // Root view
public class UserView extends VerticalLayout implements View {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1145577607570432454L;

	private Button addNeBtn = new Button("Nouveau Adresse");
	private Button addIrregBtn = new Button("Formulaire Irregation");
	private HorizontalLayout actions = new HorizontalLayout();
	
	public UserView() {
        setMargin(true);
        addNeBtn.addClickListener(e-> UI.getCurrent().getNavigator().navigateTo("address"));
        addIrregBtn.addClickListener(e-> UI.getCurrent().getNavigator().navigateTo("irrigation"));
        actions.addComponents(addNeBtn,addIrregBtn);
        addComponent(actions);
        addComponent(new Label("User view"));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // NOP
    }
}
