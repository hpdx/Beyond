package com.trident.dating.libnetwork.okhttp.utils;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by android_ls on 2017/4/25.
 */

public class SSLSocketConfig {

    static X509TrustManager mX509TrustManager;

    public static X509TrustManager getX509TrustManager(Context context) {
        try {
            if (mX509TrustManager == null) {
                CertificateFactory cf = CertificateFactory.getInstance("X.509");
                InputStream cerInputStream = context.getApplicationContext().getAssets().open("xx.crt");
                Certificate ca = cf.generateCertificate(cerInputStream);

                String keyStoreType = KeyStore.getDefaultType();
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);

                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);

                TrustManager[] trustManagers = tmf.getTrustManagers();
                if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                    mX509TrustManager = (X509TrustManager) trustManagers[0];
                }
            }
            return mX509TrustManager;
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

}
