package survivalPotager.modele.mangeables;

abstract public class Mangeable {

    /*
     * Le compteur de croissance est décrémenté de 1 à  chaque tour. Arrivé à  0, le Mangeable doit être récolté, sinon il est perdu.
     */
    protected int toursAvantCueillette;

    public int getToursAvantCueillette() {

        return this.toursAvantCueillette;
    }

    public void croitre() {

        this.toursAvantCueillette -= 1;
    }

    public abstract int getBesoinIrrigation();

    public abstract int getCycleDeCroissance();

    public abstract int getRendement();

}
