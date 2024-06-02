package org.example.Commands;


import bank.Domain.Roles;

public abstract class Command {

    public Command(String name, String description, String example, Roles[] roles) {
        Name = name;
        Description = description;
        Example = example;
        RequiredRoles = roles;
    }

    public String Name;
    public String Description;
    public String Example;
    public Roles[] RequiredRoles;

    public abstract String execute(String[] args, Roles role);

}
