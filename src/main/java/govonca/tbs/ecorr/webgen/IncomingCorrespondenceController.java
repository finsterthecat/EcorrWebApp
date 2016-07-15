package govonca.tbs.ecorr.webgen;

import govonca.tbs.ecorr.model.IncomingCorrespondence;
import govonca.tbs.ecorr.model.util.JsfUtil;
import govonca.tbs.ecorr.model.util.JsfUtil.PersistAction;
import govonca.tbs.ecorr.webgen.IncomingCorrespondenceFacade;

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

@Named("incomingCorrespondenceController")
@SessionScoped
public class IncomingCorrespondenceController implements Serializable {

    @EJB
    private govonca.tbs.ecorr.webgen.IncomingCorrespondenceFacade ejbFacade;
    private List<IncomingCorrespondence> items = null;
    private IncomingCorrespondence selected;

    public IncomingCorrespondenceController() {
    }

    public IncomingCorrespondence getSelected() {
        return selected;
    }

    public void setSelected(IncomingCorrespondence selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private IncomingCorrespondenceFacade getFacade() {
        return ejbFacade;
    }

    public IncomingCorrespondence prepareCreate() {
        selected = new IncomingCorrespondence();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("IncomingCorrespondenceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("IncomingCorrespondenceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("IncomingCorrespondenceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<IncomingCorrespondence> getItems() {
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

    public IncomingCorrespondence getIncomingCorrespondence(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<IncomingCorrespondence> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<IncomingCorrespondence> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = IncomingCorrespondence.class)
    public static class IncomingCorrespondenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            IncomingCorrespondenceController controller = (IncomingCorrespondenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "incomingCorrespondenceController");
            return controller.getIncomingCorrespondence(getKey(value));
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
            if (object instanceof IncomingCorrespondence) {
                IncomingCorrespondence o = (IncomingCorrespondence) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), IncomingCorrespondence.class.getName()});
                return null;
            }
        }

    }

}
