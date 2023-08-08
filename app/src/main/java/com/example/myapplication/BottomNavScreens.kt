package com.example.myapplication

sealed class BottomNavScreens(
    val title: String,
    val icon: Int,
    val route: String
){
    object Home : BottomNavScreens(title = "home", icon = R.drawable.home, route = BottomNavRoutes.HOME.route)
    object SEARCH : BottomNavScreens(title = "search", icon = R.drawable.search, route = BottomNavRoutes.SEARCH.route)
    object REELS : BottomNavScreens(title = "reels", icon = R.drawable.reels, route = BottomNavRoutes.REELS.route)
    object NOTIFICATION : BottomNavScreens(title = "notification", icon = R.drawable.notifications, route = BottomNavRoutes.NOTIFICATIONS.route)
    object PROFILE : BottomNavScreens(title = "profile", icon = R.drawable.profile, route = BottomNavRoutes.PROFILE.route)

}

enum class BottomNavRoutes(val route: String){
    HOME("home"),
    SEARCH("search"),
    REELS("reels"),
    NOTIFICATIONS("notifications"),
    PROFILE("profile"),
}