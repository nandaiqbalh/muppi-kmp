package com.nandaiqbalh.muppi.app

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.nandaiqbalh.muppi.core.presentation.primaryColor
import com.nandaiqbalh.muppi.core.presentation.primaryColor200
import com.nandaiqbalh.muppi.core.presentation.primaryFont
import muppi.composeapp.generated.resources.Res
import muppi.composeapp.generated.resources.ic_nav_explore_active
import muppi.composeapp.generated.resources.ic_nav_explore_inactive
import muppi.composeapp.generated.resources.ic_nav_home_active
import muppi.composeapp.generated.resources.ic_nav_home_inactive
import muppi.composeapp.generated.resources.ic_nav_saved_active
import muppi.composeapp.generated.resources.ic_nav_saved_inactive
import muppi.composeapp.generated.resources.nunito_bold
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource


sealed class BottomNavItem(
	val route: Route,
	val iconActive: DrawableResource,
	val iconInactive: DrawableResource,
	val label: String,
) {

	data object Home : BottomNavItem(
		Route.HomeScreen,
		Res.drawable.ic_nav_home_active,
		Res.drawable.ic_nav_home_inactive,
		"Home"
	)

	data object Explore : BottomNavItem(
		Route.ExploreScreen,
		Res.drawable.ic_nav_explore_active,
		Res.drawable.ic_nav_explore_inactive,
		"Explore"
	)

	data object Saved : BottomNavItem(
		Route.SavedScreen,
		Res.drawable.ic_nav_saved_active,
		Res.drawable.ic_nav_saved_inactive,
		"Saved"
	)
}

// List of navigation items
val bottomNavItems = listOf(
	BottomNavItem.Home,
	BottomNavItem.Explore,
	BottomNavItem.Saved
)


@Composable
fun BottomNavigationBar(navController: NavController) {

	val navBackStackEntry by navController.currentBackStackEntryAsState()
	val currentRoute = navBackStackEntry?.destination?.route?.substringAfterLast(".")

	Box(
		modifier = Modifier
			.fillMaxWidth()
			.background(Color.Transparent)
			.padding(8.dp)
			.navigationBarsPadding(),
		contentAlignment = Alignment.BottomCenter
	) {
		Box(
			modifier = Modifier
				.clip(RoundedCornerShape(100))
				.background(primaryColor200),
			contentAlignment = Alignment.Center
		) {
			Row(
				modifier = Modifier
					.padding(vertical = 12.dp, horizontal = 32.dp),
				horizontalArrangement = Arrangement.Center,
				verticalAlignment = Alignment.CenterVertically
			) {
				bottomNavItems.forEach { item ->
					val isSelected = currentRoute == item.route.toString()

					// Animate the spacing between icons when selected
					val space by animateDpAsState(targetValue = if (isSelected) 16.dp else 16.dp)

					if (item != bottomNavItems.first()) {
						Spacer(modifier = Modifier.width(space))
					}

					if (isSelected) {
						Row(
							modifier = Modifier
								.width(120.dp)
								.clip(RoundedCornerShape(100))
								.background(primaryColor)
								.padding(vertical = 8.dp, horizontal = 16.dp),
							verticalAlignment = Alignment.CenterVertically
						) {
							Image(
								painter = painterResource(item.iconActive),
								contentDescription = item.label,
								modifier = Modifier
							)

							Spacer(modifier = Modifier.width(8.dp))

							Text(
								modifier = Modifier,
								text = item.label,
								maxLines = 1,
								overflow = TextOverflow.Ellipsis,
								style = TextStyle(
									fontFamily = FontFamily(Font(Res.font.nunito_bold)),
									fontSize = 16.sp,
									color = primaryFont,
								)
							)
						}
					} else {
						Box(
							modifier = Modifier
								.size(40.dp)
								.clip(CircleShape)
								.clickable {
									navController.navigate(item.route) {
										popUpTo(navController.graph.startDestinationId) {
											inclusive = false
										}
										launchSingleTop = true
									}
								},
							contentAlignment = Alignment.Center
						) {
							Image(
								painter = painterResource(if (isSelected) item.iconActive else item.iconInactive),
								contentDescription = item.label,
								modifier = Modifier
							)
						}
					}

					if (item != bottomNavItems.last()) {
						Spacer(modifier = Modifier.width(space))
					}
				}
			}
		}
	}
}
