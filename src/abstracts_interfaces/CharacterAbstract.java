package abstracts_interfaces;

import abstracts_interfaces.behaviours.BehaviourCombatAbstract;
import abstracts_interfaces.behaviours.BehaviourTalkAbstract;
import implementations.organisations.Organisation;


public abstract class CharacterAbstract extends ObserverAbstract{
	

	private String nom;
	protected BehaviourCombatAbstract comportement;
	protected BehaviourTalkAbstract son;
	protected Organisation subject;
	protected String etatObservé;
	
	public enum etatFonctionnement{
		MODE_GUERRE, MODE_PAIX, MODE_NON_DEFINI, MODE_REPLI
	};
	
	public abstract String Afficher();
	
	public String getEtatObservé() {
		return etatObservé;
	}

	public abstract String SeDeplacer();

	
	public CharacterAbstract(Organisation theSubject, String lenom){
		nom=lenom;
		comportement=null;
		son=null;
		subject= theSubject;
		if (subject != null) {
			subject.Attach(this);
		}
	}
	
	public void Update(){
		this.etatObservé = subject.getModeFonctionnement();
	}

	public  String Combattre(){
		if (comportement != null) {
			return comportement.Combattre();
		}
		return "Je ne combat pas";
	}
	
	public  String EmmetreUnSon(){
		if (son != null) {
			return son.EmmettreUnSon();
		}
		return "";
	}
	
	public Organisation getSubject() {
		return subject;
	}

	public void setSubject(Organisation subject) {
		this.subject = subject;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public BehaviourCombatAbstract getComportement() {
		return comportement;
	}
	
	public void setComportement(BehaviourCombatAbstract comportement) {
		this.comportement = comportement;
	}
	
	public BehaviourTalkAbstract getSon() {
		return son;
	}
	
	public void setSon(BehaviourTalkAbstract son) {
		this.son = son;
	}
	
}
