package org.example.Commands;

import org.example.Roles;

public abstract class Command {

    public Command(String name, String description, String example, Roles role) {
        Name = name;
        Description = description;
        Example = example;
        RequiredRole = role;
    }

    public String Name;
    public String Description;
    public String Example;
    public Roles RequiredRole;

    public abstract String execute(String[] args);

}
