package remind.edu.myapplication.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Mypreferncelogin {
    private static final String MY_PREFERENCES = "my_preferencess";

    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.commit();
        }
        return first;
    }
}
