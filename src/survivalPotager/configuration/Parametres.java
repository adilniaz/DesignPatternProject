package survivalPotager.configuration;

public class Parametres {

	/* Construction du terrain */
	public static final int largeurTerrain = 7;
	public static final int hauteurTerrain = 7;

	public static final double probaParcelleRocher = 0.2;
	public static final double probaParcelleExploitableDefrichee = 0.1;
	public static final double probaParcellesExploitableEnFriche = 0.7;

	/* écoulement des tours */
	public static final int limiteDeTours = 7; // 0 pour un nombre de tours infinis.
	public static final int nombreDeToursAuDepartQuIlEstPossiblesDePasserSansManger = 3;
	public static final int nombreDeToursParPalierDeConsommation = 3;

	/* Coûts des actions */
	public static final int pointsActionParTour = 2;
	public static final int coutDefricher = 1;
	public static final int coutIrriguer = 1;
	public static final int coutPlanter = 1;
	public static final int coutRecolter = 1;
	public static final String messagePasAssezDePointsDAction = "Vous n'avez pas assez de points d'action pour accomplir cette action.";

	/* Contenu de la caissette au départ */
	public static final int nbRadisCaissette = 1;
	public static final int nbTomateCaissette = 1;
	public static final int nbRizCaissette = 1;

    /* Besoin en eau */
    public static final int besoinEauRadis = 1;
    public static final int besoinEauTomate = 2;
    public static final int besoinEauRiz = 4;

    /* Rendement des mangeables */
    public static final int rendementRadis = 2;
    public static final int rendementTomate = 3;
    public static final int rendementRiz = 4;
    
    /*Cycle de croissance*/
    public static final int croissanceRadis = 2;
    public static final int croissanceRiz = 4;
    public static final int croissanceTomate = 3;
}
