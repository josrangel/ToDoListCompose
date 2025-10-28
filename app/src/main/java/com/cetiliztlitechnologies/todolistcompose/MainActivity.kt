package com.cetiliztlitechnologies.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cetiliztlitechnologies.todolistcompose.ui.theme.ToDoListComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ToDoListComposeTheme {
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.safeDrawing)
                ) { innerPadding ->
                    ToDoApp(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ToDoApp(name: String, modifier: Modifier = Modifier) {
    var taskList by remember { mutableStateOf(listOf<String>()) }
    var newTaskString by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Lista de Tareas", fontSize = 24.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            TextField(
                value = newTaskString,
                onValueChange = { newTaskString = it },
                modifier = Modifier.weight(1f),
                label = { Text("Nueva tarea") }
            )

            Spacer(modifier = Modifier.width(8.dp))

            Button (onClick = {
                if (newTaskString.isNotBlank()) {
                    taskList = taskList + newTaskString
                    newTaskString = ""
                }
            }) {
                Text("Agregar")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(taskList.size) { index ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Text(taskList[index], modifier = Modifier.weight(1f))
                    IconButton(onClick = {
                        taskList = taskList.toMutableList().also { it.removeAt(index) }
                    }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Eliminar")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ToDoAppPreview() {
    ToDoListComposeTheme {
        ToDoApp("Android")
    }
}