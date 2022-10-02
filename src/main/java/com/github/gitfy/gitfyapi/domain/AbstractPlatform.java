package com.github.gitfy.gitfyapi.domain;

import com.github.gitfy.gitfyapi.util.HttpClientUtil;
import com.github.gitfy.gitfyapi.vo.AssetVO;
import com.github.gitfy.gitfyapi.vo.ReleaseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public abstract class AbstractPlatform {
    protected String baseUrl;

    @Autowired
    protected HttpClientUtil httpClient;



    public abstract List<ReleaseVO> getReleases();

    public abstract List<AssetVO> getAssets();

    public abstract String getReadme();
}
