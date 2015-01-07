package implementations.dbconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCreation extends Method {
	
	public DBCreation (Connection connection) {
		super(connection);
	}
	
	public void createBase () {
		if (this.estConnecter() && (!this.tableExist() || (this.getDateTableMiseAJour().before(this.getDateDerniereMiseAJour())))) {
			System.out.println("creation de la base");
			this.dropAllTable();
			this.creationTablePartie();
			this.creationTablePersonnage();
			this.creationTableStatPersonnage();
			this.creationTableObjetPersonnage();
			this.creationTablePartieSauvegarder();
			this.creationTableStatPersonnagePartieSauvegarder();
			this.creationTableObjetPersonnagePartieSauvegarder();
			this.creationTableStatEnnemiePartieSauvegarder();
			this.creationTableObjetEnnemiePartieSauvegarder();
			this.creationTableStatAnnexePartieSauvegarder();
			this.creationTableObjetAnnexePartieSauvegarder();
			this.creationTableMiseAJour();
		}
	}
	
	private void dropAllTable () {
		this.exexuteUpdate("DROP TABLE IF EXISTS mise_a_jour CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS objet_annexe_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS stat_annexe_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS objet_ennemie_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS stat_ennemie_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS objet_personnage_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS stat_personnage_partie_sauvegarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS partie_sauvergarder CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS objet_personnage CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS stat_personnage CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS personnage CASCADE");
		this.exexuteUpdate("DROP TABLE IF EXISTS partie CASCADE");
	}
    
    private boolean tableExist () {
        return this.executeQuery("SELECT * FROM partie") != null;
    }
    
    private Date getDateTableMiseAJour () {
    	Date date = null;
    	PreparedStatement prepare = this.prepareStatement("SELECT date_mise_a_jour FROM mise_a_jour");
        ResultSet result = this.executePreparedStatement(prepare);
        if (this.aResult(result)) {
            String string = this.getStringResultByString(result, "date_mise_a_jour");
            try {
                date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS").parse(string);
            } catch (ParseException ex) {
                Logger.getLogger(DBCreation.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.closeResultSet(result);
        if (date == null) {
            Calendar dateMiseAJour = Calendar.getInstance();
            dateMiseAJour.set(2010, Calendar.JANUARY, 01, 00, 00);
            date = dateMiseAJour.getTime();
        }
        return date;
    }
    
    private Date getDateDerniereMiseAJour () {
        Calendar dateMiseAJour = Calendar.getInstance();
        dateMiseAJour.set(2014, Calendar.DECEMBER, 9, 21, 50);
        return dateMiseAJour.getTime();
    }
    
    private void creationTableMiseAJour() {
        String requete = "CREATE TABLE mise_a_jour ("
                + "date_mise_a_jour TIMESTAMP"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeTableMiseAJour();
        }
    }
    
    private void insertionDonneeTableMiseAJour () {
        this.exexuteUpdate("INSERT INTO mise_a_jour (date_mise_a_jour) VALUES (NOW())");
    }
	
	private void creationTablePartie () {
        String requete = "CREATE TABLE partie ("
                + "id INT PRIMARY KEY,"
                + "temps_heure INT,"
                + "temps_min INT,"
                + "temps_seconde INT"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneePartie();
        }
    }
    
    private void insertionDonneePartie () {
        this.exexuteUpdate("INSERT INTO partie (id) VALUES (1)");
    }
	
	private void creationTablePersonnage () {
        String requete = "CREATE TABLE personnage ("
                + "id INT PRIMARY KEY,"
                + "nom varchar(32)"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneePersonnage();
        }
    }
    
    private void insertionDonneePersonnage () {
        this.exexuteUpdate("INSERT INTO personnage (id, nom) VALUES " +
        		"(1, 'Eliwood')");
    }
	
	private void creationTableStatPersonnage () {
        String requete = "CREATE TABLE stat_personnage ("
                + "id_personnage INT,"
        		+ "id_partie INT,"
                + "niveau INT,"
                + "classe varchar(32),"
                + "pv INT,"
                + "puissance INT,"
                + "capacite INT,"
                + "vitesse INT,"
                + "chance INT,"
                + "defense INT,"
                + "resistance INT,"
                + "constitution INT,"
                + "aide INT,"
                + "experience INT"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeStatPersonnage();
        }
    }
    
    private void insertionDonneeStatPersonnage () {
        this.exexuteUpdate("INSERT INTO stat_personnage " +
        		"(id_personnage, id_partie, niveau, classe, pv, puissance, capacite, vitesse, chance, " +
        		"defense, resistance, constitution, aide, experience) VALUES " +
        		"(1, 1, 1, 'lord_eliwood', 18, 5, 5, 7, 7, 5, 0, 7, 0, 90)");
    }
	
	private void creationTableObjetPersonnage () {
        String requete = "CREATE TABLE objet_personnage ("
                + "id_personnage INT,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "type_objet varchar(32),"
                + "position INT,"
                + "nombre INT"
                + ")";
        if (this.exexuteUpdate(requete)) {
            this.insertionDonneeObjetPersonnage();
        }
    }
    
    private void insertionDonneeObjetPersonnage () {
        this.exexuteUpdate("INSERT INTO objet_personnage " +
        		"(id_personnage, id_partie, nom, type_objet, position, nombre) VALUES "
        		+ "(1, 1, 'epee fer', 'epee_fer', 1, 35),"
        		+ "(1, 1, 'potion', 'potion', 2, 3)");
    }
	
	private void creationTablePartieSauvegarder () {
		String requete = "CREATE TABLE partie_sauvergarder ("
                + "id_partie INT,"
                + "tour INT,"
                + "niveau varchar(32),"
                + "temps_heure INT,"
                + "temps_min INT,"
                + "temps_seconde INT"
                + ")";
        this.exexuteUpdate(requete);
	}
	
	private void creationTableStatPersonnagePartieSauvegarder () {
        String requete = "CREATE TABLE stat_personnage_partie_sauvegarder ("
                + "id_personnage INT,"
        		+ "id_partie INT,"
                + "niveau INT,"
                + "classe varchar(32),"
                + "pv INT,"
                + "pv_max INT,"
                + "puissance INT,"
                + "capacite INT,"
                + "vitesse INT,"
                + "chance INT,"
                + "defense INT,"
                + "resistance INT,"
                + "constitution INT,"
                + "aide INT,"
                + "experience INT,"
                + "position_x INT,"
                + "position_y INT"
                + ")";
        this.exexuteUpdate(requete);
    }
	
	private void creationTableObjetPersonnagePartieSauvegarder () {
        String requete = "CREATE TABLE objet_personnage_partie_sauvegarder ("
                + "id_personnage INT,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "type_objet varchar(32),"
                + "position INT,"
                + "nombre INT"
                + ")";
        this.exexuteUpdate(requete);
    }
	
	private void creationTableStatEnnemiePartieSauvegarder () {
        String requete = "CREATE TABLE stat_ennemie_partie_sauvegarder ("
        		+ "id INT PRIMARY KEY,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "classe varchar(32),"
                + "niveau INT,"
                + "pv INT,"
                + "pv_max INT,"
                + "puissance INT,"
                + "capacite INT,"
                + "vitesse INT,"
                + "chance INT,"
                + "defense INT,"
                + "resistance INT,"
                + "constitution INT,"
                + "aide INT,"
                + "position_x INT,"
                + "position_y INT"
                + ")";
        this.exexuteUpdate(requete);
    }
	
	private void creationTableObjetEnnemiePartieSauvegarder () {
        String requete = "CREATE TABLE objet_ennemie_partie_sauvegarder ("
                + "id_ennemie INT,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "type_objet varchar(32),"
                + "position INT,"
                + "nombre INT"
                + ")";
        this.exexuteUpdate(requete);
    }
	
	private void creationTableStatAnnexePartieSauvegarder () {
        String requete = "CREATE TABLE stat_annexe_partie_sauvegarder ("
        		+ "id INT PRIMARY KEY,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "classe varchar(32),"
                + "niveau INT,"
                + "pv INT,"
                + "pv_max INT,"
                + "puissance INT,"
                + "capacite INT,"
                + "vitesse INT,"
                + "chance INT,"
                + "defense INT,"
                + "resistance INT,"
                + "constitution INT,"
                + "aide INT,"
                + "position_x INT,"
                + "position_y INT"
                + ")";
        this.exexuteUpdate(requete);
    }
	
	private void creationTableObjetAnnexePartieSauvegarder () {
        String requete = "CREATE TABLE objet_annexe_partie_sauvegarder ("
                + "id_annexe INT,"
        		+ "id_partie INT,"
                + "nom varchar(32),"
                + "type_objet varchar(32),"
                + "position INT,"
                + "nombre INT"
                + ")";
        this.exexuteUpdate(requete);
    }

}
