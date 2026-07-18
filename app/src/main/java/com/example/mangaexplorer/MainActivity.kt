package com.example.mangaexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.mangaexplorer.ui.components.HomeTopBar
import com.example.mangaexplorer.ui.navigation.NavGraph
import com.example.mangaexplorer.ui.navigation.Screen
import com.example.mangaexplorer.ui.theme.MangaExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MangaExplorerTheme {

                val navController = rememberNavController()
                var menuExpended by remember { mutableStateOf(false) }

                    Scaffold(
                        topBar = {
                            HomeTopBar(
                                onMenuClick = {
                                   menuExpended = true
                                },
                                onSignInClick = {}
                            )

                            Box(
                                modifier = Modifier.fillMaxWidth()
                                    .wrapContentSize(Alignment.TopStart)
                                    .padding(end = 16.dp)
                            ) {
                                DropdownMenu(
                                    expanded = menuExpended,
                                    onDismissRequest = { menuExpended = false}
                                ) {
                                    DropdownMenuItem(
                                        text = {Text("Home")},
                                        onClick = {
                                            menuExpended = false
                                            navController.navigate(Screen.Home.route)
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = {Text("Favorites")},
                                        onClick = {
                                            menuExpended = false
                                            navController.navigate(Screen.Favorites.route)
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = {Text("About")},
                                        onClick = {
                                        }
                                    )

                                }
                            }
                        }
                    ) { innerPadding ->

                        NavGraph(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )

                    }
                }
            }
    }
}