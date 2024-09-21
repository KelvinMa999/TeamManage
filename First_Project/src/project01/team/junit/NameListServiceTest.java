package project01.team.junit;

import project01.team.domain.Employee;
import project01.team.service.*;

//import org.junit.Test;

public class NameListServiceTest {
    @Test
	public void testGetAllEmployees(){
		NameListService listService = new NameListService();
		
		Employee[] employees = listService.gertAllEmployees();
		
		for(int i = 0;i < employees.length;i++){
			System.out.println(employees[i]);
		}
	}

    @Test
	public void testGetEmployee(){
		int id = 2;
		NameListService listService = new NameListService();
		try {
			Employee emp = listService.getEmployee(id);
			System.out.println(emp);
		} catch (TeamException e) {
			System.out.println(e.getMessage());
		}
	}
}

