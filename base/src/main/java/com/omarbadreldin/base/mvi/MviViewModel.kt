package com.omarbadreldin.base.mvi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MviViewModel<I : MVI.Intent, S : MVI.State> : ViewModel(), MVI.Model<I, S> {

    protected val _state: MutableLiveData<S> = MutableLiveData<S>()

    override val state: LiveData<S> = _state

    protected fun state(state: S) {
        _state.value = state
    }
}