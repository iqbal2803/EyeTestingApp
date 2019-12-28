package com.iqbal.eyetesting.ui.Database;

import android.provider.BaseColumns;

public class DatabaseContract {

    public static String TABLE_TEST = "tb_test";
    public static String TABLE_USER = "tb_user";

    public static final class TestColumns implements BaseColumns {
        public static String ANGKA = "angka";
        public static String KATEGORI = "kategori";
        public static String PHOTO = "photo";
    }

    public static final class UserColumns implements BaseColumns {
        public static String NAMA = "nama";
        public static String UMUR = "umur";
        public static String JENIS_KELAMIN = "jenis_kelamin";
        public static String NILAI = "nilai";
    }
}
