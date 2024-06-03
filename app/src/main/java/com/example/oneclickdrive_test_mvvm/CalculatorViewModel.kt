package com.example.oneclickdrive_test_mvvm
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CalculatorViewModel @Inject constructor() : ViewModel() {

    private val _intersection = MutableStateFlow("")
    val intersection: StateFlow<String> = _intersection.asStateFlow()

    private val _union = MutableStateFlow("")
    val union: StateFlow<String> = _union.asStateFlow()

    private val _highestNumber = MutableStateFlow("")
    val highestNumber: StateFlow<String> = _highestNumber.asStateFlow()

    fun calculate(list1: List<Int>, list2: List<Int>, list3: List<Int>) {

        viewModelScope.launch {
            val intersection = list1.filter { it in list2 && it in list3 }
            val union = (list1 + list2 + list3).distinct().sorted()
            val highestNumber = union.maxOrNull()

            _intersection.value = intersection.joinToString(", ")
            _union.value = union.joinToString(", ")
            _highestNumber.value = highestNumber?.toString() ?: ""
        }
    }
}
