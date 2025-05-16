package com.example.smartnutrition.data.remote.dto

import com.example.smartnutrition.domain.model.Data
import com.example.smartnutrition.domain.model.Energy
import com.example.smartnutrition.domain.model.Item
import com.example.smartnutrition.domain.model.Minerals


data class NutritionResponse(
    val `data`: Data,
    val success: Boolean
)



fun Data.toDomain(): Data {
    return Data(
        id = id,
        name = name,
        kalori = kalori,
        protein = protein,
        karbohidrat = karbohidrat,
        lemak = lemak,
        water = water,
        energy = Energy(
            atwater = energy.atwater,
            specific = energy.specific
        ),
        nitrogen = nitrogen,
        minerals = Minerals(
            calcium = minerals.calcium,
            iron = minerals.iron,
            magnesium = minerals.magnesium,
            phosphorus = minerals.phosphorus,
            potassium = minerals.potassium,
            sodium = minerals.sodium,
            zinc = minerals.zinc,
            copper = minerals.copper,
            manganese = minerals.manganese
        ),
        date = date,
        totalKalori = totalKalori,
        totalKarbohidrat = totalKarbohidrat,
        totalLemak = totalLemak,
        totalProtein = totalProtein,
        items = items
    )
}