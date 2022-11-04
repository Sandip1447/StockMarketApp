package com.freecodecloud.stockmarketapp.presentation.company_listings

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.freecodecloud.stockmarketapp.presentation.company_listings.components.CompanyItem
import com.freecodecloud.stockmarketapp.util.Screen
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CompanyListingsScreen(
    context: Context,
    navController: NavController,
    viewModel: CompanyListingsViewModel = hiltViewModel()
) {

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = viewModel.state.isRefreshing
    )

    val scaffoldState = rememberScaffoldState()

    val state = viewModel.state

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text("Stocks") }, actions = {
                IconButton(onClick = {
                    Toast.makeText(context, "Feature not available", Toast.LENGTH_SHORT)
                        .show()
                }) {
                    Icon(Icons.Filled.Sort, null)
                }
            })
        },
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            /*  Search Box start...... */
            OutlinedTextField(
                value = state.query,
                onValueChange = {
                    viewModel.onEvent(CompanyListingsEvent.OnSearchQueryChange(it))
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text(text = "Search...")
                },
                maxLines = 1,
                singleLine = true
            )
            //  Spacer(modifier = Modifier.width(4.dp))

            /*  ........END  Search Box ...... */

            SwipeRefresh(state = swipeRefreshState,
                onRefresh = {
                    viewModel.onEvent(CompanyListingsEvent.Refresh)
                }) {

                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(state.companies.size) { index ->
                        val company = state.companies[index]
                        CompanyItem(
                            company = company,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Navigate to company info (details) screen
                                    navController.navigate(
                                        Screen.CompanyInfo.route + "?symbol=${company.symbol}"
                                    )
                                    Log.d(
                                        "debug",
                                        "CompanyListingsScreen:symbol at ${company.symbol} "
                                    )
                                }
                                .padding(16.dp)
                        )
                        if (index < state.companies.size) {
                            Divider(modifier = Modifier.padding(horizontal = 16.dp))
                        }
                    }
                }
            }
        }
    }
}