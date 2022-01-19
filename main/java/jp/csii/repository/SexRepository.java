package jp.csii.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import jp.csii.entity.SexEntity;

@Mapper
public interface SexRepository {

	public List<SexEntity> findAll();

}
