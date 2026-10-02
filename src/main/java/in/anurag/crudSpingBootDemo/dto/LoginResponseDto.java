package in.anurag.crudSpingBootDemo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {

    private String accessToken;

    public LoginResponseDto(String accessToke){
        this.accessToken = accessToke;
    }


}
