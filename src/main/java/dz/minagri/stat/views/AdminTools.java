/**
 * 
 */
package dz.minagri.stat.views;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author bellal djamel
 *
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AdminTools.VIEW_NAME)
public class AdminTools extends VerticalLayout implements View{
	
	public static final String VIEW_NAME = "admintools";
	
	 void init() {
			removeAllComponents();
			VerticalLayout root =new VerticalLayout();
			root.addComponent(new Label("Direction de statistique Agricoles R H Control"));
			addComponent(root);
			addComponent(new Label("This is the default view"));
	    }


	/**
	 * 
	 */
	private void refresh() {
		VerticalLayout root =new VerticalLayout();
		root.addComponent(new Label("Direction de statistique Agricoles R H Control"));
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
