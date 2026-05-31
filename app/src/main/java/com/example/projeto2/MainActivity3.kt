package com.example.projeto2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
class MainActivity3 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main3)

        val edtMateria = findViewById<EditText>(R.id.edtMateria)
        val edtTempo = findViewById<EditText>(R.id.edtTempo)
        val edtConteudo = findViewById<EditText>(R.id.edtConteudo)
        val edtAnotacao = findViewById<EditText>(R.id.edtAnotacao)

        val btnSalvar = findViewById<Button>(R.id.btnSalvar)

        btnSalvar.setOnClickListener {

            if (
                edtMateria.text.toString().trim().isEmpty() ||
                edtTempo.text.toString().trim().isEmpty() ||
                edtConteudo.text.toString().trim().isEmpty() ||
                edtAnotacao.text.toString().trim().isEmpty()
            ) {

                Toast.makeText(
                    this,
                    "Preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            FirebaseDatabase.getInstance()
                .getReference("materias")
                .get()
                .addOnSuccessListener { dados ->

                    var existe = false

                    for (item in dados.children) {

                        val nomeMateria =
                            item.child("nome").value.toString()

                        if (
                            nomeMateria.equals(
                                edtMateria.text.toString(),
                                true
                            )
                        ) {
                            existe = true
                            break
                        }
                    }

                    if (!existe) {

                        Toast.makeText(
                            this,
                            "Matéria não cadastrada",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@addOnSuccessListener
                    }

                    val estudo = mapOf(
                        "materia" to edtMateria.text.toString(),
                        "tempo" to edtTempo.text.toString(),
                        "conteudo" to edtConteudo.text.toString(),
                        "anotacao" to edtAnotacao.text.toString()
                    )

                    FirebaseDatabase.getInstance()
                        .getReference("estudos")
                        .push()
                        .setValue(estudo)

                    Toast.makeText(
                        this,
                        "Estudo salvo",
                        Toast.LENGTH_SHORT
                    ).show()

                    edtMateria.setText("")
                    edtTempo.setText("")
                    edtConteudo.setText("")
                    edtAnotacao.setText("")
                }

        }
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {

            finish()

        }
    }
}