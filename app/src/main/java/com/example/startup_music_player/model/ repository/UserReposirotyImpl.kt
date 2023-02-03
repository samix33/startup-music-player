package com.example.startup_music_player.model.repository

import android.content.SharedPreferences
import com.example.startup_music_player.model.net.Apiservice
import com.google.gson.JsonObject
import ir.dunijet.dunibazaar.util.VALUE_SUCCESS

class UserReposirotyImpl(
    private val apiservice: Apiservice,
    private val sharedPref: SharedPreferences
) : UserReposiroty {


    override suspend fun Register(name: String, gmail: String, password: String): String {
        val jsonObject = JsonObject().apply {
            addProperty("name", name)
            addProperty("email", gmail)
            addProperty("password", password)
        }

        val result = apiservice.Register(jsonObject)
        if (result.success) {
            TokenInMemory.refreshToken(name, result.token)
            saveToken(result.token)
            saveusername(name)
            return VALUE_SUCCESS
        } else {
            return result.mesage
        }
    }

    override suspend fun Login(username: String, password: String): String {
        val jsonObject = JsonObject().apply {

            addProperty("email", username)
            addProperty("password", password)
        }

        val result = apiservice.Login(jsonObject)
        if (result.success) {
            TokenInMemory.refreshToken(username, result.token)
            saveToken(result.token)

            saveusername(username)

            return VALUE_SUCCESS
        } else {
            return result.mesage
        }
    }

    override fun singout() {
        TokenInMemory.refreshToken(null,null)
        sharedPref.edit().clear().apply()
    }

    override fun loadtoken() {
        TokenInMemory.refreshToken(getusername(),getToken())
    }

    override fun saveToken(newToken: String) {
        sharedPref.edit().putString("token",newToken).apply()
    }

    override fun getToken(): String {
        return sharedPref.getString("token","")!!
    }

    override fun saveusername(username: String) {
        sharedPref.edit().putString("username",username).apply()
    }

    override fun getusername(): String {
        return sharedPref.getString("username","")!!
    }
}