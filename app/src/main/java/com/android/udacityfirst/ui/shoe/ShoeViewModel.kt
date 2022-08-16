package com.android.udacityfirst.ui.shoe

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.udacityfirst.model.Shoe
import com.android.udacityfirst.ui.shoe_details.ShoeEvent

class ShoeViewModel : ViewModel() {

    var virtualShoe = Shoe()

    private val _state = MutableLiveData<ShoeState>()
    val state: LiveData<ShoeState> = _state

    private val _allShoes = mutableListOf<Shoe>()

    fun onEvent(event: ShoeEvent) {
        when (event) {
            is ShoeEvent.InsertShoe -> {
                if (!_allShoes.contains(virtualShoe)) {
                    _allShoes.add(virtualShoe)
                    _state.postValue(ShoeState(isLoading = false, shoes = _allShoes))
                } else {
                    _state.postValue(ShoeState(isLoading = false, shoes = _allShoes))
                }
            }
            is ShoeEvent.ClearOldShoe -> {
                virtualShoe = Shoe("", "", "", "")
            }
        }
    }


//    fun onEvent(event: ShoeEvent){
//        when(event){
//            is ShoeEvent.Loading -> { _state.postValue(ShoeState(isLoading = true)) }
//            is ShoeEvent.AddShoe -> {
//                Log.e(TAG, "onEvent: ${_mShoes.size}", )
//                if (!_mShoes.contains(event.shoe)){
//                    _mShoes.add(event.shoe)
//                    _state.postValue(ShoeState(isLoading = false, shoes = _mShoes))
//                } else {
//                    _state.postValue(ShoeState(isLoading = false, shoes = _mShoes))
//                }
//            }
//        }
//    }
}