/**
 * 
 */
package dz.minagri.stat.views;



import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

import dz.minagri.stat.model.CarteFellah;
import dz.minagri.stat.repositories.CarteFellahRepository;

/**
 * @author bellal djamel
 *
 */
//@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@SpringView(name = CarteFellahView.VIEW_NAME)
public class CarteFellahView extends VerticalLayout implements View {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3619365588168665581L;
	
	public static final String VIEW_NAME = "cartefellah";
	@Autowired
	CarteFellahRepository carteFellahRepository;

	 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@PostConstruct
    void init() {
		removeAllComponents();
        refresh();
    }
//	 @SuppressWarnings("rawtypes")
	private void refresh() {
		 
		 removeAllComponents();
		
		 Grid<CarteFellah> grid = new Grid<>(CarteFellah.class);
		 updateGrid(grid);
		 VerticalLayout blayout = new VerticalLayout();
		 
		 //Button Add CarteFellah
		 final Button addNewCf= new Button("Carte Fellah",new Button.ClickListener() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = -3831247956259012521L;

			@Override
			public void buttonClick(ClickEvent event) {
				// Create a sub-window and set the content
				final Window subWindow = new Window("Enregistrer une Carte Fellah");
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
				HorizontalLayout h01 = new HorizontalLayout();
				h01.setSpacing(true);
				h01.setMargin(true);
				
				final TextField zone= new TextField("ZONE");
				
				final TextField numS12= new TextField("Num S12");
				final DateField  rDate = new DateField ("date d'enregistrement");
				rDate.setValue(LocalDate.now());
				rDate.setLocale(getLocale());
				final Button btnConfirm;
				final Button btnCancel;
				subWindow.setModal(true);
				
				btnConfirm = new Button("Sauver",new Button.ClickListener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = 7296499167824024636L;

					@Override
					public void buttonClick(ClickEvent event) {
						if (zone.getValue()!=null && numS12.getValue()!=null ) {
							CarteFellah	ca=		new CarteFellah();
							ca.setNumS12(numS12.getValue());
							ca.setZone(zone.getValue());
							ca.setRegistrationDate(rDate.getValue());
							
							carteFellahRepository.save(ca);
						}
						subWindow.close();
						removeAllComponents();
						updateGrid(grid);
						refresh();
						
					}
				});
				btnCancel = new Button("Cancel",new Button.ClickListener() {
					
					/**
					 * 
					 */
					private static final long serialVersionUID = -7044268028795285553L;

					@Override
					public void buttonClick(ClickEvent event) {
						
						subWindow.close();
						removeAllComponents();
						updateGrid(grid);
						refresh();
					}
				});
				h01.addComponents(rDate,btnConfirm,btnCancel);
				h0.addComponents(numS12,zone);

				subContent.addComponents(h0,h01);
				UI.getCurrent().addWindow(subWindow);
				
			}
		});
		 blayout.addComponents(addNewCf,grid);
		 blayout.setSizeFull();
		 blayout.setMargin(true);
	     blayout.setSpacing(false);
	     
	        addComponent(blayout);
		
	    }
	/**
	 * @param grid
	 */
	private void updateGrid(Grid<CarteFellah> grid) {
		grid.setSizeFull();
		 grid.setItems(carteFellahRepository.findAll());
		 grid.setSelectionMode(SelectionMode.SINGLE);
		 grid.setColumns("numS12", "zone", "registrationDate");
	}


	/* (non-Javadoc)
	 * @see com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void enter(ViewChangeEvent event) {
		
	}

}
