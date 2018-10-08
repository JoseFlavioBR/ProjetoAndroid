package com.example.jose.vempravan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jose.vempravan.DAO.UserDao
import com.example.jose.vempravan.R
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase

class UserCadastro : AppCompatActivity() {

    lateinit var cpEmailUser : EditText
    lateinit var cpPassworUser : EditText
    lateinit var cpPassworUser2 : EditText
    var cpPlacaVan : EditText? = null
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
        val nomeUser = cpEmailUser.text.toString().trim()
        val passwordUser = cpPassworUser.text.toString().trim()
        val passwordUser2 = cpPassworUser2.text.toString().trim()
        val placaVan = cpPlacaVan!!.text.toString().trim()


        if(nomeUser.isEmpty() && passwordUser.isEmpty() && passwordUser2.isEmpty()){
            cpEmailUser.error = "Algum dos 3 campos pode estar vazio nome de usuario, password, ou password2"
        }

        val ref = FirebaseDatabase.getInstance().getReference("Usuario")
        val usuarioId = ref.push().key

        val usuario =  UserDao(usuarioId.toString(), nomeUser, passwordUser, passwordUser2)

        if (usuarioId != null) {
            ref.child(usuarioId).setValue(usuario).addOnCanceledListener {
                Toast.makeText(applicationContext, "Usuario salvo com sucesso", Toast.LENGTH_LONG).show()
            }
        }

    }

}
