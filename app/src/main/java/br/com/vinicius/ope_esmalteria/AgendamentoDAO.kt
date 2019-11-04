package br.com.vinicius.ope_esmalteria

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface AgendamentoDAO {
    @Query("SELECT * FROM agendamento where id = :id")
    fun getById(id: Long) : Agendamento?

    @Query("SELECT * FROM agendamento")
    fun findAll(): List<Agendamento>

    @Insert
    fun insert(agendamento: Agendamento)

    @Delete
    fun delete(disciplina: Agendamento)

}