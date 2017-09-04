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

import dz.minagri.stat.comp.form.ExploitantForm;

/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = AdminView.VIEW_NAME)
public class AdminView extends VerticalLayout implements View{
	
	public static final String VIEW_NAME = "adminview";
	
	 void init() {
			removeAllComponents();
			ExploitantForm	 expF = new ExploitantForm();
			VerticalLayout root =new VerticalLayout();
			root.addComponent(expF);
			addComponent(root);
	    }


	/**
	 * 
	 */
	private void refresh() {
//		ExploitantForm	 expF = new ExploitantForm();
//		
//		VerticalLayout root =new VerticalLayout();
//		root.addComponent(new Label("Direction de statistique Agricoles R H Control"));
		
	}


	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
