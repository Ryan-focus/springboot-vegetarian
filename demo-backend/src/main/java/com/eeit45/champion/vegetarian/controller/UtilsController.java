package com.eeit45.champion.vegetarian.controller;

import com.eeit45.champion.vegetarian.util.shopCart.Image;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class UtilsController {

    //輸入圖片名稱後會去target/classes/static裏面找圖片
    //選用這個資料夾不需重整即可讀取圖片，若放在resources底下的話需重整
    @GetMapping(value = "/{imageName}")
    public ResponseEntity<byte[]> getImage(@PathVariable String imageName) throws IOException {

        var imgFile = new ClassPathResource("../../target/classes/static/" + imageName);
        byte[] bytes = StreamUtils.copyToByteArray(imgFile.getInputStream());

        return ResponseEntity
                .ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }

    //前端送過來的檔案要名為file才能夠接收，接收完後會推到target/classes/static裏面
    @PostMapping("/fileUpload")
    public ResponseEntity<Image> getBlogger(@RequestParam("file") MultipartFile file) {
        try {
            UUID uuid = UUID.randomUUID();
            //命名方式為uuid+上傳圖片名字
            String fileLocation = new File("target/classes/static").getAbsolutePath() + "/" + uuid + file.getOriginalFilename();
            file.transferTo(new File(fileLocation));
            //將網址塞到物件
            //image 物件裡只包含imageUrl
            Image image = new Image();
            image.setImageUrl("http://localhost:8088/" + uuid + file.getOriginalFilename());

            return ResponseEntity.status(HttpStatus.CREATED).body(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //回傳失敗結果
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
