package com.example.p3_top30games_adrian_erika

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

data class Game(
    private var titleRes: Int,
    private var imageRes: Int,
    private var yearRes: Int,
    private var descriptionRes: Int,


) {

    public fun getTitleRes(): Int {
        return titleRes
    }

    public fun getImageRes(): Int {
        return imageRes
    }

    public fun getYearRes(): Int {
        return yearRes
    }

    public fun getDescriptionRes(): Int {
        return descriptionRes
    }

}






