object Compose {
    private const val activityComposeVersion = "1.4.0"
    const val activity = "androidx.activity:activity-compose:$activityComposeVersion"

    const val composeVersion = "1.0.5"
    const val ui = "androidx.compose.ui:ui:$composeVersion"
    const val material = "androidx.compose.material:material:$composeVersion"
    const val tooling = "androidx.compose.ui:ui-tooling:$composeVersion"
    const val icons_core = "androidx.compose.material:material-icons-core:$composeVersion"
    const val icons_extended = "androidx.compose.material:material-icons-extended:$composeVersion"
    const val foundation ="androidx.compose.foundation:foundation:$composeVersion"

    private const val navigationVersion = "2.4.0-alpha04"
    const val navigation = "androidx.navigation:navigation-compose:$navigationVersion"

    private const val hiltNavigationComposeVersion = "1.0.0-alpha03"
    const val hiltNavigation = "androidx.hilt:hilt-navigation-compose:$hiltNavigationComposeVersion"
}

object ComposeTest {
    const val uiTestJunit4 = "androidx.compose.ui:ui-test-junit4:${Compose.composeVersion}"
    const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:${Compose.composeVersion}"
}
