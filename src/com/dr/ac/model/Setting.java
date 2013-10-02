
package com.dr.ac.model;

import com.activeandroid.query.Delete;
import com.dr.ac.ACApplication;


public class Setting {

    public enum SettingsAction {
        CLEAR_DATA
    }

    private final String         name;
    private final SettingsAction mAction;

    public Setting(int nameResid, SettingsAction action) {
        this.name = ACApplication.getInstance().getString(nameResid);
        this.mAction = action;
    }

    public void doAction() {
        switch (this.mAction) {
            case CLEAR_DATA:
                new Delete().from(ResultModel.class).execute();
                break;
            default:
                break;

        }
    }

    public String getName() {
        return this.name;
    }
}
