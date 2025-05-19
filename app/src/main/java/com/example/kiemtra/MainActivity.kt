package com.example.kiemtra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                UserInfoScreen()
            }
        }
    }
}

@Composable
fun UserInfoScreen() {
    var name by remember { mutableStateOf("") }
    var ageInput by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    fun classifyAge(age: Int): String {
        return when {
            age < 2 -> "Em bé"
            age in 2..6 -> "Trẻ em"
            age in 7..65 -> "Người lớn"
            else -> "Người già"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nhập họ và tên") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = ageInput,
            onValueChange = { ageInput = it },
            label = { Text("Nhập tuổi") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )


        Button(onClick = {
            val age = ageInput.toIntOrNull()
            result = if (age != null) {
                "Tên: $name\nTuổi: $age\nPhân loại: ${classifyAge(age)}"
            } else {
                "Tuổi không hợp lệ!"
            }
        }) {
            Text("Kiểm tra")
        }

        if (result.isNotEmpty()) {
            Text(text = result, style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MaterialTheme {
        UserInfoScreen()
    }
}
