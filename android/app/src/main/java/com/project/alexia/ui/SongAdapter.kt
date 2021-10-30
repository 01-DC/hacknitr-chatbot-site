package com.project.alexia.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.project.alexia.R
import com.project.alexia.data.Song

class SongAdapter(private val list: ArrayList<Song>, private val context: Context, val listener: OnSongListener) : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {
    inner class SongViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var songName: TextView = itemView.findViewById(R.id.songName)
        var artistName: TextView = itemView.findViewById(R.id.artist)
        var duration: TextView = itemView.findViewById(R.id.duration)
        var card = itemView.findViewById<ConstraintLayout>(R.id.card)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        val viewHolder = SongViewHolder(
            LayoutInflater.from(context).inflate(R.layout.song_list, parent, false)
        )
        viewHolder.card.setOnClickListener {
            listener.onSongClick(viewHolder.adapterPosition)
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val current = list[position]
        holder.songName.text = current.name
        holder.artistName.text = current.artist
        holder.duration.text = current.duration
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
interface OnSongListener{
    fun onSongClick(position: Int)
}