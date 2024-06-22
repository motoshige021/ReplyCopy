package com.github.motoshige021.replycopy.ui.navigation

import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController

object ReplyRoute { // object宣言でシングルトンなクラスを生成できる
    const val INBOX = "Inbox"
    const val ARTICLES = "Articles"
    const val DM = "DirectMessage"
    const val GROUPS = "Groups"
}

// data classは データを保持するのに適したクラス。自動でデータ扱うのに便利なメソッドが生成される
// toString()、hashCode()、equals()、copy()、componentN()が自動生成される
data class ReplyTopLevelDistination(
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int,
)

class ReplyNavigationActions(private val navController: NavHostController) {
    fun navigateTo(destination: ReplyTopLevelDistination) {
        navController.navigate(destination.route) {
            this.popUpTo(navController.graph.findStartDestination().id) {
                this.saveState = true
            }
            this.launchSingleTop = true
            this.restoreState = true
        }
    }
}