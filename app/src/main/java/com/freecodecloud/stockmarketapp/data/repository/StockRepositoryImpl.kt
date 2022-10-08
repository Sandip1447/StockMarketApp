package com.freecodecloud.stockmarketapp.data.repository

import androidx.room.withTransaction
import com.freecodecloud.stockmarketapp.data.csv.CSVParser
import com.freecodecloud.stockmarketapp.data.local.StockDatabase
import com.freecodecloud.stockmarketapp.data.mappper.toCompanyInfo
import com.freecodecloud.stockmarketapp.data.mappper.toCompanyListing
import com.freecodecloud.stockmarketapp.data.mappper.toCompanyListingEntity
import com.freecodecloud.stockmarketapp.data.remote.StockApi
import com.freecodecloud.stockmarketapp.domain.model.CompanyInfo
import com.freecodecloud.stockmarketapp.domain.model.CompanyListing
import com.freecodecloud.stockmarketapp.domain.model.IntradayInfo
import com.freecodecloud.stockmarketapp.domain.repository.StockRepository
import com.freecodecloud.stockmarketapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val stockApi: StockApi,
    private val database: StockDatabase,
    private val companyListingParser: CSVParser<CompanyListing>,
    private val intradayInfoParser: CSVParser<IntradayInfo>
) : StockRepository {

    val dao = database.stockDao

    override suspend fun getCompanyListing(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {

        return flow {

            emit(Resource.Loading(true))

            val localListings = dao.searchCompanyListing(query)

            emit(Resource.Success(
                data = localListings.map { it.toCompanyListing() }
            ))

            val isDbEmpty = localListings.isEmpty() && query.isBlank()

            val shouldJustFetchFromCache = !isDbEmpty && !fetchFromRemote

            if (shouldJustFetchFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remoteListings = try {

                val response = stockApi.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { list ->
                database.withTransaction {
                    dao.clearCompanyListing()
                    dao.insertCompanyListing(list.map { it.toCompanyListingEntity() })
                }

                emit(
                    Resource.Success(
                        data = dao.searchCompanyListing("").map { it.toCompanyListing() })
                )
                emit(Resource.Loading(false))


            }

        }
    }

    override suspend fun getIntradayInfo(symbol: String): Resource<List<IntradayInfo>> {
        return try {
            val response = stockApi.getIntradayInfo(symbol)
            Resource.Success(data = intradayInfoParser.parse(response.byteStream()))
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load intraday info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load intraday info")
        }
    }

    override suspend fun getCompanyInfo(symbol: String): Resource<CompanyInfo> {
        return try {
            val response = stockApi.getCompanyInfo(symbol)
            Resource.Success(data = response.toCompanyInfo())
        } catch (e: IOException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load company info")
        } catch (e: HttpException) {
            e.printStackTrace()
            Resource.Error(message = "Couldn't load company info")
        }
    }

}