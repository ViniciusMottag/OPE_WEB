package br.com.vinicius.ope_esmalteria

import android.arch.persistence.room.Room

object DatabaseManager {
    // singleton
    private var dbInstance: OPEDatabase
    init {
        val appContext = LMSApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
            appContext, // contexto global
            OPEDatabase::class.java, // Referência da classe do banco
            "lms.sqlite" // nome do arquivo do banco
        ).build()
    }

    fun getAgendamentoDAO(): AgendamentoDAO {
        return dbInstance.agendamentoaDAO()
    }
}