package com.bojue.bsapp.viewmodel

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * create data : 2019/5/8
 * author : sendi
 * description :
 */
@Singleton
class AppViewModelFactory @Inject constructor(
        private val application: Application,
        private val creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<IViewModel>>
)  : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value
        if (creator != null) {
            try {
                @Suppress("UNCHECKED_CAST")
                return creator.get() as T
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        } else {
            try {
                @Suppress("UNCHECKED_CAST")
                return if (BaseViewModel::class.java.isAssignableFrom(modelClass)) {
                    modelClass.getConstructor(Application::class.java).newInstance(application) as T
                } else {
                    modelClass.newInstance() as T
                }
            } catch (e: Exception) {
                throw IllegalArgumentException("unknown model class $modelClass", e)
            }
        }
    }
}