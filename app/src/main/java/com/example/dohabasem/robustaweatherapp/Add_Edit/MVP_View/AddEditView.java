package com.example.dohabasem.robustaweatherapp.Add_Edit.MVP_View;

/**
 * Created by Doha Basem on 9/15/2017.
 */

//View Interface for the AddEditFragment_MVP Design Pattern
public interface AddEditView {

    void addEditPhoto();

    void addEditPlaceName();

    void addEditTemperature();

    void displayTemp(String temp);

    void displayHumidity(String humidity);
}
