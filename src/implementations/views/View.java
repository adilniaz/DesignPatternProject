package implementations.views;

import implementations.controller.Chapter;
import implementations.controller.Combat;
import implementations.controller.Start;

public class View {
    
    public static void createVue (Start demarrage, Window fenetre) {
        new StartView(demarrage, fenetre);
    }
    
    public static void createVue (Combat combat, Window fenetre) {
        new CombatView(combat, fenetre);
    }
    
    public static void createVue (Chapter chapitre, Window fenetre) {
        new ChapterView(chapitre, fenetre);
    }
    
}
