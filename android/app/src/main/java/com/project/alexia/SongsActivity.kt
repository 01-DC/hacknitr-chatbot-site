package com.project.alexia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.project.alexia.data.Song
import com.project.alexia.ui.OnSongListener
import com.project.alexia.ui.SongAdapter
import kotlinx.android.synthetic.main.activity_songs.*
import org.json.JSONException

class SongsActivity : AppCompatActivity(), OnSongListener {

    private lateinit var list: ArrayList<Song>
    private lateinit var mAdapter: SongAdapter
    var mood: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_songs)

        mood = intent.getStringExtra("user_mood").toString()

        list = ArrayList<Song>()
        mAdapter = SongAdapter(list, this@SongsActivity, this)
        songRecyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }
        loadSongs()
    }

    private fun loadSongs(){
        loader.visibility = View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://ws.audioscrobbler.com/2.0/?method=tag.gettoptracks&tag=$mood&limit=20&api_key=7c6af8a482e97ab7fdea7bb9778e08f2&format=json"

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                try {
                    val jsonObject = response.getJSONObject("tracks")
                    val jsonArray = jsonObject.getJSONArray("track")
                    for(i in 0 until jsonArray.length()){
                        val obj = jsonArray.getJSONObject(i)
                        val name = obj.getString("name")
                        val time = obj.getString("duration")
                        val duration = "${time.toInt()/60}:${time.toInt()%60}"
                        val link = obj.getString("url")
                        val artistObj = obj.getJSONObject("artist")
                        val artist = artistObj.getString("name")

                        val item = Song(name, artist, duration, link)
                        Log.d("aditya","$name $artist $duration")
                        list.add(item)
                    }
                    mAdapter.notifyDataSetChanged()
                    loader.visibility = View.GONE
                } catch (e: JSONException){
                    e.printStackTrace()
                }
            },
            { error ->
                Log.d("aditya", error?.localizedMessage.toString())
            }
        )
        queue.add(jsonObjectRequest)
    }

    override fun onSongClick(position: Int) {
        val intent = Intent(Intent.ACTION_VIEW)
        val url = list[position].url
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
}