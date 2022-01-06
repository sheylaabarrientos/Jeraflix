package com.jera.jeraflixx.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jera.jeraflixx.databinding.EmptyErrorFragmentBinding

class EmptyErrorFragment : Fragment() {
    lateinit var binding : EmptyErrorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = EmptyErrorFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}