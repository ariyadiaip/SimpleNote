package id.smkn4.smkcoding.simplenote

/**
 * Aip Ariyadi - SMKN 4 Bandung
 */

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

 class CreateNote:AppCompatActivity() {
protected var cursor:Cursor? = null
internal lateinit var dbHelper:DataHelper
internal lateinit var save:Button
internal lateinit var back:ImageView
internal lateinit var edtTitle:EditText
internal lateinit var edtDesc:EditText

override fun onCreate(savedInstanceState:Bundle?) {
super.onCreate(savedInstanceState)
setContentView(R.layout.activity_create_note)

dbHelper = DataHelper(this)
edtTitle = findViewById<View>(R.id.edtTitleCreate) as EditText
edtDesc = findViewById<View>(R.id.edtDescCreate) as EditText
back = findViewById<View>(R.id.backBtnCreate) as ImageView
save = findViewById<View>(R.id.btnSave) as Button

save.setOnClickListener {
    // TODO Auto-generated method stub
    val db = dbHelper.writableDatabase
    db.execSQL(
        "insert into note(title, content, favorite) values('" +
                edtTitle.text.toString() + "','" +
                edtDesc.text.toString() + "','no')"
    )
    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()
    MainActivity.ma.RefreshList()
    finish()
}
    back.setOnClickListener { finish() }
}

}

