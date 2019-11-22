package com.example.noguiapp20

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.room.Room
import com.example.noguiapp20.BD.AppDB
import com.example.noguiapp20.BD.Notas_Entity
import com.example.noguiapp20.Objects.Notas
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ActividadNotaLocal : AppCompatActivity() {
    var grid_view: GridView? = null
    var titulo: EditText? = null
    var texto:EditText? = null
    var color: TextView? = null
    var db: AppDB? = null
    var id:String = ""
    var button:FloatingActionButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actividad_nota_local)

            var toolbar = findViewById<Toolbar>(R.id.toolbarlocal)
            setSupportActionBar(toolbar)

            var actionBar = supportActionBar
            actionBar?.setDisplayHomeAsUpEnabled(true)

            //grid_view = findViewById(R.id.notaRV)

            titulo = findViewById(R.id.titleET)
            texto = findViewById(R.id.textET)
            color = findViewById(R.id.IDcolor)
            button = findViewById(R.id.btnAgregarN)


        db = Room.databaseBuilder(applicationContext, AppDB :: class.java, "KGDB").build()

        var myintent: Intent = intent
        id = myintent.getStringExtra("id")

        button?.setOnClickListener {
            almacenar()
        }

            if(id != "nueva")
                llenarDatos()
            /*val adapter = ColorBaseAdapter()
            grid_view?.adapter = adapter
            grid_view?.numColumns = 2
            grid_view?.horizontalSpacing = 15
            grid_view?.verticalSpacing = 15
            grid_view?.stretchMode = GridView.STRETCH_COLUMN_WIDTH*/
        }
       override fun onCreateOptionsMenu(menu: Menu?): Boolean {

            if(id == "nueva")
                menuInflater.inflate(R.menu.notalocal,menu)
            else
                menuInflater.inflate(R.menu.edit_nota_local,menu)

            return super.onCreateOptionsMenu(menu)

        }
        override fun onOptionsItemSelected(item: MenuItem?): Boolean {
            when(item?.itemId){
                android.R.id.home -> {
                    finish()
                    return true
                }
                R.id.del_nota_local -> {
                        Thread {
                            var idNota = id.toInt()

                            db?.NotasDao()?.deletebyId(idNota)
                        }.start()
                        finish()
                    return true
                }
                R.id.edit_nota_local -> {
                    if(!titulo?.text?.isEmpty()!! && !texto?.text?.isEmpty()!!){
                        Thread {
                            var note = Notas_Entity()
                            note.titulo = titulo?.text.toString()
                            note.descr_n = texto?.text.toString()
                           // note.color = color?.text.toString().toInt()
                            db?.NotasDao()?.updateData(note)
                        }.start()
                        finish()
                    }

                    return true
                }
                R.id.menu_add_notalocal ->{
                    almacenar()
                    return true
                }
                else ->{return super.onOptionsItemSelected(item)}
            }
        }
    fun llenarDatos(){
            Thread{
                db?.NotasDao()?.loadAllByIds(id.toInt())?.forEach{
                    titulo?.setText(it.titulo)
                    texto?.setText(it.descr_n)
                    //color?.text = it.nota_color.toString()
                }

            }.start()
        }

    fun almacenar(){
        if(!titulo?.text.toString().isEmpty() && !texto?.text.toString().isEmpty() && !color?.text.toString().isEmpty() ) {
            Thread {
                // db = Room.databaseBuilder(applicationContext, AppDB::class.java, "KGDB").build()
                var nota = Notas_Entity()
                nota.titulo = titulo?.text.toString()
                nota.descr_n = texto?.text.toString()
                nota.fecha = "01-01-2019"
                nota.realizada = true
                nota.color = 1
                db?.NotasDao()?.savaNota(nota)
               // nota.nota_color = color?.text.toString().toInt()

            }.start()
            Toast.makeText(this,"Nota Agregada", Toast.LENGTH_LONG).show()
            this.finish()
        }else{
            Toast.makeText(this, "Asegurese de llenar los campos", Toast.LENGTH_LONG).show()
        }
    }
    }


