package duckens.jacquet.gestionvoiture.Modele;

import java.util.ArrayList;
import java.util.List;

public class Session {
    public static Voiture currentVoiture;
    public static List<Voiture> listVoitures=new ArrayList<>();

    public static Voiture getCurrentVoiture() {
        return currentVoiture;
    }

    public static void setCurrentVoiture(Voiture currentVoiture) {
        Session.currentVoiture = currentVoiture;
    }

    public static List<Voiture> getListVoitures() {
        return listVoitures;
    }

    public static void setListVoitures(List<Voiture> listVoitures) {
        Session.listVoitures = listVoitures;
    }
}
