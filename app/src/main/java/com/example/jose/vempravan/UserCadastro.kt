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

    //VARIAVIS PARA GUARDAR OS VALORES DA TELA DO USUAIRO
    lateinit var cpEmailUser: EditText
    lateinit var cpPassworUser: EditText
    lateinit var cpPassworUser2: EditText
    var cpPlacaVan: EditText? = null
    lateinit var database: FirebaseDatabase
    lateinit var databaseRef: DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var btCadastrarUser: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_cadastro)

        //PEGANDO OS CAMPOS DA VIEW DO USUARIO E SETANDO AS MESMAS NA VARIAVEL
        cpEmailUser = findViewById(R.id.cpEmailUser)
        cpPassworUser = findViewById(R.id.cpPasswordUser)
        cpPassworUser2 = findViewById(R.id.cpPasswordUser2)
        cpPlacaVan = findViewById(R.id.cpPlacaVan)
        btCadastrarUser = findViewById(R.id.btCadastrarUser)

        //INSTANCIA DO FIREBASE
        database = FirebaseDatabase.getInstance()
        //AUTORIZACAO PARA ENTRADA DE USUARIOS
        auth = FirebaseAuth.getInstance()
        //REFERENMCIA UTILIZADA PARA CRIACAO DE UM USUARIO DENTRO DO ESCOPO DO PROJETO
        databaseRef = database.reference.child("Usuario")

        //UM BOTAO
        btCadastrarUser.setOnClickListener() {
            criarNovaConta()
            //salvarUser()
        }

    }

    //METODO PARA CRIAR UMA NOVA CONTA
    private fun criarNovaConta() {
        //PEGANDO VALORES DE UMA VARIAVEL E TRANSOFRMANDO EM UMA STRING
        val cpEmail: String = cpEmailUser.text.toString()
        val cpPassword: String = cpPassworUser.text.toString()
        val cpPassword2: String = cpPassworUser2.text.toString()
        val cpPlaca: String = cpPlacaVan?.text.toString()

        //VENDO SE TEM ALGUM CAMPO VAZIO, SE NÃO TIVER ELE CRIA UM USUARIO COM EMAIL E SENHA
        if (!cpEmail.isEmpty() && !cpEmail.isEmpty() && !cpPassword2.isEmpty()) {
            if (cpPassword.equals(cpPassword2)) {
                auth.createUserWithEmailAndPassword(cpEmail, cpPassword).addOnCompleteListener(this) { task ->
                    if (task.isComplete) {
                        val user: FirebaseUser? = auth.currentUser
                        verificarEmail(user!!)
                        val userDB = databaseRef.child(user.uid)
                        userDB.child("Email").setValue(cpEmail)
                        userDB.child("Senha").setValue(cpPassword)
                        userDB.child("Repetição da senha").setValue(cpPassword2)
                        userDB.child("Placa da van").setValue(cpPlaca)
                    }
                }

            }
        }

    }

    //METODO PARA VER SE O USUARIO FOI CADASTRADO OU NÃO
    private fun verificarEmail(user: FirebaseUser) {
        user.sendEmailVerification().addOnCompleteListener(this) { task ->

            if (task.isComplete) {
                Toast.makeText(this, "Email cadastrado com sucesso", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "O Email não foi cadastrado", Toast.LENGTH_LONG).show()
            }
        }
    }

    //MOVE PARA OUTRA VIEW SE O USUARIO FOR CRIADO OU SE NÃO FOR TAMBÉM
    private fun cadastroSucessoIntent() {
        startActivity(Intent(this, UserLogin::class.java))
    }


    /*private fun salvarUser() {
        val nomeUser = cpEmailUser.text.toString().trim()
        val passwordUser = cpPassworUser.text.toString().trim()
        val passwordUser2 = cpPassworUser2.text.toString().trim()
        val placaVan = cpPlacaVan?.text.toString().trim()

        val ref = FirebaseDatabase.getInstance().getReference("Usuario")
        val usuarioId = ref.push().key

        val usuario = UserDao(usuarioId.toString(), nomeUser, passwordUser, passwordUser2, placaVan)

        if (usuarioId != null) {

            if (nomeUser.isEmpty() || passwordUser.isEmpty() || passwordUser2.isEmpty()) {

                cpEmailUser.error = "O campo do email está vazio ou escrito errado"
                cpPassworUser.error = "O campo de senha está vazio"

            } else {

                ref.child(usuarioId).setValue(usuario).addOnCanceledListener {}
                Toast.makeText(applicationContext, "Usuario salvo com sucesso", Toast.LENGTH_LONG).show()

                val intent = Intent(this, UserLogin::class.java)
                startActivity(intent)

                val cpEmail: String = cpEmailUser.text.toString()
                val cpPassword: String = cpPassworUser.text.toString()
                val cpPassword2: String = cpPassworUser2.text.toString()
                val cpPlaca: String = cpPlacaVan?.text.toString()

                if (!TextUtils.isEmpty(cpEmail) && !TextUtils.isEmpty(cpPassword) && !TextUtils.isEmpty(cpPassword2)) {
                    if (cpPassword.equals(cpPassword2)) {
                        auth.createUserWithEmailAndPassword(cpEmail, cpPassword).addOnCompleteListener(this) { task ->
                            if (task.isComplete) {
                                val user: FirebaseUser? = auth.currentUser
                                val userDB = databaseRef.child(user!!.uid)
                                userDB.child("Email").setValue(cpEmail)
                                userDB.child("Senha").setValue(cpPassword)
                                userDB.child("Repetição da senha").setValue(cpPassword2)
                                userDB.child("Placa da van").setValue(cpPlaca)
                            }
                        }

                    }
                }
            }
        }*/
}






