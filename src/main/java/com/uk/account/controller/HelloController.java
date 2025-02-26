package com.uk.account.controller;

import com.uk.account.client.AccountServiceClient;
import com.uk.account.service.ProtoDownloader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class HelloController {
    private final ProtoDownloader protoDownloader;
    private final AccountServiceClient accountServiceClient;

    public HelloController(ProtoDownloader protoDownloader, AccountServiceClient accountServiceClient) {
        this.protoDownloader = protoDownloader;
        this.accountServiceClient = accountServiceClient;
    }

    @PostMapping("/download-proto")
    public void downloadProto() throws Exception {
        protoDownloader.downloadAndExtractProtoFiles();
    }

    @PostMapping("/call")
    public String call()  {
        return accountServiceClient.getUserInfo("Duan hello");
    }
}
