package com.android.udacityfirst.ui.shoe

import android.view.View
import com.android.udacityfirst.model.Shoe

data class ShoeState(
    val isLoading: Boolean = false,
    val shoes: List<Shoe> = emptyList(),
)
