package pelkidev.com.mx.minijuegos.sdk

import androidx.compose.runtime.Composable

/**
 * Contrato estable entre MiniJuegos (host) y cada juego embebible.
 * Los juegos publican un AAR que expone una implementación de esta interfaz.
 */
interface GameModule {
    val id: String
    val title: String
    val description: String

    @Composable
    fun Entry(onExit: () -> Unit)
}
