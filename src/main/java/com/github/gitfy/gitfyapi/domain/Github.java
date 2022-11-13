//package com.github.gitfy.gitfyapi.domain;
//
//import com.alibaba.fastjson.JSON;
//import com.github.gitfy.gitfyapi.infrastructure.AbstractPlatform;
//import com.github.gitfy.gitfyapi.util.StringUtil;
//import com.github.gitfy.gitfyapi.vo.AssetVO;
//import com.github.gitfy.gitfyapi.vo.ReleaseVO;
//import org.springframework.util.StringUtils;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Github extends AbstractPlatform {
//
//    public Github(String owner, String name) {
//        baseUrl = StringUtil.buildUrl("https://api.github.com/repos", owner, name);
//    }
//
//    @Override
//    public List<ReleaseVO> getReleases() {
//        String response = httpClient.doGet(baseUrl + "/releases", null, null);
//        if (!StringUtils.hasText(response)) {
//            return new ArrayList<>();
//        }
//        return JSON.parseArray(response, ReleaseVO.class);
//    }
//
//    @Override
//    public List<AssetVO> getAssets() {
//        return null;
//    }
//
//    @Override
//    public String getReadme() {
//        return null;
//    }
//}
