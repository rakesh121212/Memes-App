package com.example.myapplication

import android.annotation.SuppressLint
import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val url: String = "https://meme-api.com/gimme"

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = intent

        if (intent.hasExtra("username")){
            val receivedText = intent.getStringExtra("username")
            binding.usernameTextView.text = "${receivedText} Your today's memes"
        }


        getMemeData()


        binding.btnNewMeme.setOnClickListener {
            getMemeData()
        }
    }


    fun getMemeData(){

//       setting the progress bar
        val progressDialog = ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetch")
        progressDialog.show()



        val queue = Volley.newRequestQueue(this)

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                Log.e("Response","getMemeData: "+response.toString())
                val responseObject = JSONObject(response)


                responseObject.get("postLink")
                binding.memeTitle.text = responseObject.getString("title")
                binding.memeAuthor.text = responseObject.getString("author")
//                for image we are using glide here
                Glide.with(this).load(responseObject.get("url")).into(binding.memeImage)
                progressDialog.dismiss()

            },
            {  error->
                progressDialog.dismiss()
                Toast.makeText(this@MainActivity,"${error.localizedMessage}",Toast.LENGTH_SHORT)
                    .show()
            })


        queue.add(stringRequest)

    }
}