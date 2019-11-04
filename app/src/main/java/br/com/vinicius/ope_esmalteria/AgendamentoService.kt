package br.com.vinicius.ope_esmalteria

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Response

object AgendamentoService {

    val host = "https://viniciusmottag.pythonanywhere.com/"
    val TAG = "WS_OPE_Esmalteria"

    fun getAgendamento (context: Context): List<Agendamento> {
        var agendamento=ArrayList<Agendamento>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/agendamento"
            val json = HttpHelper.get(url)
            return parserJson(json)
            for (a in agendamento){
                saveOffline(a)
            }
            return agendamento
        } else {
            val dao= DatabaseManager.getAgendamentoDAO()
            val agendamento = dao.findAll()
            return agendamento
        }
    }

    fun save(agendamento: Agendamento): Response {
        val json = HttpHelper.post("$host/agendamento", agendamento.toJson())
        return parserJson(json)
    }

    fun delete(agendamento: Agendamento): br.com.vinicius.ope_esmalteria.Response {
        if (AndroidUtils.isInternetDisponivel(OPEApplication.getInstance().applicationContext)){
            val url = "$host/agendamento/${agendamento.id}"
            val json = HttpHelper.delete(url)
            return parserJson(json)
        } else{
            val dao = DatabaseManager.getAgendamentoDAO()
            dao.delete(agendamento)
            return Response(status = "OK", msg = "Dados salvos localmente")

        }
//        Log.d(TAG, agendamento.id.toString())
//        val url = "$host/agendamento/${agendamento.id}"
//        val json = HttpHelper.delete(url)
//        Log.d(TAG, json)
//        return parserJson(json)
    }
    fun saveOffline(agendamento: Agendamento) : Boolean {
        val dao = DatabaseManager.getAgendamentoDAO()
        if (! existeDisciplina(agendamento)) {
            dao.insert(agendamento)
        }
        return true
    }
    fun existeDisciplina(agendamento: Agendamento): Boolean {
        val dao = DatabaseManager.getAgendamentoDAO()
        return dao.getById(agendamento.id) != null

    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
