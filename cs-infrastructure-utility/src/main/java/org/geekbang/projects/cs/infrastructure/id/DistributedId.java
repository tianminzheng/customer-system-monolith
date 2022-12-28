package org.geekbang.projects.cs.infrastructure.id;

import cn.hutool.core.util.IdUtil;

public class DistributedId {

	private static DistributedId instance = new DistributedId();
	
	public String getFastSimpleUUID(){
		return IdUtil.fastSimpleUUID();
	}

	public String getSnowflake(long workerId, long datacenterId){
		return IdUtil.createSnowflake(workerId, datacenterId).toString();
	}
	
	public static DistributedId getInstance(){
		return instance;
	}
}
