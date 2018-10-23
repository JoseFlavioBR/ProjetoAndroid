package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class RecuperaSenha : AppCompatActivity() {

    lateinit var auth: FirebaseAuth
    lateinit var recuperaEmail: EditText
    lateinit var btRecuperaSenha: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recupera_senha)


        auth = FirebaseAuth.getInstance()

        recuperaEmail = findViewById(R.id.cpRecuperaSenha)
        btRecuperaSenha = findViewById(R.id.btRecuperar)
        val recuperarEmail: String = recuperaEmail.text.toString()

        btRecuperaSenha.setOnClickListener(){
            recuperarSenha()
        }

    }

    fun recuperarSenha(){
        auth!!.sendPasswordResetEmail(recuperaEmail.text.toString()).addOnCompleteListener{
            task ->
                if(task.isSuccessful){
                    Toast.makeText(this, "Por favor verifique seu email", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this, "O Email digitado não existeaaaaaaa", Toast.LENGTH_LONG).show()
                }
        }
    }
}
