package com.example.androidtp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
class LoginActivity : AppCompatActivity() {
    var connectionBtn = findViewById<Button>(R.id.btnConect)
    var mailTextEdit = findViewById<EditText>(R.id.txtMail)
    var passwordTextEdit = findViewById<EditText>(R.id.txtPassword)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        connectionBtn.setOnClickListener{
            login()
        }
    }

    public fun registerNewAccount(view: View)
    {
        val intent = Intent(this, RegisterActivity::class.java);
        startActivity(intent);
    }
    public fun login(){

        val mail = mailTextEdit.text.toString()
        val password = passwordTextEdit.text.toString()
        val loginDataInstance : LoginData = LoginData(mail, password)

        Api().post("https://mypizza.lesmoulinsdudev.com/auth", loginDataInstance, ::loginSuccess)
    }

    public fun loginSuccess(responseCode : Int, token: String?){
        if(responseCode == 200){
            val intentToNextActivity = Intent(this, OrdersActivity::class.java);
            intentToNextActivity.putExtra("TOKEN", token)
            startActivity(intentToNextActivity);
        }
        else if (responseCode == 401){
            Toast.makeText(this, "i les identifiants sont incorrects", Toast.LENGTH_SHORT).show()
        }
    }
}