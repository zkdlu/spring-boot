package com.zkdlu.slacker;

public class Vote {
    private String user;
    private String actionId;

    public Vote(String user, String actionId) {
        this.user = user;
        this.actionId = actionId;
    }

    public String getUser() {
        return user;
    }

    public String getActionId() {
        return actionId;
    }

    @Override
    public boolean equals(Object o) {
        Vote vote = (Vote)o;
        return vote.getUser().equals(user);
    }

    @Override
    public int hashCode() {
        return user.hashCode();
    }
}
