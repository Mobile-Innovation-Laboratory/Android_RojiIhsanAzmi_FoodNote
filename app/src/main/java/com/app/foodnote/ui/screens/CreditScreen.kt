package com.app.foodnote.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.app.foodnote.AuthViewModel
import com.app.foodnote.MemberDetail
import com.app.foodnote.MemberModel
import com.app.foodnote.R
import com.app.foodnote.ui.theme.AppColor
import com.app.foodnote.ui.theme.parkinsansFamily
import com.app.foodnote.ui.viewmodel.RecipeViewModel

@Composable
fun CreditContent(navController: NavController? = null, recipeViewModel: RecipeViewModel, authViewModel: AuthViewModel) {
    val members = listOf(
        MemberModel(
            "Wildan Syukri Niam",
            "Coordinator",
            "wildan_niam"
        ),
        MemberModel(
            "Moses Eliyada",
            "Vice Coordinator",
            "moses_eliyada"
        ),
        MemberModel(
            "Puri Lalita",
            "Administration",
            "puri_lalita"
        ),
        MemberModel(
            "M. Darrel Prawira",
            "Assistant Study Group",
            "darrel_prawira"
        ),
        MemberModel(
            "Adelia Nasywa",
            "Assistant Study Group",
            "adelia_nasywa"
        ),
        MemberModel(
            "M. Raihan Syahrin",
            "Assistant Study Group",
            "raihan_syahrin"
        ),
        MemberModel(
            "M. Arzu Kirana",
            "Assistant Study Group",
            "arzu_kirana"
        ),
        MemberModel(
            "Reinhard Efraim",
            "Assistant Study Group",
            "reinhard_efraim"
        ),
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn {
            items(members) { member ->
                MemberItem(
                    member.name,
                    member.role,
                    member.imageUrl,
                    onClick = {
                        navController?.navigate(
                            MemberDetail(
                                memberName = member.name,
                                memberRole = member.role,
                                memberImageUrl = member.imageUrl
                            )
                        )
                    }
                )

            }
        }
    }
}

@Composable
fun MemberItem(
    name: String,
    role: String,
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(bottom = 16.dp)
            .fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),
        ) {
            Image(
                painter = painterResource(
                    id = when (imageUrl) {
                        "wildan_niam" -> R.drawable.wildan_niam
                        "moses_eliyada" -> R.drawable.moses_eliyada
                        "puri_lalita" -> R.drawable.puri_lalita
                        "darrel_prawira" -> R.drawable.darrel_prawira
                        "adelia_nasywa" -> R.drawable.adelia_nasywa
                        "raihan_syahrin" -> R.drawable.raihan_syahrin
                        "arzu_kirana" -> R.drawable.arzu_kirana
                        "reinhard_efraim" -> R.drawable.reinhard_efraim
                        else -> android.R.drawable.ic_menu_gallery // Default image
                    }
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(76.dp)
                    .padding(4.dp),
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    name,
                    fontFamily = parkinsansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    role,
                    fontFamily = parkinsansFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}