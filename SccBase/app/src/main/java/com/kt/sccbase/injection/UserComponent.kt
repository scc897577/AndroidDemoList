package com.kt.sccbase.injection

import com.kt.lib_base.injection.PerComponentScope
import com.kt.lib_base.injection.component.ActivityComponent
import com.kt.sccbase.MainActivity
import dagger.Component

/**
 */
@PerComponentScope
@Component(modules = [(UserModule::class)], dependencies = [(ActivityComponent::class)])
interface UserComponent {
    fun inject(activity: MainActivity)
}