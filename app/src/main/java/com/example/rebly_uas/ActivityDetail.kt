package com.example.rebly_uas

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rebly_uas.databinding.ActivityDetailBinding

class ActivityDetail : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intss = intent
        val gambarT  = intss.getStringExtra("gambar")
        val namaT = intss.getStringExtra("nama")
        val descT = intss.getStringExtra("desc")
        val biografiT = intss.getStringExtra("bio")

        binding.imageTitle.text = namaT
        binding.imageDesc.text = descT
        binding.imageDetail.loadImage(gambarT)
        binding.imageBio.text = biografiT
    }
}