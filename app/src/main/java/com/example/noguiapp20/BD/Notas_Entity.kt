package com.example.noguiapp20.BD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Notas_Entity {

    @PrimaryKey(autoGenerate = true)
    var idNota : Int? = null

    @ColumnInfo(name = "TITULO")
    var titulo : String = ""

    @ColumnInfo(name = "DESCR_N")
    var descr_n : String = ""

    @ColumnInfo(name = "FECHA")
    var fecha : String = ""

    @ColumnInfo(name = "REALIZADA")
    var realizada : Boolean = false

    @ColumnInfo(name = "COLOR")
    var color : Int = 0

}