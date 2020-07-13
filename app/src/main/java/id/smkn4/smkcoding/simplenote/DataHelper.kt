package id.smkn4.smkcoding.simplenote

/**
 * Aip Ariyadi - SMKN 4 Bandung
 */

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DataHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        var sql =
            "create table note(id_note integer primary key autoincrement, title text null, content text null, favorite text null);"
        Log.d("Data", "onCreate: $sql")
        db.execSQL(sql)
        sql =
            "INSERT INTO note (title, content, favorite) VALUES ( 'Example Note', 'This is an example note. This note is automatically created when you first install this application', 'no');"
        db.execSQL(sql)

    }

    override fun onUpgrade(arg0: SQLiteDatabase, arg1: Int, arg2: Int) {

    }

    companion object {

        private val DATABASE_NAME = "simplenote.db"
        private val DATABASE_VERSION = 1
    }

}
