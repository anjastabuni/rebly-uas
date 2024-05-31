package com.example.rebly_uas

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RegeaAdapter(var mContext: Context, var regeaList:List<ItemData>):
    RecyclerView.Adapter<RegeaAdapter.RegeaViewHolder>() {

    inner class RegeaViewHolder(var v: View): RecyclerView.ViewHolder(v){
        val imgT = v.findViewById<ImageView>(R.id.imageDetail)
        val namaT = v.findViewById<TextView>(R.id.imageTitle)
        val descT = v.findViewById<TextView>(R.id.imageDesc)
//        val bioT = v.findViewById<TextView>(R.id.imageBio)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RegeaViewHolder {
        val infalter = LayoutInflater.from(parent.context)
        val v = infalter.inflate(R.layout.item_data,parent,false)
        return RegeaViewHolder(v)
    }

    override fun getItemCount(): Int = regeaList.size

    override fun onBindViewHolder(holder: RegeaViewHolder, position: Int) {
        val newList = regeaList[position]
        holder.namaT.text = newList.nama
        holder.imgT.loadImage(newList.gambar)
        holder.descT.text = newList.desc
//        holder.bioT.text = newList.bio
        holder.v.setOnClickListener {

            val nama = newList.nama
            val  desc = newList.desc
            val gambar = newList.gambar
//            val bio = newList.bio

            val mIntent = Intent(mContext, ActivityDetail::class.java)
            mIntent.putExtra("nama", nama)
            mIntent.putExtra("desc", desc)
            mIntent.putExtra("gambar", gambar)
//            mIntent.putExtra("bio", bio)
            mContext.startActivity(mIntent)
        }
    }
}