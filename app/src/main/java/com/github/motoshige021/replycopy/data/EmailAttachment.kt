package com.github.motoshige021.replycopy.data

import androidx.annotation.DrawableRes

data class EmailAttachment(
    @DrawableRes val resId: Int,
    val contentDesc: String,
)
