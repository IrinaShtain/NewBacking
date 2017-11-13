package ua.shtain.irina.newbacking.presentation.utils;

import org.androidannotations.annotations.sharedpreferences.DefaultBoolean;
import org.androidannotations.annotations.sharedpreferences.SharedPref;

/**
 * Created by Irina Shtain on 13.11.2017.
 */

@SharedPref(value = SharedPref.Scope.APPLICATION_DEFAULT)
public interface SharedPrefManager {
    @DefaultBoolean(false)
    boolean isStrategyNeeded();

    @DefaultBoolean(false)
    boolean isGipsyNeeded();
}
