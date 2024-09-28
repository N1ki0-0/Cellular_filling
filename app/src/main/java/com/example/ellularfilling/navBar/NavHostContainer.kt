package com.example.ellularfilling.navBar

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ellularfilling.CellViewModel
import com.example.ellularfilling.screens.Cells
import com.example.ellularfilling.screens.LazyColumnSample
import com.example.ellularfilling.screens.SplashScreen
import com.example.ellularfilling.screens.WelcomeScreen

@Composable
fun NavHostContainer(navController: NavHostController,
                     padding: PaddingValues){

    NavHost(navController = navController,
        modifier = Modifier.padding(paddingValues = padding),
        startDestination = Splash,
        builder = {
            composable(Splash){
                SplashScreen(navController = navController)
            }
            composable(Cells) {
                val cellViewModel: CellViewModel = viewModel()
                LazyColumnSample(navController = navController,cellViewModel)
            }
        })
}