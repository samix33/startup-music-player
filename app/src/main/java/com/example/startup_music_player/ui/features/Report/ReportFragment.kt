package com.example.startup_music_player.ui.features.Report

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.startup_music_player.R
import com.example.startup_music_player.databinding.FragmentReportBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ReportFragment : BottomSheetDialogFragment() {

    lateinit var binding: FragmentReportBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReportBinding.inflate(layoutInflater , container , false)
        setOnClickListeners()
        return binding.root
    }

    override fun getTheme() = R.style.RoundedCornersBottomSheetDialog

    private fun setOnClickListeners(){
        binding.btnCancel.setOnClickListener { dismiss() }

        binding.btnAccept.setOnClickListener {  }

    }
}