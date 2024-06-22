package com.github.motoshige021.replycopy.ui.util

import android.graphics.drawable.GradientDrawable.Orientation
import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed interface DevicePosture {
    // 折り畳みデバイスでない普通のデバイス
    object NormalPosture: DevicePosture
    // 折り畳みデバイスを開いたが180度より小さい状態
    data class BookPosture (
        val hingePosition: Rect
    ) : DevicePosture
    // 折り畳みデバイスを180度で開いた状態
    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

// HALF_OPENEDが本を手で持って読むときのように、90度(180でない角度)等で開いた状態
// 折り畳みデバイスを開いたが、180度より小さい角度で開いた状態か？
@OptIn(ExperimentalContracts::class)
fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
        foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
}

enum class ReplyContentType {
    SINGLE_PANE, DUAL_PANE,
}

enum class ReplyNavigationType {
    BOTTOM_NAVIGATION, // 下側にNavigationバー表示
    NAVIGATION_RAIL,   // 左サイドにNavigationバー表示
    PERMANENT_NAVIGATION_DRAWER, // 常にNavigationバー表示
}

enum class ReplyNavigationContentPosition {
    TOP, CENTER
}