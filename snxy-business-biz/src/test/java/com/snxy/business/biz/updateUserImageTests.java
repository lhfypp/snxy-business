package com.snxy.business.biz;

import com.snxy.business.service.UserImageService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class updateUserImageTests {

    @Resource
    private UserImageService userImageService;

      @Test
    public void updateUserImage1() throws IOException {
          File file = new File("C:\\upload\\images\\5ad8628dN03dc292f.jpg");
           FileInputStream inputStream = new FileInputStream(file);
          MultipartFile multipartFile = new MockMultipartFile(file.getName(), inputStream);

         userImageService.updateImageById(1L,multipartFile);

    }
}
