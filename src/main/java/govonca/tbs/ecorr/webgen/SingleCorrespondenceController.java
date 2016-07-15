package govonca.tbs.ecorr.webgen;

import govonca.tbs.ecorr.model.SingleCorrespondence;
import govonca.tbs.ecorr.model.util.JsfUtil;
import govonca.tbs.ecorr.model.util.JsfUtil.PersistAction;
import govonca.tbs.ecorr.webgen.SingleCorrespondenceFacade;

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

@Named("singleCorrespondenceController")
@SessionScoped
public class SingleCorrespondenceController implements Serializable {

    @EJB
    private govonca.tbs.ecorr.webgen.SingleCorrespondenceFacade ejbFacade;
    private List<SingleCorrespondence> items = null;
    private SingleCorrespondence selected;

    public SingleCorrespondenceController() {
    }

    public SingleCorrespondence getSelected() {
        return selected;
    }

    public void setSelected(SingleCorrespondence selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private SingleCorrespondenceFacade getFacade() {
        return ejbFacade;
    }

    public SingleCorrespondence prepareCreate() {
        selected = new SingleCorrespondence();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("SingleCorrespondenceCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("SingleCorrespondenceUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("SingleCorrespondenceDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<SingleCorrespondence> getItems() {
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

    public SingleCorrespondence getSingleCorrespondence(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<SingleCorrespondence> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<SingleCorrespondence> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = SingleCorrespondence.class)
    public static class SingleCorrespondenceControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            SingleCorrespondenceController controller = (SingleCorrespondenceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "singleCorrespondenceController");
            return controller.getSingleCorrespondence(getKey(value));
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
            if (object instanceof SingleCorrespondence) {
                SingleCorrespondence o = (SingleCorrespondence) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), SingleCorrespondence.class.getName()});
                return null;
            }
        }

    }

}
