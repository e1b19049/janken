package oit.is.z1204.first.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select * from users;")
  ArrayList<User> selectAllUsers();

  @Select("select * from users where name = #{name};")
  User selectByName(String name);

  @Insert("insert into users (name) values (#{name});")
  @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
  void insertUser(User user);
}
