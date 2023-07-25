package william.cs.controller.graphql.datafetcher;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import william.cs.entity.staff.CustomerGroup;
import william.cs.entity.staff.CustomerStaff;
import william.cs.mapper.CustomerGroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerGroupDataFetcher implements DataFetcher<CustomerGroup> {

    @Autowired
    private CustomerGroupMapper customerGroupMapper;

    @Override
    public CustomerGroup get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {
        CustomerStaff customerStaff = dataFetchingEnvironment.getSource();
        if(customerStaff != null) {
            return customerGroupMapper.selectById(customerStaff.getGroupId());
        }

        return null;
    }
}
