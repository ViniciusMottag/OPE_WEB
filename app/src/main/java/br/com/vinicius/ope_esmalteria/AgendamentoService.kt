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
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/agendamento"
            val json = HttpHelper.get(url)
            return parserJson(json)
        } else {
            return ArrayList<Agendamento>()
        }
    }

    fun save(agendamento: Agendamento): Response {
        val json = HttpHelper.post("$host/agendamento", agendamento.toJson())
        return parserJson(json)
    }

    fun delete(agendamento: Agendamento): Response {
        Log.d(TAG, agendamento.id.toString())
        val url = "$host/agendamento/${agendamento.id}"
        val json = HttpHelper.delete(url)
        Log.d(TAG, json)
        return parserJson(json)
    }

    inline fun <reified T> parserJson(json: String): T {
        val type = object : TypeToken<T>(){}.type
        return Gson().fromJson<T>(json, type)
    }
}
