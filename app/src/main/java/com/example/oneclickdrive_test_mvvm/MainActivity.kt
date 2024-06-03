package com.example.oneclickdrive_test_mvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.oneclickdrive_test_mvvm.ui.theme.OneClickDriveTestMVVMTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                    NumberCalculatorApp()
                }
    }
}
@Composable
fun NumberCalculatorApp() {
    val viewModel: CalculatorViewModel = viewModel()
    val intersection by viewModel.intersection.collectAsState()
    val union by viewModel.union.collectAsState()
    val highestNumber by viewModel.highestNumber.collectAsState()

    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Simple Calculator App")
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = text1,
            onValueChange = { text1 = it },
            label = { Text("Edit text one value ") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = text2,
            onValueChange = { text2 = it },
            label = { Text("Edit text Two value ") },
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = text3,
            onValueChange = { text3 = it },
            label = { Text("Edit text three value ") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val list1 = text1.split(",").map { it.trim().toIntOrNull() ?: 0 }
            val list2 = text2.split(",").map { it.trim().toIntOrNull() ?: 0 }
            val list3 = text3.split(",").map { it.trim().toIntOrNull() ?: 0 }
            viewModel.calculate(list1, list2, list3)
        }) {
            Text("Calculate")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Intersection: $intersection")
        Text("Union: $union")
        Text("Highest Number: $highestNumber")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NumberCalculatorApp()
}
