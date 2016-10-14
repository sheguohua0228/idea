package com.aplus.lk.clothes.service;



import com.aplus.lk.clothes.bean.Pager;
import com.aplus.lk.clothes.entity.Employee;
import com.aplus.lk.clothes.entity.ManagerOrder;
import com.aplus.lk.clothes.entity.ReassignmentRecord;

public interface ManagerOrderService {
	public Pager queryPager(Pager pager, String orderNumber,Integer status , Integer orderStatus, Integer payStatus, String phoneNumber);
 
	public ManagerOrder queryManagerOrderAndAddressById(long id);

    public Employee queryEmployeebyID(String  employeeId);
    

    
    public void updateStatusById(long id,long status);
    
    public void changeOrderEmployee(long orderId,String employeeId);

    public void createRecord(ReassignmentRecord record);
}
