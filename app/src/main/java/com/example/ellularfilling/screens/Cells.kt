package com.example.ellularfilling.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.ellularfilling.CellViewModel
import com.example.ellularfilling.CellsDb
import com.example.ellularfilling.R
import com.example.ellularfilling.ui.theme.Violet
import kotlin.random.Random

val gradient = Brush.linearGradient(
    0.0f to Violet,
    500.0f to Color.Black,
    start = Offset.Zero,
    end = Offset.Infinite
)

@Composable
fun Cells(cellsDb: CellsDb) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White,
                shape = RoundedCornerShape(8.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Image(
            painter = painterResource(id = cellsDb.image),
            contentDescription = null,
            modifier = Modifier.size(60.dp).clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = cellsDb.name, style = MaterialTheme.typography.headlineLarge)
            Text(text = cellsDb.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun LazyColumnSample(navController: NavHostController, cellsViewModel: CellViewModel) {

    val cellsDb = cellsViewModel.cellsDb

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 60.dp) // Оставляем место для кнопки
        ) {
            // Заголовок сверху
            Text(
                text = "Клеточное наполнение",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier
                    .padding(bottom = 16.dp, top = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                color = Color.White
            )

            // Список объектов в LazyColumn
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(cellsDb) { cell ->
                    Cells(cell)
                }
            }
        }

        // Кнопка для добавления случайной клетки
        Button(
            onClick = {
                cellsViewModel.addCell()
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Фиксируем кнопку снизу
                .padding(16.dp)
        ) {
            Text("Сотворить")
        }
    }

}

@Composable
fun MyApp() {
    MaterialTheme {

        val cellsViewModel: CellViewModel = viewModel()
        val navController = rememberNavController()
        //LazyColumnSample(navController, cellsViewModel)
        Cells(cellsDb = CellsDb(1, "Мертвая", "или прикидывается", R.drawable.maxresdefault))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp()
}