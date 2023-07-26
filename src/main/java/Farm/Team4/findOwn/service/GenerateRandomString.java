package Farm.Team4.findOwn.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class GenerateRandomString {
    @Value("${RANDOM_LENGTH}")
    private int passwordLength;
    @Value("${RANDOM_BOUND_START}")
    private int start;
    @Value("${RANDOM_BOUND_END}")
    private int end;
    public String getTempPassword(){
        String tempPassword = "";
        Random random = new Random();
        for(int i=0; i<passwordLength; i++){
            int randomNum = random.nextInt(end);
            tempPassword += (char) (randomNum + start);
        }
        return tempPassword;
    }

}
