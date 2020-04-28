package com.kt.javalib;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class ApiUtils {
    public static final String ACCESS_KEY = "access_key";
    public static final String FORMAT = "format";
    public static final String CMD = "cmd";
    public static final String TIMESTAMP = "timestamp";
    public static final String VERSION = "v";
    public static final String SIG = "sig";
    public static final String SIG_METHOD = "sig_method";
    public static final String SESSION_ID = "sessionId";
    public static final String CHARSET_UTF8 = "UTF-8";

    public static boolean isEmpty(String value) {
        if (value == null) {
            return true;
        }

        if ("".equals(value)) {
            return false;
        }
        int strLen = value.length();
        for (int i = 0; i < strLen; ++i) {
            if (!(Character.isWhitespace(value.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(Object obj) {
        if (obj == null) {
            return false;
        }
        char[] chars = obj.toString().toCharArray();
        int length = chars.length;
        if (length < 1) {
            return false;
        }
        int i = 0;
        if ((length > 1) && (chars[0] == '-')) {
            i = 1;
        }
        for (; i < length; ++i) {
            if (!(Character.isDigit(chars[i]))) {
                return false;
            }
        }
        return true;
    }

    public static boolean areNotEmpty(String[] values) {
        boolean result = true;
        if ((values == null) || (values.length == 0))
            result = false;
        else {
            for (String value : values) {
                result &= !(isEmpty(value));
            }
        }
        return result;
    }

    public static String unicodeToChinese(String unicode) {
        StringBuilder out = new StringBuilder();
        if (!(isEmpty(unicode))) {
            for (int i = 0; i < unicode.length(); ++i) {
                out.append(unicode.charAt(i));
            }
        }
        return out.toString();
    }

    public static String toUnderlineStyle(String name) {
        StringBuilder newName = new StringBuilder();
        for (int i = 0; i < name.length(); ++i) {
            char c = name.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    newName.append("_");
                }
                newName.append(Character.toLowerCase(c));
            } else {
                newName.append(c);
            }
        }
        return newName.toString();
    }

    public static String convertString(byte[] data, int offset, int length) {
        if (data == null)
            return null;
        try {
            return new String(data, offset, length, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] convertBytes(String data) {
        if (data == null)
            return null;
        try {
            return data.getBytes("UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String buildQuery(Map<String, String> params, String charset) {
        if ((params == null) || (params.isEmpty())) {
            return null;
        }

        StringBuilder query = new StringBuilder();
        Set<Entry<String, String>> entries = params.entrySet();
        boolean hasParam = false;

        for (Map.Entry entry : entries) {
            String name = (String) entry.getKey();
            String value = (String) entry.getValue();

            if (areNotEmpty(new String[] { name, value })) {
                if (hasParam)
                    query.append("&");
                else
                    hasParam = true;
                try {
                    query.append(name).append("=")
                            .append(URLEncoder.encode(value, charset));
                    System.out.println("XXXXXXXXXXX --> "  +  query.toString());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

        }

        return query.toString();
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; ++i) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }

    public static String makeSig(Map<String, String> params, String secret,
                                 Collection<String> ignoreSign) {
        String[] keys = (String[]) params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder(secret);
        for (String key : keys) {
            String value = (String) params.get(key);
            if ((ignoreSign != null) && (ignoreSign.contains(value)))
                continue;
            if (areNotEmpty(new String[] { key, value })) {
                query.append(key).append(value);
            }
        }
        byte[] hash = encryptHMAC(query.toString(), secret);
        System.out.println("hash--->  "+hash.length);
        System.out.println("ssssssssssss--->  "+byte2hex(hash));
        return byte2hex(hash);
    }

    public static byte[] encryptHMAC(String data, String secret) {
        SecretKey secretKey = new SecretKeySpec(convertBytes(secret), "HmacMD5");
        try {
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            byte[] bytes = mac.doFinal(convertBytes(data));
            return mac.doFinal(convertBytes(data));
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static byte[] encryptMD5(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            bytes = md.digest(data.getBytes("UTF-8"));
        } catch (GeneralSecurityException gse) {
            String msg = getStringFromException(gse);
            throw new IOException(msg);
        }
        return bytes;
    }

    private static String getStringFromException(Throwable e) {
        String result = "";
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(bos);
        e.printStackTrace(ps);
        try {
            result = bos.toString("UTF-8");
        } catch (IOException localIOException) {
        }
        return result;
    }

    private static String getResponseCharset(String ctype) {
        String charset = "UTF-8";
        if (!(isEmpty(ctype))) {
            String[] params = ctype.split(";");
            for (String param : params) {
                param = param.trim();
                if (param.startsWith("charset")) {
                    String[] pair = param.split("=", 2);
                    if ((pair.length != 2) || (isEmpty(pair[1])))
                        break;
                    charset = pair[1].trim();

                    break;
                }
            }
        }

        return charset;
    }

    public static String getResponseAsString(HttpURLConnection conn)
            throws IOException {
        String charset = getResponseCharset(conn.getContentType());
        InputStream es = conn.getErrorStream();
        if (es == null) {
            return getStreamAsString(conn.getInputStream(), charset);
        }
        String msg = getStreamAsString(es, charset);
        if (isEmpty(msg)) {
            throw new IOException(conn.getResponseCode() + ":"
                    + conn.getResponseMessage());
        }
        throw new IOException(msg);
    }

    public static String getStreamAsString(InputStream stream, String charset)
            throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();

            char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } finally {
            if (stream != null)
                stream.close();
        }
    }

    public static HttpURLConnection getConnection(URL url, String method,
                                                  String ctype, Map<String, String> headerMap) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        if (conn instanceof HttpsURLConnection) {
            HttpsURLConnection connHttps = (HttpsURLConnection) conn;
            try {
                SSLContext ctx = SSLContext.getInstance("TLS");
                ctx.init(null,
                        new TrustManager[] { new TrustAllTrustManager() },
                        new SecureRandom());
                connHttps.setSSLSocketFactory(ctx.getSocketFactory());
                connHttps.setHostnameVerifier(new HostnameVerifier() {
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            conn = connHttps;
        }

        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript");
        conn.setRequestProperty("User-Agent", "aws-apiclient");
        conn.setRequestProperty("Content-Type", ctype);
        if (headerMap != null) {
            for (Map.Entry entry : headerMap.entrySet()) {
                conn.setRequestProperty((String) entry.getKey(),
                        (String) entry.getValue());
            }
        }
        return conn;
    }

    public static class TrustAllTrustManager implements X509TrustManager {
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }
    }
}
