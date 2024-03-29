/*
 * This file is part of APNdroid.
 *
 * APNdroid is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * APNdroid is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with APNdroid. If not, see <http://www.gnu.org/licenses/>.
 */

package com.google.code.apndroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author Dmitry Pavlov <pavlov.dmitry.n@gmail.com>
 */
public class ActionActivity extends Activity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        // todo use separate thread for switch operations
        Intent intent = getIntent();
        if (intent != null) {
            Intent response = new Intent(Constants.APN_DROID_RESULT);

            if (intent.getAction().equals(Constants.STATUS_REQUEST)) {
                Bundle responseExtras = ActionUtils.processStatusRequest(this);
                setResult(RESULT_OK, response.putExtras(responseExtras));
            } else if (intent.getAction().equals(Constants.CHANGE_STATUS_REQUEST)) {
                Bundle responseExtras = ActionUtils.processSwitchRequest(this, intent.getExtras());
                setResult(RESULT_OK, response.putExtras(responseExtras));
            } else {
                setResult(Activity.RESULT_CANCELED);
            }
        } else {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

}
