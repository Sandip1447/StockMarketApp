package com.freecodecloud.stockmarketapp.domain.repository

import com.freecodecloud.stockmarketapp.domain.model.CompanyInfo
import com.freecodecloud.stockmarketapp.domain.model.CompanyListing
import com.freecodecloud.stockmarketapp.domain.model.IntradayInfo
import com.freecodecloud.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>


    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>

}