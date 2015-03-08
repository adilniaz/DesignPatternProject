package survivalPotager.modele.terrain;

import survivalPotager.modele.mangeables.Mangeable;

public class ParcelleCultivable extends ParcelleAbstraite {

    // Etat.
    private Boolean enFriche;
    // Ressource Mangeable.
    private Mangeable mangeable;

    public Boolean getEtat() {

        return this.enFriche;
    }

    public void setEtat(Boolean e) {

        this.enFriche = e;
    }

    public Mangeable getMangeable() {

        return this.mangeable;
    }

    public void setMangeable(Mangeable m) {

        this.mangeable = m;
    }

    public Mangeable deplanter() {
        Mangeable temp = this.mangeable;
        this.mangeable = null;
        return temp;
    }

    public void deplante() {
        this.mangeable = null;
    }


    public void croitre() {
        if (this.mangeable != null) {
            if (this.mangeable.getToursAvantCueillette() <= 0) {
                this.deplante();
            } else {
                this.mangeable.croitre();
            }
        }
    }

    public ParcelleCultivable(Boolean e) {
        this.enFriche = e;
    }
}
