package com.example.tugasapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tugasapi.databinding.ActivityMainBinding
import com.example.tugasapi.model.Chars
import com.example.tugasapi.model.Result
import com.example.tugasapi.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var charAdapter: CharAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        charAdapter = CharAdapter(emptyList()) {}

        binding.rvChar.apply {
            adapter = charAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 3)
        }

        val client = ApiClient.getInstance()
        val responseChars = client.getAllChars()

        responseChars.enqueue(object : Callback<Result> {
            override fun onResponse(call: Call<Result>, response: Response<Result>) {
                if (response.isSuccessful) {
                    response.body()?.let { chars ->
                        charAdapter = CharAdapter(chars.results) { char ->
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(char.artistHref))
                            startActivity(intent)
                        }
                        binding.rvChar.adapter = charAdapter
                    }
                }
            }

            override fun onFailure(call: Call<Result>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Connection error: ${t.message}",
                    Toast.LENGTH_LONG).show()
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}