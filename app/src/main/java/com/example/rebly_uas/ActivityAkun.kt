package com.example.rebly_uas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.rebly_uas.databinding.ActivityAkunBinding

class ActivityAkun : AppCompatActivity() {
    private lateinit var binding: ActivityAkunBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_akun)
        binding = ActivityAkunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : androidx.appcompat.widget.Toolbar = findViewById(R.id.id_toolbar)
        setSupportActionBar(toolbar)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.profil -> {
                val intent = Intent(this, ActivityAkun::class.java)
                startActivity(intent)
                true
            }
            R.id.setting -> {
                Toast.makeText(this, "Setting click", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.logout -> {
                Toast.makeText(this, "Logout click", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}