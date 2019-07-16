package edu.mum.ea.ui.domains;

public enum AuthorityType {
    ADMIN("ADMIN"), USER("USER");

    private String name;

    AuthorityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
