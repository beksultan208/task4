package com.example.finalassignment3.realThirdTask.realThirdTask.view.navigation


import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens.ActorPage
import com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens.ScreenPager
import com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens.Main_Screen
import com.example.finalassignment3.realThirdTask.realThirdTask.view.Screens.FilmScreen
import com.example.finalassignment3.realThirdTask.realThirdTask.view.viewmodel.ActorScreen
import com.example.finalassignment3.realThirdTask.realThirdTask.view.viewmodel.ActorViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationClass(viewModell: ActorViewModel) {
    val navController = rememberNavController()

    androidx.compose.material3.Scaffold(
        content = {
            NavHost(

                navController = navController,
                startDestination = "pagerScreen"
            ) {

                composable("pagerScreen") {
                    ScreenPager(navController)
                }
                composable("newScreen") {
                    Main_Screen(viewModell)
                }
                composable(
                    route = "NewScreen3/{id}",
                    arguments = listOf(navArgument("id") { type = NavType.IntType })
                ) { backStackEntry ->
                    val id = backStackEntry.arguments?.getInt("id") ?: 0

                    FilmScreen(kinopoiskId = id,navController= navController)
                }
                composable("ActorPage/{staffId}") { backStackEntry ->
                    val staffId = backStackEntry.arguments?.getString("staffId")?.toIntOrNull()
                    if (staffId != null) {
                        ActorPage(staffId = staffId, navController = navController)
                    }
                }
            }
        }
    )
}

