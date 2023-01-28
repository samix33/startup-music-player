package com.example.startup_music_player.ui.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.startup_music_player.R
import com.example.startup_music_player.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnRegister.setOnClickListener { IschektRegister() } //onclick_btnRegister
        binding.txtRegister.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        } // intent to Activity Login
    }

    private fun IschektRegister() {
        if (binding.EdtPassword.text.toString() != binding.EdtPasswordRepeat.text.toString()){
            SnackbarError("رمزی که وارد کردید با رمز اول مطابق نیست ")
        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        if (binding.EdtUser.text.isNotEmpty()
            && binding.EdtGmail.text.isNotEmpty()
            && binding.EdtPassword.text.isNotEmpty()
            && binding.EdtPasswordRepeat.text.isNotEmpty()
            && binding.EdtPassword.text.isNotEmpty()
            && binding.EdtPassword == binding.EdtPasswordRepeat

        ) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else if (binding.EdtUser.text.isEmpty() or
            binding.EdtGmail.text.isEmpty() or
            binding.EdtPassword.text.isEmpty() or
            binding.EdtPasswordRepeat.text.isEmpty()
        ) {
            SnackbarError("لطفا تمام فیلد ها را تکمیل کنید !")
        }

    } // Chek data user
    private fun SnackbarError(text : String) {
        Snackbar
            .make(binding.root, text , Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this, R.color.light_blue))
            .setTextColor(ContextCompat.getColor(this, R.color.white))
            .show()
    } // SnakBar
}

