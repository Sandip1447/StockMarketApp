package com.freecodecloud.stockmarketapp.presentation.company_info

import com.freecodecloud.stockmarketapp.domain.model.CompanyInfo
import com.freecodecloud.stockmarketapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val isDataLoading: Boolean = false,
    val message: String? = null,
    val stockInfos: List<IntradayInfo> = emptyList(),
    val companyInfo: CompanyInfo? = null
)
