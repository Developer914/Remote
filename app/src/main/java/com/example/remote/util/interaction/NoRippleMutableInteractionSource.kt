package com.chromecastremote.util.interaction

import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class NoRippleMutableInteractionSource(override val interactions: Flow<Interaction> = emptyFlow()) :
    MutableInteractionSource {
    override suspend fun emit(interaction: Interaction) {

    }

    override fun tryEmit(interaction: Interaction): Boolean {
        return false
    }
}