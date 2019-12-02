package com.example.noguiapp20.Enfermedades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import com.example.noguiapp20.Controlador.AdapterEnfermedades
import com.example.noguiapp20.Objects.Enfermedades
import com.example.noguiapp20.R
import com.google.firebase.database.*


class Enfermedades_Detail : AppCompatActivity() {

    //variable de firebase para la referecnia
    lateinit var ref1 : DatabaseReference

    var nameCult:String? = null

    lateinit var EnfermedadesList:MutableList<Enfermedades>
    lateinit var listViewEnfer:ListView
    var adapter: AdapterEnfermedades? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_enfermedades)
        val intent = intent
        nameCult = intent.getStringExtra("nameCult")

        EnfermedadesList = mutableListOf()
        listViewEnfer = findViewById(R.id.listviewEnfer)


            //la instancia y la ubicacion en la base de datos
            ref1 = FirebaseDatabase.getInstance().getReference("img").child("doMqt7SUlrHtt1nHY5q3").child("Enfermedades")

            ref1.child(nameCult.toString()).addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if(p0!!.exists()){
                        EnfermedadesList.clear()
                        for(e in p0.children){
                            val enfermedad = e.getValue(Enfermedades::class.java)

                            EnfermedadesList.add(enfermedad!!)
                        }

                        adapter =  AdapterEnfermedades(this@Enfermedades_Detail,R.layout.item_enfermedad, EnfermedadesList)
                        listViewEnfer.adapter = adapter
                    }
                }
            })

    }
}
