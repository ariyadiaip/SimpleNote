package id.smkn4.smkcoding.simplenote

/**
 * Aip Ariyadi - SMKN 4 Bandung
 */

import android.content.Intent
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class ViewNote : AppCompatActivity() {
    protected lateinit var cursor: Cursor
    internal lateinit var dbHelper: DataHelper
    internal lateinit var back: ImageView
    internal lateinit var edit: ImageView
    internal lateinit var delete: ImageView
    internal lateinit var tvTitle: TextView
    internal lateinit var tvDesc: TextView
    internal lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_note)
        vna = this
        dbHelper = DataHelper(this)
        refreshData()
    }

    fun refreshData() {
        tvTitle = findViewById(R.id.tvTitleView) as TextView
        tvDesc = findViewById(R.id.tvDescriptView) as TextView
        val db = dbHelper.readableDatabase
        cursor = db.rawQuery(
            "SELECT * FROM note WHERE title = '" +
                    intent.getStringExtra("title") + "'", null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            cursor.moveToPosition(0)
            id = cursor.getString(0).toString()
            tvTitle.text = cursor.getString(1).toString()
            tvDesc.text = cursor.getString(2).toString()
        }
        back = findViewById(R.id.backBtnView) as ImageView
        back.setOnClickListener {
            MainActivity.ma.RefreshList()
            finish()
        }
        edit = findViewById(R.id.editBtn) as ImageView
        edit.setOnClickListener {
            val `in` = Intent(applicationContext, UpdateNote::class.java)
            `in`.putExtra("id", id)
            startActivity(`in`)
        }
        delete = findViewById(R.id.deleteBtn) as ImageView
        delete.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL("delete from note where title = '" + tvTitle.text.toString() + "'")
            MainActivity.ma.RefreshList()
            finish()
        }
    }

    companion object {
        lateinit var vna: ViewNote
    }

}