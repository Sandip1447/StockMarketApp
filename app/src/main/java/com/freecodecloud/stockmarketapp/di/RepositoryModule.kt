package com.freecodecloud.stockmarketapp.di

import com.freecodecloud.stockmarketapp.data.csv.CSVParser
import com.freecodecloud.stockmarketapp.data.csv.CompanyListingParser
import com.freecodecloud.stockmarketapp.data.csv.IntradayInfoParser
import com.freecodecloud.stockmarketapp.data.repository.StockRepositoryImpl
import com.freecodecloud.stockmarketapp.domain.model.CompanyListing
import com.freecodecloud.stockmarketapp.domain.model.IntradayInfo
import com.freecodecloud.stockmarketapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindIntradayInfoParser(
        intradayInfoParser: IntradayInfoParser
    ): CSVParser<IntradayInfo>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository

}