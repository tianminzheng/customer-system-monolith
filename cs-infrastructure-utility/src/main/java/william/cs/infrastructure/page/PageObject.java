package william.cs.infrastructure.page;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@ToString
@Accessors(chain = true)
public class PageObject<T> {

    private Long total;

    private Long pageIndex;

    private Long pageSize;

    private List<T> list;

    public static <T> PageObject<T> buildPage(List<T> list, Long total, Long pageIndex, Long pageSize) {
        PageObject<T> pageObject = new PageObject<>();
        pageObject.setList(list);
        pageObject.setTotal(total);
        pageObject.setPageIndex(pageIndex);
        return pageObject;
    }

}
