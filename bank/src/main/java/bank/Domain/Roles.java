package bank.Domain;

public enum Roles {
    ADMIN(2) ,
    CUSTOMER(1),
    NOTAUTHORIZED(0);

    private int priority;

    Roles(int priority)
    {
        this.priority = priority;
    }

    public int getPriority(){ return priority; }

}
