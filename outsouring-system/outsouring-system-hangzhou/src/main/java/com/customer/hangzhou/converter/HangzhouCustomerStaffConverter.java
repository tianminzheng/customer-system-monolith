package com.customer.hangzhou.converter;

import com.customer.hangzhou.controller.vo.req.HangzhouCustomerStaffCreateReqVO;
import com.customer.hangzhou.controller.vo.req.HangzhouCustomerStaffUpdateReqVO;
import com.customer.hangzhou.controller.vo.resp.HangzhouCustomerStaffRespVO;
import com.customer.hangzhou.entity.HangzhouCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HangzhouCustomerStaffConverter {

    HangzhouCustomerStaffConverter INSTANCE = Mappers.getMapper(HangzhouCustomerStaffConverter.class);

    //Entity->VO
    List<HangzhouCustomerStaffRespVO> convertListResp(List<HangzhouCustomerStaff> list);
    HangzhouCustomerStaffRespVO convertResp(HangzhouCustomerStaff entity);

    //VO->Entity
    HangzhouCustomerStaff convertCreateReq(HangzhouCustomerStaffCreateReqVO vo);
    HangzhouCustomerStaff convertUpdateReq(HangzhouCustomerStaffUpdateReqVO vo);
}
