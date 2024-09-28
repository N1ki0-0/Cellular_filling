package com.example.ellularfilling

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class CellViewModel: ViewModel() {

    private val predefinedCells = listOf(
        CellsDb(0, "Живая", "и шевелится!", R.drawable.explosion),
        CellsDb(1, "Мёртвая", "или прикидывается", R.drawable.skull),
        CellsDb(2, "Жизнь", "ку-ку", R.drawable.chicken)
    )

    private val _cellsDb = mutableStateListOf<CellsDb>()
    val cellsDb: List<CellsDb> get() = _cellsDb

    private var lastAliveCount = 0
    private var lastDeadCount = 0

    fun addCell() {
        val isAlive = when {
            lastAliveCount >= 3 -> true // Если три живые клетки подряд
            lastDeadCount >= 3 -> false // Если три мёртвые клетки подряд
            else -> Random.nextBoolean() // Иначе случайно
        }

        val newCell = if (isAlive) {
            lastAliveCount++
            lastDeadCount = 0 // Сброс счетчика мёртвых клеток
            predefinedCells[0] // Выбираем первую клетку живая
        } else {
            lastDeadCount++
            lastAliveCount = 0 // Сброс счетчика живых клеток
            predefinedCells[1] // Выбираем первую клетку мёртвая
        }

        _cellsDb.add(0, newCell)

        // Применяем правила
        checkAndApplyRules()
    }

    private fun checkAndApplyRules() {
        if (lastAliveCount >= 3) {
            _cellsDb.add(0, predefinedCells[2]) // Добавляем клетку жизнь
            lastAliveCount = 0
        }

        if (lastDeadCount >= 3) {
            val lifeIndex = _cellsDb.indexOfFirst { it.name == "Жизнь" }
            if (lifeIndex != -1) {
                _cellsDb.removeAt(lifeIndex) // Удаляем ближайшую клетку жизнь
            }
            lastDeadCount = 0
        }
    }
}