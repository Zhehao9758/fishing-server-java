package com.zhehao.fishing.catches.service.impl;


import com.amazonaws.HttpMethod;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhehao.fishing.catches.mapper.CatchMapper;
import com.zhehao.fishing.catches.service.CatchService;
import com.zhehao.fishing.common.interfaces.CatchBatchService;
import com.zhehao.fishing.common.interfaces.CatchesInLikeService;
import com.zhehao.fishing.exceptions.CatchNotFoundException;
import com.zhehao.fishing.model.CatchEntity;
import io.seata.spring.annotation.GlobalTransactional;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

@Service
@DubboService(interfaceClass = CatchBatchService.class)
public class CatchServiceImpl extends ServiceImpl<CatchMapper, CatchEntity> implements CatchService, CatchBatchService {
    private final CatchMapper catchMapper;
    private final String bucketName = "fishing-catches";
    private final String region = "us-west-1";
    private final String awsAccessKey = "AKIA2EFBW5KD5CYD7BXJ"; // 替换为实际的 AWS 访问密钥
    private final String awsSecretKey = "ti3I/CGVvW/SaRB3aNO6vSoZNlsLKTA25GbzS/no"; // 替换为实际的 AWS 机密访问密钥

    @DubboReference(interfaceClass = CatchesInLikeService.class)
    private CatchesInLikeService catchesInLikeService;

    @Autowired
    public CatchServiceImpl(CatchMapper catchMapper){
        this.catchMapper = catchMapper;
    }

    @Override
    public CatchEntity getCatchById(Long catchId) {
        return catchMapper.getCatchById(catchId);
    }


    @Override
    public void insertCatch(CatchEntity catchEntity) {
        catchMapper.insertCatch(catchEntity);
    }


    @Override
    public List<CatchEntity> getCatchesByUserIdOrderedByTime(Long user_id) {
        return catchMapper.getCatchesByUserIdOrderedByTime(user_id);
    }

    @Override
    public void updateCatch(CatchEntity catchEntity) {
        catchMapper.updateCatch(catchEntity);
    }


    @Override
    @GlobalTransactional
    public void deleteCatchById(Long id) {
        if(getCatchById(id)==null){
            throw new CatchNotFoundException("Catch not exits");
        }
        catchMapper.deleteCatchById(id);
        // delete like
        catchesInLikeService.deleteLikesByCatchId(id);
    }

    @Override
    public List<CatchEntity> getCatchesByIds(List<Long> ids) {
        return catchMapper.getCatchesByIds(ids);
    }

    @Override
    public List<CatchEntity> getCatchPage(int pageNumber, int size) {
        Page<CatchEntity> page = new Page<>(pageNumber, size);
        QueryWrapper<CatchEntity> wrapper = new QueryWrapper<>();
        wrapper.last("LIMIT " + (pageNumber - 1) * size + ", " + size);
        Page<CatchEntity> catchEntityPage = catchMapper.selectPage(page, wrapper);
        return catchEntityPage.getRecords();
    }

    @Override
    public String generateTemporaryUploadUrl(Long userId) {
        long timestamp = System.currentTimeMillis();


        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestampStr = dateFormat.format(new Date(timestamp));

        // S3 bucket key
        String s3Key = "user_" + userId + "_timestamp_" + timestampStr;

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .build();

        // generate presigned url
        Date expiration = new Date(System.currentTimeMillis() + 600000); // 10 mins
        GeneratePresignedUrlRequest generatePresignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, s3Key)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration)
                .withContentType("image/png");

        URL presignedUrl = s3Client.generatePresignedUrl(generatePresignedUrlRequest);
        return presignedUrl.toString();
    }


}
