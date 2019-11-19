package com.itqf.mapper;

import com.itqf.dto.UserRoleDTO;
import com.itqf.entity.SysRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysRoleMapper {
    int deleteByPrimaryKey(Long roleId);

    int insert(SysRole record);

    int insertSelective(SysRole record);

    SysRole selectByPrimaryKey(Long roleId);

    int updateByPrimaryKeySelective(SysRole record);

    int updateByPrimaryKey(SysRole record);

    List<SysRole> getRoleList(@Param("search") String search, @Param("order") String order);

    int deleteRoleBatch(List<Long> ids);

    List<SysRole> notHasRoles(long userId);

    int insertUserRole(UserRoleDTO userRoleDTO);
}