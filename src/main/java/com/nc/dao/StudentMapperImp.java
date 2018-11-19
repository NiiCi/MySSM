package com.nc.dao;
import com.nc.entity.Students;

public class StudentMapperImp implements StudentMapper {

	@Override
	public Students updateStu(Students stu) {
		stu.setName(stu.getName());
		stu.setAge(stu.getAge());
		stu.setScore(stu.getScore());
		return stu;
	}
}
