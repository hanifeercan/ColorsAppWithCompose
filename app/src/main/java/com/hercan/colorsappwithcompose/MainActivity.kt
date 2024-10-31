package com.hercan.colorsappwithcompose

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.hercan.colorsappwithcompose.ui.theme.ColorsAppWithComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorsAppWithComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    BookScreen(viewModel)
                }
            }
        }
    }
}

@Composable
fun BookScreen(viewModel: MainViewModel) {
    LaunchedEffect(Unit) {
        viewModel.getAllColors()
    }
    val uiState by viewModel.colorScreenUiState.collectAsState()

    when {
        uiState.isError -> {
            Text(text = "Error: ${uiState.errorMessage}")
        }

        uiState.isLoading -> {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(64.dp), color = Color.Black, strokeWidth = 5.dp
                )
            }
        }

        uiState.colors.isNotEmpty() -> {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.colors.size) { index ->
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(vertical = 16.dp)
                    ) {
                        BrushStrokeViewCompose(
                            context = LocalContext.current,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp)
                                .height(50.dp),
                            hexColor = "#${uiState.colors[index].hex}"
                        )
                        SelectionContainer {
                            Text(
                                text = AnnotatedString(uiState.colors[index].name ?: ""),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .padding(top = 8.dp)
                            )
                        }
                        SelectionContainer {
                            Text(
                                text = uiState.colors[index].hex ?: "",
                                modifier = Modifier.padding(horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BrushStrokeViewCompose(
    context: Context, modifier: Modifier = Modifier, hexColor: String = "#00000000"
) {
    AndroidView(modifier = modifier, factory = {
        ColorView(context).apply {
            setColor(hexColor)
        }
    }, update = {
        it.setColor(hexColor)
    })
}
