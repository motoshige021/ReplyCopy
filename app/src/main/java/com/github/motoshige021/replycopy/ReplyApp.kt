package com.github.motoshige021.replycopy

import android.telecom.Call.Details
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import com.github.motoshige021.replycopy.ui.ReplyHomeUIState
import com.github.motoshige021.replycopy.ui.navigation.ReplyNavigationActions
import com.github.motoshige021.replycopy.ui.util.*
import androidx.compose.runtime.getValue
import com.github.motoshige021.replycopy.ui.navigation.ReplyRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReplyApp(
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    replyHomeUIState: ReplyHomeUIState,
    closeDetailScreen: ()-> Unit = { },
    navigateToDetail: (Long, ReplyContentType) -> Unit = { _, _ -> }
) {
    val navigationType : ReplyNavigationType
    val contentType : ReplyContentType
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    val foldingDevicePosture = when {
        // ここの -> は when の ->  この部分はfoldingDevicePostureに whenで場合分けした値を代入
        // 折り畳みデバイスかつ、くの字開き関数がtrueの時、DevicePostureのくの字開きの矩形を設定
        isBookPosture(foldingFeature) -> DevicePosture.BookPosture(foldingFeature.bounds)
        // 折り畳みデバイスを180度で開いた状態
        isSeparating(foldingFeature)
            -> DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)
        // 折り畳みデバイスでない普通のデバイス
        else -> DevicePosture.NormalPosture
    }
    when(windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.SINGLE_PANE
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
            contentType = if (foldingDevicePosture != DevicePosture.NormalPosture) {
                // 折り畳みでないデバイスの時、デュアルパネル
                ReplyContentType.DUAL_PANE
            } else {
                ReplyContentType.SINGLE_PANE
            }

        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = if (foldingDevicePosture is DevicePosture.BookPosture) {
                //　完全に開いていない(平面でない)時、操作すると左側にナビゲーションバーを表示
                ReplyNavigationType.NAVIGATION_RAIL
            } else { // 平面に開いた時、常にナビゲーションバーを表示
                ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
            }
            contentType = ReplyContentType.DUAL_PANE
        } else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
            contentType = ReplyContentType.SINGLE_PANE
        }
    }

    val navigationContentPosition = when(windowSize.heightSizeClass) {
        WindowHeightSizeClass.Compact -> {
            ReplyNavigationContentPosition.TOP
        }
        WindowHeightSizeClass.Medium, WindowHeightSizeClass.Expanded -> {
            ReplyNavigationContentPosition.CENTER
        } else -> {
            ReplyNavigationContentPosition.TOP
        }
    }
    // 引数を全て編集したので描画Compomentをコールする
    ReplyNavigationWrapper(
        navigationType = navigationType,
        contentType = contentType,
        displayFeatures = displayFeatures,
        navigationContentPosition = navigationContentPosition,
        replyHomeUIState = replyHomeUIState,
        closeDetailScreen = closeDetailScreen,
        navigationToDetail = navigateToDetail
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ReplyNavigationWrapper(
    navigationType: ReplyNavigationType,
    contentType: ReplyContentType,
    displayFeatures: List<DisplayFeature>,
    navigationContentPosition: ReplyNavigationContentPosition,
    replyHomeUIState: ReplyHomeUIState,
    closeDetailScreen: () -> Unit,
    navigationToDetail: (Long, ReplyContentType) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navigationAction = remember(navController) {
        ReplyNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // ?: エルビス演算子 routeがnullならINBOX、nullでなければ routeの値を設定
    val selectedDestination = navBackStackEntry?.destination?.route ?: ReplyRoute.INBOX
    // >>>>>>>>>>>>>>>>> 実装 <<<<<<<<<<<<<<<<<<<<
    NotImplementedError("ReplyApp()")
}