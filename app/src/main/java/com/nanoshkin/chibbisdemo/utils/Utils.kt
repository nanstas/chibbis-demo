package com.nanoshkin.chibbisdemo.utils

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

object Utils {
    fun formattingMinCost(minCost: Int): String = "от ${minCost}Р"

    fun formattingDeliveryCost(costDelivery: Int): String =
        when (costDelivery) {
            0 -> "Бесплатно"
            else -> "${costDelivery}Р"
        }

    fun formattingDeliveryTime(deliveryTime: Int): String = "$deliveryTime мин"

    fun formattingReviewsCount(reviewsCount: Int): String {
        val value = Math.abs(reviewsCount) % 100
        val num = value % 10
        if (value in 11..19) return "$reviewsCount отзывов"
        if (num in 2..4) return "$reviewsCount отзыва"
        if (num == 1) return "$reviewsCount отзыв"
        return "$reviewsCount отзывов"
    }

    fun hideKeyboard(view: View) {
        val imm = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}