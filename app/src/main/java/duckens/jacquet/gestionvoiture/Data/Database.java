package duckens.jacquet.gestionvoiture.Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Database {
    private  DatabaseHandler databaseHandler;
    private SQLiteDatabase DB;

    public Database(Context context){
        databaseHandler=new DatabaseHandler
                (context,"DB_Voiture",null,1);
    }
    public void open(){DB=databaseHandler.getWritableDatabase();}
    public void close(){DB.close();}
    public SQLiteDatabase getDB(){return DB;}

}
