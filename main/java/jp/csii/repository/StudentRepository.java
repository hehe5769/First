package jp.csii.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.csii.entity.StudentEntity;
import jp.csii.entity.StudentView;

@Mapper
public interface StudentRepository {

	public List<StudentView> findAll();

	public void deleteById(String id);

	public List<StudentEntity> findById(String id);
	
	public void update(StudentEntity studententity);
	
	public void insert(StudentEntity studententity);
}
