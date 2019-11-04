package br.com.vinicius.ope_esmalteria

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Agendamento::class), version = 1)
abstract class OPEDatabase: RoomDatabase(){
    abstract fun agendamentoaDAO(): AgendamentoDAO

}