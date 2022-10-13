import models.Clan;
import models.ClanNames;
import models.Works;
import services.UsersServiceImpl;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainApplication {
    public static void main(String[] args) {

        CountDownLatch clan1 = new CountDownLatch(100);
        CountDownLatch clan2 = new CountDownLatch(100);
        ExecutorService es = Executors.newFixedThreadPool(100);
        System.out.println("Starting");
        es.execute(new TaskService(clan1, ClanNames.CLAN_1.toString()));
        es.execute(new TaskService(clan2, ClanNames.CLAN_2.toString()));
        try {
            clan1.await();
            clan2.await();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        es.shutdown();
        System.out.println("Done");
    }
}

class TaskService implements Runnable {
    UsersServiceImpl usersService = new UsersServiceImpl();
    private String name;
    private CountDownLatch latch;

    public TaskService(CountDownLatch clan2, String name) {
        this.name = name;
        this.latch = clan2;
        new Thread(this);
    }

    @Override
    public void run() {
        for (int i = 0; i < latch.getCount(); i++) {
            int res = usersService.getRandomNumber(2);
            //if 1 - add golds
            //if 0 - minus golds
            if (res == 1) {
                Clan clan = new Clan();
                clan.setId(usersService.findMaxId() + 1);
                clan.setName(name);
                clan.setTypesOfEarning(Works.WORKING_MINE.name());
                clan.setCauseOfLoss("-");
                clan.setGoldSum(usersService.getSize(name) == 0 ? usersService.getRandomNumber(100) : usersService.findClanByClanName(name).getGoldSum() + usersService.getRandomNumber(100));
                try {
                    usersService.save(clan);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            else {
                int gold = usersService.getRandomNumber(100);
                Clan clan = new Clan();
                if (usersService.getSize(name) > 0) {
                    if (gold <= usersService.findClanByClanName(name).getGoldSum() && usersService.findClanByClanName(name).getGoldSum() > 0) {
                        clan.setId(usersService.findMaxId() + 1);
                        clan.setName(name);
                        clan.setTypesOfEarning("-");
                        clan.setCauseOfLoss(Works.DIED_IN_THE_WAR.name());
                        clan.setGoldSum(usersService.findClanByClanName(clan.getName()).getGoldSum() - gold);
                        try {
                            usersService.save(clan);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}