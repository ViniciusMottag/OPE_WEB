package br.com.vinicius.ope_esmalteria

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "agendamento")
class Agendamento:Serializable {

    @PrimaryKey
    var id:Long=0
    var nome = ""
    var data = ""
    var horario=""
    var foto=""

    override fun toString(): String {
        return "Agendamento(nome='$nome')"
    }
    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }

}