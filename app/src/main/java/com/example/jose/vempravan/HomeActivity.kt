package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    var auth: FirebaseUser? = FirebaseAuth.getInstance().currentUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        if(auth != null){
            var intent = Intent(this, UserHome::class.java)
            startActivity(intent)
        }

        btLoginUser.setOnClickListener(){
            val intent = Intent(this, UserLogin::class.java)
            startActivity(intent)
        }

        btCadastrar.setOnClickListener(){
            val intent = Intent(this, UserCadastro::class.java)
            startActivity(intent)
        }

        btNavRecuperar.setOnClickListener(){
            val intent = Intent(this, RecuperaSenha::class.java)
            startActivity(intent)
        }

    }

}
