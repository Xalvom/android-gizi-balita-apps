package com.himman.gizibalita.Api;

import com.himman.gizibalita.Model.BalitaResponse;
import com.himman.gizibalita.Model.BbtbResponse;
import com.himman.gizibalita.Model.BbuResponse;
import com.himman.gizibalita.Model.DevResponse;
import com.himman.gizibalita.Model.ArtikelResponse;
import com.himman.gizibalita.Model.ImunResponse;
import com.himman.gizibalita.Model.KMSResponse;
import com.himman.gizibalita.Model.PesanResponse;
import com.himman.gizibalita.Model.StimulasiResponse;
import com.himman.gizibalita.Model.TbuResponse;
import com.himman.gizibalita.Model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("balita/balitaid")
    Call<BalitaResponse> getListBalita(@Query("id_user") String idUser);

    @GET("balita")
    Call<BalitaResponse> getBalitaId(@Query("id_balita") String idBalita);

    @GET("gizi/bbu")
    Call<BbuResponse> getListBbu(@Query("id_balita") String idBalita);

    @GET("gizi/tbu")
    Call<TbuResponse> getListTbu(@Query("id_balita") String idBalita);

    @GET("gizi/bbtb")
    Call<BbtbResponse> getListBbtb(@Query("id_balita") String idBalita);

    @GET("balita/perkembangan")
    Call<DevResponse> getListPerkembangan(@Query("kat_umur") String katUmur);

    @GET("user/pesan")
    Call<PesanResponse> getListPesan(@Query("id_pengirim") String idPengirim);

    @GET("user")
    Call<UserResponse> getListUser(@Query("id_user") String idUser);

    @GET("gizi/imun")
    Call<ImunResponse> getImunData(@Query("id_balita") String idBalita);

    @GET("user/artikel")
    Call<ArtikelResponse> getArtikelData();

    @GET("gizi")
    Call<KMSResponse> getKmsData(@Query("kelamin") String kelamin);

    @GET("balita/stimulasi")
    Call<StimulasiResponse> getStData(@Query("kat_umur") String katUmur);

    @FormUrlEncoded
    @POST("balita")
    Call<BalitaResponse> apiInsertBalita(@Field("nama_balita") String namaBalita,
                                         @Field("rt_balita") String rtBalita,
                                         @Field("rw_balita") String rwBalita,
                                         @Field("alamat_balita") String alamatBalita,
                                         @Field("tgllahir_balita") String tglBalita,
                                         @Field("kelamin_balita") String kelaminBalita,
                                         @Field("id_user") String id_user,
                                         @Field("nama_orangtua") String namaOrangtua,
                                         @Field("beratbadan_balita") String beratBadan,
                                         @Field("tinggibadan_balita") String tinggiBadan,
                                         @Field("update_balita") String updateBalita,
                                         @Field("created_at") String createdAt);

    @FormUrlEncoded
    @POST("gizi/bbu")
    Call<BbuResponse> apiInsertBbu(@Field("id_balita") String idBalita,
                                   @Field("umur") String umurBbu,
                                   @Field("berat_badan") String beratBadan,
                                   @Field("kelamin") String kelaminBbu,
                                   @Field("update_bbu") String updateBbu);

    @FormUrlEncoded
    @POST("gizi/tbu")
    Call<TbuResponse> apiInsertTbu(@Field("id_balita") String idBalita,
                                   @Field("umur") String umurTbu,
                                   @Field("tinggi_badan") String tinggiBadan,
                                   @Field("kelamin") String kelaminTbu,
                                   @Field("update_tbu") String updateTbu);

    @FormUrlEncoded
    @POST("gizi/bbtb")
    Call<BbtbResponse> apiInsertBbtb(@Field("id_balita") String id_balita,
                                     @Field("kelamin") String Kelamin,
                                     @Field("tinggi_badan") String tinggiBadan,
                                     @Field("berat_badan") String beratBadan,
                                     @Field("kat_umur") String katUmur,
                                     @Field("update_bbtb") String updateBbtu);

    @GET("user/loginApp")
    Call<UserResponse> apiLogin(@Query("no_hp_user") String noHpUser,
                                @Query("password_user") String passwordUser);

    @FormUrlEncoded
    @POST("user/daftar")
    Call<UserResponse> apiRegister(@Field("nama_user") String namaUser,
                                   @Field("no_hp_user") String noHpUser,
                                   @Field("email_user") String emailUser,
                                   @Field("password_user") String passwordUser,
                                   @Field("level_user") String levelUser,
                                   @Field("is_aktif") String isAktif);

    @FormUrlEncoded
    @POST("user/pesan")
    Call<PesanResponse> apiPesan(@Field("id_pengirim") String idPengirim,
                                 @Field("penjawab") String penjawab,
                                 @Field("judul_pesan") String judulPesan,
                                 @Field("pertanyaan") String pertanyaan,
                                 @Field("jawaban") String jawaban,
                                 @Field("tanggal_pesan") String tanggalPesan);

    @FormUrlEncoded
    @POST("gizi/imun")
    Call<ImunResponse> apiInsertImun(@Field("hb0") String hb0,
                                     @Field("bcg") String bcg,
                                     @Field("polio1") String polio1,
                                     @Field("dpt1") String dpt1,
                                     @Field("polio2") String polio2,
                                     @Field("dpt2") String dpt2,
                                     @Field("polio3") String polio3,
                                     @Field("dpt3") String dpt3,
                                     @Field("polio4") String polio4,
                                     @Field("ipv") String ipv,
                                     @Field("campak") String campak,
                                     @Field("dpt_lanjut") String dptLanjut,
                                     @Field("campak_lanjut") String campakLanjut,
                                     @Field("update_imunisasi") String updateImunisasi,
                                     @Field("id_balita") String idBalita);

    @FormUrlEncoded
    @PUT("user")
    Call<UserResponse> apiUpdateUser(@Path("id_user") String idUser,
                                     @Field("password_user") String passwordUser);

    @FormUrlEncoded
    @PUT("balita")
    Call<BalitaResponse> apiUpdateBalita(@Field("id_balita") String idBalita,
                                         @Field("nama_balita") String namaBalita,
                                         @Field("nama_orangtua") String namaOrangtua,
                                         @Field("rt_balita") String rtBalita,
                                         @Field("rw_balita") String rwBalita,
                                         @Field("alamat_balita") String alamatBalita,
                                         @Field("tgllahir_balita") String tglBalita,
                                         @Field("kelamin_balita") String kelaminBalita,
                                         @Field("id_user") String id_user);

    @FormUrlEncoded
    @PUT("balita/gizi")
    Call<BalitaResponse> apiUpdateGiziBalita(@Field("id_balita") String idBalita,
                                            @Field("beratbadan_balita") String beratbadanBalita,
                                             @Field("tinggibadan_balita") String tinggibadanBalita,
                                            @Field("update_balita") String updateBalita);

    @FormUrlEncoded
    @PUT("gizi/imun")
    Call<ImunResponse> apiUpdateImun(@Field("hb0") String hb0,
                                        @Field("bcg") String bcg,
                                        @Field("polio1") String polio1,
                                        @Field("dpt1") String dpt1,
                                        @Field("polio2") String polio2,
                                        @Field("dpt2") String dpt2,
                                        @Field("polio3") String polio3,
                                        @Field("dpt3") String dpt3,
                                        @Field("polio4") String polio4,
                                        @Field("ipv") String ipv,
                                        @Field("campak") String campak,
                                        @Field("dpt_lanjut") String dptLanjut,
                                        @Field("campak_lanjut") String campakLanjut,
                                        @Field("update_imunisasi") String updateImunisasi,
                                        @Field("id_balita") String idBalita,
                                        @Field("id_imunisasi") String idImunisasi);

    @FormUrlEncoded
    @PUT("user/paswd")
    Call<UserResponse> apiUpdatePasswd(@Field("id_user") String idUser,
                                       @Field("password_user") String passwordUser);

    @FormUrlEncoded
    @HTTP(method ="DELETE", path = "balita", hasBody = true)
//    @DELETE("balita/{id_balita}")
    Call<BalitaResponse> apiDeleteBalita(@Field("id_balita") String idBalita);
}
