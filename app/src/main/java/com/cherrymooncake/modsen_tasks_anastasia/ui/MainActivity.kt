package com.cherrymooncake.modsen_tasks_anastasia.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.SuccessLoginScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.navigation.ScreenRoute
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsViewModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.taskList.TaskListScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.theme.ModsenTasksAnastasiaTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ModsenTasksAnastasiaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = ScreenRoute.TaskList.route
                    ) {
                        composable(ScreenRoute.TaskList.route) {
                            TaskListScreen(
                                onNavigateToTask1 = {
                                    navController.navigate(ScreenRoute.Login.route)
                                },
                                onNavigateToTask2 = {
                                    navController.navigate(ScreenRoute.Posts.route)
                                }
                            )
                        }
                        composable(ScreenRoute.Login.route) {
                            LoginScreen(
                                onNavigateToSuccess = {
                                    navController.navigate(ScreenRoute.SuccessLogin.route) {
                                        popUpTo(ScreenRoute.Login.route) { inclusive = true }
                                    }
                                }
                            )
                        }
                        composable(ScreenRoute.SuccessLogin.route) {
                            SuccessLoginScreen()
                        }
                        composable(ScreenRoute.Posts.route) {
                            val postsViewModel: PostsViewModel = koinViewModel()

                            PostsScreen(postsViewModel)
                        }
                    }
                }
            }
        }
    }
}