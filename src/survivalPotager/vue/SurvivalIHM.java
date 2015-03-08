package survivalPotager.vue;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import survivalPotager.configuration.Parametres;
import survivalPotager.modele.jeu.Jeu;
import survivalPotager.modele.mangeables.Radis;
import survivalPotager.modele.mangeables.Riz;
import survivalPotager.modele.mangeables.Tomate;
import survivalPotager.modele.terrain.ParcelleCultivable;
import survivalPotager.modele.terrain.ParcelleEau;
import survivalPotager.modele.terrain.ParcelleRocher;
import survivalPotager.modele.terrain.Terrain;
import survivalPotager.outils.Coordonnees;

public class SurvivalIHM extends JFrame {

    private Jeu jeu;
    private Terrain terrain;
    private JPanel panelTerrain;
    private JPanel menu;
    private JLabel infoTour;
    private JLabel infoCaissette;
    private JPanel infos;
    private JPanel infoT;
    private JPanel infoC;
    private Button planter;
    private Button riz;
    private Button tomate;
    private Button radis;
    private Button recolter;
    private Button irriguer;
    private Button defricher;
    private JButton passerTourS;
    private JButton legende;
    private JPanel menuTemp;
    private Boolean blocageBoutons;

    public SurvivalIHM(Jeu jeu) {

        this.jeu = jeu;
        this.terrain = this.jeu.getTerrain();
        this.panelTerrain = new JPanel();
        this.panelTerrain.setLayout(new GridLayout(Parametres.hauteurTerrain, Parametres.largeurTerrain));
        this.blocageBoutons = false;
        this.infos = new JPanel();
        this.infos.setLayout(new GridLayout(3, 1));
        this.infoT = new JPanel();
        this.infoC = new JPanel();
        this.menu = new JPanel();
        this.menu.setLayout(new GridLayout(1, 6));

        this.passerTourS = new JButton("Passer au tour suivant");
        this.passerTourS.addActionListener(new EcouteurBoutonPsTour());
        this.legende = new JButton("Legende");
        this.legende.addActionListener(new EcouteurBoutonLegende());
        this.menuTemp = new JPanel();
        this.menuTemp.setLayout(new GridLayout(5, 1));

        this.jeu.ajouterEcouteurJeu(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("actionEffectuée")) {
                    menuTemp.removeAll();
                    JLabel text = new JLabel();
                    text.setText(evt.getNewValue().toString());
                    menuTemp.add(text);
                    actualiserAffichage();
                } else if (evt.getPropertyName().equals("gagner")) {
                    actualiserAffichage();
                    blocageBoutons = true;
                    menuTemp.removeAll();
                    JLabel victoire = new JLabel("<html>Bravo vous avez réussi à survivre !<br> Un bateau vient vous récupérer.");
                    menuTemp.add(victoire);
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                } else if (evt.getPropertyName().equals("perdre")) {
                    actualiserAffichage();
                    blocageBoutons = true;
                    menuTemp.removeAll();

                    JLabel defaite = new JLabel("<html>Vous avez perdu !<br>(Vous êtes mort de faim !)");
                    menuTemp.add(defaite);
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                } else if (evt.getPropertyName().equals("infoM")) {
                    menuTemp.removeAll();
                    JLabel text = new JLabel();
                    text.setText(evt.getNewValue().toString());
                    menuTemp.add(text);
                    choixAl();
                } else if (evt.getPropertyName().equals("autoPs")) {
                    passageAutoTour();
                } else if (evt.getPropertyName().equals("passerAuTourSuivant")) {
                    actualiserAffichage();
                } else {
                    menuTemp.removeAll();
                    JLabel text = new JLabel();
                    text.setText(evt.getNewValue().toString());
                    menuTemp.add(text);
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                }
            }
        });

        this.setTitle("Survival Potager");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        actualiserAffichage();
        this.setVisible(true);
    }

    public void actualiserAffichage() {

        this.panelTerrain.removeAll();
        afficheTerrain();
        this.infoTour = new JLabel("<html> <br>Tour " + this.jeu.getNbTour() + "/" + Parametres.limiteDeTours);
        this.infoT.removeAll();
        this.infoT.add(this.infoTour);
        this.infoCaissette = new JLabel("<html> <br> Caissette: <br>Radis : " + this.jeu.getNbRadis() + " - Tomate : " + this.jeu.getNbTomate() + " - Riz : " + this.jeu.getNbRiz() + "  <br> Points d'action : " + this.jeu.getNbPa());
        this.infoC.removeAll();
        this.infoC.add(this.infoCaissette);
        this.infos.add(this.infoT);
        this.infos.add(this.infoC);
        this.infos.add(this.menuTemp);
        this.add(this.infos, BorderLayout.EAST);
        this.panelTerrain.setSize(700, 500);
        this.add(this.panelTerrain, BorderLayout.CENTER);
        this.menu.add(this.passerTourS);
        this.menu.add(this.legende);
        this.add(this.menu, BorderLayout.SOUTH);

    }

    public void afficheTerrain() {

        int i;
        int j;
        Button bouton;

        for (i = 0; i < this.terrain.getTailleH(); i++) {
            for (j = 0; j < this.terrain.getTailleV(); j++) {
                if (this.terrain.getParcelle(i, j) instanceof ParcelleCultivable) {
                    ParcelleCultivable cultivable;
                    cultivable = (ParcelleCultivable) this.terrain.getParcelle(i, j);
                    if (!cultivable.getEtat() && cultivable.getMangeable() == null) {
                        Coordonnees c = new Coordonnees(i, j);
                        bouton = new Button(c);
                        bouton.setIcon(new ImageIcon(this.getClass().getResource("terre2.jpeg")));
                        bouton.addActionListener(new EcouteurBoutonsParcelleCultivable());
                    } else if (cultivable.getMangeable() instanceof Riz) {
                        Coordonnees c = new Coordonnees(i, j);
                        bouton = new Button(c);
                        bouton.setIcon(new ImageIcon(this.getClass().getResource("riz.jpeg")));
                        bouton.addActionListener(new EcouteurBoutonsParcelleCultivable());
                    } else if (cultivable.getMangeable() instanceof Radis) {
                        Coordonnees c = new Coordonnees(i, j);
                        bouton = new Button(c);
                        bouton.setIcon(new ImageIcon(this.getClass().getResource("radis.jpeg")));
                        bouton.addActionListener(new EcouteurBoutonsParcelleCultivable());
                    } else if (cultivable.getMangeable() instanceof Tomate) {
                        Coordonnees c = new Coordonnees(i, j);
                        bouton = new Button(c);
                        bouton.setIcon(new ImageIcon(this.getClass().getResource("tomate.jpeg")));
                        bouton.addActionListener(new EcouteurBoutonsParcelleCultivable());
                    } else {
                        Coordonnees c = new Coordonnees(i, j);
                        bouton = new Button(c);
                        bouton.setIcon(new ImageIcon(this.getClass().getResource("herbe1.jpg")));
                        bouton.addActionListener(new EcouteurBoutonsParcelleCultivable());
                    }
                } else if (this.terrain.getParcelle(i, j) instanceof ParcelleRocher) {
                    Coordonnees c = new Coordonnees(i, j);
                    bouton = new Button(c);
                    bouton.setIcon(new ImageIcon(this.getClass().getResource("rocher2.jpg")));
                    bouton.addActionListener(new EcouteurBoutonsParcelleNonCultivable());
                } else {
                    Coordonnees c = new Coordonnees(i, j);
                    bouton = new Button(c);
                    bouton.setIcon(new ImageIcon(this.getClass().getResource("eau.jpg")));
                    bouton.addActionListener(new EcouteurBoutonsParcelleNonCultivable());
                }
                this.panelTerrain.add(bouton);
            }
        }
        this.add(this.panelTerrain);
    }

    private class EcouteurBoutonsParcelleCultivable implements ActionListener {

        public void actionPerformed(ActionEvent arg) {

            Button bout = (Button) arg.getSource();
            Coordonnees c = bout.getCoordonnes();

            if (!blocageBoutons) {
                if (terrain.getParcelle(c) instanceof ParcelleCultivable) {
                    ParcelleCultivable cultivable;
                    ParcelleEau eau = new ParcelleEau();
                    cultivable = (ParcelleCultivable) terrain.getParcelle(c);
                    if (!cultivable.getEtat() && cultivable.getMangeable() == null && terrain.calculerNombreDeParcellesAdjacentes(c, eau) > 0) {
                        menuTemp.removeAll();
                        planter = new Button(c);
                        planter.setText("Planter(" + Parametres.coutPlanter + ")");
                        planter.addActionListener(new EcouteurBoutonPlanter());
                        menuTemp.add(planter);
                        irriguer = new Button(c);
                        irriguer.setText("Irriguer(" + Parametres.coutIrriguer + ")");
                        irriguer.addActionListener(new EcouteurBoutonIrriguer());
                        menuTemp.add(irriguer);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);

                    } else if (!cultivable.getEtat() && cultivable.getMangeable() == null) {
                        menuTemp.removeAll();
                        JLabel info = new JLabel();
                        info.setText("Aucune action possible !");
                        menuTemp.add(info);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);
                    } else if (cultivable.getEtat() && terrain.calculerNombreDeParcellesAdjacentes(c, eau) > 0) {
                        menuTemp.removeAll();
                        irriguer = new Button(c);
                        irriguer.setText("Irriguer(" + Parametres.coutIrriguer + ")");
                        irriguer.addActionListener(new EcouteurBoutonIrriguer());
                        menuTemp.add(irriguer);
                        defricher = new Button(c);
                        defricher.setText("Défricher(" + Parametres.coutDefricher + ")");
                        defricher.addActionListener(new EcouteurBoutonDefricher());
                        menuTemp.add(defricher);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);
                    } else if (!cultivable.getEtat() && cultivable.getMangeable() != null && cultivable.getMangeable().getToursAvantCueillette() > 0) {
                        menuTemp.removeAll();
                        irriguer = new Button(c);
                        irriguer.setText("Irriguer(" + Parametres.coutIrriguer + ")");
                        irriguer.addActionListener(new EcouteurBoutonIrriguer());
                        menuTemp.add(irriguer);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);
                    } else if (cultivable.getMangeable() != null && cultivable.getMangeable().getToursAvantCueillette() == 0) {
                        menuTemp.removeAll();
                        irriguer = new Button(c);
                        irriguer.setText("Irriguer(" + Parametres.coutIrriguer + ")");
                        irriguer.addActionListener(new EcouteurBoutonIrriguer());
                        menuTemp.add(irriguer);
                        recolter = new Button(c);
                        recolter.setText("Récolter(" + Parametres.coutRecolter + ")");
                        recolter.addActionListener(new EcouteurBoutonRecolter());
                        menuTemp.add(recolter);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);
                    } else {
                        menuTemp.removeAll();
                        defricher = new Button(c);
                        defricher.setText("Défricher(" + Parametres.coutDefricher + ")");
                        defricher.addActionListener(new EcouteurBoutonDefricher());
                        menuTemp.add(defricher);
                        menuTemp.setVisible(false);
                        menuTemp.setVisible(true);
                    }
                }
            }
        }
    }

    private class Button extends JButton {

        Coordonnees coordBout;

        public Button(Coordonnees coord) {
            this.coordBout = coord;
        }

        public Coordonnees getCoordonnes() {
            return this.coordBout;
        }
    }

    private class EcouteurBoutonMangeable implements ActionListener {

        public void actionPerformed(ActionEvent arg) {

            Button bout = (Button) arg.getSource();
            Coordonnees c = bout.getCoordonnes();
            menuTemp.removeAll();
            if (bout.getText().equals("Riz") && c != null) {
                jeu.setChoixAlim(1);
                jeu.planter(c);
                menuTemp.setVisible(false);
                menuTemp.setVisible(true);
                jeu.passeAutoTrSuivGraph();
            } else if (bout.getText().equals("Tomate") && c != null) {
                jeu.setChoixAlim(2);
                jeu.planter(c);
                menuTemp.setVisible(false);
                menuTemp.setVisible(true);
                jeu.passeAutoTrSuivGraph();
            } else if (bout.getText().equals("Radis") && c != null) {
                jeu.setChoixAlim(3);
                jeu.planter(c);
                menuTemp.setVisible(false);
                menuTemp.setVisible(true);
                jeu.passeAutoTrSuivGraph();
            } else if (bout.getText().equals("Riz") && c == null) {
                jeu.setChoixAlim(1);
                jeu.mangerGraph2();
                if (!jeu.testCaissetteVideEtPasMangerAssez() && jeu.getAConsommer() == 0) {
                    menuTemp.removeAll();
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                    blocageBoutons = false;
                    jeu.passerAuTourSuivantGraph2();
                } else if (jeu.testCaissetteVideEtPasMangerAssez()) {
                    menuTemp.removeAll();
                    jeu.passerAuTourSuivantGraph2();
                } else {
                    actualiserAffichage();
                    menuTemp.removeAll();
                    jeu.mangerGraph();
                }
            } else if (bout.getText().equals("Tomate") && c == null) {
                jeu.setChoixAlim(2);
                jeu.mangerGraph2();
                if (!jeu.testCaissetteVideEtPasMangerAssez() && jeu.getAConsommer() == 0) {
                    menuTemp.removeAll();
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                    blocageBoutons = false;
                    jeu.passerAuTourSuivantGraph2();
                } else if (jeu.testCaissetteVideEtPasMangerAssez()) {
                    menuTemp.removeAll();
                    jeu.passerAuTourSuivantGraph2();
                } else {
                    actualiserAffichage();
                    menuTemp.removeAll();
                    jeu.mangerGraph();
                }
            } else {
                jeu.setChoixAlim(3);
                jeu.mangerGraph2();
                if (!jeu.testCaissetteVideEtPasMangerAssez() && jeu.getAConsommer() == 0) {
                    menuTemp.removeAll();
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                    blocageBoutons = false;
                    jeu.passerAuTourSuivantGraph2();
                } else if (jeu.testCaissetteVideEtPasMangerAssez()) {
                    menuTemp.removeAll();
                    jeu.passerAuTourSuivantGraph2();
                } else {
                    actualiserAffichage();
                    menuTemp.removeAll();
                    jeu.mangerGraph();
                }
            }
        }
    }

    private class EcouteurBoutonPlanter implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Button bout = (Button) event.getSource();
            Coordonnees c = bout.getCoordonnes();

            menuTemp.removeAll();

            if (jeu.getNbRiz() > 0) {
                riz = new Button(c);
                riz.setText("Riz");
                riz.addActionListener(new EcouteurBoutonMangeable());
                menuTemp.add(riz);
            }

            if (jeu.getNbTomate() > 0) {
                tomate = new Button(c);
                tomate.setText("Tomate");
                tomate.addActionListener(new EcouteurBoutonMangeable());
                menuTemp.add(tomate);
            }

            if (jeu.getNbRadis() > 0) {
                radis = new Button(c);
                radis.setText("Radis");
                radis.addActionListener(new EcouteurBoutonMangeable());
                menuTemp.add(radis);
            }

            menuTemp.setVisible(false);
            menuTemp.setVisible(true);
        }
    }

    private class EcouteurBoutonIrriguer implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Button bout = (Button) event.getSource();
            Coordonnees c = bout.getCoordonnes();

            menuTemp.removeAll();
            jeu.irriguer(c);
            menuTemp.setVisible(false);
            menuTemp.setVisible(true);
            jeu.passeAutoTrSuivGraph();
        }
    }

    private class EcouteurBoutonDefricher implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Button bout = (Button) event.getSource();
            Coordonnees c = bout.getCoordonnes();

            menuTemp.removeAll();
            jeu.defricher(c);
            menuTemp.setVisible(false);
            menuTemp.setVisible(true);
            jeu.passeAutoTrSuivGraph();
        }
    }

    private class EcouteurBoutonRecolter implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            Button bout = (Button) event.getSource();
            Coordonnees c = bout.getCoordonnes();

            menuTemp.removeAll();
            jeu.recolter(c);
            menuTemp.setVisible(false);
            menuTemp.setVisible(true);
            jeu.passeAutoTrSuivGraph();
        }
    }

    private class EcouteurBoutonsParcelleNonCultivable implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (!blocageBoutons) {
                menuTemp.removeAll();
                JLabel info = new JLabel();
                info.setText("Aucune action possible !");
                menuTemp.add(info);
                menuTemp.setVisible(false);
                menuTemp.setVisible(true);
            }
        }
    }

    private class EcouteurBoutonPsTour implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (!blocageBoutons) {
                if (jeu.getNbTour() <= 3) {
                    menuTemp.removeAll();
                    menuTemp.setVisible(false);
                    menuTemp.setVisible(true);
                    jeu.passerAuTourSuivant();
                } else {
                    menuTemp.removeAll();
                    jeu.passerAuTourSuivant1();
                    jeu.mangerGraph();
                }
            }
        }
    }

    private class EcouteurBoutonLegende implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            // création d'une nouvelle fenetre avec la légende
            JFrame legende = new JFrame("Légende");
            legende.setSize(450, 500);
            JPanel panleg = new JPanel();
            panleg.setLayout(new GridLayout(7, 2));

            JButton cultivDefrich = new JButton();
            cultivDefrich.setIcon(new ImageIcon(this.getClass().getResource("terre2.jpeg")));
            JLabel cultDefr = new JLabel();
            cultDefr.setText("Parcelle cultivable défrichée");

            JButton cultivEnfrich = new JButton();
            cultivEnfrich.setIcon(new ImageIcon(this.getClass().getResource("herbe1.jpg")));
            JLabel cultEnfr = new JLabel();
            cultEnfr.setText("Parcelle cultivable en friche");

            JButton pRiz = new JButton();
            pRiz.setIcon(new ImageIcon(this.getClass().getResource("riz.jpeg")));
            JLabel parcRiz = new JLabel();
            parcRiz.setText("Parcelle avec plantation de riz");

            JButton pRadis = new JButton();
            pRadis.setIcon(new ImageIcon(this.getClass().getResource("radis.jpeg")));
            JLabel parcRad = new JLabel();
            parcRad.setText("Parcelle avec plantation de radis");

            JButton pTomate = new JButton();
            pTomate.setIcon(new ImageIcon(this.getClass().getResource("tomate.jpeg")));
            JLabel parcTom = new JLabel();
            parcTom.setText("Parcelle avec plantation de tomate");

            JButton pRocher = new JButton();
            pRocher.setIcon(new ImageIcon(this.getClass().getResource("rocher2.jpg")));
            JLabel parcRoc = new JLabel();
            parcRoc.setText("Parcelle rocher");

            JButton pEau = new JButton();
            pEau.setIcon(new ImageIcon(this.getClass().getResource("eau.jpg")));
            JLabel parcEau = new JLabel();
            parcEau.setText("Parcelle eau");

            panleg.add(cultivDefrich);
            panleg.add(cultDefr);
            panleg.add(cultivEnfrich);
            panleg.add(cultEnfr);
            panleg.add(pRiz);
            panleg.add(parcRiz);
            panleg.add(pRadis);
            panleg.add(parcRad);
            panleg.add(pTomate);
            panleg.add(parcTom);
            panleg.add(pRocher);
            panleg.add(parcRoc);
            panleg.add(pEau);
            panleg.add(parcEau);

            legende.add(panleg);
            legende.setVisible(true);
        }
    }

    public void choixAl() {

        blocageBoutons = true;
        if (jeu.getNbRiz() > 0) {
            riz = new Button(null);
            riz.setText("Riz");
            riz.addActionListener(new EcouteurBoutonMangeable());
            menuTemp.add(riz);
        }

        if (jeu.getNbTomate() > 0) {
            tomate = new Button(null);
            tomate.setText("Tomate");
            tomate.addActionListener(new EcouteurBoutonMangeable());
            menuTemp.add(tomate);
        }

        if (jeu.getNbRadis() > 0) {
            radis = new Button(null);
            radis.setText("Radis");
            radis.addActionListener(new EcouteurBoutonMangeable());
            menuTemp.add(radis);
        }
        menuTemp.setVisible(false);
        menuTemp.setVisible(true);
    }

    public void passageAutoTour() {

        if (!blocageBoutons) {
            if (jeu.getNbTour() <= 3) {
                menuTemp.removeAll();
                menuTemp.setVisible(false);
                menuTemp.setVisible(true);
                jeu.passerAuTourSuivant();
            } else {
                menuTemp.removeAll();
                jeu.passerAuTourSuivant1();
                jeu.mangerGraph();
            }
        }
    }
}
