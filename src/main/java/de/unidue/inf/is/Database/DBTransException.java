package de.unidue.inf.is.Database;


public final class DBTransException extends RuntimeException {

    private static final long serialVersionUID = -1626236348481345515L;


    public DBTransException(Exception e) {
        super(e);
    }

}
