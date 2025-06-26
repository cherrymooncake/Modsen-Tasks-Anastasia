package com.cherrymooncake.modsen_tasks_anastasia.ui.navigation

sealed class ScreenRoute(val route: String){

    data object Login : ScreenRoute("login_screen")
    data object TaskList: ScreenRoute("tasks_list_screen")
    data object SuccessLogin: ScreenRoute("success_screen")

}