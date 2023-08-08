package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.selects.select
import org.w3c.dom.Text

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    InstagramApp()
                }
            }
        }
    }
}

@Composable
fun InstagramApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavBar(navController) },
        containerColor = Color.White
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            InstagramNavGraph(navHostController = navController)

        }
    }
}

@Composable
fun BottomNavBar(navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val screenList = listOf(
        BottomNavScreens.Home,
        BottomNavScreens.SEARCH,
        BottomNavScreens.REELS,
        BottomNavScreens.NOTIFICATION,
        BottomNavScreens.PROFILE
    )

    BottomAppBar() {
        screenList.forEach {screen ->
            NavigationBarItem(
                selected = currentDestination?.route == screen.route,
                onClick = {navController.navigate(screen.route) },
                icon = { Icon(painter = painterResource(id = screen.icon), contentDescription = "nav icon" ) },
                modifier = Modifier.background(color = Color.White),
                label = { Text(text = screen.title)}
            )

        }
    }
}
