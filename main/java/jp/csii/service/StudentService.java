package jp.csii.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.csii.entity.StudentEntity;
import jp.csii.entity.StudentView;
import jp.csii.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentrepository;

	public List<StudentView> findAll() {
		return studentrepository.findAll();
	}

	public void deleteById(String id) {
		studentrepository.deleteById(id);
	}

	public List<StudentEntity> findById(String id) {
		return studentrepository.findById(id);
	}
	
	public void update(StudentEntity studententity){
		studentrepository.update(studententity);
	}
	
	public void insert(StudentEntity studententity){
		studentrepository.insert(studententity);
	}
}
