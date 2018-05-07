package com.twobScene.web.dao;

import com.twobScene.web.model.User;

public interface UserDAO {
	User getUserByCredencials(String userId, String password);
}
