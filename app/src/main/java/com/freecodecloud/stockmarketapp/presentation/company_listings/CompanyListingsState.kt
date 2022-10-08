package com.freecodecloud.stockmarketapp.presentation.company_listings

import com.freecodecloud.stockmarketapp.domain.model.CompanyListing

data class CompanyListingsState(
    val isDataLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val companies: List<CompanyListing> = emptyList(),
    val query: String = ""
)
