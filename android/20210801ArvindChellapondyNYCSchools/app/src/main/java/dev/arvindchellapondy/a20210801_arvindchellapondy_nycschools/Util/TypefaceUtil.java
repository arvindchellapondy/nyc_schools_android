//
//  20210801-ArvindChellapondy-NYCSchools
//
//  Created by Arvind Chellapondy on 08/01/21.
//

package dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.Util;

import android.graphics.Typeface;

import androidx.annotation.StringDef;

import java.util.concurrent.ConcurrentHashMap;

import dev.arvindchellapondy.a20210801_arvindchellapondy_nycschools.NycSchoolsApplication;

/**
 * TypefaceUtil to load custom typeface
 */
public class TypefaceUtil {

    private static ConcurrentHashMap<String, Typeface> fontCache = new ConcurrentHashMap<>();

    public static Typeface get(@FontCatalog String fontCatalog){
        if(!fontCache.contains(fontCatalog)){
            final Typeface typeface = Typeface.createFromAsset
                    (NycSchoolsApplication.getContext().getAssets(),fontCatalog);
            fontCache.put(fontCatalog,typeface);
        }

        return fontCache.get(fontCatalog);
    }

    @StringDef({METROPOLIS_LIGHT, METROPOLIS_MEDIUM})
    public @interface FontCatalog{}

    public static final String METROPOLIS_LIGHT = "Metropolis-Light.otf";
    public static final String METROPOLIS_MEDIUM = "Metropolis-Medium.otf";
}
