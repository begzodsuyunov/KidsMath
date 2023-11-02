package com.example.kidsmath.data.repository.impl

import android.util.Log
import com.example.kidsmath.data.model.Question
import com.example.kidsmath.data.repository.Repository
import com.example.kidsmath.data.room.dao.GameDao
import com.example.kidsmath.data.room.entity.GameEntity
import com.example.kidsmath.data.shp.MySharedPreference
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random

class RepositoryImpl @Inject constructor(
    private val dao: GameDao
) : Repository {

    private val shp = MySharedPreference.getInstance()
    private val gson = Gson()


    override fun getAllEasyLevel(): Flow<List<GameEntity>> = flow {

        if (!shp.firstEasy) {
            val list = ArrayList<GameEntity>()

            for (i in 0..59) {

                val questionList = ArrayList<Question>()

                for (j in 0..2) {
                    val a = Random.nextInt(0, 30)
                    val b = Random.nextInt(0, 30)
                    var c = 0
                    val operationList = operators()
                    val randomIndex = Random.nextInt(operationList.size);
                    val randomElement = operationList[randomIndex]

                    when (randomElement) {
                        "+" -> {
                            c = a.plus(b)
                        }

                        "*" -> {
                            c = a * b
                        }
                    }
                    questionList.add(Question(a, randomElement, b, c))
                }
                val gson = Gson()
                val type = object : TypeToken<List<Question>>() {}.type
                val s = gson.toJson(questionList, type)

                list.add(GameEntity(0, 3, 1, i + 1, s, false, 0, 0))
            }

            dao.insert(list)
            shp.firstEasy = true
        }
        Log.d("DDD", shp.firstEasy.toString())
        dao.getAllEasyLevel().map {
            emit(it)
        }.collect()
    }.flowOn(Dispatchers.IO)

    override fun getAllMediumLevel(): Flow<List<GameEntity>> = flow {

        if (!shp.firstMedium) {
            val list = ArrayList<GameEntity>()
            for (i in 0..59) {

                val questionList = ArrayList<Question>()

                for (j in 0..5) {
                    val a = Random.nextInt(30, 60)
                    val b = Random.nextInt(30, 60)
                    var c = 0
                    val operationList = operators()
                    val randomIndex = Random.nextInt(operationList.size);
                    val randomElement = operationList[randomIndex]

                    when (randomElement) {
                        "+" -> {
                            c = a.plus(b)
                        }

                        "*" -> {
                            c = a * b
                        }
                    }
                    questionList.add(Question(a, randomElement, b, c))
                }

                val type = object : TypeToken<List<Question>>() {}.type
                val s = gson.toJson(questionList, type)

                list.add(GameEntity(0, 5, 2, i + 1, s, false, 0, 0))
            }
            dao.insert(list)
            shp.firstMedium = true
        }

        dao.getAllMediumLevel().map {
            Log.d("TTT", it.size.toString())
            emit(it)
        }.collect()
    }.flowOn(Dispatchers.IO)


    override fun getAllHardLevel(): Flow<List<GameEntity>> = flow {

        if (!shp.firstHard) {

            val list = ArrayList<GameEntity>()

            for (i in 0..59) {

                val questionList = ArrayList<Question>()

                for (j in 0..7) {
                    val a = Random.nextInt(60, 90)
                    val b = Random.nextInt(60, 90)
                    var c = 0
                    val operationList = operators()
                    val randomIndex = Random.nextInt(operationList.size);
                    val randomElement = operationList[randomIndex]

                    when (randomElement) {
                        "+" -> {
                            c = a.plus(b)
                        }

                        "-" -> {
                            c = a.minus(b)
                        }

                        "*" -> {
                            c = a * b
                        }
                    }
                    questionList.add(Question(a, randomElement, b, c))
                }

                val type = object : TypeToken<List<Question>>() {}.type
                val s = gson.toJson(questionList, type)

                list.add(GameEntity(0, 7, 3, i + 1, s, false, 0, 0))
            }

            dao.insert(list)
            shp.firstHard = true
        }

        dao.getAllHardLevel().map {
            emit(it)
        }.collect()
    }.flowOn(Dispatchers.IO)

    override fun update(entity: GameEntity) = dao.update(entity)

    override suspend fun generateQuestion() {
        dao.deleteAll()

        shp.firstHard = false
        shp.firstEasy = false
        shp.firstMedium = false
    }

    override fun getLevel(): Flow<String> = flow { emit(shp.level) }

    override suspend fun openNextLevel(level: Int, number: Int) {
        dao.getAllLevel().map { it ->
            it.map {
                if (it.level == level && it.number == number) {
                    it.state = true
                    dao.update(it)
                }
            }
        }.collect()
    }

    override suspend fun setLevel(level: String) {
        shp.level = level
    }

    override fun getByNumber(level: Int, number: Int): Flow<List<Question>> = flow{

        val type = object : TypeToken<List<Question>>() {}.type
        dao.getByNumber(level, number).map {
            val gameModel = gson.fromJson<List<Question>>(it.questionList, type)
            emit(gameModel)
        }.collect()

    }.flowOn(Dispatchers.IO)


    private fun operators(): ArrayList<String> {
        return arrayListOf("+", "*")
    }
}