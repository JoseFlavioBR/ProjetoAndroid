package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.jose.vempravan.R
import com.google.firebase.auth.FirebaseAuth

class UserLogin : AppCompatActivity() {

    lateinit var cpEmail: EditText
    lateinit var cpPassword: EditText
    lateinit var cbManterConectado: CheckBox
    lateinit var btEntrarUser: Button
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_login)

        cbManterConectado = findViewById(R.id.cbManterConectado)
        cpEmail = findViewById(R.id.cpEmail)
        cpPassword = findViewById(R.id.cpPassword)
        btEntrarUser = findViewById(R.id.btEntrarUser)
        auth = FirebaseAuth.getInstance()

        btEntrarUser.setOnClickListener() {
            //loginUsuario()

            val user: String = cpEmail.text.toString()
            val password: String = cpPassword.text.toString()

            if(!user.isEmpty() && !password.isEmpty()){
                auth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this){
                    task ->
                    if(task.isSuccessful){
                        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
                        val editor = pref.edit()
                        if(cbManterConectado.isChecked){
                            editor.putBoolean("manterConectado", true).apply()
                        }else{
                            editor.putBoolean("manterConectado", false).apply()
                        }
                        SucessoLogin()
                    }else{
                        Toast.makeText(this, "Este usuario não existe", Toast.LENGTH_LONG).show()
                    }
                }
            }

        }



    }
    private fun SucessoLogin() {
        val intent = Intent(this, UserHome::class.java)
        startActivity(intent)
    }


    /*      if(!TextUtils.isEmpty(user) && TextUtils.isEmpty(password)){
            auth.signInWithEmailAndPassword(user, password).addOnCompleteListener(this){
                task ->
                if(task.isSuccessful){
                    SucessoLogin()
                }else{
                    Toast.makeText(this, "Erro de autenticação", Toast.LENGTH_LONG).show()
                }
            }
        }
    }*/


}
