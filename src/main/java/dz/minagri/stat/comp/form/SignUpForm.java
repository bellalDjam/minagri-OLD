/**
 * 
 */
package dz.minagri.stat.comp.form;

import java.util.ArrayList;
import java.util.Date;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * @author bellal djamel
 *
 */
public class SignUpForm extends VerticalLayout implements View {
    
////    MainSystem main = new MainSystem(); //connect to backend
//    
//    Label lblTitle;
//    Label lblHeader;
//    TextField tfID;
//    TextField tfName;
//    PasswordField tfPassword;
//    DateField dfDOB;
//    ComboBox cbType;
//    Button btnConfirm;
//    Button btnCancel;
////    OptionGroup opGender;
//    
//    public SignUpForm() {
//        
//        //INITIAL SETUP
//        setSpacing(true);
//        setMargin(true);
//        
//        //UI COMPONENTS
//        lblTitle = new Label("Sign Up Form");
//        lblTitle.addStyleName("h1");
//        addComponent(lblTitle);
//        
//        //Set up form
//        FormLayout formLayout = new FormLayout();
//        formLayout.setMargin(false);
//        formLayout.setWidth("700");
//        formLayout.addStyleName("light");
//        addComponent(formLayout);
//        
//        lblHeader = new Label("Personal Information");
//        lblHeader.addStyleName("h2");
//        lblHeader.addStyleName("colored");
//        formLayout.addComponent(lblHeader);
//        
//        tfID = new TextField("zID");
//        tfID.setRequired(true);
//        formLayout.addComponent(tfID);
//        
//        tfName = new TextField("Name");
//        tfName.setRequired(true);
//        formLayout.addComponent(tfName);
//        
//        tfPassword = new PasswordField("Password");
//        tfPassword.setRequired(true);
//        formLayout.addComponent(tfPassword);
//        
//        dfDOB = new DateField("Date of Birth");
//        dfDOB.setDateFormat("dd-MM-yyyy");
//        dfDOB.setValue(new Date()); //set the date to today's date
//        formLayout.addComponent(dfDOB);
//        
//        opGender = new OptionGroup("Gender");
//        opGender.addItem("Male");
//        opGender.addItem("Female");
//        opGender.addStyleName("horizontal");    //display radio buttons horizontally
//        formLayout.addComponent(opGender);
//        
//        //Create combobox with arraylist
//        ArrayList<String> listType = new ArrayList<>();
//        listType.add("Staff");
//        listType.add("Student");
//        
//        cbType = new ComboBox("Type", listType);
//        cbType.setRequired(true);
//        formLayout.addComponent(cbType);
//        
//        btnConfirm = new Button("Confirm");
//        btnConfirm.addStyleName("primary");
//        
//        btnConfirm.addClickListener(new Button.ClickListener(){
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                //sign user up 
//                main.signUp(tfID.getValue(), tfName.getValue(), tfPassword.getValue(), cbType.getValue().toString(), dfDOB.getValue(), opGender.getCaption());
//                
//                //show notification
//                Notification.show("You signed up successfully!");
//                
//                //Navigate back to login screen
//                getUI().getNavigator().navigateTo("login");
//            }
//            
//            
//        });
//        
//        btnCancel = new Button("Cancel");
//        btnCancel.addStyleName("danger");
//        
//        btnCancel.addClickListener(new Button.ClickListener(){
//            @Override
//            public void buttonClick(Button.ClickEvent event) {
//                //Navigate back to login screen
//                getUI().getNavigator().navigateTo("login");
//            }
//        
//        });
//        
//        //Horizontal footer for buttons
//        HorizontalLayout footer = new HorizontalLayout();
//        footer.setMargin(new MarginInfo(true, false, true, false)); //sets margin for top and bottom
//        footer.setSpacing(true);
//        footer.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
//        formLayout.addComponent(footer);
//        footer.addComponent(btnConfirm);
//        footer.addComponent(btnCancel);
//
//    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
}

