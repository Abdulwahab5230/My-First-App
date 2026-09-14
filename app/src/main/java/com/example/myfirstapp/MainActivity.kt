package com.example.myfirstapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TimerApp()
        }
    }
}

@Composable
fun TimerApp() {

    var seconds by rememberSaveable { mutableStateOf(0) }
    var isRunning by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(isRunning) {
        while (isRunning) {
            delay(1000)
            seconds++
        }
    }

    val minutes = seconds / 60
    val remainingSeconds = seconds % 60

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "TIMER",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = String.format("%02d:%02d", minutes, remainingSeconds),
            style = MaterialTheme.typography.displayLarge
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                isRunning = true
            }
        ) {
            Text("START")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                isRunning = false
            }
        ) {
            Text("STOP")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                isRunning = false
                seconds = 0
            }
        ) {
            Text("RESET")
        }
    }
}