package survivalPotager.modele.mangeables;

abstract public class Mangeable {

    /*
     * Le compteur de croissance est d�cr�ment� de 1 � chaque tour. Arriv� � 0, le Mangeable doit �tre r�colt�, sinon il est perdu.
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
