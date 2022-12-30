package org.geekbang.projects.cs.servicebus.filter;

public abstract class AbstractCustomerStaffFilter implements CustomerStaffFilter {

    private CustomerStaffFilter next;

    public void setNext(CustomerStaffFilter filter) {
        this.next = filter;
    }

    public CustomerStaffFilter getNext() {
        return next;
    }

    public CustomerStaffFilter getLast() {
        CustomerStaffFilter last = this;
        while (last.getNext() != null) {
            last = last.getNext();
        }
        return last;
    }
}
