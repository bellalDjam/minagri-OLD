/**
 * 
 */
package dz.minagri.stat.comp.form;

import com.vaadin.ui.ComboBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.TextField;

import dz.minagri.stat.model.Commune;
import dz.minagri.stat.model.Exploitation;
import dz.minagri.stat.model.Wilaya;

/**
 * @author bellal djamel
 *   
 */
public class RGAForm extends GridLayout{
	private TextField surface = new TextField();
	private TextField coorGPS = new TextField();
	private  ComboBox<Wilaya> wcomb =new ComboBox<Wilaya>();
	private  ComboBox<Commune> cComb =new ComboBox<Commune>();
	private  ComboBox<Exploitation> lieudit =new ComboBox<Exploitation>();
	private  ComboBox<Exploitation> exploitComb =new ComboBox<Exploitation>();

	
	public RGAForm() {
		super(3,8); 
		wcomb.setPlaceholder("Wilaya");
		cComb.setPlaceholder("Commune");
		exploitComb.setPlaceholder("Exploitant");
		lieudit.setPlaceholder("Lieu dit");
		coorGPS.setPlaceholder("G P S");
		surface.setPlaceholder("Surface");
//		addExp.setP
		
		addComponent(wcomb);
		addComponent(cComb);
		addComponent(exploitComb);
		addComponent(lieudit);
		addComponent(coorGPS);
		addComponent(surface);
		
		
		
	}

}
//Creates a new combobox using an existing container
//Collection<CountryData> countriesData = ExampleUtil.getCountriesData();
//
//sample = new ComboBox<>("Select your country", countriesData);
//
//sample.setPlaceholder("No country selected");
//
//// Sets the combobox to show a certain property as the item caption
//sample.setItemCaptionGenerator(CountryData::getFullName);
//
//// Sets the icon to use with the items
//sample.setItemIconGenerator(CountryData::getFlag);
//
//// Disallow null selections
//sample.setEmptySelectionAllowed(false);
//
//// Set full width
//sample.setWidth(100.0f, Unit.PERCENTAGE);
//
//// Check if the caption for new item already exists in the list of item
//// captions before approving it as a new item.
//ComboBox.NewItemHandler itemHandler = newItemCaption -> {
//    boolean newItem = countriesData.stream().noneMatch(data -> data.getFullName().equalsIgnoreCase(newItemCaption));
//    if (newItem) {
//        // Adds new option
//        CountryData newCountryData = new CountryData(newItemCaption, null);
//        countriesData.add(newCountryData);
//        sample.setItems(countriesData);
//        sample.setSelectedItem(newCountryData);
//    }
//};
//sample.setNewItemHandler(itemHandler);
//
//
//...
//
//sample.addValueChangeListener(event -> Notification.show("Value changed:",
//        String.valueOf(event.getValue()),
//        Type.TRAY_NOTIFICATION));