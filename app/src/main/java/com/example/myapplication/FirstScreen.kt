package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.databinding.ActivityFirstScreenBinding

class FirstScreen : AppCompatActivity() {

    private lateinit var binding: ActivityFirstScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.buttonNext.setOnClickListener {
            val userName = binding.editTextTextUserName.text.toString()
            if (userName.isEmpty()){
                Toast.makeText(this@FirstScreen,"Please enter your name",Toast.LENGTH_SHORT)
                    .show()
            }
            else{
                val intent = Intent(this@FirstScreen,MainActivity::class.java)
                intent.putExtra("username",userName)
                startActivity(intent)
                finish()


            }
        }







    }
}