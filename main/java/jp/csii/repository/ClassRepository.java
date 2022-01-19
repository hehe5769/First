package jp.csii.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.csii.entity.ClassView;

@Mapper
public interface ClassRepository {

	public List<ClassView> findAll();

}
