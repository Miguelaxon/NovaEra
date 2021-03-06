package com.example.novaera.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.novaera.R
import com.example.novaera.databinding.FragmentSecondBinding
import com.example.novaera.viewmodel.ViewModel
import com.example.novaera.local.ClassNovaEraDetail
import com.example.novaera.ui.adapter.AdapterDetail

class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private val viewModel: ViewModel by activityViewModels()
    var idM: Int = 0
    lateinit var name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idM = requireArguments().getInt("idCel")
            name = requireArguments().getString("name", "")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var adapter = AdapterDetail()
        binding.rv2.adapter = adapter
        binding.rv2.layoutManager = LinearLayoutManager(context)

        viewModel.returnDetail().observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            sendEmail()
        }

    }

    fun sendEmail() {
        val para = arrayOf("info@novaera.cl")
        val copia = arrayOf("")
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.data = Uri.parse("mailto:")
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, para)
        emailIntent.putExtra(Intent.EXTRA_CC, copia)
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.mailSubject, name, idM))
        emailIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.mailText, name, idM))
        try {
            startActivity(Intent.createChooser(emailIntent, "Enviar email..."))
        } catch (ex: ActivityNotFoundException) {
            Toast.makeText(context,
                    "No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show()
        }
    }
}