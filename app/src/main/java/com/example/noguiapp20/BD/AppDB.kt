package com.example.noguiapp20.BD

import androidx.room.Database
import androidx.room.RoomDatabase

@Database (entities = [(Users_Entity::class),(Notas_Entity::class)],version = 2)
abstract class AppDB : RoomDatabase() {

    abstract fun UsuariosDao() : Users_Dao
    abstract fun NotasDao() : Notas_Dao

}