package com.example.noguiapp20.Controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.noguiapp20.Objects.Cultivo
import com.example.noguiapp20.Objects.Enfermedades
import com.example.noguiapp20.R

class AdapterEnfermedades(val mCtx : Context, val notificacionId:Int, private val NotificacionList:List<Enfermedades>)
    : ArrayAdapter<Enfermedades>(mCtx,notificacionId,NotificacionList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)


        val view: View = layoutInflater.inflate(notificacionId,null)

        val texto = view.findViewById<TextView>(R.id.tvNombreEnfer)
        val desc = view.findViewById<TextView>(R.id.tvDescrEnfer)
        val foto = view.findViewById<ImageView>(R.id.imgEnfer)

        var Enferme: Enfermedades = NotificacionList[position]


        texto.text = Enferme.NameEnf
        desc.text = Enferme.DescEnf
        val foto_id = Enferme.ImageEnf

        //seleccion de foto dependiendo del tipo de tarea

        when(foto_id){
            1 -> foto.setImageResource(R.drawable.cercosporiosis_chile_uno)
            2 -> foto.setImageResource(R.drawable.roya_maiz_enfermedad_uno)
            3 -> foto.setImageResource(R.drawable.enfermedad_cana_uno)
            else -> foto.setImageResource(R.drawable.cercosporiosis_chile_uno)
        }

        return view
    }

}