package com.example.facemeshgraphic.di

import com.example.facemeshgraphic.model.usecase.GetFaceAnalyzerUseCase
import com.example.facemeshgraphic.model.usecase.GetFaceAnalyzerUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface InterfaceModule {

    @Binds
    fun bindGetFaceAnalyzerUseCase(
        getFaceAnalyzer: GetFaceAnalyzerUseCaseImpl
    ): GetFaceAnalyzerUseCase

}