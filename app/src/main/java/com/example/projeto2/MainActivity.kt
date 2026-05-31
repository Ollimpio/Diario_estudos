package com.example.projeto2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val btnMateria = findViewById<Button>(R.id.btnMateria)
        val btnEstudo = findViewById<Button>(R.id.btnEstudo)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)

        btnMateria.setOnClickListener {

            val tela2 = Intent(this, MainActivity2::class.java)
            startActivity(tela2)

        }

        btnEstudo.setOnClickListener {

            val tela3 = Intent(this, MainActivity3::class.java)
            startActivity(tela3)

        }

        btnHistorico.setOnClickListener {

            val tela4 = Intent(this, MainActivity4::class.java)
            startActivity(tela4)

        }
    }
}