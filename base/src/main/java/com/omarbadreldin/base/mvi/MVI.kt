package com.omarbadreldin.base.mvi

import androidx.lifecycle.LiveData

interface MVI {

    interface View<I: Intent, S: State, M: Model<I, S>> {

        val viewModel: M

        fun render(state: S)
    }

    interface Model<I: Intent, S: State> {

        val state: LiveData<S>

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