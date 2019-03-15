package com.xiaozhu.repocket.reposity;

import com.xiaozhu.repocket.po.AdminUserPo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface AdminRepository extends JpaRepository<AdminUserPo, Integer> {

    AdminUserPo findByUsername(String username);

    @Modifying
    @Transactional
    @Query("update AdminUserPo u set u.lastLoginTime = :lastLoginTime where u.username = :username")
    Integer updateLoginTime(@Param("username") String username, @Param("lastLoginTime") Long lastLoginTime);

    @Query("update AdminUserPo u set u.password = :newPassword where u.username = :username and u.password = :oldPassword")
    @Modifying
    @Transactional
    Integer updatePassword(@Param("username") String username, @Param("newPassword") String newPassword, @Param("oldPassword") String oldPassword);

    @Transactional
    Integer deleteByUsername(String username);
}
