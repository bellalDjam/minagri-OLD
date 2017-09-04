/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.repositories.CarteFellahRepository;
import dz.minagri.stat.views.CarteFellahView;

/**
 * @author bellal djamel
 *
 */
@UIScope
@SpringComponent
//
//@SpringComponent
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class FellahForm extends VerticalLayout implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7153236350388368648L;

	@Autowired
	CarteFellahRepository carteFellahRepo;
//	@Autowired
//	CarteFellah ca;
	FormLayout form;
	private TextField zone;
	private TextField numS12;
	

	private Button btnConfirm;
	private Button btnCancel;
	private CarteFellahView carteFellahView;

	public FellahForm() {
		// initial setup
		setSizeUndefined();
		setSpacing(true);
		setMargin(true);

		form = new FormLayout();
		form.setMargin(false);
		form.setWidth("900");
		form.addStyleName("light");
		addComponent(form);

		zone = new TextField("Ajout ZONE");
		// zone.setRequired(true);
		form.addComponent(zone);

		numS12 = new TextField("Ajout Num S-12");
		// numS12.setRequired(true);
		form.addComponent(numS12);

		final DateField rDate = new DateField();
//		rDate.setValue(new Date());
		
		form.addComponentAsFirst(rDate);

		btnConfirm = new Button("Confirm");
		btnCancel = new Button("Cancel");

//		btnConfirm.addClickListener(new Button.ClickListener() {

//			@Override
//			public void buttonClick(ClickEvent event) {
//				if (zone.getValue()!=null && numS12.getValue()!=null &&
//						rDate.getValue()!=null) {
//					CarteFellah	ca=		new CarteFellah();
//					ca.setNumS12(numS12.getValue());
//					ca.setZone(zone.getValue());
//					ca.setRegistrationDate(rDate.getValue());
//					System.out.println(ca+"++++++++++++++++++++++");
//					System.out.println(carteFellahRepo+"________________");
//					carteFellahRepo.save(ca);
//				}

//			}
//		});

		HorizontalLayout btnMenu = new HorizontalLayout(btnConfirm,btnCancel);
		btnMenu.setSpacing(true);

		form.addComponent(btnMenu);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.
	 * ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeListener.ViewChangeEvent event) {

	}

}
