package com.cmdevs.projectunknown.di

import com.cmdevs.projectunknown.data.AccessTokenStateDataSource
import com.cmdevs.projectunknown.data.FacebookAccessTokenStateDataSource
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton

val dataSourceModule = Kodein.Module("data_source_module") {
    bind<AccessTokenStateDataSource>() with singleton {
        FacebookAccessTokenStateDataSource(
            instance(),
            instance(),
            instance()
        )
    }
}