package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.jose.vempravan.DAO.UserDao
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UserCadastro : AppCompatActivity() {

    lateinit var cpEmailUser : EditText
    lateinit var cpPassworUser : EditText
    lateinit var cpPassworUser2 : EditText
    var cpPlacaVan : EditText? = null
    lateinit var database: FirebaseDatabase
    lateinit var databaseRef : DatabaseReference
    lateinit var autorizacao : FirebaseAuth
    lateinit var btCadastrarUser : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_cadastro)

        cpEmailUser = findViewById(R.id.cpEmailUser)
        cpPassworUser = findViewById(R.id.cpPasswordUser)
        cpPassworUser2 = findViewById(R.id.cpPasswordUser2)
        cpPlacaVan = findViewById(R.id.cpPlacaVan)
        btCadastrarUser = findViewById(R.id.btCadastrarUser)

        database = FirebaseDatabase.getInstance()
        autorizacao = FirebaseAuth.getInstance()
        databaseRef = database.reference.child("Usuario")

        btCadastrarUser.setOnClickListener(){
            criarNovaConta()
            //salvarUser()
        }

    }

    private fun criarNovaConta(){
        val cpEmail : String = cpEmailUser.text.toString()
        val cpPassword : String = cpPassworUser.text.toString()
        val cpPassword2 : String = cpPassworUser2.text.toString()
        val cpPlaca : String = cpPlacaVan?.text.toString()

        if(!TextUtils.isEmpty(cpEmail) && !TextUtils.isEmpty(cpPassword) && !TextUtils.isEmpty(cpPassword2)){
            if(cpPassword.equals(cpPassword2)){
                autorizacao.createUserWithEmailAndPassword(cpEmail, cpPassword).addOnCompleteListener(this){
                    task ->
                        if(task.isComplete){
                            val user: FirebaseUser? = autorizacao.currentUser
                                if (user != null) {
                                    verificarEmail(user)
                                    val userDB = databaseRef.child(user.uid)
                                    userDB.child("Email").setValue(cpEmail)
                                    userDB.child("Senha").setValue(cpPassword)
                                    userDB.child("Repetição da senha").setValue(cpPassword2)
                                    userDB.child("Placa da van").setValue(cpPlaca)
                                    cadastroSucessoIntent()
                                }
                        }
                    }

                }
            }

    }

    private fun verificarEmail(user: FirebaseUser){
        user.sendEmailVerification().addOnCompleteListener(this){
            task ->

            if(task.isComplete){
                Toast.makeText(this, "Email cadastrado com sucesso", Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(this, "O Email não foi cadastrado", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun cadastroSucessoIntent(){
        startActivity(Intent(this, UserLogin::class.java))
    }





    /*private fun salvarUser(){
        val nomeUser = cpEmailUser.text.toString().trim()
        val passwordUser = cpPassworUser.text.toString().trim()
        val passwordUser2 = cpPassworUser2.text.toString().trim()
        val placaVan = cpPlacaVan?.text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("Usuario")
        val usuarioId = ref.push().key

        val usuario =  UserDao(usuarioId.toString(), nomeUser, passwordUser, passwordUser2, placaVan)

        if (usuarioId != null) {

            if(nomeUser.isEmpty() || passwordUser.isEmpty() || passwordUser2.isEmpty()){

                cpEmailUser.error = "O campo do email está vazio ou escrito errado"
                cpPassworUser.error ="O campo de senha está vazio"

            }else{

                ref.child(usuarioId).setValue(usuario).addOnCanceledListener{}
                Toast.makeText(applicationContext, "Usuario salvo com sucesso", Toast.LENGTH_LONG).show()
                val intent = Intent(this, UserLogin::class.java)
                startActivity(intent)

            }
        }

        }
        */


}
