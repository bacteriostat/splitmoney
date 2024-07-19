package org.openapp.splitmoney.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.openapp.splitmoney.models.Member

@Composable
fun MembersList(members: List<Member>, innerPadding: PaddingValues) {

    Text("Members", Modifier.padding(innerPadding))

}