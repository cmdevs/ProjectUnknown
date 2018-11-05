package com.cmdevs.projectunknown.util

import com.airbnb.lottie.LottieAnimationView

fun LottieAnimationView.loadingStart() {
    playAnimation()
}

fun LottieAnimationView.loadingStop() {
    pauseAnimation()
}