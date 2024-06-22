package com.github.motoshige021.replycopy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue

import com.github.motoshige021.replycopy.ui.ReplyHomeViewModel
import com.github.motoshige021.replycopy.ui.theme.ReplyCopyTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures

class MainActivity : ComponentActivity() {
    private val viewModel: ReplyHomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReplyCopyTheme {
                val windowSize = calculateWindowSizeClass(this)
                val displayFutures = calculateDisplayFeatures(this)
                // getValue()がないエラーの対処 次を追加: import androidx.compose.runtime.getValue
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                ReplyApp(
                    windowSize = windowSize,
                    displayFeatures = displayFutures,
                    replyHomeUIState = uiState,
                    closeDetailScreen = {
                        viewModel.closeDetailScreen()
                    },
                    navigateToDetail = { email, pane ->
                        viewModel.setSelectedEmail(email, pane)
                    }
                )

                //Greeting("Tom")
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReplyCopyTheme {
        Greeting("Android")
    }
}