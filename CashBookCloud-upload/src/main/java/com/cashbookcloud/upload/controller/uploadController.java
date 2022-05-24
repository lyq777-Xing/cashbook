package com.cashbookcloud.upload.controller;

import com.cashbookcloud.common.constant.RedisConstant;
import com.cashbookcloud.common.result.ResponseResult;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import static com.cashbookcloud.upload.utils.CosUtils.createCOSClient;

@RestController
@RequestMapping("/upload")
public class uploadController {
    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping("/upload")
    public ResponseResult upload(@RequestParam("imgFile") MultipartFile imgFile) throws IOException {
//        System.out.println(imgFile);
//        System.out.println("aaaaaaaa");
        ResponseResult<Object> result=new ResponseResult<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(redisTemplate.getConnectionFactory());
        SetOperations<Object, Object> set = redisTemplate.opsForSet();
//        System.out.println(imgFile);
        String originalFilename = imgFile.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String extention = originalFilename.substring(index);
        String fileName= UUID.randomUUID().toString()+extention;
/*        COSClient cosClient =createCOSClient();
        System.out.println(fileName);
// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = "fumao-1310753755";
// 对象键(Key)是对象在存储桶中的唯一标识。
        String key = fileName;
// 本地文件路径
//        String localFilePath = fileName;
//        File localFile = new File(localFilePath);

        PutObjectRequest putObjectRequest = null;

            putObjectRequest = new PutObjectRequest(bucketName, key, (File) imgFile);
            set.add(RedisConstant.SCENERY_PIC_RESOURCES,fileName);

        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }

// 确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();*/
        // 调用 COS 接口之前必须保证本进程存在一个 COSClient 实例，如果没有则创建
// 详细代码参见本页：简单操作 -> 创建 COSClient
        COSClient cosClient = createCOSClient();

// 存储桶的命名格式为 BucketName-APPID，此处填写的存储桶名称必须为此格式
        String bucketName = "cashbook-1310707740";
// 对象键(Key)是对象在存储桶中的唯一标识。
        String key = fileName;
// 这里创建一个 ByteArrayInputStream 来作为示例，实际中这里应该是您要上传的 InputStream 类型的流
        byte[] bytes = imgFile.getBytes();
//        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        long inputStreamLength = 1024 * 1024;
//        byte data[] = new byte[stream];
        InputStream inputStream = new ByteArrayInputStream(bytes);

        ObjectMetadata objectMetadata = new ObjectMetadata();
// 上传的流如果能够获取准确的流长度，则推荐一定填写 content-length
// 如果确实没办法获取到，则下面这行可以省略，但同时高级接口也没办法使用分块上传了
//        objectMetadata.setContentLength(inputStreamLength);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream,null);
        set.add(RedisConstant.CASHBOOK_PIC_RESOURCES,fileName);
        try {
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            System.out.println(putObjectResult.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        } catch (CosClientException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

// 确认本进程不再使用 cosClient 实例之后，关闭之
        cosClient.shutdown();
        result.Success("图片上传成功",fileName);
        return result;
    }
}
