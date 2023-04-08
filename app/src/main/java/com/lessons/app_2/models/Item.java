package com.lessons.app_2.models;

import java.io.Serializable;

public interface Item extends Serializable {

    String getTitle();

    String getSubtitle();

    String getDescription();

    String getImage();
}
