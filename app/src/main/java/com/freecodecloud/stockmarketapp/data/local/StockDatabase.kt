package com.freecodecloud.stockmarketapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.freecodecloud.stockmarketapp.data.local.dao.StockDao
import com.freecodecloud.stockmarketapp.data.local.entity.CompanyListingEntity

@Database(
    entities = [CompanyListingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StockDatabase : RoomDatabase() {

    abstract val stockDao: StockDao

    companion object {
        const val DATABASE_NAME = "stock_db"
    }
}