package com.example.noguiapp20.Controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.noguiapp20.Objects.Cultivo
import com.example.noguiapp20.R

class AdapterCultivo(val mCtx : Context, val notificacionId:Int, private val NotificacionList:List<Cultivo>)
    : ArrayAdapter<Cultivo>(mCtx,notificacionId,NotificacionList)
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val layoutInflater : LayoutInflater = LayoutInflater.from(mCtx)


        val view: View = layoutInflater.inflate(notificacionId,null)

        val texto = view.findViewById<TextView>(R.id.tvNombreCultivo)
        //val desc = view.findViewById<TextView>(R.id.tvDesc)
        val foto = view.findViewById<ImageView>(R.id.imgCultivo)

        var Cultivos: Cultivo = NotificacionList[position]


        texto.text = Cultivos.Name
        //desc.text = Cultivos.Desc
        var foto_id = Cultivos.Image

        //seleccion de foto dependiendo del tipo de tarea

        /*when(foto_id){
            1 -> foto.setImageResource(R.drawable.maiz_c)
            2 -> foto.setImageResource(R.drawable.cana_azucar)
            3 -> foto.setImageResource(R.drawable.chile_rojo)
            else -> foto.setImageResource(R.drawable.maiz_c)
        }*/

        return view
    }

}