package com.jincrates.restfulwebservice.user.entity;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties(value={"password", "ssn"})
@JsonFilter("UserInfo")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    private Integer id;

    @Size(min = 2, message = "이름은 두 글자 이상 입력해주세요.")
    private String name;
    @Past
    private Date joinDate;

    //@JsonIgnore
    private String password;

    //@JsonIgnore
    private String ssn;
}
