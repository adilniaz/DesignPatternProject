package survivalPotager.modele.terrain;

import java.util.ArrayList;

import abstracts_interfaces.factories.gameplatforms.AccessAbstract;
import abstracts_interfaces.factories.gameplatforms.GamePlatformAbstract;
import abstracts_interfaces.factories.gameplatforms.ZoneAbstract;
import survivalPotager.configuration.Parametres;
import survivalPotager.outils.Coordonnees;

public class Terrain extends GamePlatformAbstract{

    /*
     * Tableau � deux dimensions (dallage) contenant les parcelles
     */
    private ParcelleAbstraite[][] parcelles;

    public Terrain(int tailleH, int tailleV) {

        this.parcelles = new ParcelleAbstraite[tailleH][tailleV];
    }

    /*
     * Place une parcelle "p" aux coordonn�es "(x, y)".
     */
    public void setParcelle(int x, int y, ParcelleAbstraite p) {
        this.parcelles[x][y] = p;
    }

    /*
     * Place une parcelle "p" aux coordonn�es stock�es dans "c".
     */
    public void setParcelle(Coordonnees c, ParcelleAbstraite p) {
        this.parcelles[c.getX()][c.getY()] = p;
    }

    public ParcelleAbstraite getParcelle(int x, int y) {

        return this.parcelles[x][y];
    }

    public ParcelleAbstraite getParcelle(Coordonnees c) {

        return this.parcelles[c.getX()][c.getY()];
    }

    /*
     * Obtenir la taille horizontale du terrain.
     */
    public int getTailleH() {

        return this.parcelles.length;
    }

    /*
     * Obtenir la taille verticale du terrain.
     */
    public int getTailleV() {

        return this.parcelles[0].length;
    }

    /*
     * Renvoie le nombre de parcelles adjacentes d'un certain type de la parcelle dont on sp�cifie les coordonn�es.
     */
    public int calculerNombreDeParcellesAdjacentes(Coordonnees c, ParcelleAbstraite parcelle) {
        int n = 0;
        int x = c.getX();
        int y = c.getY();


        if (x - 1 >= 0 && y - 1 >= 0 ) {
            if (this.getParcelle(x - 1, y - 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (x - 1 >= 0) {
            if (this.getParcelle(x - 1, y).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (x - 1 >= 0 && y + 1 < Parametres.hauteurTerrain) {
            if (this.getParcelle(x - 1, y + 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if ( y - 1 >= 0) {
            if (this.getParcelle(x, y - 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (y + 1 < Parametres.hauteurTerrain) {
            if (this.getParcelle(x, y + 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (y - 1 >= 0 && x + 1 < Parametres.largeurTerrain) {
            if (this.getParcelle(x + 1, y - 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (x + 1 < Parametres.largeurTerrain) {
            if (this.getParcelle(x + 1, y).getClass() == parcelle.getClass()) {
                n++;
            }
        }

        if (x + 1 < Parametres.largeurTerrain && y + 1 < Parametres.hauteurTerrain) {
            if (this.getParcelle(x + 1, y + 1).getClass() == parcelle.getClass()) {
                n++;
            }
        }
        return n;
    }

    /*
     * Fait �voluer de 1 jour toutes ses parcelles.
     */
    public void croitre() {

        for (int i = 0; i < this.parcelles.length; i++) {
            for (int j = 0; j < this.parcelles[i].length; j++) {
                this.getParcelle(i, j).croitre();
            }
        }
    }

    public void terrainAleatoire() { 
       
        int caseEauPrecedente = (int) (Math.random() * Parametres.hauteurTerrain); //Case d'eau � laquelle on se r�f�re pour placer la prochaine
        int caseEauSuivante; // Case d'eau que l'on veut remplir
        double verticaleEau = Math.random();
        double parcelleAleatoire; // nombre qui va dire quelle parcelleAbstraite sera dans la case

        if (verticaleEau < 0.5) {
            this.setParcelle(0, caseEauPrecedente, new ParcelleEau()); // On place la premi�re parcelle EN HAUT

        } else {
            this.setParcelle(caseEauPrecedente, 0, new ParcelleEau()); // On place la premi�re parcelle A GAUCHE
        }
        if (verticaleEau > 0.5) { //BOUCLE SI A EN HAUT
            for (int hauteur = 1; hauteur < Parametres.hauteurTerrain; hauteur++) { // Cette boucle place les prochaines cases d'eau

                if (caseEauPrecedente == Parametres.largeurTerrain - 1) { // Si la case pr�c�dente est au bord du terrain � droite
                    caseEauSuivante = ((int) (Math.random() * 2)) - 1 + caseEauPrecedente; // on a le choix qu'entre deux cases
                } else if (caseEauPrecedente == 0) {// Si la case pr�c�dente est au bord du terrain � gauche
                    caseEauSuivante = ((int) (Math.random() * 2));  // on a le choix qu'entre deux cases
                } else {
                    caseEauSuivante = ((int) (Math.random() * 3)) - 1 + caseEauPrecedente; // sinon on a le choix entre trois cases
                }
                this.parcelles[caseEauSuivante][hauteur] = new ParcelleEau(); // on ajoute la parcelle au tableau
                caseEauPrecedente = caseEauSuivante; // on se d�place dans les lignes
            }
        } else { //BOUCLE SI GAUCHE
            for (int hauteur = 1; hauteur < Parametres.largeurTerrain; hauteur++) {
                if (caseEauPrecedente == Parametres.hauteurTerrain - 1) { // Si la case pr�c�dente est au bord du terrain � droite
                    caseEauSuivante = ((int) (Math.random() * 2)) - 1 + caseEauPrecedente; // on a le choix qu'entre deux cases
                } else if (caseEauPrecedente == 0) {// Si la case pr�c�dente est au bord du terrain � gauche
                    caseEauSuivante = ((int) (Math.random() * 2));  // on a le choix qu'entre deux cases
                } else {
                    caseEauSuivante = ((int) (Math.random() * 3)) - 1 + caseEauPrecedente; // sinon on a le choix entre trois cases
                }
                this.parcelles[hauteur][caseEauSuivante] = new ParcelleEau(); // on ajoute la parcelle au tableau
                caseEauPrecedente = caseEauSuivante; // on se d�place dans les lignes
            }
        }


        for (int hauteur = 0; hauteur < this.parcelles.length; hauteur++) { // cette double boucle place les �lements qui ne sont pas de l'eau dans le terrain
            for (int largeur = 0; largeur < this.parcelles[hauteur].length; largeur++) {
                if (!(this.parcelles[hauteur][largeur] instanceof ParcelleEau)) { // UNIQUEMENT si il n'y a pas d'eau
                    parcelleAleatoire = Math.random() * (Parametres.probaParcelleRocher + Parametres.probaParcelleExploitableDefrichee + Parametres.probaParcellesExploitableEnFriche); // nb al�atoire
                    if (parcelleAleatoire < Parametres.probaParcelleRocher) { // si c'est un rocher
                        this.parcelles[hauteur][largeur] = new ParcelleRocher(); // alors le placer
                    } else if (parcelleAleatoire < Parametres.probaParcelleRocher + Parametres.probaParcelleExploitableDefrichee) { // si c'est d�frich�e
                        ParcelleCultivable p = new ParcelleCultivable(false); // alors placer la zone d�frich�e
                        // p.setEtat(false); // mettre l'�tat de friche � false
                        this.parcelles[hauteur][largeur] = p;
                    } else if (parcelleAleatoire < (Parametres.probaParcelleRocher + Parametres.probaParcelleExploitableDefrichee + Parametres.probaParcellesExploitableEnFriche)) { // Si c'est en friche
                        this.parcelles[hauteur][largeur] = new ParcelleCultivable(true); // alors placer la zone en friche

                    }
                }

            }
        }
    }

	@Override
	public void addAccess(ArrayList<AccessAbstract> access) {
		
	}

	@Override
	public void addZone(ArrayList<ZoneAbstract> zones) {
		
	}
}
