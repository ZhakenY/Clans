package services;

import models.Clan;
import settings.ConfigDB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersServiceImpl implements UserService {
    ConfigDB configDB = new ConfigDB();
    Connection connection = configDB.connect();

    @Override
    public void save(Clan clan) throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO clan VALUES (" + clan.getId() + ", '" + clan.getName() + "', " + clan.getGoldSum() + ", '" + clan.getTypesOfEarning() + "', '" + clan.getCauseOfLoss() + "')";
        int a = stmt.executeUpdate(sql);
        if (a == 1) System.out.println("inserted successfully : " + sql);
        else System.out.println("insertion failed");
    }

    @Override
    public Integer findMaxId() {
        Connection connection = configDB.connect();
        try (connection; Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("select max(id) from clan")) {
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Clan findClanByClanName(String name) {
        Connection connection = configDB.connect();
        try (connection; Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("select t.*\n" + "from clan t\n" + " inner join (\n" + "    " + "select name, max(insert_date) as max_date\n" + "    from clan\n" + "    group by name\n" + ") tm on t.name = '" + name + "' and t.insert_date = tm.max_date");) {
            while (rs.next()) {
                Clan clan = new Clan();
                clan.setName(rs.getString("name"));
                clan.setGoldSum(rs.getInt("gold_sum"));
                clan.setId(rs.getInt("id"));
                clan.setTypesOfEarning(rs.getString("types_of_earning"));
                clan.setTypesOfEarning(rs.getString("cause_of_loss"));
                return clan;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new Clan();
    }

    @Override
    public Integer getRandomNumber(int num) {
        return (int) (Math.random() * num);
    }

    @Override
    public Integer getSize(String name) {
        Connection connection = configDB.connect();
        try (connection; Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery("select t.*\n" + "from clan t\n" + " inner join (\n" + "    " + "select name, max(insert_date) as max_date\n" + "    from clan\n" + "    group by name\n" + ") tm on t.name = '" + name + "' and t.insert_date = tm.max_date");) {
            while (rs.next()) {
                Clan clan = new Clan();
                clan.setName(rs.getString("name"));
                clan.setGoldSum(rs.getInt("gold_sum"));
                clan.setId(rs.getInt("id"));
                clan.setTypesOfEarning(rs.getString("types_of_earning"));
                clan.setTypesOfEarning(rs.getString("cause_of_loss"));
                return clan.getName().length();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
