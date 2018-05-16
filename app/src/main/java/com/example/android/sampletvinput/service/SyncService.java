/*
 * Copyright 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.sampletvinput.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.android.sampletvinput.sync.ChannelSyncAdapter;

/**
 * Service which provides the SyncAdapter implementation to the framework on request.
 */
public class SyncService extends Service {
    private static final Object syncAdapterLock = new Object();
    private static ChannelSyncAdapter syncAdapter = null;

    @Override
    public void onCreate() {
        super.onCreate();
        synchronized (syncAdapterLock) {
            if (syncAdapter == null) {
                syncAdapter = new ChannelSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return syncAdapter.getSyncAdapterBinder();
    }
}
