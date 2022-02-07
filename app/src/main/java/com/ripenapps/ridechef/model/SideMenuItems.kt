package com.ripenapps.ridechef.model

import com.ripenapps.ridechef.R

val sideMenuList = listOf(
    SideMenuItems(title = "Notification", icon = R.drawable.notification),
    SideMenuItems(title = "Profile Settings", icon = R.drawable.settings),
    SideMenuItems(title = "My Favorites", icon = R.drawable.favorites_red),
    SideMenuItems(title = "My Orders", icon = R.drawable.my_order_red),
    SideMenuItems(title = "Saved Address", icon = R.drawable.location),
    SideMenuItems(title = "About us", icon = R.drawable.about_us_red),
    SideMenuItems(title = "Terms & Conditions", icon = R.drawable.terms_condition),
    SideMenuItems(title = "Privacy Policy", icon = R.drawable.privacy_policy_red),
    SideMenuItems(title = "Help & Support", icon = R.drawable.help_support_red),
    SideMenuItems(title = "Logout", icon = R.drawable.logout_red),
)


class SideMenuItems(val title: String, val icon: Int) {}