package com.nanoshkin.chibbisdemo.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import kotlin.math.abs

object Utils {
    fun formattingMinCost(minCost: Int): String = "от ${minCost}Р"

    fun formattingDeliveryCost(costDelivery: Int): String =
        when (costDelivery) {
            0 -> "Бесплатно"
            else -> "${costDelivery}Р"
        }

    fun formattingDeliveryTime(deliveryTime: Int): String = "$deliveryTime мин"

    fun formattingRestaurantSpecializations(specializations: List<String>): String = specializations.reduce { acc, s -> "$acc/ $s" }

    fun formattingReviewsCount(reviewsCount: Int): String {
        val value = abs(reviewsCount) % 100
        val num = value % 10
        if (value in 11..19) return "$reviewsCount отзывов"
        if (num in 2..4) return "$reviewsCount отзыва"
        if (num == 1) return "$reviewsCount отзыв"
        return "$reviewsCount отзывов"
    }

    fun formattingHitCost(minCost: Int): String = "${minCost}Р"

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun formattingReviewDate(data: String): String {
        val formatData = data.split("-", "T", ":", ".")
        return "${formatData[2]}.${formatData[1]}.${formatData[0]} ${formatData[3]}:${formatData[4]}"
    }
}