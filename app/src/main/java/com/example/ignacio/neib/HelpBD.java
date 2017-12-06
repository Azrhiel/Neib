package com.example.ignacio.neib;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Ignacio on 04-12-2017.
 */

public class HelpBD extends SQLiteOpenHelper{

    public static class DATA implements BaseColumns {
    public static final String TableName = "Directorio";
    public static final String Columna_Id = "Id";
    public static final String Columna_Nombres = "NameStore";
    public static final String Columna_Ciudad = "City";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String Crear_Tabla =
    "CREATE TABLE " + DATA.TableName + " (" +
                    DATA.Columna_Id + " INTEGER PRIMARY KEY," +
                    DATA.Columna_Nombres + TEXT_TYPE + COMMA_SEP +
                    DATA.Columna_Ciudad + TEXT_TYPE + " )";

        private static final String SQL_DELETE_ENTRIES =
    "DROP TABLE IF EXISTS " + DATA.TableName;
}

        public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "NeibBD.db";

    public HelpBD(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATA.Crear_Tabla);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATA.SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
