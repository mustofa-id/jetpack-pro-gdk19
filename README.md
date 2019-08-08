# Submission

Repository ini merupakan hasil belajar dari [Dicoding Academy](https://www.dicoding.com) pada kelas [Belajar Android Jetpack Pro](https://www.dicoding.com/academies/129). Kelas ini saya dapatkan sebagai beasiswa setelah lulus kelas [MADE](https://www.dicoding.com/academies/14/) dari [Google Developer Kejar 2019](https://events.withgoogle.com/googledeveloperskejar/).

[Habib Mustofa](https://www.dicoding.com/users/413434)
 
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

* AmberMovieTest:
    * Menampilkan Tab dan Halaman
        * Memastikan jumlah tab yang tampil 2 tab
        * Memastikan judul tab yang tampil Movie dan Tv Show
    * Berpindah ke DetailMovieActivity
        * Memastikan RecyclerView pada halaman Movie tampil
        * Memberi aksi klik pada item pertama RecyclerView Movie
        * Memastikan TextView judul film sudah tampil
        * Memastikan nilai dari judul film sesuai harapan
        * Memberi aksi tombol kembali
        * Memberi aksi geser untuk berpindah ke halaman Tv Show
        * Memberi aksi klik pada item pertama RecyclerView Tv Show
        * Memastikan TextView acara tv film sudah tampil
        * Memastikan nilai dari judul acara tv sesuai harapan

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
