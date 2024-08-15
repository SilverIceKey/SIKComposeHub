package com.sik.sikcomposebase.base

import androidx.navigation.NavOptions

/**
 * 预设NavOptionBuilder
 */
object PresetsNavOptionBuilders {
    /**
     * 单例置顶选项
     */
    val launchSingleTopOption = NavOptions
        .Builder()
        .apply {
            setLaunchSingleTop(true)
        }
}