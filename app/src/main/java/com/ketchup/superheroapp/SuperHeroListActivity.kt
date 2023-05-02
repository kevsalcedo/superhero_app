package com.ketchup.superheroapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.ketchup.superheroapp.databinding.ActivitySuperHeroListBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrofit = getRetrofit()
        initUI()
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun searchByName(query: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val myResponse = retrofit.create(ApiService::class.java).getSuperheroes(query)
            if (myResponse.isSuccessful){
                Log.i("kesam","is ok :)")
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null){
                    Log.i("kesam", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superheroList)
                        binding.progressBar.isVisible = false
                    }
                }
            }else{
                Log.i("kesam","is not ok :(")
            }
        }
    }

    /**
     * This method will return the Retrofit instance
     */
    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun navigateToDetail(id:String){
        val intent = Intent(this, DetailSuperheroActivity::class.java)
        intent.putExtra(EXTRA_ID, id)
        startActivity(intent)
    }
}