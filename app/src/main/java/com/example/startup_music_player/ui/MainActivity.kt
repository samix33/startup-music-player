package com.example.startup_music_player.ui

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.startup_music_player.R
import com.example.startup_music_player.databinding.ActivityMainBinding
import com.example.startup_music_player.model.repository.TokenInMemory
import com.example.startup_music_player.model.repository.UserReposiroty
import com.example.startup_music_player.ui.features.Main.MainFragment
import com.example.startup_music_player.ui.features.Register.RegisterFragment
import com.example.startup_music_player.util.NetworkChecker
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var broadcastReceiver: BroadcastReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val userReposiroty: UserReposiroty = get()
        userReposiroty.loadtoken()
        chekinternet() //chekinternet
        transform(RegisterFragment())
        if (TokenInMemory.Token != null){
            transform(MainFragment())
        }
    }
    private fun transform(fragment : Fragment){
        val transform = supportFragmentManager.beginTransaction()
        transform.replace(R.id.FrameLayoutMain,fragment)
        transform.commit()
    } // transform btn navigeshen
    private fun chekinternet(){
        val internet: Boolean = NetworkChecker(this).isInternetConnected
        // broadcastReceiver ->
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                if (!internet) {
                    //snalbar
                }
            }
        }
        val intentfilter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")
        registerReceiver(broadcastReceiver, intentfilter)

    }


}