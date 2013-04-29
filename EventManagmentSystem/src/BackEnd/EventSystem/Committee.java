package BackEnd.EventSystem;

import BackEnd.User;
import java.util.ArrayList;

/**
 *
 * @author Ketty Lezama
 */

public class Committee implements Reportable {
    private int EVENT_ID, COMMITTEE_ID;
    private String title;
    private ArrayList<User> memberList, budgetAccessList;
    private User chair;
    private ArrayList<Task> taskList;
    private Budget budget;
    
    public Committee(int event_id, int committee_id, String title) {
        EVENT_ID = event_id;
        COMMITTEE_ID = committee_id;
        this.title = title;
        memberList = new ArrayList<User>();
        budgetAccessList = new ArrayList<User>();
        chair = null;
        taskList = new ArrayList<Task>();
        budget = new Budget();
    }
    
    public boolean isFinished() {
        boolean completed = true;
        
        for (Task task : taskList)
            if (task.getCompleted() == false)
                completed = false;
        
        return completed;
    }
    
    public int getEVENT_ID() {
        return EVENT_ID;
    }
    
    private void setCOMMITTEE_ID(int committee_id) {
        COMMITTEE_ID = committee_id;
    }
    
    public int getCOMMITTEE_ID() {
        return COMMITTEE_ID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setMemberList(ArrayList<User> memberList) {
        this.memberList = memberList;
    }
    
    public ArrayList<User> getMemberList() {
        return memberList;
    }
    
    public void setBudgetAccessList(ArrayList<User> budgetAccessList) {
        this.budgetAccessList = budgetAccessList;
    }
    
    public ArrayList<User> getBudgetAccessList() {
        return budgetAccessList;
    }
    
    public void setChair(User user) {
        chair = user;
    }
    
    public User getChair() {
        return chair;
    }
    
    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public void setBudget(Budget budget) {
        this.budget = budget;
    }
    
    public Budget getBudget() {
        return budget;
    }
    
    public boolean equals(Committee committee) {
        if (this.getEVENT_ID() == committee.getEVENT_ID()
               && this.getCOMMITTEE_ID() == committee.getCOMMITTEE_ID()
               && this.getTitle().equalsIgnoreCase(committee.getTitle())
               && this.getMemberList().equals(committee.getMemberList())
               && this.getBudgetAccessList().equals(committee.getBudgetAccessList())
               && this.getChair().equals(committee.getChair())
               && this.getTaskList().equals(committee.getTaskList())
               && this.getBudget().equals(committee.getBudget()))
            return true;
        else
            return false;    
    }
    
    public String toString() {
        int totalBudget = 0;
        String taskDescriptions = "";
        
        for (int income : budget.getIncomeList())
            totalBudget += income.getValue();
        
        for (Task task : taskList)
            taskDescriptions += task.toString() + "\n";
            
        return "Committee Title: " + title + "\nTotal Budget: " + totalBudget + "\nTask List: \n" + taskDescriptions;
    }
    
    public ArrayList<String> getReport() {
        
    }
}