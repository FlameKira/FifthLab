package com.example.gson

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import timber.log.Timber
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), PhotoAdapter.OnImageClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        val url = URL("https://api.flickr.com/services/rest/?method=flickr.photos.search" +
                "&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1")

        val rView: RecyclerView = findViewById(R.id.rView)
        val photoAdapter: PhotoAdapter = PhotoAdapter(this)
        rView.layoutManager = GridLayoutManager(this, 2)
        rView.adapter = photoAdapter


        Thread(Runnable {
            val linksList = mutableListOf<String>()
            val con = url.openConnection() as HttpURLConnection
            val dataString = con.inputStream.bufferedReader().readText()
            var gson = Gson()
            val data = gson.fromJson(dataString, Wrapper::class.java)
            for (photoNum in 0 until data.photos.photo.size step 5){
                val photo = data.photos.photo[photoNum]
                Timber.v(photo.toString())
                linksList.add("https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}_z.jpg")
            }
            photoAdapter.setItems(linksList)
            runOnUiThread { photoAdapter.notifyDataSetChanged() }
        }).start()
    }

    override fun onClickImage(url: String) {
        Timber.i(url)
        val clipboard: ClipboardManager =
            getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip: ClipData = ClipData.newPlainText("url", url)
        clipboard.setPrimaryClip(clip)
    }
}