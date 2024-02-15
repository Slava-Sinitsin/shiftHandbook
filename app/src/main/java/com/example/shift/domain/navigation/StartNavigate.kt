package com.example.shift.domain.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.shift.ui.theme.SHIFTTheme

class StartNavigate : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SHIFTTheme {
                RootNavGraph(navController = rememberNavController())
            }
        }
    }
}