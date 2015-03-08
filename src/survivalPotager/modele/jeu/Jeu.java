package survivalPotager.modele.jeu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import survivalPotager.configuration.Parametres;
import survivalPotager.modele.mangeables.Mangeable;
import survivalPotager.modele.mangeables.Radis;
import survivalPotager.modele.mangeables.Riz;
import survivalPotager.modele.mangeables.Tomate;
import survivalPotager.modele.terrain.ParcelleCultivable;
import survivalPotager.modele.terrain.ParcelleEau;
import survivalPotager.modele.terrain.Terrain;
import survivalPotager.outils.Coordonnees;

public class Jeu {

    private Terrain terrain;
    private int nbTour;
    private int nbRiz;
    private int nbRadis;
    private int nbTomate;
    private int nbPa;
    private int aconsommer;
    private int consommationParTour;
    private boolean debManger;
    private PropertyChangeSupport pcsDeroulementJeu = new PropertyChangeSupport(this);
    private int choixAlim;

    public Jeu() {

        this.terrain = new Terrain(Parametres.largeurTerrain, Parametres.hauteurTerrain);
        this.terrain.terrainAleatoire();
        this.nbTour = 1;
        this.nbRadis = Parametres.nbRadisCaissette;
        this.nbRiz = Parametres.nbRizCaissette;
        this.nbTomate = Parametres.nbTomateCaissette;
        this.nbPa = Parametres.pointsActionParTour;
        this.consommationParTour = 1;
        this.aconsommer = 0;
        this.debManger = false;
    }

    public void planter(Coordonnees c) {

        ParcelleCultivable parcelleRiz;
        ParcelleCultivable parcelleTomate;
        ParcelleCultivable parcelleRadis;
        ParcelleCultivable cultivable;
        ParcelleEau eau;

        parcelleRiz = new ParcelleCultivable(false);
        parcelleRiz.setMangeable(new Riz());
        parcelleTomate = new ParcelleCultivable(false);
        parcelleTomate.setMangeable(new Tomate());
        parcelleRadis = new ParcelleCultivable(false);
        parcelleRadis.setMangeable(new Radis());
        eau = new ParcelleEau();

        switch (this.choixAlim) {

            case 1:
                // Riz
                if (this.nbRiz > 0) {
                    if (this.terrain.calculerNombreDeParcellesAdjacentes(c, eau) >= Parametres.besoinEauRiz) {
                        cultivable = parcelleRiz;
                        this.terrain.setParcelle(c, cultivable);
                        this.nbPa -= Parametres.coutPlanter;
                        this.nbRiz -= 1;
                        this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Riz planté sur la parcelle" + c.toString() + "\n");
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Pas assez de parcelles d'eau adjacentes\n");
                    }
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Vous n'avez pas de Riz dans votre caissette\n");
                }
                break;
            case 2:
                //Tomate
                if (this.nbTomate > 0) {
                    if (this.terrain.calculerNombreDeParcellesAdjacentes(c, eau) >= Parametres.besoinEauTomate) {
                        cultivable = parcelleTomate;
                        this.terrain.setParcelle(c, cultivable);
                        this.nbPa -= Parametres.coutPlanter;
                        this.nbTomate -= 1;
                        this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Tomate plantée sur la parcelle" + c.toString() + "\n");
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Pas assez de parcelles d'eau adjacentes\n");
                    }
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Vous n'avez pas de Tomate dans votre caissette\n");
                }
                break;
            case 3:
                // Radis
                if (this.nbRadis > 0) {
                    if (this.terrain.calculerNombreDeParcellesAdjacentes(c, eau) >= Parametres.besoinEauRadis) {
                        cultivable = parcelleRadis;
                        this.terrain.setParcelle(c, cultivable);
                        this.nbPa -= Parametres.coutPlanter;
                        this.nbRadis -= 1;
                        this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Radis planté sur la  parcelle" + c.toString() + "\n");
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Pas assez de parcelles d'eau adjacentes\n");
                    }
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Vous n'avez pas de Radis dans votre caissette\n");
                }
                break;

            default:

        }
    }

    public boolean testCoordonneesAvtPlanter(Coordonnees c) {

        ParcelleCultivable cultivable;
        if (this.terrain.getParcelle(c) instanceof ParcelleCultivable) {
            cultivable = (ParcelleCultivable) this.terrain.getParcelle(c);

            if (!cultivable.getEtat()) {
                if (cultivable.getMangeable() == null) {
                    return true;
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle contient déja un mangeable\n");
                    return false;
                }
            } else {
                this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle cultivable est en friche\n");
                return false;
            }
        } else {
            this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle n'est pas une parcelle cultivable\n");
            return false;
        }
    }

    public void defricher(Coordonnees c) {

        ParcelleCultivable cultivable2;

        if (this.nbPa > 0) {
            if (this.terrain.getParcelle(c) instanceof ParcelleCultivable) {
                cultivable2 = (ParcelleCultivable) this.terrain.getParcelle(c);
                if (cultivable2.getEtat()) {
                    cultivable2 = new ParcelleCultivable(false);
                    this.terrain.setParcelle(c, cultivable2);
                    this.nbPa -= Parametres.coutDefricher;
                    this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Parcelle " + c.toString() + " défrichée\n");
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle est déja défrichée !\n");
                }
            } else {
                this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle n'est pas une parcelle cultivable\n");
            }
        } else {
            this.pcsDeroulementJeu.firePropertyChange("erreur", null, Parametres.messagePasAssezDePointsDAction + "\n");
        }
    }

    public void irriguer(Coordonnees c) {

        ParcelleEau eau;
        eau = new ParcelleEau();

        if (this.nbPa > 0) {
            if (this.terrain.getParcelle(c) instanceof ParcelleCultivable) {
                this.terrain.setParcelle(c, eau);
                this.nbPa -= Parametres.coutIrriguer;
                this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Parcelle " + c.toString() + " irriguée\n");
            } else {
                this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette parcelle ne peut pas être irriguée ce n'est pas une parcelle cultivable\n");
            }
        } else {
            this.pcsDeroulementJeu.firePropertyChange("erreur", null, Parametres.messagePasAssezDePointsDAction + "\n");
        }
    }

    public void recolter(Coordonnees c) {

        ParcelleCultivable cultivable;
        Mangeable recolte;

        if (this.nbPa > 0) {
            if (this.terrain.getParcelle(c) instanceof ParcelleCultivable) {

                cultivable = (ParcelleCultivable) this.terrain.getParcelle(c);
                if (cultivable.getMangeable() != null) {
                    if (cultivable.getMangeable().getToursAvantCueillette() == 0) {
                        recolte = cultivable.deplanter();
                        this.nbPa -= Parametres.coutRecolter;
                        if (recolte instanceof Riz) {
                            this.nbRiz += Parametres.rendementRiz;
                            this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Riz récolté !\n");
                        } else if (recolte instanceof Tomate) {
                            this.nbTomate += Parametres.rendementTomate;
                            this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Tomates récoltées !\n");
                        } else {
                            this.nbRadis += Parametres.rendementRadis;
                            this.pcsDeroulementJeu.firePropertyChange("actionEffectuée", null, "Radis récolté\n");
                        }
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Le mangeable ne peut pas encore être récolté\n");
                    }
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Vous n'avez pas planter sur cette parcelle\n");
                }
            } else {
                this.pcsDeroulementJeu.firePropertyChange("erreur", null, "Cette Parcelle n'est pas une Parcelle Cultivable\n");
            }
        } else {
            this.pcsDeroulementJeu.firePropertyChange("erreur", null, Parametres.messagePasAssezDePointsDAction + "\n");
        }
    }

    public void manger() {

        if (this.nbTour > Parametres.nombreDeToursAuDepartQuIlEstPossiblesDePasserSansManger) {
            this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous devez manger " + this.aconsommer + " aliment(s)");

            this.pcsDeroulementJeu.firePropertyChange("choixAlim", null, null);
            switch (this.choixAlim) {
                case 1:
                    if (this.nbRiz > 0) {
                        this.nbRiz -= 1;
                        this.aconsommer -= 1;
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Riz\n");
                    }
                    break;

                case 2:
                    if (this.nbTomate > 0) {
                        this.nbTomate -= 1;
                        this.aconsommer -= 1;
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Tomate\n");
                    }
                    break;

                case 3:
                    if (this.nbRadis > 0) {
                        this.nbRadis -= 1;
                        this.aconsommer -= 1;
                    } else {
                        this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Radis\n");
                    }
                    break;
                default:
            }

        }
    }

    public void passageAuTourSuivantAutomatique() {
        if (this.nbPa == 0) {
            passerAuTourSuivant();
        }
    }

    public void passeAutoTrSuivGraph() {
        if (this.nbPa == 0) {
            this.pcsDeroulementJeu.firePropertyChange("autoPs", null, null);
        }
    }

    public void passerAuTourSuivant() {

        // Après un nombre de tour on augmente la consommation par Jour d'aliment
        if (this.nbTour > 0 && this.nbTour % Parametres.nombreDeToursParPalierDeConsommation == 0 && this.debManger) {
            this.consommationParTour += 1;
            this.pcsDeroulementJeu.firePropertyChange("info", null, "Augmentation de votre besoin de consommation d'une unitée");
        }
        if (this.nbTour > 3) {
            this.debManger = true;
            this.aconsommer = this.consommationParTour;
        }
        // on mange
        while (this.aconsommer > 0 && !this.testCaissetteVideEtPasMangerAssez()) {
            manger();
        }
        if (this.aconsommer == 0) {
            // passage au tour suivant
            if (this.nbTour < Parametres.limiteDeTours) {
                this.terrain.croitre();
                this.nbTour += 1;
                this.nbPa = Parametres.pointsActionParTour;
                this.pcsDeroulementJeu.firePropertyChange("passerAuTourSuivant", null, null);
            } // Fin de la partie vous avez gagné
            else {
                this.pcsDeroulementJeu.firePropertyChange("gagner", null, "Bravo vous avez réussi à survivre ! Un bateau vient vous récupérer.");
            }
        } // Pas assez mangé ! perdu
        else {
            this.pcsDeroulementJeu.firePropertyChange("perdre", null, "Vous avez perdu !(Vous êtes mort de faim !)");
        }
    }

    public void mangerGraph() {
        if (this.nbRadis != 0 || this.nbRiz != 0 || this.nbTomate != 0) {
            this.pcsDeroulementJeu.firePropertyChange("infoM", null, "Vous devez manger " + this.aconsommer + " aliment(s)");
        } else {
            this.pcsDeroulementJeu.firePropertyChange("perdre", null, null);
        }
    }

    public void mangerGraph2() {
        switch (this.choixAlim) {
            case 1:
                if (this.nbRiz > 0) {
                    this.nbRiz -= 1;
                    this.aconsommer -= 1;
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Riz\n");
                }
                break;

            case 2:
                if (this.nbTomate > 0) {
                    this.nbTomate -= 1;
                    this.aconsommer -= 1;
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Tomate\n");
                }
                break;

            case 3:
                if (this.nbRadis > 0) {
                    this.nbRadis -= 1;
                    this.aconsommer -= 1;
                } else {
                    this.pcsDeroulementJeu.firePropertyChange("info", null, "Vous n'avez plus de Radis\n");
                }
                break;
            default:
        }
    }

    public void passerAuTourSuivant1() {
        if (this.nbTour > 0 && this.nbTour % Parametres.nombreDeToursParPalierDeConsommation == 0 && this.debManger) {
            this.consommationParTour += 1;
        }

        this.debManger = true;
        this.aconsommer = this.consommationParTour;
    }

    public void passerAuTourSuivantGraph2() {
        if (this.aconsommer == 0) {
            // passage au tour suivant
            if (this.nbTour < Parametres.limiteDeTours) {
                this.terrain.croitre();
                this.nbTour += 1;
                this.nbPa = Parametres.pointsActionParTour;
                this.pcsDeroulementJeu.firePropertyChange("passerAuTourSuivant", null, null);
            } // Fin de la partie vous avez gagné
            else {
                this.pcsDeroulementJeu.firePropertyChange("gagner", null, null);
            }
        } // Pas assez mangé ! perdu
        else {
            this.pcsDeroulementJeu.firePropertyChange("perdre", null, null);
        }
    }

    public boolean testCaissetteVideEtPasMangerAssez() {

        return (this.nbRadis == 0 && this.nbTomate == 0 && this.nbRiz == 0 && this.aconsommer > 0);
    }

    public int getNbTour() {
        return this.nbTour;
    }

    public Terrain getTerrain() {
        return this.terrain;
    }

    public int getNbRadis() {
        return this.nbRadis;
    }

    public int getNbTomate() {
        return this.nbTomate;
    }

    public int getNbRiz() {
        return this.nbRiz;
    }

    public int getNbPa() {
        return this.nbPa;
    }

    public int getAConsommer() {
        return this.aconsommer;
    }

    public void setChoixAlim(int choix) {
        this.choixAlim = choix;
    }

    // Ajout d'un écouteur de changement dans le Jeu
    public void ajouterEcouteurJeu(PropertyChangeListener ecouteur) {
        pcsDeroulementJeu.addPropertyChangeListener(ecouteur);
    }

    //  Suppression d'un écouteur de changement dans le Jeu
    public void retirerEcouteurJeu(PropertyChangeListener ecouteur) {
        pcsDeroulementJeu.removePropertyChangeListener(ecouteur);
    }
}
