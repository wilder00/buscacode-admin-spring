package com.buscacode.admin.buscacodeadmin.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.buscacode.admin.buscacodeadmin.tasks.jobs.UserTask;

import jakarta.annotation.PostConstruct;

@Component
public class ScheduledTasks {

  @Autowired
  private UserTask userTask;

  public ScheduledTasks() {
  }

  /* ONE TIME TASK AT FIRST RUN */
  @PostConstruct
  public void executeOneTimeTask() {
    try {
      userTask.createInitialUsers();
    } catch (Exception e) {
      System.out.println("Messages: ============");
      System.out.println(e.getMessage());
    }
  }

  /*
   * @Scheduled(cron = "0 0 1 * * ?") // Ejecuta cada día a la 1:00 AM
   * public void dailyTask() {
   * System.out.println("Running daily task...");
   * }
   */
  @Scheduled(cron = "0 */2 * * * ?") // Cada 2 minutos
  public void dailyTask() {
    System.out.println("Running each 2 minutes task...");
  }
}
