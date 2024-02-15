package com.example.shift.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.shift.ui.viewmodels.PersonInfoScreenViewModel

@Suppress("UNCHECKED_CAST")
@Composable
fun PersonInfoScreen(
    personIndex: Int,
    navController: NavHostController
) {
    val viewModel = viewModel<PersonInfoScreenViewModel>(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PersonInfoScreenViewModel(personIndex, navController) as T
            }
        }
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { PersonInfoCenterAlignedTopAppBar(viewModel = viewModel) }
    ) { scaffoldPadding ->
        PersonInfo(viewModel = viewModel, paddingValues = scaffoldPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonInfoCenterAlignedTopAppBar(
    viewModel: PersonInfoScreenViewModel
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "${viewModel.person.name?.title} ${viewModel.person.name?.first} ${viewModel.person.name?.last}",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { viewModel.onBackButtonClick() }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "ArrowBackIconButton",
                )
            }
        }
    )
}

@Composable
fun PersonInfo(
    viewModel: PersonInfoScreenViewModel,
    paddingValues: PaddingValues
) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding())
    ) {

    }
}