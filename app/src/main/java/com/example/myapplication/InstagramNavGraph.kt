package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.screens.HomeScreen
import com.example.myapplication.screens.NotificationScreen
import com.example.myapplication.screens.ProfileScreen
import com.example.myapplication.screens.ReelsScreen
import com.example.myapplication.screens.SearchScreen

@Composable
fun InstagramNavGraph(navHostController: NavHostController){

    NavHost(navController =navHostController , startDestination = BottomNavScreens.Home.route){
         composable(route = BottomNavRoutes.HOME.route){
             HomeScreen()
         }
        composable(route = BottomNavRoutes.SEARCH.route){
             SearchScreen()
         }
        composable(route = BottomNavRoutes.REELS.route){
             ReelsScreen()
         }
        composable(route = BottomNavRoutes.NOTIFICATIONS.route){
             NotificationScreen()
         }
        composable(route = BottomNavRoutes.PROFILE.route){
             ProfileScreen()
         }
    }
}