package org.geekbang.projects.cs.controller.vo.req;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AddOutsourcingSystemReqVO {

    private String systemName;
    private String description;
    private String systemUrl;
    private String appId;
    private String appKey;
    private String appSecret;
}
