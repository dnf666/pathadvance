package com.pms.dataService.user;

import com.pms.dataModel.User.PersonInfo;
import com.pms.model.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserModelService {
    public PersonInfo toPersonInfo(User user){
        return new PersonInfo(user);
    }
}
