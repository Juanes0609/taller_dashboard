module co.edu.uniquindio.poo {
requires javafx.controls;
requires javafx.fxml;
requires javafx.graphics;

opens co.edu.uniquindio.poo to javafx.fxml;
opens co.edu.uniquindio.poo.controllers to javafx.fxml;
opens co.edu.uniquindio.poo.model to javafx.base;

exports co.edu.uniquindio.poo;
exports co.edu.uniquindio.poo.controllers;
exports co.edu.uniquindio.poo.model;
}
