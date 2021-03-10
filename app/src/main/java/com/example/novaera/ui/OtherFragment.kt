package com.example.novaera.ui

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.novaera.R
import com.example.novaera.databinding.FragmentOtherBinding
import com.example.novaera.databinding.PopupExitBinding
import com.example.novaera.viewmodel.ViewModel
import java.text.DecimalFormat
import kotlin.toBigDecimal as toBigDecimal1

class OtherFragment : Fragment() {
    private lateinit var binding: FragmentOtherBinding
    private lateinit var binding2: PopupExitBinding
    private val viewModel: ViewModel by activityViewModels()
    var idM: Int = 0
    lateinit var name: String
    private lateinit var alertDialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            idM = requireArguments().getInt("idCel")
            name = requireArguments().getString("name", "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOtherBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedId(idM).observe(viewLifecycleOwner, {
            it?.let {
                Glide.with(this).load(it.image).into(binding.ivImage)
                binding.tvName.text = it.name
                val formatter = DecimalFormat("$#,###")
                binding.tvPrice2.text = formatter.format(it.price).toString()
                binding.tvLastPrice.text = formatter.format(it.lastPrice).toString()
                binding.tvDescription.text = it.description
                if (it.credit) {
                    binding.tvCredEfec.text = getString(R.string.credito)
                    binding.ivCredEfec.setImageResource(R.drawable.ic_credito)
                } else {
                    binding.tvCredEfec.text = getString(R.string.efectivo)
                    binding.ivCredEfec.setImageResource(R.drawable.ic_efectivo)
                }
            }
        })

        binding.btnBuy.setOnClickListener { sendEmail() }

        binding.btnBack.setOnClickListener { findNavController()
            .navigate(R.id.action_otherFragment_to_FirstFragment) }

        binding.btnExit.setOnClickListener {
            //showDialog()
            showCustomDialog()
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
            Toast.makeText(
                context,
                "No tienes clientes de email instalados.", Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun showDialog() {
        val dialogBuilder = context?.let { AlertDialog.Builder(it) }
        dialogBuilder?.setMessage("Seguro desea salir?")
        dialogBuilder?.setPositiveButton(
            "Done"
        ) { _, _ -> }
        val b = dialogBuilder?.create()
        b?.show()
    }

    private fun showCustomDialog() {
        binding2 = PopupExitBinding.inflate(LayoutInflater.from(context))
        binding2.tvHeader.text = getString(R.string.salir)
        binding2.tvDialog.text = getString(R.string.desea_salir)
        binding2.btnYes.setOnClickListener { activity?.finish() }
        binding2.btnNot.setOnClickListener { findNavController()
            .navigate(R.id.action_otherFragment_to_FirstFragment)
            this.alertDialog.dismiss() }
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        dialogBuilder.setOnDismissListener { true }
        dialogBuilder.setView(binding2.root)

        alertDialog = dialogBuilder.create();
        //alertDialog.window!!.attributes.windowAnimations = R.style.PauseDialogAnimation
        alertDialog.show()
    }
}