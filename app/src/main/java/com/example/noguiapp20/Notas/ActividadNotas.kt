package com.example.noguiapp20.Notas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.GridView
import android.widget.ViewSwitcher
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.noguiapp20.BD.AppDB
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

        grid_view = findViewById(R.id.notesRV)
       // viewSwitcher = findViewById(R.id.switchView)
        var toolbar = findViewById<Toolbar>(R.id.toolbarlocal)
        setSupportActionBar(toolbar)

        db = Room.databaseBuilder(applicationContext, AppDB::class.java, "KGDB").build()

        lista = mutableListOf()
        lista?.clear()
        CargaDatos()
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
