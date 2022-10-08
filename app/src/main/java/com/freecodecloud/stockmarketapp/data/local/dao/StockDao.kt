package com.freecodecloud.stockmarketapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.freecodecloud.stockmarketapp.data.local.entity.CompanyListingEntity

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(
        list: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListing()

    @Query(
        """
        SELECT * 
        FROM companylistingentity
        WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
        UPPER(:query) == symbol
    """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>
}