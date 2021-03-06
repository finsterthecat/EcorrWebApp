/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package govonca.tbs.ecorr.webgen;

import govonca.tbs.ecorr.model.SingleCorrespondence;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author brouwerto
 */
@Stateless
public class SingleCorrespondenceFacade extends AbstractFacade<SingleCorrespondence> {

    @PersistenceContext(unitName = "govonca.tbs_EcorrWebApp_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SingleCorrespondenceFacade() {
        super(SingleCorrespondence.class);
    }
    
}
