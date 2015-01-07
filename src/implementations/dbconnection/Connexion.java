package implementations.dbconnection;

import implementations.Position;
import implementations.chapters.Chapters;
import implementations.chapters.ChapterFactory;
import implementations.character.FireEmblemCharacterFactory;
import implementations.character.Character;
import implementations.character.FireEmblemCharacterType;
import implementations.controller.Chapter;
import implementations.gameplatform.GamePlatform;
import implementations.object.ObjetFactory;
import implementations.object.Objet;
import implementations.object.ObjetType;
import implementations.strategy.NoMovementStrategy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import abstracts_interfaces.CharacterAbstract;
import abstracts_interfaces.factories.characters.CreateCharactersFactoryAbstract;


public class Connexion extends Method {
	
	private int partie;
	
	public Connexion (Connection connection) {
		super(connection);
		this.partie = 1;
	}
	
	public void save (Chapter chapitre) {
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
        	Character perso = (Character) character;
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
	
	public void savePartie (Chapter chapitre) {
		System.out.println("sauvegarde en cours...");
		PreparedStatement prepare = this.prepareStatement("INSERT INTO partie_sauvergarder "
			+ "(id_partie, tour, niveau, temps_heure, temps_min, temps_seconde) VALUES (?,?,?,?,?,?)");
        this.setParameterInt(prepare, this.partie, 1);
        this.setParameterInt(prepare, chapitre.getTour(), 2);
        this.setParameterString(prepare, chapitre.getNom(), 3);
        this.setParameterInt(prepare, 0, 4);
        this.setParameterInt(prepare, 0, 5);
        this.setParameterInt(prepare, 0, 6);
        this.executeUpdatePreparedStatement(prepare);
        
        this.savePersonnagesPartie(chapitre.getPlateauDeJeu().getPersonnages(), "personnage");
        this.savePersonnagesPartie(chapitre.getPlateauDeJeu().getEnnemies(), "ennemie");
        this.savePersonnagesPartie(chapitre.getPlateauDeJeu().getAnnexes(), "annexe");
        System.out.println("sauvegarde terminer");
	}
	
	private void savePersonnagesPartie (List<CharacterAbstract> personnages, String type) {
		PreparedStatement prepare;
		if ("personnage".equals(type)) {
			prepare = this.prepareStatement("INSERT INTO stat_"+type+"_partie_sauvegarder "
	        	+ "(id_partie, id_personnage, niveau, classe, pv, pv_max, puissance, capacite, vitesse, chance, "
				+ "defense, resistance, constitution, aide, experience, position_x, position_y) "
	        	+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		} else {
			prepare = this.prepareStatement("INSERT INTO stat_"+type+"_partie_sauvegarder "
	        	+ "(id_partie, id, niveau, classe, pv, pv_max, puissance, capacite, vitesse, chance, "
				+ "defense, resistance, constitution, aide, position_x, position_y) "
	        	+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		}
        PreparedStatement prepareObjet = this.prepareStatement("INSERT INTO objet_"+type+"_partie_sauvegarder " +
            	"(id_partie, id_"+type+", nom, type_objet, position, nombre) VALUES (?,?,?,?,?,?)");
        for (int i = 0 ; i < personnages.size() ; i++) {
        	Character perso = (Character) personnages.get(i);
        	int id = 0;
        	if ("personnage".equals(type)) {
        		PreparedStatement select = this.prepareStatement("SELECT id FROM personnage WHERE nom = ?");
                this.setParameterString(select, perso.getName(), 1);
                ResultSet result = this.executePreparedStatement(select);
                while (this.aResult(result)) {
                    id = this.getIntResultByString(result, "id");
                }
                this.closeResultSet(result);
                this.setParameterInt(prepare, perso.getExperience(), 15);
                this.setParameterInt(prepare, perso.getPosition().getPositionX(), 16);
                this.setParameterInt(prepare, perso.getPosition().getPositionY(), 17);
        	} else {
        		id = i+1;
                this.setParameterInt(prepare, perso.getPosition().getPositionX(), 15);
                this.setParameterInt(prepare, perso.getPosition().getPositionY(), 16);
        	}
        	this.savePersonnagePartie(perso, prepare, prepareObjet, id);
        }
	}
	
	private void savePersonnagePartie (Character perso, PreparedStatement prepare, PreparedStatement prepareObjet, int id) {
		this.setParameterInt(prepare, this.partie, 1);
        this.setParameterInt(prepare, id, 2);
		this.setParameterInt(prepare, perso.getNiv(), 3);
        this.setParameterString(prepare, perso.getComportementPersonnage().getName(), 4);
        this.setParameterInt(prepare, perso.getPv(), 5);
        this.setParameterInt(prepare, perso.getPvMax(), 6);
        this.setParameterInt(prepare, perso.getPuissance(), 7);
        this.setParameterInt(prepare, perso.getCapacite(), 8);
        this.setParameterInt(prepare, perso.getVitesse(), 9);
        this.setParameterInt(prepare, perso.getChance(), 10);
        this.setParameterInt(prepare, perso.getDef(), 11);
        this.setParameterInt(prepare, perso.getResistance(), 12);
        this.setParameterInt(prepare, perso.getConstitution(), 13);
        this.setParameterInt(prepare, perso.getAide(), 14);
        this.executeUpdatePreparedStatement(prepare);
        
        for (int i = 0; i < perso.getObjets().length ; i++) {
        	if (perso.getObjets()[i] != null) {
            	this.setParameterInt(prepareObjet, this.partie, 1);
            	this.setParameterInt(prepareObjet, id, 2);
            	this.setParameterString(prepareObjet, perso.getObjets()[i].getName(), 3);
            	this.setParameterString(prepareObjet, perso.getObjets()[i].getType().name(), 4);
            	this.setParameterInt(prepareObjet, (i+1), 5);
            	this.setParameterInt(prepareObjet, perso.getObjets()[i].getUtilisation(), 6);
                this.executeUpdatePreparedStatement(prepareObjet);
        	}
        }
	}
	
	public Chapter loadPartie () {
		System.out.println("chargement en cours...");
		Chapter chapitre = null;
		PreparedStatement select = this.prepareStatement("SELECT tour, niveau FROM partie_sauvergarder");
        ResultSet result = this.executePreparedStatement(select);
        int tour = 0;
        Chapters chapitres = null;
        while (this.aResult(result)) {
            tour = this.getIntResultByString(result, "tour");
            chapitres = Chapters.getByName(this.getStringResultByString(result, "niveau"));
        }
        ChapterFactory factoryChapitre = new ChapterFactory();
        chapitre = factoryChapitre.createChapitre(chapitres);
        chapitre.setTour(tour);
        this.closeResultSet(result);
        while (!chapitre.getPlateauDeJeu().getPersonnages().isEmpty()) {
        	chapitre.getPlateauDeJeu().getPersonnages().remove(0);
        }
        this.loadPersonnagesPartie("personnage", chapitre.getPlateauDeJeu());
        while (!chapitre.getPlateauDeJeu().getAnnexes().isEmpty()) {
        	chapitre.getPlateauDeJeu().getAnnexes().remove(0);
        }
        this.loadPersonnagesPartie("annexe", chapitre.getPlateauDeJeu());
        while (!chapitre.getPlateauDeJeu().getEnnemies().isEmpty()) {
        	chapitre.getPlateauDeJeu().getEnnemies().remove(0);
        }
        this.loadPersonnagesPartie("ennemie", chapitre.getPlateauDeJeu());
        System.out.println("chargement terminer");
		return chapitre;
	}
	
	public void loadPersonnagesPartie (String type, GamePlatform plateauJeu) {
		PreparedStatement select;
		if ("personnage".equals(type)) {
			select = this.prepareStatement("SELECT id, nom, niveau, classe, pv, pv_max, puissance, "
				+ "capacite, vitesse, chance, defense, resistance, constitution, aide, experience, position_x, "
				+ "position_y FROM stat_"+type+"_partie_sauvegarder JOIN personnage ON id_personnage = id");
		} else {
			select = this.prepareStatement("SELECT id, nom, niveau, classe, pv, pv_max, puissance, "
				+ "capacite, vitesse, chance, defense, resistance, constitution, aide, position_x, "
				+ "position_y FROM stat_"+type+"_partie_sauvegarder");
		}
		ResultSet result = this.executePreparedStatement(select);
		ObjetFactory factoryObjet = new ObjetFactory();
        while (this.aResult(result)) {
    		Character perso = this.loadPersonnagePartie(result);
    		select = this.prepareStatement("SELECT nom, type_objet, nombre "
				+ "FROM objet_"+type+"_partie_sauvegarder WHERE id_partie = ? AND id_"+type+" = ? "
    			+ "ORDER BY position");
    		this.setParameterInt(select, this.partie, 1);
    		this.setParameterInt(select, this.getIntResultByString(result, "id"), 2);
			ResultSet resultObjet = this.executePreparedStatement(select);
			while (this.aResult(resultObjet)) {
				Objet objet = factoryObjet.createObjet(this.getStringResultByString(resultObjet, "nom"), ObjetType.valueOf(this.getStringResultByString(resultObjet, "type_objet")));
				objet.setUtilisation(this.getIntResultByString(resultObjet, "nombre"));
				perso.ajouterObjet(objet);
			}
			this.closeResultSet(resultObjet);
			perso.setStrategie(new NoMovementStrategy(perso));
			if ("personnage".equals(type)) {
				plateauJeu.ajouterPersonnage(perso);
			} else if ("annexe".equals(type)) {
				plateauJeu.ajouterAnnexe(perso);
			} else if ("ennemie".equals(type)) {
				plateauJeu.ajouterEnnemie(perso);
			}
    		
        }
        this.closeResultSet(result);
	}
	
	private Character loadPersonnagePartie (ResultSet result) {
		CreateCharactersFactoryAbstract factoryPersonnage = new FireEmblemCharacterFactory();
		Character personnage = (Character)factoryPersonnage.createCharacter(this.getStringResultByString(result, "nom"), null, FireEmblemCharacterType.getTypeByName(this.getStringResultByString(result, "classe")));
		personnage.setNiv(this.getIntResultByString(result, "niveau"));
		personnage.setPv(this.getIntResultByString(result, "pv"));
		personnage.setPvMax(this.getIntResultByString(result, "pv_max"));
		personnage.setPuissance(this.getIntResultByString(result, "puissance"));
		personnage.setCapacite(this.getIntResultByString(result, "capacite"));
		personnage.setVitesse(this.getIntResultByString(result, "vitesse"));
		personnage.setChance(this.getIntResultByString(result, "chance"));
		personnage.setDef(this.getIntResultByString(result, "defense"));
		personnage.setResistance(this.getIntResultByString(result, "resistance"));
		personnage.setConstitution(this.getIntResultByString(result, "constitution"));
		personnage.setAide(this.getIntResultByString(result, "aide"));
		if (this.getIntResultByString(result, "experience") != -1) {
			personnage.setExperience(this.getIntResultByString(result, "experience"));
		}
		personnage.setPosition(new Position(this.getIntResultByString(result, "position_x"), this.getIntResultByString(result, "position_y")));
		return personnage;
	}

}
