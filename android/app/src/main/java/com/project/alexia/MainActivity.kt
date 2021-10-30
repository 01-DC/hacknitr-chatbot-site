package com.project.alexia

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.project.alexia.data.Message
import com.project.alexia.data.Song
import com.project.alexia.ui.MessagingAdapter
import com.project.alexia.utils.BotResponse
import com.project.alexia.utils.Constants.OPEN_GOOGLE
import com.project.alexia.utils.Constants.OPEN_SEARCH
import com.project.alexia.utils.Constants.OPEN_SONG_ACTIVITY
import com.project.alexia.utils.Constants.RECEIVE_ID
import com.project.alexia.utils.Constants.RUN_MODEL
import com.project.alexia.utils.Constants.SEND_ID
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_songs.*
import kotlinx.coroutines.*
import org.json.JSONException

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"
    var messagesList = mutableListOf<Message>()
    private lateinit var adapter: MessagingAdapter
    lateinit var loadingDialog: LoadingDialog
    var mood: String = "random"
    var value = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView()
        clickEvents()
        customBotMessage("Hello! I'm Alexia, your personal companion. I'll analyze your mood and recommend songs and activities.")

        loadingDialog = LoadingDialog(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.analyze -> {
                loadingDialog.startLoadingDialog()
                GlobalScope.launch {
                    delay(3000)
                    withContext(Dispatchers.Main){
                        loadingDialog.dismissDialog()
                        val intent = Intent(this@MainActivity, Activities::class.java)
                        startActivity(intent)
                    }
                }
                return true
            }
            R.id.songs -> {
                val intent = Intent(this@MainActivity, SongsActivity::class.java)
                intent.putExtra("user_mood", mood)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun clickEvents() {

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun recyclerView() {
        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }

    override fun onStart() {
        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()

        if (message.isNotEmpty()) {
            messagesList.add(Message(message, SEND_ID))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID))
            rv_messages.scrollToPosition(adapter.itemCount - 1)
            if(value == 0){
                messagesList.add(Message(RUN_MODEL, RECEIVE_ID))
                adapter.insertMessage(Message(RUN_MODEL, RECEIVE_ID))
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                val queue = Volley.newRequestQueue(this)
                val url = "base_url/pred/\"$message\""
                val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
                    { response ->
                        try {
                            val emotion = response.getString("emotion")
                            mood = emotion
                            messagesList.add(Message(OPEN_SONG_ACTIVITY, RECEIVE_ID))
                            adapter.insertMessage(Message(OPEN_SONG_ACTIVITY, RECEIVE_ID))
                            rv_messages.scrollToPosition(adapter.itemCount - 1)
                        } catch (e: JSONException){
                            e.printStackTrace()
                        }
                    },
                    { error ->
                        Log.d("aditya", error?.localizedMessage.toString())
                    }
                )
                queue.add(jsonObjectRequest)
                value = -1;
            }
            else{
                botResponse(message)
            }
        }
    }

    private fun botResponse(message: String) {

        GlobalScope.launch {
            //Fake response delay
            delay(1000)

            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)
                if(response == "How are you feeling today ?"){
                    value = 0;
                }

                //Starts Google
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    private fun customBotMessage(message: String) {

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                messagesList.add(Message(message, RECEIVE_ID))
                adapter.insertMessage(Message(message, RECEIVE_ID))

                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

}