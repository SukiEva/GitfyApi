package com.github.gitfy.gitfyapi.util;

import com.github.gitfy.gitfyapi.vo.RepoVO;
import org.apache.http.client.utils.URIBuilder;

import java.net.URISyntaxException;
import java.util.Locale;

public class StringUtil {
    /**
     * 构建仓库 String
     *
     * @param repo 仓库 VO
     * @return String
     */
    public static String buildRepo(RepoVO repo) {
        return String.format(Locale.ROOT, "%s/%s/%s",
                repo.getPlatform(), repo.getOwner(), repo.getName());
    }

    /**
     * 构建完整 url
     *
     * @param url   请求地址
     * @param paths 请求路径
     * @return String
     */
    public static String buildUrl(String url, String... paths) {
        try {
            return new URIBuilder(url)
                    .setPathSegments(paths)
                    .build()
                    .toString();
        } catch (URISyntaxException exception) {
            return url;
        }
    }
}
