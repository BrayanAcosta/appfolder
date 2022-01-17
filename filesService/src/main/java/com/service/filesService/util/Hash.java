/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service.filesService.util;

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author Brayan
 */
@Service
public class Hash {
    
    public String encript(String text){
        byte[] data = text.getBytes(StandardCharsets.UTF_8);
        String digest = DigestUtils.sha1Hex(data);
        return digest;
    }
    
}
