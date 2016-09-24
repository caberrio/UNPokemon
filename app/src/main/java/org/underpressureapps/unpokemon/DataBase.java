package org.underpressureapps.unpokemon;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class DataBase extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "unpokemon.db";


    //Contacts table name
    public static final String TABLE_1 = "dbCharacter";
    public static final String TABLE_2 = "dbPokemon";
    public static final String TABLE_3 = "dbPokeBall";
    public static final String TABLE_4 = "dbBag";
    public static final String TABLE_5 = "dbPotion";
    public static final String TABLE_6 = "dbPokeStop";


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_1 = "CREATE TABLE " + TABLE_1 + "("
                + "id" + " integer primary key," + "name" + " text not null,"
                + "gender" + " text,"+ "bag_id" + " int," + "pokemon_id" + " int" + ")";

        String CREATE_TABLE_2 = "CREATE TABLE " + TABLE_2 + "("
                + "id" + " integer primary key," + "name" + " text,"
                + "type" + " text,"+ "total" + " int," + "hp" + " int," + "attack" + " int,"
                + "defense" + " int," + "sp_attack" + " int," + "sp_defense" + " int,"
                + "speed" + "img_front" + " text," + "img_back" + " text," + "gif_front" + " text,"
                + "gif_back" + "imgurl" + " text," + "evolution_id" + " int" + ")";

        String CREATE_TABLE_3 = "CREATE TABLE " + TABLE_3 + "("
                + "id" + " integer primary key," + "name" + " text not null,"
                + "type" + " text" + ")";

        String CREATE_TABLE_4 = "CREATE TABLE " + TABLE_4 + "("
                + "id" + " integer primary key," + "item_id" + " int not null" + ")";

        String CREATE_TABLE_5 = "CREATE TABLE " + TABLE_5 + "("
                + "id" + " integer primary key," + "name" + " text not null" + ")";

        String CREATE_TABLE_6 = "CREATE TABLE " + TABLE_6 + "("
                + "id" + " integer primary key," + "latitude" + " text not null,"
                + "longitude" + " text" + ")";

        if (db.isOpen()) {
            db.execSQL(CREATE_TABLE_1);
            db.execSQL(CREATE_TABLE_2);
            db.execSQL(CREATE_TABLE_3);
            db.execSQL(CREATE_TABLE_4);
            db.execSQL(CREATE_TABLE_5);
            db.execSQL(CREATE_TABLE_6);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_3);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_4);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_5);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_6);
    }
    public SQLiteDatabase getWriteDatabase(){
        return super.getWritableDatabase();
    }


}
