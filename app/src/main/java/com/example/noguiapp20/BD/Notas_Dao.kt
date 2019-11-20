package com.example.noguiapp20.BD

import androidx.room.*

@Dao
interface Notas_Dao {
    @Insert
    fun savaNota(nota : Notas_Entity)

    @Query("select * from Notas_Entity ")
    fun readNota() : List<Notas_Entity>

    @Query("select *from Notas_Entity WHERE idNota = (:notaId)")
    fun loadAllByIds(notaId: Int): List<Notas_Entity>

    @Delete
    fun delData (not : Notas_Entity)

    @Update
    fun updateData(not : Notas_Entity)
}