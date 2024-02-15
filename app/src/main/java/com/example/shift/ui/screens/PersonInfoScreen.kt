package com.example.shift.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Divider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.shift.R
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
            .padding(top = paddingValues.calculateTopPadding() + 10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .height(150.dp)
                    .width(150.dp)
                    .clip(RoundedCornerShape(75.dp)),
                model = viewModel.person.picture?.large,
                contentDescription = "PersonInfoPicture"
            )
            Text(
                text = "${viewModel.person.name?.title} ${viewModel.person.name?.first} ${viewModel.person.name?.last}",
                fontSize = 30.sp
            )
            Text(
                text = "@${viewModel.person.login?.username}",
                fontSize = 15.sp
            )
            Divider(
                modifier = Modifier.padding(vertical = 10.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(R.drawable.cake),
                            contentDescription = "CakeIcon"
                        )
                        Text(
                            modifier = Modifier.padding(start = 4.dp),
                            text = viewModel.formatDate(viewModel.person.dob?.date ?: "")
                        )
                    }
                    Row(
                        modifier = Modifier.padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                when (viewModel.person.gender) {
                                    "male" -> {
                                        R.drawable.male
                                    }

                                    "female" -> {
                                        R.drawable.female
                                    }

                                    else -> {
                                        R.drawable.person
                                    }
                                }
                            ),
                            contentDescription = "GenderIcon"
                        )
                        Text(
                            modifier = Modifier.padding(start = 2.dp),
                            text = viewModel.person.dob?.age.toString()
                        )
                    }
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "LocationOnIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = "${viewModel.person.location?.country}, ${viewModel.person.location?.city}, ${viewModel.person.location?.street?.name} ${viewModel.person.location?.street?.number}"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Email, contentDescription = "EmailIcon")
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = viewModel.person.email ?: "Not specified"
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp)
                ) {
                    Icon(imageVector = Icons.Filled.Phone, contentDescription = "PhoneIcon")
                    Text(
                        modifier = Modifier.padding(start = 4.dp),
                        text = viewModel.person.phone ?: "Not specified"
                    )
                }
            }
        }
    }
}