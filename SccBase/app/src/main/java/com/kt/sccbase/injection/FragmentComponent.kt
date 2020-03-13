package com.kt.sccbase.injection

import com.kt.lib_base.injection.PerComponentScope
import com.kt.lib_base.injection.component.ActivityComponent
import dagger.Component

@PerComponentScope
@Component(dependencies = [(ActivityComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

}