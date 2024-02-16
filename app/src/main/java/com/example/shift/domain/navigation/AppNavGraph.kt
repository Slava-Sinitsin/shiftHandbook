package com.example.shift.domain.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.shift.ui.screens.PeopleListScreen
import com.example.shift.ui.screens.PersonInfoScreen
import com.example.shift.ui.theme.SHIFTTheme
import dagger.hilt.android.AndroidEntryPoint

private const val PERSON_INDEX = "person_index"

@AndroidEntryPoint
class AppNavGraph : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SHIFTTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    route = Graph.ROOT,
                    startDestination = Graph.PEOPLE_LIST
                ) {
                    composable(route = Graph.PEOPLE_LIST) {
                        PeopleListScreen(onPersonClick = { personIndex ->
                            navController.navigate("${Graph.PERSON_INFO}/$personIndex")
                        })
                    }
                    composable(
                        route = "${Graph.PERSON_INFO}/{$PERSON_INDEX}",
                        arguments = listOf(navArgument(PERSON_INDEX) { type = NavType.IntType })
                    ) {
                        val personIndex = it.arguments?.getInt(PERSON_INDEX)
                        personIndex?.let { index ->
                            PersonInfoScreen(
                                personIndex = index,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val PEOPLE_LIST = "people_list"
    const val PERSON_INFO = "person_info"
}