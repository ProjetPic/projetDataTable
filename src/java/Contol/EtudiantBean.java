/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Contol;

import Model.Etudiant;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

/**
 *
 * @author abdelkader
 */
@ManagedBean
public class EtudiantBean implements Serializable{
    
    private Etudiant p=new Etudiant();
    private static ArrayList<Etudiant> listEtudiant=new ArrayList<>();
    
    public String addEtudiant(){
        EtudiantBean.listEtudiant.add(p);
        afficher(listEtudiant);
        return null;
    }
    public Etudiant getP() {
        return p;
    }

    public void setP(Etudiant p) {
        this.p = p;
    }

    public  static ArrayList<Etudiant> getListEtudiant() {
        return EtudiantBean.listEtudiant;
    }
    
      public ArrayList<Etudiant> list() {
        return EtudiantBean.listEtudiant;
    }
    
    private void afficher(ArrayList<Etudiant> listEtudiant1){
       System.err.println("----  -------  -------  ------");
       for (Etudiant etudiant : listEtudiant1) {
            System.out.println(etudiant.getNom()+" - "+etudiant.getPrenom()+" - "+etudiant.getNote());
       }
    }
    
    public String delete(Etudiant et){
        EtudiantBean.listEtudiant.remove(et);
        return null;
    }
    
    public String edittext(Etudiant et){
        et.setEditable(true);
        return null;
    }
    
    public String save(Etudiant et){
        int index=listEtudiant.indexOf(et);
        listEtudiant.set(index, et);
        et.setEditable(false);
        return null;
    }
    
    public void validateForm(ComponentSystemEvent event) {
                float num = 0;
		FacesContext fc = FacesContext.getCurrentInstance();
                UIComponent components = event.getComponent();
                

		// get Note
		UIInput uiInputNote = (UIInput) components.findComponent("note");

		String nom1 = uiInputNote.getLocalValue() == null ? ""
                        : uiInputNote.getLocalValue().toString();

                
                System.out.println("note : "+nom1);
		if (nom1.isEmpty()) {
			return;
		}
                
                try{
                    num= Float.parseFloat(nom1);
                }catch(Exception e)
                {
                        FacesMessage msg = new FacesMessage("le champ doit etre un nombre");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(uiInputNote.getClientId(), msg);
			fc.renderResponse();
                }
                
		if (num <0 || num >20) {

			FacesMessage msg = new FacesMessage("Note compris entre 0 et 20");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(uiInputNote.getClientId(), msg);
			fc.renderResponse();
		}
                
	}
}
