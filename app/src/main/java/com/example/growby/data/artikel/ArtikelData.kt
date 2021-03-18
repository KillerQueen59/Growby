package com.example.growby.data.artikel

import com.example.growby.R

object ArtikelData {
    private val title = arrayOf("Lakukan Hal Ini untuk Tumbuh Kembang si Kecil","5 manfaat ASI terhadap bayi","Perhatikan tinggi dan berat bayi anda, sudahkah sesuai?","Jangan lakukan hal ini terhadap bayi anda")
    private val detail = arrayOf(
        "Padahal, aktivitas fisik bukanlah sesuatu yang bisa dianggap remeh. Penelitian membuktikan bahwa aktivitas fisik dan bermain di luar rumah memiliki hubungan positif terhadap tumbuh kembang Si Kecil, khususnya dalam aspek motorik dan kognitif.\n\nDikatakan bahwa aktivitas fisik dan bermain di luar rumah dengan cara yang tepat dapat menstimulasi otak Si Kecil untuk berpikir. Hal ini pada akhirnya akan meningkatkan kemampuan kognitif Si Kecil.\n\nDi samping itu, aktivitas fisik juga melatih interaksi Si Kecil dengan temannya dalam bentuk kerja sama tim. Hal ini turut mengasah dan meningkatkan kemampuan sosial Si Kecil.\n\nPenerapan sikap disiplin, sportivitas, kesabaran, dan kontrol emosi juga dilatih saat melakukan aktivitas fisik. Dari aspek pertumbuhan, aktivitas fisik dan bermain di luar rumah tentu dapat mencegah Si Kecil mengalami obesitas alias kelebihan berat badan.",
        "Tubuh seorang perempuan diciptakan untuk memproduksi ASI guna memenuhi kebutuhan bayinya, dan merupakan hal yang terbaik untuknya. Susu formula merupakan pengganti yang buruk dan hanya digunakan jika Anda benar-benar tidak dapat menyusui. ASI mengandung kombinasi nutrisi yang sempurna untuk bayi, di mana hampir 100 jenis yang terkandung di dalamnya! Nutrisi tersebut berubah sesuai dengan kebutuhan bayi. Bayi yang baru lahir membutuhkan tingkat lemak yang lebih tinggi selama 6 bulan. Dan tubuh seorang ibu akan mengetahui hal itu dan menyesuaikannya dengan tepat.\n",
        "Pemantauan secara berkala berat badan bayi mulai dari lahir hingga usia 2 tahun sangat penting agar Ibu bisa mengejar keterlambatan pertumbuhan atau menetralkan percepatan pertumbuhan. Di dalamnya termasuk juga dalam memperhatikan indeks massa tubuh yang merupakan ukuran lemak tubuh berdasarkan tinggi dan berat badan. Setelah memantau berat badan bayi ideal 0-24 bulan, orang tua bisa memberikan asupan makanan bergizi, olahraga, dan istirahat yang cukup untuk menjaga postur tubuh ideal anak.",
        "Menurut berbagai sumber, kasus kematian AH murni karena kelalaian dan ketidaktahuan sang ibu. Si ibu tidak tahu bahwa bayi berusia 40 hari hanya boleh diberikan ASI sebagai makanan sehari-harinya. Padahal, hal ini sudah sering diimbau oleh beberapa lembaga kesehatan.\nBadan Kesehatan Dunia (WHO) dan Ikatan Dokter Anak Indonesia (IDAI) merekomendasikan program ASI eksklusif yaitu selama 6 bulan. Selama waktu tersebut, bayi hanya boleh mengonsumsi ASI sebagai asupan makanan utamanya. Nantinya, program ini bisa dilanjutkan hingga umur 2 tahun sambil didampingi MPASI."
    )
    private val date = arrayOf("15 Maret 2020","21 Juni 2020","09 Oktober 2020","10 November 2020")
    private val source = arrayOf("CNN","BCC","BNN","MetroTV")
    private val photo = arrayOf(
        R.drawable.artikel1,
        R.drawable.artikel2,
        R.drawable.artikel3,
        R.drawable.artikel4,
    )
    val listArtikel: ArrayList<Artikel>
    get() {
        val list = arrayListOf<Artikel>()
        for (position in title.indices){
            val artikel = Artikel()
            artikel.title = title[position]
            artikel.details = detail[position]
            artikel.date = date[position]
            artikel.source = source[position]
            artikel.photo = photo[position]
            list.add(artikel)
        }
        return list
    }

}