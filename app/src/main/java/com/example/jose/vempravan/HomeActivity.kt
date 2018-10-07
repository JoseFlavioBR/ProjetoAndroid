package com.example.jose.vempravan

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        btLoginUser.setOnClickListener(){
            val intent = Intent(this, UserLogin::class.java)
            startActivity(intent)
        }

        btCadastrar.setOnClickListener(){
            val intent = Intent(this, UserCadastro::class.java)
            startActivity(intent)
        }
    }

}
