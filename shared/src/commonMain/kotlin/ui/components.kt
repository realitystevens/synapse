package com.synapseplus.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.synapseplus.viewmodel.*
import com.synapseplus.habits.HabitDTO

@Composable 
fun LoginScreen(vm: AuthVM, onLoggedIn: () -> Unit) {
    val state by vm.state.collectAsState()
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    
    Column(Modifier.padding(16.dp)) {
        Text("Sign in", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = user, 
            onValueChange = { user = it }, 
            label = { Text("Username") }
        )
        OutlinedTextField(
            value = pass, 
            onValueChange = { pass = it }, 
            label = { Text("Password") }
        )
        Spacer(Modifier.height(12.dp))
        Button(
            enabled = !state.loading, 
            onClick = { vm.login(user, pass) }
        ) { 
            Text("Login") 
        }
        if (state.token != null) {
            LaunchedEffect(state.token) { onLoggedIn() }
        }
        state.error?.let { 
            Text("Error: $it", color = MaterialTheme.colorScheme.error) 
        }
    }
}

@Composable 
fun HabitListScreen(vm: HabitListVM, onSelect: (HabitDTO) -> Unit) {
    val s by vm.state.collectAsState()
    LaunchedEffect(Unit) { vm.load() }
    
    when {
        s.loading -> CircularProgressIndicator()
        s.error != null -> Text("Error: ${s.error}")
        else -> LazyColumn(Modifier.fillMaxSize().padding(8.dp)) {
            items(s.habits) { h -> 
                ListItem(
                    headlineContent = { Text(h.name) }, 
                    supportingContent = { Text(h.type) },
                    modifier = Modifier.fillMaxWidth().clickable { onSelect(h) }
                )
                HorizontalDivider()
            }
        }
    }
}

@Composable 
fun CompletionSheet(vm: CompletionVM, habit: HabitDTO, onDone: () -> Unit) {
    val s by vm.state.collectAsState()
    var metaKey by remember { mutableStateOf("volume_ml") }
    var metaVal by remember { mutableStateOf("500") }
    
    Column(Modifier.padding(16.dp)) {
        Text("Log completion: ${habit.name}", style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = metaKey, 
            onValueChange = { metaKey = it }, 
            label = { Text("Meta key") }
        )
        OutlinedTextField(
            value = metaVal, 
            onValueChange = { metaVal = it }, 
            label = { Text("Meta value") }
        )
        Spacer(Modifier.height(12.dp))
        Button(
            enabled = !s.submitting, 
            onClick = { 
                vm.submit(habit.id, mapOf(metaKey to metaVal.toIntOrNull() ?: metaVal)) 
            }
        ) { 
            Text("Submit") 
        }
        if (s.success) {
            LaunchedEffect(Unit) { onDone() }
        }
        s.error?.let { 
            Text("Error: $it", color = MaterialTheme.colorScheme.error) 
        }
    }
}

@Composable 
fun ImpactMeScreen(vm: ImpactVM) {
    val s by vm.state.collectAsState()
    LaunchedEffect(Unit) { vm.loadMe() }
    
    when {
        s.loading -> CircularProgressIndicator()
        s.error != null -> Text("Error: ${s.error}")
        else -> Column(Modifier.padding(16.dp)) {
            Text("Your Impact", style = MaterialTheme.typography.headlineSmall)
            Spacer(Modifier.height(8.dp))
            val t = s.me
            Text("🌳 Trees: ${t?.trees ?: 0.0}")
            Text("🍽 Meals: ${t?.meals ?: 0.0}")
            Text("💧 Water: ${t?.water ?: 0.0}")
        }
    }
}
