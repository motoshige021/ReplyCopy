package com.github.motoshige021.replycopy.ui.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import com.github.motoshige021.replycopy.ui.util.ReplyNavigationContentPosition

@Composable
fun ReplyNavigationRail(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (ReplyTopLevelDistination) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    // TODO >>>>>>>>>>>> <<<<<<<<<<<<<<<
}

@Composable
fun ReplyBottomNavigationBar(
    selectedDestination: String,
    navigateToTopLevelDestination: (ReplyTopLevelDistination) -> Unit
) {
    // TODO >>>>>>>>>>>> <<<<<<<<<<<<<<<
}

@Composable
fun ModalNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (ReplyTopLevelDistination) -> Unit,
    onDrawerClicked: () -> Unit,
) {
    // TODO >>>>>>>>>>>> <<<<<<<<<<<<<<<
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParmanentNavigationDrawerContent(
    selectedDestination: String,
    navigationContentPosition: ReplyNavigationContentPosition,
    navigateToTopLevelDestination: (ReplyTopLevelDistination) -> Unit
) {
    // TODO >>>>>>>>>>>> <<<<<<<<<<<<<<<
}