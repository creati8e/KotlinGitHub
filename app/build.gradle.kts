plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("realm-android")
    id("kotlin-kapt")
}

android {
    defaultConfig {
        applicationId = "serg.chuprin.khub"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf(".*jar"))))
    implementation(Libraries.kotlin)

    // Android.
    implementation(Libraries.Android.ktx)
    implementation(Libraries.Android.design)
    implementation(Libraries.Android.appcompat)
    implementation(Libraries.Android.recyclerView)
    implementation(Libraries.Android.customTabs)
    implementation(Libraries.Android.drawerLayout)
    implementation(Libraries.Android.coordinator)

    // ViewModel.
    kapt(Libraries.Android.viewModelCompiler)
    implementation(Libraries.Android.viewModel)
    implementation(Libraries.Android.lifecycleExtensions)

    // Dagger.
    implementation(Libraries.Dagger.lib)
    kapt(Libraries.Dagger.compiler)

    // Rx.
    implementation(Libraries.Rx.rxJava)
    implementation(Libraries.Rx.rxAndroid)
    implementation(Libraries.Rx.rxBindingRecycler)

    // Images.
    implementation(Libraries.glide)
    implementation(Libraries.circleImageView)

    //network
    implementation(Libraries.Network.retrofit)
    implementation(Libraries.Network.rxAdapter)
    implementation(Libraries.Network.gsonConverter)
    implementation(Libraries.Network.loggingInterceptor)

    // Anko.
    implementation(Libraries.anko)
    implementation(Libraries.realmExtensions)

    implementation(Libraries.adapter)

    // Timber.
    implementation(Libraries.timber)

    // Navigation.
    implementation(Libraries.Android.navigation)
    implementation(Libraries.Android.navigationUi)
}