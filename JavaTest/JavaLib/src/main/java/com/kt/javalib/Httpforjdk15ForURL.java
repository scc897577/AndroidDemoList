package com.kt.javalib;

import com.alibaba.fastjson.JSON;

import java.net.HttpURLConnection;
import java.net.URL;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Httpforjdk15ForURL {

    // 编码方式
    public static final String ALGORITHM_HMAC = "HmacSHA1";
    public static final String ALGORITHMHMAC__MD5 = "HmacMD5";
    public static final String ALGORITHM_MD5 = "MD5";

    public static final String FORMAT_JSON = "json";
    public static final String FORMAT_XML = "xml";
    private static String serverUrl="http://shuhe.sit.lattebank.com/awsbpm/openapi?";//请求的AWS服务地址;
    private static String accessKey="mk";//私钥;
    private static String secret="1dow3oxI2Gs_nOK2gz7gmQ";//访问凭证;
    private static String format = "json";//传输格式
    private static int connectTimeout = 3000;
    private static int readTimeout = 15000;
    public Httpforjdk15ForURL() {
        // TODO Auto-generated constructor stub
    }
    /**
     * 执行BOAPI中的 get方法
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        String cmd ="bo.datas.create";//调用方法
        String boName ="BO_EU_FQ_INVOICES";//cmd的参数  ，参数1表名
        //String boId ="0c1c853f-259b-4a82-953e-ef3c4d7db281";//cmd的参数  ，参数 2BOID
        //System.out.println("boId::::::"+boId);



        Map<String, String> t = new HashMap<>();



        //------------以下是cmd方法的参数,都需要放到这个t这个map中---------------------
        List<Map<String,String>> recordDatas = new ArrayList<>();


        Map<String, String> map = new HashMap<>();
        map.put("INVOICECODE", "S-0006");
        map.put("INVOICENUM", "0006");
        map.put("INVOICEDATE", "2020-03-20");
        recordDatas.add(map);


        Map<String,String> map2 = new HashMap<>();
        map2.put("INVOICECODE", "S-0007");
        map2.put("INVOICENUM", "0007");
        map2.put("INVOICEDATE", "2020-03-20");
        recordDatas.add(map2);



        String result = JSON.toJSONString(recordDatas);
        System.out.println("JSON result --> "+result);

        t.put("boName", boName);
        t.put("recordDatas", result);
        t.put("uid", "admin");
        //--------------------
        t.put(ApiUtils.TIMESTAMP, Long.toString(Long.parseLong("1585238838822")));
        t.put(ApiUtils.CMD, cmd);
        t.put(ApiUtils.FORMAT, format);
        t.put(ApiUtils.ACCESS_KEY, accessKey);
        t.put(ApiUtils.SIG_METHOD, ALGORITHMHMAC__MD5);




        String sig =ApiUtils.makeSig(t, secret, null);

        System.out.println("t-->   "+t.toString());
        System.out.println("secret-->   "+secret);

        System.out.println("sig::::::"+sig);


        t.put(ApiUtils.SIG, sig);

        String charset = ApiUtils.CHARSET_UTF8;
        String ctype = "application/x-www-form-urlencoded;charset=" + charset;

        String query = ApiUtils.buildQuery(t, charset);

        System.out.println("queryqueryqueryquery--->  "+ query);

        byte[] content = {};
        if (query != null) {
            content = ApiUtils.convertBytes(query);
            System.out.println("content "+new String(content));
        }
        try {
            String values = _doPost(serverUrl, ctype, content, connectTimeout, readTimeout, null);

            System.out.println("serverUrl-->  "+ serverUrl + "   ctype-->  " +ctype + "  content --> "+ content +"  ");


            System.out.println("success---> values"+values);
        } catch (Exception e) {

        }
    }
    private static String _doPost(String url, String ctype, byte[] content, int connectTimeout, int readTimeout, Map<String, String> headerMap) throws IOException {
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        try {
            try {
                conn = ApiUtils.getConnection(new URL(url), "POST", ctype, headerMap);
                conn.setConnectTimeout(connectTimeout);
                conn.setReadTimeout(readTimeout);
            } catch (IOException e) {
                throw e;
            }
            try {
                out = conn.getOutputStream();
                out.write(content);
                rsp = ApiUtils.getResponseAsString(conn);
            } catch (IOException e) {
                throw e;
            }
        } finally {
            if (out != null) {
                out.close();
            }
            if (conn != null) {
                conn.disconnect();
            }
        }

        return rsp;
    }
}
