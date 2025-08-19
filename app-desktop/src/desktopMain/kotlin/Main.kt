import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "Synapse+") { 
        /* call shared UI root similar to Android AppHome */ 
    }
}
