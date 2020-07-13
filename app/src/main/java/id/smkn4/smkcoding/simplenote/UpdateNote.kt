package id.smkn4.smkcoding.simplenote

/**
 * Aip Ariyadi - SMKN 4 Bandung
 */

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class UpdateNote : AppCompatActivity() {
    protected lateinit var cursor: Cursor
    internal lateinit var dbHelper: DataHelper
    internal lateinit var save: Button
    internal lateinit var back: ImageView
    internal lateinit var edtTitle: EditText
    internal lateinit var edtDesc: EditText
    internal lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_note)

        dbHelper = DataHelper(this)
        edtTitle = findViewById(R.id.edtTitleEdit) as EditText
        edtDesc = findViewById(R.id.edtDescEdit) as EditText
        val db = dbHelper.readableDatabase
        cursor = db.rawQuery(
            "SELECT * FROM note WHERE id_note = '" +
                    intent.getStringExtra("id") + "'", null
        )
        cursor.moveToFirst()
        if (cursor.count > 0) {
            cursor.moveToPosition(0)
            id = cursor.getString(0).toString()
            edtTitle.setText(cursor.getString(1).toString())
            edtDesc.setText(cursor.getString(2).toString())
        }
        back = findViewById(R.id.backBtnUpdate) as ImageView
        save = findViewById(R.id.btnSaveChanges) as Button
        save.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.execSQL(
                "update note set title='" +
                        edtTitle.text.toString() + "', content='" +
                        edtDesc.text.toString() + "' where id_note='" + id + "'"
            )
            Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
            MainActivity.ma.RefreshList()
            ViewNote.vna.refreshData()
            finish()
        }
        back.setOnClickListener { finish() }
    }

}

