package com.example.shift.domain.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.shift.ui.screens.Application

@Composable
fun RootNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.PEOPLE_LIST
    ) {
        composable(route = Graph.PEOPLE_LIST) {
            Application()
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val PEOPLE_LIST = "people_list"
}