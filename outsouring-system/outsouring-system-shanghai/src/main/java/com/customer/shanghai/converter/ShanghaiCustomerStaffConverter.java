package com.customer.shanghai.converter;

import com.customer.shanghai.controller.vo.req.ShanghaiCustomerStaffCreateReqVO;
import com.customer.shanghai.controller.vo.req.ShanghaiCustomerStaffUpdateReqVO;
import com.customer.shanghai.controller.vo.resp.ShanghaiCustomerStaffRespVO;
import com.customer.shanghai.entity.ShanghaiCustomerStaff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ShanghaiCustomerStaffConverter {

    ShanghaiCustomerStaffConverter INSTANCE = Mappers.getMapper(ShanghaiCustomerStaffConverter.class);

    //Entity->VO
    List<ShanghaiCustomerStaffRespVO> convertListResp(List<ShanghaiCustomerStaff> list);
    ShanghaiCustomerStaffRespVO convertResp(ShanghaiCustomerStaff entity);

    //VO->Entity
    ShanghaiCustomerStaff convertCreateReq(ShanghaiCustomerStaffCreateReqVO vo);
    ShanghaiCustomerStaff convertUpdateReq(ShanghaiCustomerStaffUpdateReqVO vo);
}
