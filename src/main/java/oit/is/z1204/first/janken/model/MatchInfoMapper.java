package oit.is.z1204.first.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchInfoMapper {

  @Select("select * from matchinfo;")
  ArrayList<MatchInfo> selectAllMatchInfo();

  @Select("select * from matchinfo where id = #{id}")
  MatchInfo selectById(int id);

  @Insert("INSERT INTO matchinfo (user1,user2,user1Hand,isActive) VALUES (#{user1},#{user2},#{user1Hand},#{isActive})")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatchInfo(MatchInfo matchinfo);

  @Update("update matchinfo set isActive=false WHERE ID = #{id}")
  void updateById(MatchInfo matchinfo);
}
