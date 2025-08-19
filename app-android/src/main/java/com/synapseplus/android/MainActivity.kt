package com.synapseplus.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.koin.android.ext.android.inject
import com.synapseplus.viewmodel.*
import com.synapseplus.auth.AuthApi
import com.synapseplus.auth.JwtStorage
import com.synapseplus.habits.HabitsApi
import com.synapseplus.impact.ImpactApi
import com.synapseplus.ui.*

class MainActivity : ComponentActivity() {
    private val authApi: AuthApi by inject()
    private val jwt: JwtStorage by inject()
    private val habitsApi: HabitsApi by inject()
    private val impactApi: ImpactApi by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val authVM = remember { AuthVM(authApi, jwt) }
                var authed by remember { mutableStateOf(false) }
                if (!authed) {
                    LoginScreen(authVM) { authed = true }
                } else {
                    AppHome(habitsApi, impactApi)
                }
            }
        }
    }
}

@Composable 
private fun AppHome(habitsApi: HabitsApi, impactApi: ImpactApi) {
    val listVM = remember { HabitListVM(habitsApi) }
    val completionVM = remember { CompletionVM(habitsApi) }
    val impactVM = remember { ImpactVM(impactApi) }
    var selected by remember { mutableStateOf<com.synapseplus.habits.HabitDTO?>(null) }
    
    if (selected == null) {
        Scaffold(
            bottomBar = { 
                NavigationBar { 
                    NavigationBarItem(
                        selected = true, 
                        onClick = {}, 
                        label = { Text("Habits") }, 
                        icon = {}
                    )
                    NavigationBarItem(
                        selected = false, 
                        onClick = {}, 
                        label = { Text("Impact") }, 
                        icon = {}
                    ) 
                } 
            }
        ) {
            Column(Modifier.padding(it)) {
                HabitListScreen(listVM) { h -> selected = h }
                HorizontalDivider()
                ImpactMeScreen(impactVM)
            }
        }
    } else {
        CompletionSheet(completionVM, selected!!) { selected = null }
    }
}
