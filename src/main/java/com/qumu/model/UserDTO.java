package com.qumu.model;

import com.qumu.web.validation.NotHtml;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by Bruno Correia on 01/07/2017.
 */
public class UserDTO {

    @NotEmpty
    @NotHtml
    @Length(max = 50)
    private String name;

    private long dateOfBirthMillis;

    public UserDTO() {
        //Do nothing
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDateOfBirthMillis() {
        return dateOfBirthMillis;
    }

    public void setDateOfBirthMillis(long dateOfBirthMillis) {
        this.dateOfBirthMillis = dateOfBirthMillis;
    }
}
