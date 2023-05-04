package com.ketchup.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import com.ketchup.superheroapp.databinding.ActivityDetailSuperheroBinding
import com.ketchup.superheroapp.databinding.ActivitySuperHeroListBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.roundToInt


class DetailSuperheroActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ID = "superhero_id"
    }

    private lateinit var binding: ActivityDetailSuperheroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailSuperheroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra(EXTRA_ID).orEmpty()
        getSuperheroInformation(id)
    }

    private fun getSuperheroInformation(id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val superheroDetail =
                getRetrofit().create(ApiService::class.java).getSuperheroDetail(id)

            if (superheroDetail.body() != null){
                runOnUiThread {
                    createUI(superheroDetail.body()!!)
                }
            }
        }
    }

    private fun createUI(superhero: SuperHeroDetailResponse) {
        Picasso.get().load(superhero.superheroImage.superheroImageDetail).into(binding.ivSuperheroDetail)
        binding.tvSuperHeroNameDetail.text = superhero.superheroName
        binding.tvRealName.text = superhero.superheroBiography.superheroRealName
        binding.tvPublisher.text = superhero.superheroBiography.publisher
        prepareStats(superhero.powerStats)
    }

    private fun prepareStats(powerStats: SuperheroPowerStatsResponse){

        updateHeight(binding.viewCombat, powerStats.combat)
        updateHeight(binding.viewStrength, powerStats.strength)
        updateHeight(binding.viewDurability, powerStats.durability)
        updateHeight(binding.viewPower, powerStats.power)
        updateHeight(binding.viewSpeed, powerStats.speed)
        updateHeight(binding.viewIntelligence, powerStats.intelligence)


    }

    private fun updateHeight(view: View, stat:String){
        val params = view.layoutParams
        params.height = pxToDp(stat.toFloat())
        view.layoutParams = params
    }

    private fun pxToDp(px:Float):Int{
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, px, resources.displayMetrics).roundToInt()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}