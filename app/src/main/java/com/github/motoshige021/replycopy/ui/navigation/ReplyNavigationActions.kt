package com.github.motoshige021.replycopy.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Inbox
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.outlined.ChatBubbleOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import com.github.motoshige021.replycopy.R

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

val TOP_LEVEL_DESTINATIONS = listOf(
    ReplyTopLevelDistination(
        route = ReplyRoute.INBOX,
        //　Implement("androidx.compose.material:material-icons-extended")
        selectedIcon = Icons.Default.Inbox,
        unselectedIcon = Icons.Default.Inbox,
        iconTextId = R.string.tab_inbox,
    ),
    ReplyTopLevelDistination(
        route = ReplyRoute.ARTICLES,
        selectedIcon = Icons.Default.Article,
        unselectedIcon = Icons.Default.Article,
        iconTextId = R.string.tab_article,
    ),
    ReplyTopLevelDistination(
        route = ReplyRoute.DM,
        selectedIcon = Icons.Outlined.ChatBubbleOutline,
        unselectedIcon = Icons.Outlined.ChatBubbleOutline,
        iconTextId = R.string.tab_inbox,
    ),
    ReplyTopLevelDistination(
        route = ReplyRoute.GROUPS,
        selectedIcon = Icons.Default.People,
        unselectedIcon = Icons.Default.People,
        iconTextId = R.string.tab_article,
    )
)