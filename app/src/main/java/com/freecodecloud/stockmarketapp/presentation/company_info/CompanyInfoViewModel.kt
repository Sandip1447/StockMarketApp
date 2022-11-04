package com.freecodecloud.stockmarketapp.presentation.company_info

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.freecodecloud.stockmarketapp.domain.repository.StockRepository
import com.freecodecloud.stockmarketapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    private val repository: StockRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

     val companySymbol: String = checkNotNull(savedStateHandle["symbol"])

    var state by mutableStateOf(CompanyInfoState())

    init {

        viewModelScope.launch {
            savedStateHandle.get<String>("symbol")?.let { symbol ->
                state = state.copy(isDataLoading = true)
                val companyInfoResult = async { repository.getCompanyInfo(symbol) }
                val intradayInfoResult = async { repository.getIntradayInfo(symbol) }

                when (val result = companyInfoResult.await()) {
                    is Resource.Error -> {
                        state = state.copy(
                            isDataLoading = false,
                            companyInfo = null,
                            message = result.message
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isDataLoading = false,
                            companyInfo = result.data,
                            message = null
                        )
                    }
                    else -> Unit
                }

                when (val result = intradayInfoResult.await()) {
                    is Resource.Error -> {
                        state = state.copy(
                            isDataLoading = false,
                            stockInfos = emptyList(),
                            message = result.message
                        )
                    }
                    is Resource.Success -> {
                        state = state.copy(
                            isDataLoading = false,
                            stockInfos = result.data ?: emptyList(),
                            message = null
                        )
                    }
                    else -> Unit
                }

            }

        }
    }
}