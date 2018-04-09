package com.example.demo;


//import org.apache.commons.codec.binary.Base64;

import org.apache.tomcat.util.codec.binary.Base64;

public class CommonMesSigEncr {

    public String ToMesEncr(String  originalContent,String aesKey)
    {
        String encData="";
        try {

            System.out.println(originalContent);
            byte[] inputContent = originalContent.getBytes("utf-8");
            byte[] aesKeyCode = Base64.decodeBase64(aesKey.getBytes("utf-8"));
            // 报文加密加密
            encData = new String(Base64.encodeBase64(AESUtil.encrypt(inputContent, aesKeyCode)),"utf-8");
        }
        catch (Exception e)
        {
            encData=e.getMessage();
        }
        return encData;

    }

    public String ToSigEncr(String  originalContent,String privateKey)
    {
        String sign ="";
        try {

            byte[] inputContent = originalContent.getBytes("utf-8");
            byte[] privateKeyCode = Base64.decodeBase64(privateKey.getBytes("utf-8"));

            //生成数字签名
            sign = new String(Base64.encodeBase64(RSAUtil.sign(inputContent, privateKeyCode)),"utf-8");

        }
        catch (Exception e)
        {
            sign=e.getMessage();
        }
        return sign;

    }

}
