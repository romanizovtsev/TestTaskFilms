package com.example.testfilms.di

import com.example.testfilms.view.FilmsFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(filmsFragment: FilmsFragment)
}