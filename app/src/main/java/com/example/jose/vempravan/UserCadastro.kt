package com.example.jose.vempravan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.example.jose.vempravan.R
import com.google.firebase.FirebaseApp

class UserCadastro : AppCompatActivity() {

    lateinit var cpEmailUser : EditText
    lateinit var cpPassworUser : EditText
    lateinit var cpPassworUser2 : EditText
    lateinit var cpPlacaVan : EditText
    lateinit var btCadastrarUser : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_cadastro)

        cpEmailUser = findViewById(R.id.cpEmailUser)
        cpPassworUser = findViewById(R.id.cpPasswordUser)
        cpPassworUser2 = findViewById(R.id.cpPasswordUser2)
        cpPlacaVan = findViewById(R.id.cpPlacaVan)
        btCadastrarUser = findViewById(R.id.btCadastrarUser)

        btCadastrarUser.setOnClickListener(){
            salvarUser()
        }

    }


    private fun salvarUser(){
        val nomeUser = cpEmailUser.toString().trim()
        val passwordUser = cpPassworUser.toString().trim()
        val passwordUser2 = cpPassworUser2.toString().trim()
        val placaVan = cpPlacaVan.toString().trim()

        if(nomeUser.isEmpty() && passwordUser.isEmpty() && passwordUser2.isEmpty()){
            cpEmailUser.error = "Algum dos 3 campos pode estar vazio nome de usuario, password, ou password2"
        }

        val referenciafirebase = Fire

    }

}
