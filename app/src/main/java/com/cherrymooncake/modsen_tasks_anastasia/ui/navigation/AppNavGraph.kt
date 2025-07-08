package com.cherrymooncake.modsen_tasks_anastasia.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.cherrymooncake.modsen_tasks_anastasia.R
import com.cherrymooncake.modsen_tasks_anastasia.domain.model.PostDomainModel
import com.cherrymooncake.modsen_tasks_anastasia.ui.details.PostCommentsScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.LoginScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.login.SuccessLoginScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.posts.PostsScreen
import com.cherrymooncake.modsen_tasks_anastasia.ui.taskList.TaskListScreen
import com.google.gson.Gson

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScreenRoute.TaskList.route,
        modifier = modifier
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
            PostsScreen(
                onPostClick = { postDomainModel ->
                    val postJson = Gson().toJson(postDomainModel)
                    navController.navigate("${ScreenRoute.PostComments.route}/$postJson")
                }
            )
        }
        composable(
            route = "${ScreenRoute.PostComments.route}/{postJson}",
            arguments = listOf(navArgument("postJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val postJson = backStackEntry.arguments?.getString("postJson")
            if (!postJson.isNullOrEmpty()) {
                val postObject = Gson().fromJson(postJson, PostDomainModel::class.java)
                PostCommentsScreen(post = postObject)
            } else {
                Text(stringResource(R.string.error_post_data_could_not_be_retrieved))
            }
        }
    }
}
