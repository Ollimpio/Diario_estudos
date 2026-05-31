package com.example.projeto2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.google.firebase.database.FirebaseDatabase
class MainActivity4 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main4)

        val listHistorico = findViewById<ListView>(R.id.listHistorico)

        val lista = ArrayList<String>()

        FirebaseDatabase.getInstance()
            .getReference("estudos")
            .get()
            .addOnSuccessListener { dados ->

                for (item in dados.children) {

                    val materia =
                        item.child("materia").value.toString()

                    val tempo =
                        item.child("tempo").value.toString()

                    val conteudo =
                        item.child("conteudo").value.toString()

                    val anotacao =
                        item.child("anotacao").value.toString()

                    lista.add(
                        "Matéria: $materia\n" +
                                "Tempo: $tempo min\n" +
                                "Conteúdo: $conteudo\n" +
                                "Anotação: $anotacao"
                    )
                }

                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    lista
                )

                listHistorico.adapter = adapter
            }
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {

            finish()

        }
    }
}