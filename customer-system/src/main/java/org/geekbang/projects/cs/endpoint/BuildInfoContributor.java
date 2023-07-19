package org.geekbang.projects.cs.endpoint;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShenao
 * @date 2023/7/19 10:43 AM
 * @description: 构建信息Info Contributor 扩展 Info Endpoint
 */
@Component
public class BuildInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        //自定义Info信息
        Map<String, Object> buildInfo = new HashMap<>(2);
        buildInfo.put("built_by", "zsa");
        buildInfo.put("timestamp", new Date());
        builder.withDetail("build", buildInfo);
    }
}
