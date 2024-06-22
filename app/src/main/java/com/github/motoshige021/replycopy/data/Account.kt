package com.github.motoshige021.replycopy.data

import androidx.annotation.DrawableRes

data class Account(
    val id: Long,
    val uid: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val altEmail: String,
    // @DrawableRes: drawableのIDとして定義したものに限定する
    @DrawableRes val avatar: Int,
    var isCurrentAccount: Boolean = false,
) {
    val fullName: String = "$firstName $lastName"
}
