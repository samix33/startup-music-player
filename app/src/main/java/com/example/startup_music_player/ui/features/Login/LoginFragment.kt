package com.example.startup_music_player.ui.features.Login


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.startup_music_player.R
import com.example.startup_music_player.databinding.FragmentLoginBinding

import com.example.startup_music_player.ui.features.Main.MainFragment
import com.example.startup_music_player.ui.features.Register.RegisterFragment
import com.google.android.material.snackbar.Snackbar
import ir.dunijet.dunibazaar.util.VALUE_SUCCESS
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater,container,false)
        binding.animLogin.playAnimation() // Ply Anim lotti
        binding.txtRegister.setOnClickListener {
            transform(RegisterFragment())
        } // intent to Activity Register
        binding.btnLogin.setOnClickListener { IschektLogin() } //intent to Activity home
        return binding.root
    }

    private fun IschektLogin() {
        val viewmodel : LoginViewModel by viewModel()
        if (binding.EdtEmailLogin.text.isNotEmpty() && binding.EdtPasswordLogin.text.isNotEmpty()) {
            // Input user
            viewmodel.username.value = binding.EdtEmailLogin.text.toString()
            viewmodel.password.value = binding.EdtPasswordLogin.text.toString()
            // ischekt Answer server
            viewmodel.username.value = binding.EdtEmailLogin.text.toString()
            viewmodel.password.value = binding.EdtPasswordLogin.text.toString()
            viewmodel.LoginUser {

                if (it == VALUE_SUCCESS){
                    transform(MainFragment())

                }else{
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            // scakbar
            if (binding.EdtEmailLogin.text.isEmpty() && binding.EdtPasswordLogin.text.isEmpty()){
                SnackbarError("???????? ???????? ?????? ?????????? ???? ???? ????????")
            }
            if (viewmodel.username.value != binding.EdtEmailLogin.text.toString()) {
                SnackbarError("?????????? ???? ?????? ???????? ???????? ?????? ???????????? ?????? !")
            }

            if ( viewmodel.password.value != binding.EdtPasswordLogin.text.toString()){
                SnackbarError("?????????? ???? ?????? ???????? ???????? ?????? ???????????? ?????? !")
            }
        }
    }
    private fun transform(fragment : Fragment){
        val transform = parentFragmentManager.beginTransaction()
        transform.replace(R.id.FrameLayoutMain,fragment)
        transform.commit()
    } // transform

    private fun SnackbarError(text: String) {
        Snackbar
            .make(binding.root, text, Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(binding.root.context, R.color.light_blue))
            .setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            .show()
    } // SnakBar
}