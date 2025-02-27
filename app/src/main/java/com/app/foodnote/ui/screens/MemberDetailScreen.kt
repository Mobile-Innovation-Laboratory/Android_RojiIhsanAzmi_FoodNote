package com.app.foodnote.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.foodnote.MemberDetail
import com.app.foodnote.R
import com.app.foodnote.ui.theme.AppColor
import com.app.foodnote.ui.theme.parkinsansFamily

@Composable
fun MemberDetailScreen(
    param: MemberDetail
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.background_image),
            contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        
        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(32.dp)
                .fillMaxWidth(0.8f),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Thanks To",
                    fontFamily = parkinsansFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 27.sp,
                    color = Color.Red
                )

                Spacer(modifier = Modifier.height(12.dp))

                Image(
                    painter = painterResource(
                        id = when (param.memberImageUrl) {
                            "wildan_niam" -> R.drawable.wildan_niam
                            "moses_eliyada" -> R.drawable.moses_eliyada
                            "puri_lalita" -> R.drawable.puri_lalita
                            "darrel_prawira" -> R.drawable.darrel_prawira
                            "adelia_nasywa" -> R.drawable.adelia_nasywa
                            "raihan_syahrin" -> R.drawable.raihan_syahrin
                            "arzu_kirana" -> R.drawable.arzu_kirana
                            "reinhard_efraim" -> R.drawable.reinhard_efraim
                            else -> android.R.drawable.ic_menu_gallery
                        }
                    ),
                    contentDescription = param.memberName,
                    modifier = Modifier
                        .size(120.dp)
                        .padding(bottom = 8.dp),
                )

                Text(
                    text = param.memberName,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Gray,
                            offset = Offset(4f, 4f),
                            blurRadius = 3f // Adjust blur radius for effect
                        )
                    ),
                    fontSize = 20.sp,
                    fontFamily = parkinsansFamily,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )

                Text(
                    text = param.memberRole,
                    fontSize = 16.sp,
                    fontFamily = parkinsansFamily,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMemberDetailScreen() {
    MemberDetailScreen(
        param = MemberDetail(
            memberName = "Wildan Syukri Niam",
            memberRole = "Coordinator",
            memberImageUrl = "wildan_niam"
        )
    )
}
