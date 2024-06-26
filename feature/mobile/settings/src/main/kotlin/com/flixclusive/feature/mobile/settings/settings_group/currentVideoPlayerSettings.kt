package com.flixclusive.feature.mobile.settings.settings_group

import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.stringResource
import com.flixclusive.feature.mobile.settings.KEY_PLAYER_QUALITY_DIALOG
import com.flixclusive.feature.mobile.settings.KEY_PLAYER_RESIZE_MODE_DIALOG
import com.flixclusive.feature.mobile.settings.KEY_PLAYER_SEEK_INCREMENT_MS_DIALOG
import com.flixclusive.feature.mobile.settings.SettingsItem
import com.flixclusive.feature.mobile.settings.util.rememberLocalAppSettings
import com.flixclusive.feature.mobile.settings.util.rememberSettingsChanger
import com.flixclusive.model.datastore.player.ResizeMode
import com.flixclusive.core.util.R as UtilR

@Composable
internal fun currentVideoPlayerSettings(): List<SettingsItem> {
    val appSettings by rememberLocalAppSettings()
    val onChangeSettings by rememberSettingsChanger()

    return listOf(
        SettingsItem(
            title = stringResource(UtilR.string.release_player),
            description = stringResource(UtilR.string.release_player_desc),
            onClick = {
                onChangeSettings(appSettings.copy(shouldReleasePlayer = !appSettings.shouldReleasePlayer))
            },
            previewContent = {
                Switch(
                    checked = appSettings.shouldReleasePlayer,
                    onCheckedChange = {
                        onChangeSettings(appSettings.copy(shouldReleasePlayer = it))
                    },
                    modifier = Modifier.scale(0.7F)
                )
            }
        ),
        SettingsItem(
            title = stringResource(UtilR.string.reverse_player_time),
            onClick = {
                onChangeSettings(appSettings.copy(isPlayerTimeReversed = !appSettings.isPlayerTimeReversed))
            },
            previewContent = {
                Switch(
                    checked = appSettings.isPlayerTimeReversed,
                    onCheckedChange = {
                        onChangeSettings(appSettings.copy(isPlayerTimeReversed = !appSettings.isPlayerTimeReversed))
                    },
                    modifier = Modifier.scale(0.7F)
                )
            }
        ),
        SettingsItem(
            title = stringResource(UtilR.string.preferred_quality),
            description = appSettings.preferredQuality.qualityName,
            dialogKey = KEY_PLAYER_QUALITY_DIALOG,
        ),
        SettingsItem(
            title = stringResource(UtilR.string.preferred_resize_mode),
            description = ResizeMode.entries.find { it.mode == appSettings.preferredResizeMode }.toString(),
            dialogKey = KEY_PLAYER_RESIZE_MODE_DIALOG,
        ),
        SettingsItem(
            title = stringResource(UtilR.string.seek_length_label),
            description = "${appSettings.preferredSeekAmount / 1000} seconds",
            dialogKey = KEY_PLAYER_SEEK_INCREMENT_MS_DIALOG,
        ),
    )
}