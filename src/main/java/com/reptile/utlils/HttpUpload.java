package com.reptile.utlils;


import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * http后台上传文件
 *
 * @author wx
 *
 */
public class HttpUpload {

    /**
     * 基于HttpUrlConnection上传文件
     * @param inputStream 文件流（File类型更抽象）
     * @param fileName 文件名
     * @param uploadUrl 上传的url
     * @return
     * @throws MalformedURLException
     */
    public String uploadFileWithHttpUrlConnection(InputStream inputStream, String fileName, String uploadUrl)
            throws Exception {
        /* 边界、分隔 */
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        URL url = new URL(uploadUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false);
        con.setRequestMethod("POST");
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        con.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
        DataOutputStream ds = new DataOutputStream(con.getOutputStream());
        ds.writeBytes(twoHyphens + boundary + end);
        //这个可以给post带参数
        ds.writeBytes("Content-Disposition: form-data; " + "name=\"file\";filename=\"" + fileName + "\" " + end);
        ds.writeBytes(end);
        /* 文件较大时一定要设置缓冲区，防止内存溢出 */
        int bufferSize = 2048;
        byte[] buffer = new byte[bufferSize];
        int length = -1;
        /* 循环将流输出 */
        while ((length = inputStream.read(buffer)) != -1) {
            ds.write(buffer, 0, length);
        }
        ds.writeBytes(end);
        ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
        inputStream.close();
        ds.flush();
        /* 读取响应 */
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuffer stringBuffer = new StringBuffer();
        String str = "";
        while ((str = br.readLine()) != null) {
            stringBuffer.append(str);
        }
        return stringBuffer.toString();
    }

    /**
     * @param actionUrl
     * @param params
     * @return
     */
    public String uplaod(String actionUrl, Map<String, String> params) {
        InputStream in = null;
        String BOUNDARY = java.util.UUID.randomUUID().toString();
        String PREFFIX = "--", LINEND = "\r\n";
        String MULTIPART_FROM_DATA = "multipart/form-data";
        String CHARSET = "UTF-8";
        URL uri;
        StringBuilder sb2 = null;
        String filePath = params.get("FILE_PATH");
        try {
            uri = new URL(actionUrl);
            HttpURLConnection conn = (HttpURLConnection) uri.openConnection();// 设置从主机读取数据超时
            conn.setReadTimeout(60 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Content-Type", MULTIPART_FROM_DATA + ";boundary=" + BOUNDARY);

            // 首先组拼文本类型的参数
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                sb.append(PREFFIX);
                sb.append(BOUNDARY);
                sb.append(LINEND);
                sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"" + LINEND);
                sb.append("Content-Type: text/plain; charset=" + CHARSET + LINEND);
                sb.append("Content-Transfer-Encoding: 8bit" + LINEND);
                sb.append(LINEND);
                sb.append(entry.getValue());
                sb.append(LINEND);
            }

            DataOutputStream outStream = new DataOutputStream(conn.getOutputStream());
            outStream.write(sb.toString().getBytes(CHARSET));

            // 构建发送字符串数据
            if (StringUtils.isNotEmpty(filePath)) {
                String fileName = params.get("FILE_NAME");
                StringBuilder sb1 = new StringBuilder();
                sb1.append(PREFFIX);
                sb1.append(BOUNDARY);
                sb1.append(LINEND);
                sb1.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"" + LINEND);
                sb1.append("Content-Type: application/octet-stream;chartset=" + CHARSET + LINEND);
                sb1.append(LINEND);
                // 写入到输出流中
                outStream.write(sb1.toString().getBytes());

                // 将文件读入输入流中
                InputStream is = new FileInputStream(filePath);
                byte[] buffer = new byte[2048];
                int len = 0;
                // 写入输出流
                while ((len = is.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                is.close(); // 添加换行标志
                outStream.write(LINEND.getBytes());
            } // 请求结束标志
            byte[] end_data = (PREFFIX + BOUNDARY + PREFFIX + LINEND).getBytes();
            outStream.write(end_data); // 刷新发送数据
            outStream.flush(); // 得到响应码
            int res = conn.getResponseCode();

            // 上传成功返回200
            if (res == 200) {
                in = conn.getInputStream();
                int ch;
                sb2 = new StringBuilder(); // 保存数据
                while ((ch = in.read()) != -1) {
                    sb2.append((char) ch);
                }
            }
            conn.disconnect();
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sb2 == null ? null : sb2.toString();
    }


}
