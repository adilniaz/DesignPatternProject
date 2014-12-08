package fireemblem.connexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;

import fireemblem.controlleur.Chapitre;
import fireemblem.personnage.Personnage;

public class Connexion extends Methode {
	
	private int partie;
	
	public Connexion (Connection connection) {
		super(connection);
		this.partie = 1;
	}
	
	public void save (Chapitre chapitre) {
		PreparedStatement prepare = this.prepareStatement("UPDATE partie SET temps_heure = ?, temps_min = ?, temps_seconde = ? WHERE id=?");
        this.setParameterInt(prepare, 0, 1);
        this.setParameterInt(prepare, 0, 2);
        this.setParameterInt(prepare, 0, 3);
        this.setParameterInt(prepare, this.partie, 4);
        this.executeUpdatePreparedStatement(prepare);
        
        List<CharacterAbstract> personnages = chapitre.getPlateauDeJeu().getPersonnages();
        prepare = this.prepareStatement("UPDATE stat_personnage SET " +
        	"niveau = ?, classe = ?, pv = ?, puissance = ?, capacite = ?, vitesse = ?, " +
        	"chance = ?, defense = ?, resistance = ?, constitution = ?, aide = ?, experience = ? " +
        	"WHERE id_partie = ? AND id_personnage = (SELECT id FROM personnage WHERE nom = ?)");
        PreparedStatement prepareObjet = this.prepareStatement("UPDATE objet_personnage SET " +
            	"nom = ?, type_objet = ?, position = ?, nombre = ? " +
            	"WHERE id_partie = ? AND id_personnage = (SELECT id FROM personnage WHERE nom = ?)");
        for (CharacterAbstract character : personnages) {
        	Personnage perso = (Personnage) character;
        	this.setParameterInt(prepare, perso.getNiv(), 1);
            this.setParameterString(prepare, perso.getComportementPersonnage().getName(), 2);
            this.setParameterInt(prepare, perso.getPv(), 3);
            this.setParameterInt(prepare, perso.getPuissance(), 4);
            this.setParameterInt(prepare, perso.getCapacite(), 5);
            this.setParameterInt(prepare, perso.getVitesse(), 6);
            this.setParameterInt(prepare, perso.getChance(), 7);
            this.setParameterInt(prepare, perso.getDef(), 8);
            this.setParameterInt(prepare, perso.getResistance(), 9);
            this.setParameterInt(prepare, perso.getConstitution(), 10);
            this.setParameterInt(prepare, perso.getAide(), 11);
            this.setParameterInt(prepare, perso.getExperience(), 12);
            this.setParameterInt(prepare, this.partie, 13);
            this.setParameterString(prepare, perso.getName(), 14);
            this.executeUpdatePreparedStatement(prepare);
            
            for (int i = 0; i < perso.getObjets().length ; i++) {
            	if (perso.getObjets()[i] != null) {
	            	this.setParameterString(prepareObjet, perso.getObjets()[i].getName(), 1);
	            	this.setParameterString(prepareObjet, perso.getObjets()[i].getType().name(), 2);
	            	this.setParameterInt(prepareObjet, (i+1), 3);
	            	this.setParameterInt(prepareObjet, perso.getObjets()[i].getUtilisation(), 4);
	            	this.setParameterInt(prepareObjet, this.partie, 5);
	            	this.setParameterString(prepareObjet, perso.getName(), 6);
	                this.executeUpdatePreparedStatement(prepareObjet);
            	}
            }
        }
        
	}
	
	public void savePartie (Chapitre chapitre) {
		
	}
	
	public void load (Chapitre chapitre) {
		
	}
	
	public void loadPartie (Chapitre chapitre) {
		
	}

}
