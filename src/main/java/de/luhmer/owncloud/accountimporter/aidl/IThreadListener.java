package de.luhmer.owncloud.accountimporter.aidl;

/**
 * Created by david on 29.06.17.
 */

public interface IThreadListener {

    void onThreadFinished(final Thread thread);

}