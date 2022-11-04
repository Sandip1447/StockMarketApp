package com.freecodecloud.stockmarketapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.freecodecloud.stockmarketapp.presentation.company_info.CompanyInfoScreen
import com.freecodecloud.stockmarketapp.presentation.company_listings.CompanyListingsScreen
import com.freecodecloud.stockmarketapp.presentation.ui.theme.StockMarketAppTheme
import com.freecodecloud.stockmarketapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize()) {

                    // Required NavController
                    val navController = rememberNavController()

                    // A NavHost required
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CompanyListing.route
                    ) {

                        // For Company listing screen route
                        composable(
                            route = Screen.CompanyListing.route
                        ) {
                            CompanyListingsScreen(
                                context = this@MainActivity,
                                navController = navController
                            )
                        }

                        // For Company info with screen route with passing argument symbol
                        composable(
                            route = Screen.CompanyInfo.route + "?symbol={symbol}",
                            arguments = listOf(
                                navArgument(
                                    name = "symbol"
                                ) {
                                    type = NavType.StringType
                                    defaultValue = ""
                                }
                            )
                        ) {
                            CompanyInfoScreen(navController = navController)
                        }

                    }
                }
            }
        }
    }
}
