package govonca.tbs.ecorr.webgen;

import govonca.tbs.ecorr.model.ClientSnapshot;
import govonca.tbs.ecorr.model.util.JsfUtil;
import govonca.tbs.ecorr.model.util.JsfUtil.PersistAction;
import govonca.tbs.ecorr.webgen.ClientSnapshotFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("clientSnapshotController")
@SessionScoped
public class ClientSnapshotController implements Serializable {

    @EJB
    private govonca.tbs.ecorr.webgen.ClientSnapshotFacade ejbFacade;
    private List<ClientSnapshot> items = null;
    private ClientSnapshot selected;

    public ClientSnapshotController() {
    }

    public ClientSnapshot getSelected() {
        return selected;
    }

    public void setSelected(ClientSnapshot selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private ClientSnapshotFacade getFacade() {
        return ejbFacade;
    }

    public ClientSnapshot prepareCreate() {
        selected = new ClientSnapshot();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("ClientSnapshotCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("ClientSnapshotUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("ClientSnapshotDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<ClientSnapshot> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public ClientSnapshot getClientSnapshot(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<ClientSnapshot> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<ClientSnapshot> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = ClientSnapshot.class)
    public static class ClientSnapshotControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ClientSnapshotController controller = (ClientSnapshotController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "clientSnapshotController");
            return controller.getClientSnapshot(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ClientSnapshot) {
                ClientSnapshot o = (ClientSnapshot) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), ClientSnapshot.class.getName()});
                return null;
            }
        }

    }

}
