package com.whu.jyx.netframework.net;

import com.whu.jyx.netframework.BuildConfig;

import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit封装类
 *
 * @author Wang.J
 * @date 2016-10-23
 */
public class ApiService {

    public static String BASE_URL="http://192.168.1.1/Demo/";

    private static final boolean IS_DEV=BuildConfig.DEBUG;

    private Retrofit mRetrofit;

    private static volatile ApiService mInstance;

    public static ApiService getmInstance(){
        if(mInstance==null){
            synchronized (ApiService.class){
                if(mInstance==null){
                    mInstance=new ApiService();
                }
            }
        }
        return mInstance;
    }

    private ApiService(){
        this.mRetrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
    }

    public <T> T createApi(Class<T> clazz){
        return this.mRetrofit.create(clazz);
    }

    private OkHttpClient getClient(){
        HttpLoggingInterceptor logging=new HttpLoggingInterceptor();
        if(IS_DEV){
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }else{
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        //如果使用到HTTPS,需要创建SLLSocketFactory,并设置到client
        SSLSocketFactory sslSocketFactory=null;
        try{
            //创建一个不做证书串验证的TrustManager
            TrustManager[] trustAllCerts=new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                        }

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[0];
                        }
                    }};
            SSLContext sslContext=SSLContext.getInstance("SSL");
            sslContext.init(null,trustAllCerts,new SecureRandom());
            sslSocketFactory=sslContext.getSocketFactory();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory)
                //信任所有主机名
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .cookieJar(new CookieJar() {
                    private final HashMap<HttpUrl,List<Cookie>> cookieStore=new HashMap<>();
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                        cookieStore.put(HttpUrl.parse(url.host()),cookies);
                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies=cookieStore.get(HttpUrl.parse(url.host()));
                        return cookies!=null?cookies:new ArrayList<Cookie>();
                    }
                }).build();
    }


}
