package Farm.Team4.findOwn.service.member;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class MemberUtils {
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
    public boolean checkLengthOfPassword(String password){
        if (password.length() >= 8) return true;
        return false;
    }
    public boolean checkExistOfSpecialSymbol(String password){
        char[] specialSymbols = {'!', '@', '#', '$', '%', '^', '&', '*'};
        for(int i = 0; i < password.length(); i++){
            for(int j = 0; j < specialSymbols.length; j++){
                if (password.charAt(i) == specialSymbols[j])
                    return true;
            }
        }
        return false;
    }

}
