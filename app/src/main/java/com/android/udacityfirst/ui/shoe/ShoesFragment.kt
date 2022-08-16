package com.android.udacityfirst.ui.shoe

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.android.udacityfirst.R
import com.android.udacityfirst.databinding.FragmentShoesBinding
import com.android.udacityfirst.databinding.ItemShoeBinding
import com.android.udacityfirst.model.Shoe
import com.android.udacityfirst.util.MySharedPref
import com.android.udacityfirst.util.OnNavigate
import com.android.udacityfirst.util.setNavigateAction

class ShoesFragment : Fragment() {

    private var _binding: FragmentShoesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ShoeViewModel by activityViewModels()
    private var _container: ViewGroup? = null
    private val container get() = _container!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes ,container, false)
        val view = binding.root
        _container = container
        binding.lifecycleOwner = viewLifecycleOwner
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
        observable()
        binding.viewModel = viewModel

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding?.unbind()
        _binding = null
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.loginFragment -> {
                MySharedPref.shEmail = ""
                MySharedPref.shPassword = ""
                setNavigateAction(OnNavigate.SHOES_TO_LOGIN)
            }
        }
        return super.onOptionsItemSelected(item)
    }



    private fun onClick(){
        binding.apply {
            btnAddItem.setOnClickListener {
                setNavigateAction(OnNavigate.SHOES_TO_SHOE_DETAILS)
            }
        }
    }


    private fun observable(){
        viewModel.state.observe(viewLifecycleOwner, {
            Log.e(TAG, "observable: ${it.shoes}" )
            println("Size:: ${it.shoes.size}")
            it.shoes.forEach { shoe ->
                addNewView(shoe = shoe)
            }
        })
    }

    private fun addNewView(shoe: Shoe?){

        val repeatedView: ItemShoeBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_shoe, container, false)

        try {
            repeatedView.singleShoe = shoe
            binding.container.addView(repeatedView.root)
        }catch (e: Exception){
            Log.e(TAG, "addNewView: ${e.message ?: e.toString()}" )
        }


//        binding.singleShoe?.let {
//            it.shoeName = "${shoe.shoeName}"
//            it.shoeCompany = "${shoe.shoeCompany}"
//            it.shoeSize = "${shoe.shoeSize}"
//            it.shoeDescription = "${shoe.shoeDescription}"
//        }


//        val mItem = layoutInflater.inflate(R.layout.item_shoe, null)

//        val name = mItem.findViewById<TextView>(R.id.tv_shoeName)
//        val company = mItem.findViewById<TextView>(R.id.tv_shoeCompany)
//        val size = mItem.findViewById<TextView>(R.id.tv_shoeSize)
//        val description = mItem.findViewById<TextView>(R.id.tv_shoeDescription)
//
//        name.text = "${shoe.shoeName}"
//        company.text = "${shoe.shoeCompany}"
//        size.text = "${shoe.shoeSize}"
//        description.text = "${shoe.shoeDescription}"

    }



}