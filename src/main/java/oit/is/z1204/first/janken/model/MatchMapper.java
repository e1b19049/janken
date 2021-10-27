package oit.is.z1204.first.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface MatchMapper {

  @Select("select * from matches;")
  ArrayList<Match> selectAllMatches();

  @Insert("INSERT INTO matches (user1,user2,user1Hand,user2Hand,isActive,matchinfo) VALUES (#{user1},#{user2},#{user1Hand},#{user2Hand},#{isActive},#{matchinfo});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertMatch(Match match);

  @Update("update matches set isActive=false WHERE ID = #{id}")
  void updateById(Match match);
}
