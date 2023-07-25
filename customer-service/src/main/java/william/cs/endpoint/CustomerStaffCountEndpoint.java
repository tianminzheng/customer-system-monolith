package william.cs.endpoint;

import william.cs.mapper.CustomerStaffCountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.context.annotation.Configuration;

@Configuration
@Endpoint(id = "customerStaffCount", enableByDefault = true)
public class CustomerStaffCountEndpoint {

    @Autowired
    private CustomerStaffCountMapper customerStaffCountMapper;

    @WriteOperation
    public Long countCustomerStaffByPhoneNumber(@Selector String arg0) {

        return customerStaffCountMapper.countCustomerStaff(arg0);
    }
}
