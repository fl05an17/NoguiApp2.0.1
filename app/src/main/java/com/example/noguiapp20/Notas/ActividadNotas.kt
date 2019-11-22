package com.example.noguiapp20.Notas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.GridView
import android.widget.ViewSwitcher
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.noguiapp20.ActividadNotaLocal
import com.example.noguiapp20.BD.AppDB
import com.example.noguiapp20.BD.Notas_Entity
import com.example.noguiapp20.Controlador.NotaAdapter
import com.example.noguiapp20.Objects.Notas
import com.example.noguiapp20.R

class ActividadNotas : AppCompatActivity() {

    lateinit var grid_view: GridView
    var viewSwitcher: ViewSwitcher? = null
    var lista:MutableList<Notas>? = null
    var db: AppDB? = null

    var estado:Int = 0
    var num_col:Int = 2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_notas)

        var toolbar = findViewById<Toolbar>(R.id.toolbarlocal)
        setSupportActionBar(toolbar)

        var actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)


        grid_view = findViewById(R.id.notesRV)
       // viewSwitcher = findViewById(R.id.switchView)

        db = Room.databaseBuilder(applicationContext, AppDB::class.java, "KGDB").build()

        lista = mutableListOf()
        lista?.clear()

     /*   Thread{
            var nota = Notas_Entity()
            nota.titulo = "prueba"
            nota.descr_n = "esto es una prueba"
            nota.fecha = "01-01-2019"
            nota.realizada = true
            nota.color = 1
            db?.NotasDao()?.savaNota(nota)
        }.start()

*/
        CargaDatos()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.notalocal,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
            R.id.menu_add_notalocal ->{
                var intent = Intent(this,ActividadNotaLocal::class.java)
                intent.putExtra("id","nueva")
                startActivity(intent)
                return true
            }
            else ->{return super.onOptionsItemSelected(item)}
        }
    }
    override fun onResume() {
        super.onResume()
        if(estado == 1) {
            lista?.clear()
            CargaDatos()
            estado = 0
        }
    }

    override fun onPause() {
        super.onPause()
        estado = 1
    }
    public fun CargaDatos(){
        Thread{
            db?.NotasDao()?.readNota()?.forEach{
                Log.i("@DATOS",""""TITULO : ${it.titulo} """")
                Log.i("@DATOS",""""TEXTO : ${it.descr_n} """")

                lista?.add(
                    Notas(it.idNota!!.toInt(),it.titulo,it.descr_n,it.fecha,it.realizada,it.color)
                )
            }
        }.start()
        val adapter = NotaAdapter(lista!!)
        grid_view.adapter = adapter
        grid_view.numColumns = num_col
        grid_view.horizontalSpacing = 15
        grid_view.verticalSpacing = 15
        grid_view.stretchMode = GridView.STRETCH_COLUMN_WIDTH
    }


}
