package com.reptile.task;

import com.reptile.dao.ReptileDao;
import com.reptile.entity.IpPostEntity;
import com.sun.jdi.IntegerValue;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: zz_reptile1
 * Created by s on 2019/3/24 17:15
 */
@Component
@EnableScheduling
public class GetIpTask {

    private static final Logger log = LoggerFactory.getLogger(GetIpTask.class);

    @Autowired
    private ReptileDao mapper;

    @Scheduled(cron = "0 0 0 1/2 * ? ")
    public void getIp()  {

        try {
            IpPostEntity ipPostEntity = null;
            for (int j = 1; j < 10; j++) {
                Connection con= Jsoup.connect("https://www.xicidaili.com/nn/"+j);//鑾峰彇杩炴帴

                con.ignoreContentType(true).ignoreHttpErrors(true);
                con.timeout(1000 * 20);
                Document document  = con.get();
                Elements trs = document.select("tr");
                org.jsoup.nodes.Element tr = null;
                Elements tds = null;
                String ip = "";
                int post = 0;
                if(trs!=null) {
                    for (int i = 1,num= trs.size(); i <num; i++) {
                        tr = trs.get(i);
                        //https://www.xicidaili.com/nn
                        tds = tr.select("td");
                        ip = tds.get(1).text();
                        post = Integer.valueOf(tds.get(2).text());

                        ipPostEntity = new IpPostEntity();
                        try {
                            ipPostEntity.setIp(ip);
                            ipPostEntity.setPost(post);
                            ipPostEntity.setState(1);
                            connect(ip,post);
                            mapper.insertsIpPost(ipPostEntity);
                        } catch (Exception e) {
                            ipPostEntity.setState(0);
                            mapper.insertsIpPost(ipPostEntity);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Scheduled(cron = "0 0 0 2/2 * ? ")
//    @Scheduled(initialDelay=100,fixedDelay=1000*60*5)
    public void getIp2()  {

        try {
            mapper.deleteIpPost();

            IpPostEntity ipPostEntity = null;
            String url = "http://www.xiladaili.com/gaoni/page/";

            for (int j = 10; j < 30; j++) {
                Connection con= Jsoup.connect(url.replace("page",j+""));//鑾峰彇杩炴帴

                con.ignoreContentType(true).ignoreHttpErrors(true);
                con.timeout(1000 * 20);
                Document document  = con.get();
                Elements trs = document.select("tr");
                org.jsoup.nodes.Element tr = null;
                Elements tds = null;
                String ip = "";
                String[] ipPost ;
                int post = 0;
                if(trs!=null) {
                    for (int i = 1,num= trs.size(); i <num; i++) {
                        try {
                            tr = trs.get(i);
                            tds = tr.select("td");
                            ipPost = tds.get(0).text().split(":");
                            System.out.println(ip+":"+post);
                            ip = ipPost[0];
                            post  =Integer.valueOf(ipPost[1]);
                            ipPostEntity = new IpPostEntity();

                            ipPostEntity.setIp(ip);
                            ipPostEntity.setPost(post);
                            ipPostEntity.setState(1);
                            connect(ip,post);
                            mapper.insertsIpPost(ipPostEntity);
                        } catch (Exception e) {
                            ipPostEntity.setState(0);
                            mapper.insertsIpPost(ipPostEntity);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void connect(String server, int servPort) throws Exception {
        Socket socket = new Socket();
        socket.setReceiveBufferSize(servPort);
        socket.setSoTimeout(3000);
        SocketAddress address = new InetSocketAddress(server, servPort);
        socket.connect(address,3000);
    }


    @Scheduled(cron = "${setIpPost}")
//    @Scheduled(initialDelay=100,fixedDelay=1000*60*5)
    public void job3(){
        IpPostEntity ipPostEntity = new IpPostEntity();
        List<IpPostEntity> l = mapper.selectIpPost(ipPostEntity);
        System.out.println(l.size());
        for (IpPostEntity i : l) {
            try {
                connect(i.getIp(),i.getPost());
                i.setState(1);
                mapper.insertsIpPost(i);
                System.out.println("成功==="+i.getIp()+":"+i.getPost());
            } catch (Exception e) {
                i.setState(0);
                mapper.insertsIpPost(i);
                System.out.println("失败==="+i.getIp()+":"+i.getPost());

            }
        }

        ipPostEntity.setState(1);
        l = mapper.selectIpPost(ipPostEntity);

        log.info("刷新IP成功，当前IP数量："+l.size());

    }
}
