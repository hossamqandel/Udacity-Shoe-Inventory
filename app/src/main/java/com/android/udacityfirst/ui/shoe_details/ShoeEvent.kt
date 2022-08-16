package com.android.udacityfirst.ui.shoe_details

import com.android.udacityfirst.model.Shoe

sealed class ShoeEvent {
    data class AddShoe(val shoe: Shoe) : ShoeEvent()
    object Loading : ShoeEvent()
    object InsertShoe : ShoeEvent()
    object ClearOldShoe : ShoeEvent()
}
