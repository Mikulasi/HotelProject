package by.bsu.ino.carrent.exception;

import java.sql.SQLException;

public class DataBaseException extends Exception {

    public DataBaseException(String s, SQLException e) {
    }

    public DataBaseException(String s, InterruptedException e) {

    }
}
