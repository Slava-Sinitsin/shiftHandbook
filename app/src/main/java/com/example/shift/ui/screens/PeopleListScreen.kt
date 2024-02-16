package com.example.shift.ui.screens

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.shift.domain.di.ViewModelFactoryProvider
import com.example.shift.ui.viewmodels.PeopleListScreenViewModel
import dagger.hilt.android.EntryPointAccessors

@OptIn(ExperimentalMaterial3Api::class)
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

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            PeopleListTopAppBar(
                viewModel = viewModel,
                scrollBehavior = scrollBehavior
            )
        }
    ) { scaffoldPadding ->
        PeopleList(viewModel = viewModel, paddingValues = scaffoldPadding)
        if (viewModel.isError) {
            Toast.makeText(LocalContext.current, viewModel.responseMessage, Toast.LENGTH_SHORT)
                .show()
            viewModel.setIsError(false)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeopleListTopAppBar(
    viewModel: PeopleListScreenViewModel,
    scrollBehavior: TopAppBarScrollBehavior
) {
    TopAppBar(
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
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(scrolledContainerColor = MaterialTheme.colorScheme.surface)
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
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    modifier = Modifier
                        .padding(start = 4.dp, top = 2.dp)
                        .clip(RoundedCornerShape(30.dp)),
                    model = person.picture?.large,
                    contentDescription = "PersonCardPicture"
                )
                Text(
                    modifier = Modifier
                        .padding(start = 20.dp, top = 16.dp, bottom = 16.dp),
                    textAlign = TextAlign.Start,
                    text = "${person.name?.title} ${person.name?.first} ${person.name?.last}",
                    fontSize = 20.sp
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
                Row(modifier = Modifier.padding(vertical = 3.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "PhoneIcon"
                    )
                    Text(
                        modifier = Modifier.padding(start = 2.dp),
                        text = "${person.phone}",
                        fontSize = 15.sp
                    )
                }
            }
        }
    }
}