package com.example.novaera.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.novaera.R
import com.example.novaera.databinding.FragmentFirstBinding
import com.example.novaera.ui.adapter.AdapterList
import com.example.novaera.viewmodel.ViewModel

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private val viewModel: ViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AdapterList()
        binding.rv.adapter = adapter
        binding.rv.layoutManager = GridLayoutManager(context,2)

        viewModel.listAll.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })
    }
}