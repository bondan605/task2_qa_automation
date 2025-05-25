package response_model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginUserResponse {

    @JsonProperty("token")
    private String token;

}