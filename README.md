# Submission

Repository ini merupakan hasil belajar dari [Dicoding Academy](https://www.dicoding.com) pada kelas [Belajar Android Jetpack Pro](https://www.dicoding.com/academies/129). Kelas ini saya dapatkan sebagai beasiswa setelah lulus kelas [MADE](https://www.dicoding.com/academies/14/) dari [Google Developer Kejar 2019](https://events.withgoogle.com/googledeveloperskejar/).

[Habib Mustofa](https://www.dicoding.com/users/413434)

## Setup sebelum import project

Buat satu file properties di root project directory dengan nama dan ekstensi `app.properties` lalu isi dengan key: `moviedb.apikey` dan value: themoviedb api key seperti berikut:

```properties
tmdb.apikey=1234567891011121314151617181920X
```

## Instrumentation Tests Scenario

* DetailMovieActivityTest:
    * Memuat data Movie
        * Membuka halaman DetailMovieActivity
        * Memastikan judul movie tampil pada TextView dan nilainya sesuai dengan harapan
        * Memastikan tanggal rilis movie tampil pada TextView dan nilainya sesuai dengan harapan
        * Memastikan rating movie tampil pada RatingBar dan nilainya sesuai dengan harapan
        * Memastikan ikhtisar movie tampil pada TextView dan nilainya sesuai dengan harapan
        * Memastikan genre movie tampil pada Chip dan nilainya sesuai dengan harapan
        * Memastikan jumlah genre movie yang tampil pada ChipGroup sesuai dengan harapan
    * Memuat gambar poster dan backdrop
        * Membuka halaman DetailMovieActivity
        * Memastikan gambar poster tampil
        * Memastikan gambar backdrop tampil
    * Menambahkan/Menghapus Favorite
        * Membuka halaman DetailMovieActivity
        * Memastikan tombol favorite tampil dan memberi aksi klik
        * Mengecek teks pesan ditambahkan atau dihapus dari favorite

* AmberMovieTest:
    * Menampilkan Tab dan Halaman
        * Memastikan jumlah tab yang tampil 2 tab
        * Memastikan judul tab yang tampil Movie dan Tv Show
    * Menampilkan Detail Movie
        * Mengklik menu item Discover pada BottomNavigationBar
        * Mengecek title pada toolbar sesuai dengan menu item yang diklik (Discover)
        * Memastikan RecyclerView pada halaman Discover Movie tampil
        * Memberi aksi klik pada item pertama RecyclerView Discover Movie
        * Memastikan TextView judul film sudah tampil
        * Memberi aksi klik pada tombol favorite
        * Mengecek teks pesan ditambahkan atau dihapus dari favorite
        * Memberi aksi pada tombol kembali
        * Mengklik menu item Favorite pada BottomNavigationBar
        * Mengecek title pada toolbar sesuai dengan menu item yang diklik (Favorite)
        * Memastikan RecyclerView pada halaman Favorite Movie tampil
        * Memberi aksi klik pada item pertama RecyclerView Favorite Movie atau mengecek pesan empty
        * Memastikan TextView judul film sudah tampil
    * Menampilkan Detail Tvshow
        * Mengklik menu item Discover pada BottomNavigationBar
        * Mengecek title pada toolbar sesuai dengan menu item yang diklik (Discover)
        * Memastikan RecyclerView pada halaman Discover Tvshow tampil
        * Memberi aksi klik pada item pertama RecyclerView Discover Tvshow
        * Memastikan TextView judul acara tv sudah tampil
        * Memberi aksi klik pada tombol favorite
        * Mengecek teks pesan ditambahkan atau dihapus dari favorite
        * Memberi aksi pada tombol kembali
        * Mengklik menu item Favorite pada BottomNavigationBar
        * Mengecek title pada toolbar sesuai dengan menu item yang diklik (Favorite)
        * Memastikan RecyclerView pada halaman Favorite Tvshow tampil
        * Memberi aksi klik pada item pertama RecyclerView Favorite Tvshow atau mengecek pesan empty
        * Memastikan TextView judul acara tv sudah tampil

* MovieFragmentTest:
    * Memuat data Movies
        * Membuka halaman MovieFragment
        * Memastikan RecyclerView dapat tampil
        * Memastikan jumlah item pada RecyclerView sesuai harapan

* TvshowFragmentTest:
    * Memuat data TvShows
        * Membuka halaman TvshowFragment
        * Memastikan RecyclerView dapat tampil
        * Memastikan jumlah item pada RecyclerView sesuai harapan

* MovieFavoriteFragmentTest:
    * Memuat data favorite Movies
        * Membuka halaman MovieFavoriteFragment
        * Memastikan RecyclerView dapat tampil
        * Memastikan jumlah item pada RecyclerView sesuai harapan

* TvshowFavoriteFragmentTest:
    * Memuat data favorite TvShows
        * Membuka halaman TvshowFavoriteFragment
        * Memastikan RecyclerView dapat tampil
        * Memastikan jumlah item pada RecyclerView sesuai harapan
