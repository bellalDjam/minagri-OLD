/**
 * 
 */
package dz.minagri.stat.views;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * @author bellal djamel
 *
 */

@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = InBoxView.VIEW_NAME)
public class InBoxView extends VerticalLayout implements View{

	private static final long serialVersionUID = 1543489686237634156L;
	
	
	public static final String VIEW_NAME = "inboxview";
	
	  public InBoxView() {
	        setMargin(true);
	        addComponent(new Label("Boit aux Messages "));
	    }
}
