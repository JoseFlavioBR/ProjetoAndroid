package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.Toast
import com.example.jose.vempravan.R
import com.google.firebase.auth.FirebaseAuth

class UserLogin : AppCompatActivity() {

    lateinit var cpEmailUser : EditText
    lateinit var cpPassworUser: EditText
    lateinit var autorizacao : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        cpEmailUser = findViewById(R.id.cpEmailUser)
        cpPassworUser = findViewById(R.id.cpPasswordUser)
        autorizacao = FirebaseAuth.getInstance()

    }

    fun loginUsuario(){
        val user: String = cpEmailUser.text.toString()
        val password: String = cpPassworUser.text.toString()

        if(!TextUtils.isEmpty(user) && TextUtils.isEmpty(password)){
            autorizacao.signInWithEmailAndPassword(user, password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    usuarioHome()
                }else{
                    Toast.makeText(this, "Erro na autenticação", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    private fun usuarioHome(){
        startActivity(Intent(this, UserHome::class.java))
    }



}
