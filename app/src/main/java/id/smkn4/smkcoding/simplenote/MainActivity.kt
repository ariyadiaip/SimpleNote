package id.smkn4.smkcoding.simplenote

/**
 * Aip Ariyadi - SMKN 4 Bandung
 */

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    internal lateinit var daftar: Array<String?>
    internal lateinit var lvNote: ListView
    protected lateinit var cursor: Cursor
    internal lateinit var dbcenter: DataHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add = findViewById<View>(R.id.addNote) as FloatingActionButton

        add.setOnClickListener {
            val intent = Intent(this@MainActivity, CreateNote::class.java)
            startActivity(intent)
        }
        ma = this
        dbcenter = DataHelper(this)
        RefreshList()
    }

    fun RefreshList() {
        val db = dbcenter.readableDatabase
        cursor = db.rawQuery("SELECT * FROM note", null)
        daftar = arrayOfNulls(cursor.count)
        cursor.moveToFirst()
        for (cc in 0 until cursor.count) {
            cursor.moveToPosition(cc)
            daftar[cc] = cursor.getString(1).toString()
        }
        lvNote = findViewById<View>(R.id.listViewNote) as ListView
        lvNote.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar)
        lvNote.isSelected = true
        lvNote.onItemClickListener = OnItemClickListener { arg0, arg1, arg2, arg3 ->
            val selection = daftar[arg2]
            //.getItemAtPosition(arg2).toString();
            val i = Intent(applicationContext, ViewNote::class.java)
            i.putExtra("title", selection)
            startActivity(i)
            (lvNote.adapter as ArrayAdapter<*>).notifyDataSetInvalidated()
        }
    }

    companion object {
        lateinit var ma: MainActivity
    }

}