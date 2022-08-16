package com.android.udacityfirst.ui.shoe_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.android.udacityfirst.R
import com.android.udacityfirst.databinding.FragmentShoeDetailBinding
import com.android.udacityfirst.model.Shoe
import com.android.udacityfirst.ui.shoe.ShoeViewModel
import com.android.udacityfirst.util.DoAsync
import com.google.android.material.snackbar.Snackbar

class ShoeDetailFragment : Fragment() {

    private var _binding: FragmentShoeDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail ,container, false)
        val view = binding.root
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mViewModel = viewModel

        onClicks2()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        menu.clear()
    }

    private fun onClicks2(){
        binding.apply {
            btnAddShoe.setOnClickListener {
                // Tell view model at first to Insert the new SHOE data
                viewModel.onEvent(ShoeEvent.InsertShoe)
                // After the data inserted we clear and viewModel add it to the list,
                // then we give view model a new order to clear old object data
                viewModel.onEvent(ShoeEvent.ClearOldShoe)
                navigateBackToShoesListScreen()
            }
            btnCancel.setOnClickListener {
                // if user put some data and cancel it... if he back he will find what he wrote so any
                // button clicked will clean all data wrote before
                viewModel.onEvent(ShoeEvent.ClearOldShoe)
                navigateBackToShoesListScreen()
            }
        }

    }
    private fun onClicks() {
        binding.apply {
            btnAddShoe.setOnClickListener {
                val shoeName = etShoeName.text.toString().trim().filter { !it.isWhitespace() }
                val shoeCompany = etShoeCompany.text.toString().trim().filter { !it.isWhitespace() }
                val shoeSize = etShoeSize.text.toString().trim().filter { !it.isWhitespace() }
                val shoeDescription = etShoeDescription.text.toString().trim().filter { !it.isWhitespace() }
                onValidate(Shoe(shoeName, shoeCompany, shoeSize, shoeDescription))
            }

            btnCancel.setOnClickListener {
                navigateBackToShoesListScreen()
            }
        }
    }

    private fun onValidate(shoe: Shoe) {
        binding.apply {
            if (shoe.shoeName.toString().isBlank()) {
                etShoeName.error = getString(R.string.blank)
                return
            }
            if (shoe.shoeCompany.toString().isBlank()) {
                etShoeCompany.error = getString(R.string.blank)
                return
            }
            if (shoe.shoeSize.toString().isBlank()) {
                etShoeSize.error = getString(R.string.blank)
                return
            }
            if (shoe.shoeDescription.toString().isBlank()) {
                etShoeDescription.error = getString(R.string.blank)
                return
            } else {
                etShoeName.error = null
                etShoeCompany.error = null
                etShoeSize.error = null
                etShoeDescription.error = null
                addShoe(shoe)
                showSuccessfullySnackBarMessage()
                return@apply
            }
        }
    }

    private fun addShoe(shoe: Shoe) {
//            viewModel.onEvent(ShoeEvent.Loading)
            navigateBackToShoesListScreen()
            // because this app work on offline environment everything will be run so fast so
            // i added this loop to give the chance to (shoesList Fragment ui) to show the progressbar
            // and at the end, Data will send and load successfully in the ui
            // NOTE:: Coroutine will be a better way instead of DoAsync
            DoAsync {
                println("${Thread.currentThread()}")
                for (i in 0 until 30000){
                    println(i)
                }
//                viewModel.onEvent(ShoeEvent.AddShoe(shoe))
            }.execute()
        }

    private fun navigateBackToShoesListScreen(){
        findNavController().navigateUp()
    }

    private fun showSuccessfullySnackBarMessage(){
        Snackbar.make(binding.etShoeSize, getString(R.string.item_added_to_cart), Snackbar.LENGTH_LONG).show()
    }

}