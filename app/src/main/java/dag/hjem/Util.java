package dag.hjem;

import android.content.Context;

import java.io.File;

/**
 * Created by Dag on 15.03.2015.
 */
public class Util {
    public static File getExternalFile(Context context, String filnavn) {
        return new File(context.getExternalFilesDir(null), filnavn);
    }


}
