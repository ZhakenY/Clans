package services;

import models.Clan;

import java.sql.SQLException;

public interface UserService {

    void save(Clan clan) throws SQLException;

    Integer findMaxId();

    Clan findClanByClanName(String name);

    Integer getRandomNumber(int num);

    Integer getSize(String name);

}
