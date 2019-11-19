package com.example.noguiapp20

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.noguiapp20.Controlador.AdapterCultivo
import com.example.noguiapp20.Objects.Cultivo
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_info_cultivo.*

class InfoCultivo : AppCompatActivity() {
    //variable de firebase para la referecnia
    lateinit var ref : DatabaseReference


    var sUser:String? = null
    var idCultivo:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_cultivo)

        //la instancia y la ubicacion en la base de datos
        ref = FirebaseDatabase.getInstance().getReference("img").child("doMqt7SUlrHtt1nHY5q3").child("Cultivos")


        val intent = intent
        sUser = intent.getStringExtra("idUser")
        idCultivo = intent.getStringExtra("idCultivo")



       ref.child(idCultivo.toString()).addValueEventListener(object : ValueEventListener {



            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }


            override fun onDataChange(p0: DataSnapshot) {
                tvCultivoName.text = p0.child("Name").getValue().toString()
                tv_Usr.text = sUser.toString()
                tv_id_cultivo.text = p0.child("id").getValue().toString()
            }

        })





    }
}
