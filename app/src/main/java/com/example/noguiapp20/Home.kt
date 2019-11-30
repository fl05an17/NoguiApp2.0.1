package com.example.noguiapp20

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ListView
import android.widget.Toast
import com.example.noguiapp20.Controlador.AdapterCultivo
import com.example.noguiapp20.Objects.Cultivo
import com.google.firebase.database.*

class Home : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    lateinit var CultivoList:MutableList<Cultivo>
    lateinit var listviewCultivo: ListView
    var adapter: AdapterCultivo? =null
    var sUser:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val intent = intent
        sUser = intent.getStringExtra("id")


        CultivoList = mutableListOf()

        listviewCultivo = findViewById(R.id.listviewCultivo)

        val Image = findViewById<ImageView>(R.id.imgCultivo)




        ref = FirebaseDatabase.getInstance().getReference("img").child("doMqt7SUlrHtt1nHY5q3").child("Cultivos")


        ref.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {

                if (p0!!.exists()){
                    CultivoList.clear()
                    for (e in p0.children){
                        val cultivo = e.getValue(Cultivo::class.java)

                        CultivoList.add(cultivo!!)
                    }

                    adapter = AdapterCultivo(this@Home,R.layout.itemcultivo, CultivoList)
                    listviewCultivo.adapter = adapter

                }
            }
        })

        listviewCultivo.setOnItemClickListener{ parent, view, position, id ->

            Toast.makeText(this,CultivoList.get(position).id, Toast.LENGTH_SHORT).show();
            val intent = Intent(this,InfoCultivo::class.java)
            intent.putExtra("idCultivo",CultivoList.get(position).id)
            intent.putExtra("idUser",sUser)
            startActivity(intent)

        }


    }
}
