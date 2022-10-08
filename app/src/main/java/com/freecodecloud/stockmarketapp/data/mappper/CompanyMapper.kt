package com.freecodecloud.stockmarketapp.data.mappper

import com.freecodecloud.stockmarketapp.data.local.entity.CompanyListingEntity
import com.freecodecloud.stockmarketapp.data.remote.dto.CompanyInfoDto
import com.freecodecloud.stockmarketapp.domain.model.CompanyInfo
import com.freecodecloud.stockmarketapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange,
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        name = name ?: "",
        description = description ?: "",
        symbol = symbol ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}