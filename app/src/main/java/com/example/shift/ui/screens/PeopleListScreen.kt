package com.example.shift.ui.screens

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.shift.data.repository.ViewModelFactoryProvider
import com.example.shift.ui.viewmodels.PeopleListScreenViewModel
import dagger.hilt.android.EntryPointAccessors

@Composable
fun PeopleListScreen(onPersonClick: (personIndex: Int) -> Unit) {
    val factory = EntryPointAccessors.fromActivity(
        LocalContext.current as Activity,
        ViewModelFactoryProvider::class.java
    ).peopleListScreenViewModelFactory()
    val viewModel: PeopleListScreenViewModel = viewModel(
        factory = PeopleListScreenViewModel.providePeopleListScreenViewModel(
            factory,
            onPersonClick
        )
    )
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = { PeopleListCenterAlignedTopAppBar(viewModel = viewModel) }
    ) { scaffoldPadding ->
        PeopleList(viewModel = viewModel, paddingValues = scaffoldPadding)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListCenterAlignedTopAppBar(
    viewModel: PeopleListScreenViewModel
) {
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = "Handbook",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 20.sp
            )
        },
        actions = {
            IconButton(
                onClick = { viewModel.refreshPeopleList() }
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = "RefreshPeopleListIconButton",
                )
            }
        }
    )
}

@Composable
fun PeopleList(
    viewModel: PeopleListScreenViewModel,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding())
    ) {
        items(viewModel.peopleCardInfo.size) { index ->
            Person(
                viewModel = viewModel,
                index = index
            )
        }
    }
}

@Composable
fun Person(viewModel: PeopleListScreenViewModel, index: Int) {
    val person = viewModel.getPersonCardInfo(index = index)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { viewModel.navigateToPerson(index) },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        colors = CardDefaults.cardColors()
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(30.dp)),
                    model = person.picture?.large,
                    contentDescription = "PersonCardPicture"
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.Start,
                    text = "${person.name?.title} ${person.name?.first} ${person.name?.last}",
                    fontSize = 15.sp
                )
            }
            Divider(
                modifier = Modifier.padding(vertical = 2.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.secondary
            )
            Column {
                Row {
                    Icon(
                        imageVector = Icons.Filled.LocationOn,
                        contentDescription = "LocationOnIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = "${person.location?.country}, ${person.location?.city}",
                        fontSize = 15.sp
                    )
                }
                Row(modifier = Modifier.padding(top = 3.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "PhoneIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 3.dp),
                        text = "${person.phone}",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}