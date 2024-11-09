package com.example.androidtp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.content.Intent

class RegisterActivity : AppCompatActivity() {

    private lateinit var enregistrerbtn : Button
    private lateinit var txtRegisterName: EditText
    private lateinit var txtRegisterMail: EditText
    private lateinit var txtRegisterPassword: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        enregistrerbtn = findViewById<Button>(R.id.btnRegister)
        txtRegisterName = findViewById<EditText>(R.id.txtRegisterName)
        txtRegisterMail = findViewById<EditText>(R.id.txtRegisterMail)
        txtRegisterPassword = findViewById<EditText>(R.id.txtRegisterPassword)
        enregistrerbtn.setOnClickListener{
           register()
        }
    }

    public fun goToLogin(view: View) {
        finish();
    }

    private fun register() {
        val name = txtRegisterName.text.toString()
        val mail = txtRegisterMail.text.toString()
        val password = txtRegisterPassword.text.toString()
        val registerDataInstance: RegisterData = RegisterData(name, mail, password)

        Api().post<RegisterData>("https://mypizza.lesmoulinsdudev.com/register", registerDataInstance, ::registerSuccess)
    }

    private fun registerSuccess(responseCode: Int) {
        if (responseCode == 200) {
            runOnUiThread {
                Toast.makeText(this, "enregistrement OK", Toast.LENGTH_SHORT).show()
                val intentToNextActivity = Intent(this, LoginActivity::class.java);
                startActivity(intentToNextActivity);
            }
        } else if (responseCode == 500) {
            Toast.makeText(this, "erreur lors de l’enregistrement", Toast.LENGTH_SHORT).show()
        } else if (responseCode == 400) {
            Toast.makeText(this, "la requête est mauvaise", Toast.LENGTH_SHORT).show()
        }
    }
}

