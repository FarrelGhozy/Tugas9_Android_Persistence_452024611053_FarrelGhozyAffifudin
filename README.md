# Tugas9 — Android Persistence dengan Room & Coroutines

Aplikasi Android sederhana untuk menyimpan catatan menggunakan **Room Persistence Library** dan **Kotlin Coroutines** sebagai implementasi pemrograman asinkron.

## Identitas Mahasiswa

| | |
|---|---|
| **NIM** | 452024611053 |
| **Nama** | Farrel Ghozy Affiudin |
| **Kelas** | TI5 A2 |
| **Prodi** | Teknik Informatika |
| **Fakultas** | Sains dan Teknologi |

## Teknologi yang Digunakan

- **Kotlin** 2.2.10
- **Jetpack Compose** — UI deklaratif
- **Room** 2.7.1 — Persistensi database lokal
- **KSP** 2.2.10-2.0.2 — Annotation processing
- **Kotlin Coroutines** — Operasi async di background thread
- **Android Gradle Plugin** 9.1.1

## Struktur Project

```
com.example.tugas9/
├── MainActivity.kt          # Entry point + UI Compose
├── data/
│   ├── Note.kt              # @Entity
│   ├── NoteDao.kt           # @Dao (CRUD + suspend)
│   └── NoteDatabase.kt      # @Database (Singleton)
└── ui/
    ├── theme/               # Tema Material3
    └── viewmodel/
        └── NoteViewModel.kt # ViewModel + viewModelScope
```

## Fitur

- Tambah catatan baru (title + content)
- Tampilkan semua catatan dalam daftar real-time (Flow)
- Update dan hapus catatan
- Database lokal dengan Room (SQLite)
- Operasi database berjalan di `Dispatchers.IO` agar UI tetap responsif

## Cara Menjalankan

1. Buka projekt di **Android Studio**
2. Sync Gradle (**File → Sync Project with Gradle Files**)
3. Hubungkan device Android atau jalankan emulator
4. Klik **Run** (tombol segitiga hijau)

## Pengujian

Jalankan instrumented test untuk memvalidasi operasi database:

```bash
./gradlew connectedAndroidTest
```

Test mencakup:
- ✅ Insert dan retrieve data
- ✅ Insert dan delete data
- ✅ Insert dan update data
