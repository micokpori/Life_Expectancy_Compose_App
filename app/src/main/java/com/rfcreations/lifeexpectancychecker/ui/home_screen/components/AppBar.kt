package com.rfcreations.lifeexpectancychecker.ui.home_screen.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.rfcreations.lifeexpectancychecker.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
    toggleShowAppThemeDialog: () -> Unit,
    clearAllSelections: () -> Unit
) {
    var expandAppBarDropDownMenu by remember { mutableStateOf(false) }

    TopAppBar(
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
            )
        }, actions = {

            IconButton(
                onClick = toggleShowAppThemeDialog
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.night),
                    stringResource(R.string.content_desc_clear_all_selection),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(
                onClick = { expandAppBarDropDownMenu = true }) {
                Icon(Icons.Filled.MoreVert, null)
            }

            DropdownMenu(
                expanded = expandAppBarDropDownMenu,
                onDismissRequest = { expandAppBarDropDownMenu = false }
                // offset = DpOffset((-102).dp, (-64).dp),
            ) {
                DropdownMenuItem(
                    text = { Text(text = stringResource(R.string.clear_all_selections)) },
                    leadingIcon = {
                        Icon(
                            painterResource(id = R.drawable.clear_all), null
                        )
                    },
                    onClick = {
                        clearAllSelections()
                        expandAppBarDropDownMenu = false
                    }
                )
            }
        },
      //  colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surfaceDim)

    )
}