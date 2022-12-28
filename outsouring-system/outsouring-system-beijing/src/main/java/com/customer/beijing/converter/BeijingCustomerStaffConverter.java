package com.customer.beijing.converter;

import com.customer.beijing.controller.vo.req.BeijingCustomerStaffCreateReqVO;
import com.customer.beijing.controller.vo.req.BeijingCustomerStaffUpdateReqVO;
import com.customer.beijing.controller.vo.resp.BeijingCustomerStaffRespVO;
import com.customer.beijing.entity.BeijingCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BeijingCustomerStaffConverter {

    BeijingCustomerStaffConverter INSTANCE = Mappers.getMapper(BeijingCustomerStaffConverter.class);

    //Entity->VO
    List<BeijingCustomerStaffRespVO> convertListResp(List<BeijingCustomerStaff> list);
    BeijingCustomerStaffRespVO convertResp(BeijingCustomerStaff entity);

    //VO->Entity
    BeijingCustomerStaff convertCreateReq(BeijingCustomerStaffCreateReqVO vo);
    BeijingCustomerStaff convertUpdateReq(BeijingCustomerStaffUpdateReqVO vo);
}
