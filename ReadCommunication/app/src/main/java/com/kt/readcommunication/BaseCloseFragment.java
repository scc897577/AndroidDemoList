package com.kt.readcommunication;
/*
 * @author : created by CC
 * time    : 2019/10/14  10:04
 * desc    :
 */

import android.app.Fragment;
import android.app.FragmentTransaction;

import java.io.Closeable;
import java.io.IOException;

/**
 * 可关闭Fragment
 * @author BC
 * @since 1.2.0
 */
abstract class BaseCloseFragment extends Fragment implements Closeable {

    /**
     * 关闭碎片
     * @since 1.2.0
     */
    @Override
    public void close() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.remove(this);
        transaction.commitAllowingStateLoss();
    }

}
