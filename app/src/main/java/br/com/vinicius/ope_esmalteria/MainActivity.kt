package br.com.vinicius.ope_esmalteria

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity  : DebugActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        botao_login.setOnClickListener{ onClickBotao() }
    }
     fun onClickBotao(){
         val texto_usuario = campo_usuario.text.toString()
         val texto_senha = campo_senha.text.toString()
         val usuario="aluno"
         val senha="impacta"
         if (texto_usuario==usuario && texto_senha==senha){
             Toast.makeText(this,"Olá $texto_usuario",
                 Toast.LENGTH_SHORT).show()
             var intent = Intent(this,TelaInicialActivity::class.java)
             return startActivity(intent)
         }
         else{
             Toast.makeText(this,"Usuário ou senha incorretos",
                 Toast.LENGTH_SHORT).show()
         }

     }
}
