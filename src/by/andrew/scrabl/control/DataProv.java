package by.andrew.scrabl.control;

import java.util.ArrayList;
import java.util.List;

public interface DataProv {

     public void newUser(String name);
    
    public Object getField(String name);

    public String getAsk(String name);

    public void setAnswer(String name, String answer, ArrayList data);
		
}
