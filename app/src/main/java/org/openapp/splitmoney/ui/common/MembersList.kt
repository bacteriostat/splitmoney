package org.openapp.splitmoney.ui.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.openapp.splitmoney.models.Member
import org.openapp.splitmoney.models.Transaction

@Composable
fun MembersList(members: List<Member>, innerPadding: PaddingValues) {

    LazyColumn(modifier = Modifier.padding(innerPadding)) {
        items(members) { item -> MemberItemView(item) }
    }

}

@Composable
fun MemberItemView(member: Member) {
    ListItem(
        headlineContent = {
            Text(text = member.name)
        }
    )
}