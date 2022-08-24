package com.sunset.grass.dao2;

import com.sunset.grass.entity.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserInfo get(UserInfo userInfo);
    int save(UserInfo userInfo);
}
