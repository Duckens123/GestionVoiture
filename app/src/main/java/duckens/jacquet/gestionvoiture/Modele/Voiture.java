package duckens.jacquet.gestionvoiture.Modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import duckens.jacquet.gestionvoiture.Data.Database;

public class Voiture {
    String numImmatriculation, marque, modele, couleur, transmission;
    int annee, id;

    public Voiture() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumImmatriculation() {
        return numImmatriculation;
    }

    public void setNumImmatriculation(String numImmatriculation) {
        this.numImmatriculation = numImmatriculation;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    @NonNull
    @Override
    public String toString() {
        return this.marque+ " "+this.modele+" "+this.annee+" "+this.transmission+" "+this.couleur+" "+this.numImmatriculation;
    }

    //Gestion de la base de donnees
    public static String getScript() {
        String script = " CREATE TABLE Voiture(id INTEGER PRIMARY KEY AUTOINCREMENT,";
        script += "numImmatriculation TEXT,";
        script += "marque TEXT";
        script += "modele TEXT,";
        script += "annee TEXT,";
        script += "transmission TEXT,";
        script += "couleur TEXT);";
        return script;
    }

    public static long Insert(Context context, Voiture obj) {
        Database db = new Database(context);
        db.open();
        ContentValues valeur = new ContentValues();
        valeur.put("numImmatriculation", obj.getNumImmatriculation());
        valeur.put("marque", obj.getMarque());
        valeur.put("modele", obj.getModele());
        valeur.put("annee", obj.getAnnee());
        valeur.put("transmission", obj.getTransmission());
        valeur.put("couleur", obj.getCouleur());
        db.getDB().insert("Voiture", null, valeur);
        long l = 0;
        Cursor cursor = db.getDB().rawQuery("SELECT MAX(id) FROM Voiture", null);
        if (cursor.moveToNext()) {
            l = cursor.getLong(0);

        }
        cursor.close();
        db.close();

        return l;
    }

    public static void update(Context context, Voiture obj) {
        Database db = new Database(context);
        db.open();
        ContentValues valeur = new ContentValues();
        valeur.put("numImmatriculation", obj.getNumImmatriculation());
        valeur.put("marque", obj.getMarque());
        valeur.put("modele", obj.getModele());
        valeur.put("annee", obj.getAnnee());
        valeur.put("transmission", obj.getTransmission());
        valeur.put("couleur", obj.getCouleur());
        db.getDB().update("Voiture", valeur, "id=?", new String[]{String.valueOf(obj.getId())});
        db.close();
    }

    public static void delete(Context context, long id) {
        Database db = new Database(context);
        db.open();
        db.getDB().delete("Voiture", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public static void deleteAll(Context context, long id) {
        Database db = new Database(context);
        db.open();
        db.getDB().delete("Voiture", null, null);
        db.close();
    }

    public static List<Voiture> selectAll(Context context) {
        Database db = new Database(context);
        db.open();
        String query = "select ";
        query += "id, numImmatriculation,marque,modele,annee,transmission,couleur";
        query += "from Voiture";
        Cursor cursor = db.getDB().rawQuery(query, null);
        List<Voiture> list = new ArrayList<>();
        while (cursor.moveToNext()) {
            Voiture obj = new Voiture();
            obj.setId(cursor.getInt(0));
            obj.setNumImmatriculation(cursor.getString(1));
            obj.setMarque(cursor.getString(2));
            obj.setModele(cursor.getString(3));
            obj.setAnnee(cursor.getInt(4));
            obj.setTransmission(cursor.getString(5));
            obj.setCouleur(cursor.getString(6));
            list.add(obj);
        }
        cursor.close();
        db.close();
        return list;

    }
}
