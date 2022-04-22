package com.omarbadreldin.base.mvi

import androidx.lifecycle.LiveData

interface MVI {

    interface View<I: Intent, M: Model<I>, S: State> {

        val viewModel: M

        fun render(state: S)
    }

    interface Model<I: Intent> {

        val state: LiveData<State>

        fun onIntent(intent: I)
    }

    /**
     * Marker interface
     */
    interface Intent

    /**
     * Marker interface
     */
    interface State
}