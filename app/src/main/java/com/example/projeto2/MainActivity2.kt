package com.example.projeto2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.projeto2.R.id
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase
import android.widget.Toast
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main2)
        val edtMateria = findViewById<EditText>(R.id.edtMateria)
        val btnCadastrar = findViewById<Button>(R.id.btnCadastrar)

        btnCadastrar.setOnClickListener {

            if (edtMateria.text.toString().trim().isEmpty()) {

                Toast.makeText(
                    this,
                    "Digite uma matéria",
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

                    if (existe) {

                        Toast.makeText(
                            this,
                            "Matéria já cadastrada",
                            Toast.LENGTH_SHORT
                        ).show()

                        return@addOnSuccessListener
                    }

                    val materia = mapOf(
                        "nome" to edtMateria.text.toString()
                    )

                    FirebaseDatabase.getInstance()
                        .getReference("materias")
                        .push()
                        .setValue(materia)

                    Toast.makeText(
                        this,
                        "Matéria cadastrada com sucesso!",
                        Toast.LENGTH_SHORT
                    ).show()

                    edtMateria.setText("")
                }
        }
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        btnVoltar.setOnClickListener {

            finish()

        }
    }
}