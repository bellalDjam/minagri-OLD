/**
 * 
 */
package dz.minagri.stat.security;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.vaadin.ui.Notification;

/**
 * @author bellal djamel
 *
 */
@Service
public class AdminService {

    @Secured("ROLE_ADMIN")
    public void doSomeAdministrationTask() {
        Notification.show("Restricted admin task performed", "", Notification.Type.HUMANIZED_MESSAGE);
    }
}
