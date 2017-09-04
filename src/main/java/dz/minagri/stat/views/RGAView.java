/**
 * 
 */
package dz.minagri.stat.views;

import java.util.EnumSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.annotations.PropertyId;
import com.vaadin.data.BeanValidationBinder;
import com.vaadin.data.Binder;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dz.minagri.stat.comp.form.ExploitantForm;
import dz.minagri.stat.comp.form.RGAForm;
import dz.minagri.stat.model.Exploitant;
import dz.minagri.stat.model.Exploitant.ExploitantStatus;
import dz.minagri.stat.model.Exploitant.Gender;
import dz.minagri.stat.repositories.ExploitantRepository;

/**
 * @author bellal djamel
 *
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = RGAView.VIEW_NAME)
public class RGAView extends VerticalLayout implements View{
	
	@Autowired
	private  ExploitantRepository expRepo;
//	@Autowired
//	private ExploitantForm expF; 
	
	public static final String VIEW_NAME = "rgaview";
	
	private Button  addExp ;
	
	@PostConstruct
	 void init() {
			removeAllComponents();
			refresh() ;
	    }


	/**
	 * 
	 */
	private void refresh() {
		 removeAllComponents();
	     RGAForm  rgaf= new RGAForm();
	     
	     addExp = new Button("New exploitant", new ClickListener() {
				
				@Override
				public void buttonClick(ClickEvent event) {
					// Create a sub-window and set the content
					final Window subWindow = new Window("Nouveau Exploitant");
					VerticalLayout subContent = new VerticalLayout();
					subContent.setWidth("90%");
					subContent.setMargin(true);
					subContent.setSpacing(true);
					subWindow.setContent(subContent);
					// Put some components in it
					// Center it in the browser window
					subWindow.center();

					HorizontalLayout h0 = new HorizontalLayout();
					h0.setSpacing(true);
					h0.setMargin(true);
					HorizontalLayout h1 = new HorizontalLayout();
					h1.setSpacing(true);
					h1.setMargin(true);
					HorizontalLayout h2 = new HorizontalLayout();
					h2.setSpacing(true);
					h2.setMargin(true);
					HorizontalLayout h3 = new HorizontalLayout();
					h3.setSpacing(true);
					h3.setMargin(true);
					HorizontalLayout h01 = new HorizontalLayout();
					h01.setSpacing(true);
					h01.setMargin(true);
					
					final TextField firstnameField = new TextField();
					firstnameField.setPlaceholder("Nom");
					final TextField lastnameField = new TextField();

					final TextField nationalNumber = new TextField();
					
					final NativeSelect<Gender> gender = new NativeSelect<Gender>();
					gender.setItems(EnumSet.allOf(Gender.class));
					final NativeSelect<ExploitantStatus> exploitantStatus = new NativeSelect<ExploitantStatus>();
					
					final DateField birthday = new DateField();
					
					final DateField registrationDate = new DateField();
					
					final TextField carteFellah = new TextField();
					
					Binder<Exploitant> expolBinder = new BeanValidationBinder<>(Exploitant.class);
					exploitantStatus.setItems(EnumSet.allOf(ExploitantStatus.class));
					final Button save ;
					
					final Button cancel ;
				
					subWindow.setModal(true);
					
					save = new Button("Sauver",new Button.ClickListener() {
					

						/**
						 * 
						 */
						private static final long serialVersionUID = 1L;

						@Override
						public void buttonClick(ClickEvent event) {
							if (firstnameField.getValue()!=null && lastnameField.getValue()!=null && birthday.getValue()!=null ) {
								Exploitant	exp=		new Exploitant();
								exp.setFirstname(firstnameField.getValue());
								exp.setLastname(lastnameField.getValue());
								exp.setBirthday(birthday.getValue());
								exp.setNationalNumber(nationalNumber.getValue());
								exp.setGender(gender.getValue());
								exp.setRegistrationDate(registrationDate.getValue());
								exp.setExploitantStatus(exploitantStatus.getValue());
								
								expRepo.save(exp);
								subWindow.close();
								
								refresh();
							}							
						}
					});
					cancel = new Button("Cancel",new Button.ClickListener() {
						
						/**
						 * 
						 */
						private static final long serialVersionUID = -7044268028795285553L;

						@Override
						public void buttonClick(ClickEvent event) {
							
							subWindow.close();
//							refresh();
						}
						
					});
					cancel.addStyleName("danger");
//					save.addStyleName(style);
					CssLayout actions = new CssLayout(save, cancel);
					h01.addComponent(actions);
					h0.addComponents(nationalNumber,lastnameField);
					h1.addComponents(firstnameField,gender);
					h2.addComponents(exploitantStatus,birthday);
					h3.addComponents(registrationDate);

					subContent.addComponents(h0,h1,h2,h3,h01);
					UI.getCurrent().addWindow(subWindow);
					
				}
				
			});
	     addComponents(rgaf,addExp);
	}

	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}

}
