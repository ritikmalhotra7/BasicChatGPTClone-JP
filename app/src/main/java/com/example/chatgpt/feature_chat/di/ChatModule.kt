package com.example.chatgpt.feature_chat.di

import com.example.chatgpt.core.repositories.ChatRepository
import com.example.chatgpt.feature_chat.data.remote.ChatApi
import com.example.chatgpt.feature_chat.data.remote.repositories.ChatRepositoryImpl
import com.example.chatgpt.feature_chat.domain.use_cases.ChatUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

const val BASE_URL="https://api.openai.com/v1/"
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideApi(): ChatApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ChatApi::class.java)
    }
    @Provides
    @Singleton
    fun provideRepository(api: ChatApi):ChatRepository = ChatRepositoryImpl(api)

    @Provides
    @Singleton
    fun provideChatUseCase(repository: ChatRepository):ChatUseCase = ChatUseCase(repository)
}