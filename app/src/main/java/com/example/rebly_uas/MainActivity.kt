package com.example.rebly_uas

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rebly_uas.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class MainActivity : AppCompatActivity() {
    private lateinit var regeaRecyclerView: RecyclerView
    private lateinit var regeaList: MutableList<ItemData>
    private lateinit var regeaAdapter: RegeaAdapter
    private lateinit var binding: ActivityMainBinding
    private var mStorage:FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar : androidx.appcompat.widget.Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        regeaRecyclerView = binding.recyclerView
        regeaRecyclerView.setHasFixedSize(true)
        regeaRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        binding.myDataLoaderProgressBar.visibility = View.VISIBLE
        regeaList = ArrayList()
        regeaAdapter = RegeaAdapter(this@MainActivity, regeaList)
        regeaRecyclerView.adapter = regeaAdapter


       mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("regea")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@MainActivity, error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderProgressBar.visibility = View.VISIBLE
            }


            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                regeaList.clear()
                for (teacherSnapShot in snapshot.children){
                    val upload = teacherSnapShot.getValue(ItemData::class.java)
                    upload!!.key = teacherSnapShot.key
                    regeaList.add(upload)
                }
                regeaAdapter.notifyDataSetChanged()
                binding.myDataLoaderProgressBar.visibility = View.GONE
            }
        })


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                FirebaseAuth.getInstance().signOut()
                Intent(this, ActivityLogin::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)

                }
            }
            R.id.profil -> {
                val intent = Intent(this, ActivityAkun::class.java)
                startActivity(intent)

            }
        }
        return true
    }
}