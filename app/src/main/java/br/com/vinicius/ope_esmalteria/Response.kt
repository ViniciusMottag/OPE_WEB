package br.com.vinicius.ope_esmalteria

class Response (val status:String, val msg:String){
    fun isOK() = "OK".equals(status, ignoreCase = true)
}