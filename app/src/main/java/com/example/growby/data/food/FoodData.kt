package com.example.growby.data.food

import com.example.growby.R

object FoodData {
    private val name = arrayOf("Usia 4-6 Bulan","Usia 6-8 Bulan","Usia 8-10 Bulan","Usia 10-12 Bulan","Usia 2 Tahun","Usia 3 Tahun","Usia 4 Tahun","Usia 5 Tahun")
    private val detail = arrayOf(
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi.",
        "Biasanya makanan bayi yang paling awal diperkenalkan adalah \nbubur sereal bayi yang dicampur ASI atau susu formula. \nJangan terburu-buru memberikan bubur bayi atau makanan padat sejenisnya,  tapi perkenalkanlah secara perlahan. Caranya,  berikan satu sendok sereal lumat yang didekatkan pada mulut bayi. Jika bayi menolak, tunda sejenak atau tunggu beberapa hari sebelum mencoba lagi."
        )
    private val photo = arrayOf(
        R.drawable.food1,
        R.drawable.food2,
        R.drawable.food3,
        R.drawable.food4,
        R.drawable.food1,
        R.drawable.food1,
        R.drawable.food1,
        R.drawable.food1,
    )
    val listFood: ArrayList<Food>
        get() {
            val list =  arrayListOf<Food>()
            for(position in name.indices){
               val food = Food()
                food.name = name[position]
                food.detail = detail[position]
                food.price = 25000
                food.photo = photo[position]
                list.add(food)
            }
            return list
        }
}