package com.kt.sccbase.injection

import com.kt.sccbase.BaseService
import com.kt.sccbase.ServiceImpl
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {
    @Provides
    fun provideFragmentService(fragmentService: ServiceImpl): BaseService {
        return fragmentService
    }
}