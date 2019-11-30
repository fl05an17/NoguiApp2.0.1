package com.example.noguiapp20.Controlador

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.noguiapp20.ActividadNotaLocal
import com.example.noguiapp20.Home
import com.example.noguiapp20.Objects.Notas
import com.example.noguiapp20.R

class NotaAdapter(var list: List<Notas>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        // Inflate the custom view
        var nota: Notas = list[position]

        val inflater =
            parent?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_note, null)

        // Get the custom view widgets reference
        val tv = view.findViewById<TextView>(R.id.tvTitulo_N)
        val tx = view.findViewById<TextView>(R.id.tvDescr_N)
        val fec = view.findViewById<TextView>(R.id.tvFecha)
        val check = view.findViewById<CheckBox>(R.id.cbNotePenidente)
        val card = view.findViewById<CardView>(R.id.card)

        // Display color name on text view
        tv.text = nota.titulo
        tx.text = nota.descr_n
        fec.text = nota.fecha
        check.isChecked = nota.realizada


        // Set background color for card view
        card.setCardBackgroundColor(nota.color)

        // Set a click listener for card view
        card.setOnClickListener{
            // Show selected color in a toast message
            //Toast.makeText(parent.context, "Clicked : ${nota.titulo}",Toast.LENGTH_SHORT).show()

            // Get the activity reference from parent
            val activity  = parent.context as Activity

            // Get the activity root view
            val viewGroup = activity.findViewById<ViewGroup>(android.R.id.content).getChildAt(0)

            // Change the root layout background color
            viewGroup.setBackgroundColor(nota.color)

            var intent = Intent(parent.context, ActividadNotaLocal::class.java)
            intent.putExtra("id", nota.idNota.toString())
            parent.context.startActivity(intent)

        }
        // Finally, return the view
        return view
    }
    override fun getItem(position: Int): Any? {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
    // Count the items
    override fun getCount(): Int {
        return list.size
    }
}


