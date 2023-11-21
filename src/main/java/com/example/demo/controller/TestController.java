package com.example.demo.controller;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
@RestController
@CrossOrigin("*")
public class TestController {
    @GetMapping("/Test")
    public String demerrertest() {
        Object[] partnerIds = new Object[0];
        try {
            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

            // Odoo server URL
            config.setServerURL(new URL("http://localhost:8069/xmlrpc/2/common"));

            XmlRpcClient client = new XmlRpcClient();
            client.setConfig(config);

            // Authentication
            String db = "tatabue";
            String username = "yassinebenayad@gmail.com";
            String password = "1234";

            List<Object> emptyList = Arrays.asList();
            ;
            Integer uid = (Integer) client.execute("authenticate", Arrays.asList(
                    db, username, password, emptyList)
            );
            System.out.println(uid);
            config.setServerURL(new URL("http://localhost:8069/xmlrpc/2/object"));
            XmlRpcClient client2 = new XmlRpcClient();
            client2.setConfig(config);

            // Example: Reading records from Odoo's 'res.partner' model
            HashMap<String, Object> searchReadParams = new HashMap<>();
            searchReadParams.put("fields", Arrays.asList("country_id", "comment"));
            partnerIds = (Object[]) client2.execute("execute_kw", Arrays.asList(
                    db, uid, password,
                    "res.partner", "search_read", Arrays.asList(Arrays.asList(
                            Arrays.asList("id", "<>", 0))), searchReadParams
            ));

            for (Object partnerId : partnerIds) {
                System.out.println("Partner: " + partnerId);
            }

        } catch (XmlRpcException | java.net.MalformedURLException e) {
            e.printStackTrace();
        }

        return "yassssss";
    }

}
