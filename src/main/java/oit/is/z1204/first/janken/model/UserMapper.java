package oit.is.z1204.first.janken.model;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

  @Select("select * from users;")
  ArrayList<User> selectAllUsers();
}
